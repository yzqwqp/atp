package com.uusoft.atp.service.impl;
import java.util.ArrayList;
import java.util.LinkedHashMap;
/** 
* 类说明 ：
* 	TestCaseService实现类
* @author 邱鹏
* @email qiupeng@toutoujinrong.com
* @since 2016年12月13日 上午10:19:12 
*/
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import static org.assertj.core.api.Assertions.*;

import com.alibaba.fastjson.JSONObject;
import com.uusoft.atp.dao.InitParaMapper;
import com.uusoft.atp.dao.InitServiceMapper;
import com.uusoft.atp.dao.TestCaseMapper;
import com.uusoft.atp.dao.TestMethodMapper;
import com.uusoft.atp.dao.TestReportMapper;
import com.uusoft.atp.model.ParameterVo;
import com.uusoft.atp.model.TestCaseInfo;
import com.uusoft.atp.model.TestCaseVo;
import com.uusoft.atp.model.TestMethodInfo;
import com.uusoft.atp.model.TestReportInfo;
import com.uusoft.atp.service.InitMethodService;
import com.uusoft.atp.service.TestCaseService;
import com.uusoft.atp.utils.HttpClientUtil;
import com.uusoft.atp.utils.JsonUtil;
import com.uusoft.atp.utils.ResultTool;
import com.uusoft.atp.utils.SpringUtil;


@Service("TestCaseService")
@Transactional
public class TestCaseServiceImpl implements TestCaseService {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(TestCaseServiceImpl.class);
	
	@Resource
	TestCaseMapper mapper;
	@Resource
	TestMethodMapper methodMapper;
	@Resource
	private InitParaMapper paraMapper;
	@Resource
	private TestReportMapper testReportMapper;
	@Resource
	private InitMethodService initMethodService;
	@Resource
	private InitServiceMapper serviceMapper;
	@Resource
	private TestDataServiceImpl testDataService;
	
	/** 
	 * <p>Description:根据方法名获取入参数据</p>
	 * @param method
	 * @return 
	 * @author Adele
	 * @date 2016年12月11日 下午12:59:16   
	 */
	public List<String>  getDatasByMethod(String method){
		List<String> datas = new ArrayList<String>();
		if(method.isEmpty()){
			LOGGER.info("Given methodName is empty!!!");
		}
		else {
			int methodId = methodMapper.selectMethodIdByName(method);
			if (0 == methodId) {
				LOGGER.info("There's no method named"+method+"in the DB!");
			}
			else {
				 datas = mapper.selectDatasByMethodId(methodId);
			}
		}
		return datas;
	}
	
	/** 
	 * <p>Description:根据caseId获取入参数据</p>
	 * @param caseId
	 * @return
	 * @author Adele
	 * @date 2016年12月11日 下午2:41:57   
	 */
	public String getDataById(int caseId){
		String data = mapper.selectDataById(caseId);
		if (data.equals(null)) {
			LOGGER.info("There's no data related to"+caseId);
		}
		 return data;
	}
	
	/** 
	 * <p>Description:根据方法名称和服务名称去获取方法的参数类型</p>
	 * @param method
	 * @return Map<Integer,List<String>> Integer记录方法Id，List记录这个方法的参数类型
	 * @author Adele
	 * @date 2016年12月14日 上午10:44:06   
	 */
	public List<LinkedHashMap<String, String>> selectParasByMethod(String service,String method){
		List<Integer> methodId = serviceMapper.selectMethodIdByNameAndService(service,method);
//		List<List<String>> methodParaTypes = new ArrayList<List<String>>();
		List<LinkedHashMap<String, String>> methodParaTypes = new ArrayList<LinkedHashMap<String, String>>();
		if(0 == methodId.size())
			LOGGER.info("There's NO method named"+method);
		else{
			//TODO如果相同的方法，参数不同，需要区分
			for(int methodid:methodId){
//				List<String> paras = paraMapper.selectParaTypesByMethId(methodid);
				List<LinkedHashMap<String, String>> paras = paraMapper.selectParaLinkedHashMap(methodid);
//				methodParaTypes.add(paras);
				methodParaTypes.addAll(paras);
			}
		}
		return methodParaTypes;
		
	}
	
	@Override
	public int insert(TestCaseInfo testCaseInfo) {
		return mapper.insert(testCaseInfo);
	}

	@Override
	public List<TestCaseInfo> selectAll() {
		return mapper.selectAll();
	}

	@Override
	public TestCaseInfo selectById(int case_id) {
		return mapper.selectById(case_id);
	}

	@Override
	public int update(TestCaseInfo testCaseInfo) {
		return mapper.update(testCaseInfo);
	}

	@Override
	public int deleteById(int case_id) {
		return mapper.deleteById(case_id);
	}

	@Override
	public ResultTool<Object> run(int case_id) {
		
		//--1--获取case对象的值
		LOGGER.info("********开始 runCase , id is :【"+ case_id+"】**********");
		TestCaseVo caseVo = mapper.selectByCaseId(case_id);
		
		//--5--获取caseVo的校验数据，如果没有校验数据，直接返回【执行失败】
		String assertType = caseVo.getCase_assert_type();
		String assertValue = caseVo.getCase_assert_value();
		if (assertType.isEmpty() || assertValue.isEmpty()) {
			return ResultTool.setResult("2222", "无校验数据，CASE校验失败", null);
		}		
		
		//--2--获取serviceName\methodName\caseData
		String serviceName = caseVo.getService_name();
		String methodName = caseVo.getMethod_name();
		String caseData = caseVo.getCase_data();
		
		//--3--根据case对象的data值进行json转义
		//--4--调用initMethodService,传入serviceName\methodName\caseData
		LOGGER.info("#####开始查询serviceName ："+serviceName+" methodName："+methodName+" caseData : "+caseData);
		ParameterVo parameterVo = testDataService.parseTestData(case_id);
		if (parameterVo.getParamSize() < 1) {
			return ResultTool.setResult("9998", "初始化case值失败", null);
		}
		String[] strTypes= parameterVo.getParamTypes();
		Object[] objValues= parameterVo.getParamValues();
		LOGGER.info("#####开始打印strTypes####");
		for(int i=0; i < strTypes.length; i++) {
			LOGGER.info(strTypes[i]);
		}
		LOGGER.info("#####开始打印objValues####");
		for(int i=0; i < objValues.length; i++) {
			LOGGER.info(objValues[i].toString());
		}
		
		//--5--根据初始化返回的两个数组（值类型string数组，值obj数组），泛化调用进行反射dubbo调用服务器，实现方法
		ResultTool<Object> result = SpringUtil.genericInvoke(serviceName, methodName, strTypes, objValues);
		LOGGER.info("##########用例执行 :【"+ case_id+"】########## run's result is 【" +result.toString() +"】##########");
		
		//--7--
		//写入测试结果
		TestReportInfo testReportInfo = new TestReportInfo();
		testReportInfo.setCase_id(case_id);
		testReportInfo.setMethod_id(caseVo.getMethod_id());
		testReportInfo.setService_id(caseVo.getService_id());
		testReportInfo.setReport_data(result.getObj().toString());
		testReportMapper.insert(testReportInfo)	;	
		
		//--6--判断返回值的code
		if (result.getCode().equals("9999")) {
			return ResultTool.setResult("9999", "Case执行失败", null);
		}
		//--6--根据getCase_assert_type类型不同，进行result返回值校验（如果调用的是void方法，暂时未想好怎么校验）
		if (result.getObj().equals(null)) {
			return ResultTool.setResult("9999", "Case执行返回值为空", null);
		}
		
		return result;
	}

	@Override
	public List<TestCaseInfo> selectByMethodId(int method_id) {
		return mapper.selectByMethodId(method_id);
	}

	@Override
	public List<TestCaseInfo> selectByServiceId(int service_id) {
		return mapper.selectByServiceId(service_id);
	}

	@Override
	public ResultTool<String> runById(int case_id) {
		TestCaseInfo caseInfo = mapper.selectById(case_id);
		TestMethodInfo methodInfo =  methodMapper.selectById(caseInfo.getMethod_id());
		String httpOrgCreateTestRtn = "";
		int httpStatus = 0;//0-初始化 1-成功 2失败
		String httpError = "";
		int assertStatus = 0;
		String assertError = "";
		try {
			httpOrgCreateTestRtn = HttpClientUtil.doPost(methodInfo.getMethod_address(), caseInfo.getCase_data(), "utf-8");
			httpStatus = 1;
		} catch (Exception e) {
			LOGGER.error("", e);
			httpError = e.getMessage();
			httpStatus = 2;
		}
		
		if (httpStatus != 1)
		{
			// throw 记录结果（执行ID，用例ID，用例data，返回结果，httpStatus,httpError）
		}
		
		if (StringUtils.isEmpty(httpOrgCreateTestRtn))
		{
			// throw
		}
		
		JSONObject jsonobj = JSONObject.parseObject(httpOrgCreateTestRtn);
		if (jsonobj == null)
		{
			// throw
		}
		
		if (!jsonobj.containsKey("code"))
		{
			// throw
		}
		
		String code = jsonobj.getString("code");
		if (StringUtils.isEmpty(code))
		{
			// throw
		}
		
//		Map<String, String> map = JsonUtil.json2Map(httpOrgCreateTestRtn);
		Boolean a = code.equals(caseInfo.getCase_assert_value());
		if (a == true) {
			return ResultTool.setResult("9999", "Case执行返回值为空", null);
		} else {
			return ResultTool.setResult("9999", "Case执行返回值为空", null);
		}
	}

}
