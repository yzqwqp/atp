package com.uusoft.atp.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.uusoft.atp.dao.TestConstantMapper;
import com.uusoft.atp.model.TestCaseInfo;
import com.uusoft.atp.model.TestConstantInfo;
import com.uusoft.atp.service.TestConstantService;
import com.uusoft.atp.utils.JsonUtil;
import com.uusoft.atp.utils.ResultTool;

@Service("TestConstantService")
@Transactional
public class TestConstantServiceImpl implements TestConstantService {

	private final static Logger LOGGER = LoggerFactory.getLogger(TestConstantServiceImpl.class);

	@Resource
	TestConstantMapper mapper;

	@Override
	public int insert(TestConstantInfo testConstantInfo) {
		return mapper.insert(testConstantInfo);
	}

	@Override
	public List<TestConstantInfo> selectAll() {
		return mapper.selectAll();
	}

	@Override
	public TestConstantInfo selectByConstantId(int constant_id) {
		return mapper.selectByConstantId(constant_id);
	}

	@Override
	public TestCaseInfo mofiyCaseInfo(TestCaseInfo testCaseInfo) {
		// 判断接收的对象为空
		if (testCaseInfo == null) {
			return testCaseInfo;
		}
		// 判断 CaseInfo 中的 执行类型为null
		if (testCaseInfo.getBefore_run_type() == null || testCaseInfo.getBefore_run() == null) {
			LOGGER.info("Before_run_type　或者　Before_run　 为null,不做改变返回接收值"); // 改用LOGGER，标准输出
			return testCaseInfo;
		}
		// 实例 TestConstantInfo 对象
		TestConstantInfo constant = selectByConstantId(testCaseInfo.getBefore_run());
		// 需要判断取到的对象是不是空
		if (constant == null) {
			return testCaseInfo;
		}
		// 判断获取的执行前要处理的值是不是为null,为null直接返回接收值
		if (constant.getConstant_value() == null || constant.getConstant_name() == null) {
			return testCaseInfo;
		}
		LOGGER.info("==========要替换的常量量名为==============" + constant.getConstant_name());
		LOGGER.info("==========要替换的常量值为==============" + constant.getConstant_value());
		// 如果修改的类型为2，修改变量的值
		if (testCaseInfo.getBefore_run_type() == 2) {
			// 获取test_case 的Case_data 请求产数
			String jsonStr = testCaseInfo.getCase_data();
			// 将字符串 转换成json 格式
			JSONObject jsonbject = (JSONObject) JSON.parse(jsonStr);
			// 获取修改前参数名和参数值，并进行打印
			String beforeVal = jsonbject.getString(constant.getConstant_name());
			LOGGER.info("修改前字段：" + constant.getConstant_name() + "、值为：" + beforeVal);
			// 将 值进行替换
			jsonbject.put(constant.getConstant_name(), constant.getConstant_value());
			String afterVal = jsonbject.get(constant.getConstant_name()).toString();
			LOGGER.info("修改后字段：" + constant.getConstant_name() + "、值为：" + afterVal);
			// 将json格式 转成string 的对象
			String afterJsonStr = JSONObject.toJSONString(jsonbject);
			// 将修改过的参数进行替换老的
			testCaseInfo.setCase_data(afterJsonStr);
			LOGGER.info("修改后的 testCaseInfo 值为：" + testCaseInfo.toString());
			return testCaseInfo;
		} else {
			LOGGER.info("Before_run_type 不等于2,不做改变返回接收值");
			return testCaseInfo;
		}
	}

	@Override
	public TestConstantInfo selectByConstantName(String constant_name) {
		return mapper.selectByConstantName(constant_name);
	}

	@Override
	public ResultTool<String> mofiyConstantInfoAfterRun(TestCaseInfo testCaseInfo, JSONObject jsonObj) {
		String code = "9999";
		// after_run_type不是2(替换业务变量),不执行
		if(!testCaseInfo.getAfter_run_type().equals(2)) {
			return ResultTool.setResult(code, "after_run_type不是2(替换业务变量),不执行", null);
		}
		// after_run 是null 不执行
		if(testCaseInfo.getAfter_run().equals(null)) {
			return ResultTool.setResult(code, "after_run 是null 不执行", null);
		}
		TestConstantInfo testConstantInfo = selectByConstantId(testCaseInfo.getAfter_run());
		if(testConstantInfo.equals(null)) {
			return ResultTool.setResult(code, "testConstantInfo为空", null);
		}
		String kStr = testConstantInfo.getConstant_name();
		String vStr = testConstantInfo.getConstant_value();
		LOGGER.info("######### kStr:"+kStr+", before vStr:"+vStr);
		ResultTool<Map<String, Object>> result = JsonUtil.findJsonValueByKey(jsonObj, kStr);
		
		if(result.getCode().equals("0000")){
			String vAfterStr = (String) result.getObj().get(kStr);
			LOGGER.info("######### kStr:"+kStr+", after vStr:"+vAfterStr);
			testConstantInfo.setConstant_value(vAfterStr);
			update(testConstantInfo);
			return ResultTool.setResult(result.getCode(), result.getMessage(), null);
		} else {
			return ResultTool.setResult(code, result.getMessage(), null);
		}
	}

	@Override
	public int update(TestConstantInfo testConstantInfo) {
		return mapper.update(testConstantInfo);
	}

	@Override
	public ResultTool<TestCaseInfo> mofiySqlCaseInfo(TestCaseInfo testCaseInfo) {
		String sqlStrOld = null;
		int beforeRun = 0;
		sqlStrOld = testCaseInfo.getCase_data();
		beforeRun = testCaseInfo.getBefore_run();
		if(testCaseInfo.getBefore_run_type() != 3){
			return ResultTool.setResult("9999", "Before_run_type不等于3异常", testCaseInfo);
		}
		TestConstantInfo testConstantInfo =selectByConstantId(beforeRun);
		String value = testConstantInfo.getConstant_value();
		if((StringUtils.isEmpty(sqlStrOld)) || (beforeRun == 0) || testConstantInfo.equals(null) || (StringUtils.isEmpty(value))){
			return ResultTool.setResult("9999", "前置处理SqlString异常", testCaseInfo);
		}
		if(!sqlStrOld.contains("?")){
			return ResultTool.setResult("9999", "前置处理SqlStr找不到问号", testCaseInfo);
		}
		String sqlStrNew = sqlStrOld.replace("?", "'"+value+"'");
		LOGGER.info("######### 替换后的SQL为：【"+sqlStrNew+"】");
		testCaseInfo.setCase_data(sqlStrNew);
		return ResultTool.setResult("0000", "前置处理SqlStr成功", testCaseInfo);
	}

}
