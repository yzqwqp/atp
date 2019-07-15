package com.uusoft.atp.service.impl;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.uusoft.atp.dao.TestExecutionMapper;
import com.uusoft.atp.dao.TestResultMapper;
import com.uusoft.atp.model.TestCaseInfo;
import com.uusoft.atp.model.TestExecutionInfo;
import com.uusoft.atp.model.TestResultInfo;
import com.uusoft.atp.service.TestCaseService;
import com.uusoft.atp.service.TestExecutionService;
import com.uusoft.atp.service.TestResultService;
import com.uusoft.atp.utils.HttpClientUtil;
import com.uusoft.atp.utils.ResultTool;
import com.uusoft.atp.utils.StringUtil;


@Service("TestExecutionService")
@Transactional
public class TestExecutionServiceImpl implements TestExecutionService {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(TestExecutionServiceImpl.class);
	
	@Resource
	TestExecutionMapper testExecutionMapper;
	@Resource
	TestResultMapper testResultMapper;
	@Resource
	TestCaseService testCaseService;
	@Resource
	TestResultService testResultService;

	private TestExecutionInfo testExecutionInfo = new TestExecutionInfo();
	
	private TestResultInfo runByCaseId(TestCaseInfo caseInfo, String tkStr) {
		
		TestResultInfo testResultInfo = new TestResultInfo();
		String httpResponseStr = "";
		int httpStatus = 0;//0-初始化 1-成功 2失败
		String httpError = "";
		int assertStatus = 0;
		String assertError = "";
		String responseAssertValue = "";
		JSONObject jsonobj = null;
		//处理需要断言的值
		String assertValue_k = "";
		String assertValue_v = "";
		
		testResultInfo.setCase_id(caseInfo.getCase_id());
		testResultInfo.setCase_des(caseInfo.getCase_des());
		testResultInfo.setMethod_address(caseInfo.getMethod_address());
		testResultInfo.setCase_data(caseInfo.getCase_data());
		testResultInfo.setCase_assert_type(caseInfo.getCase_assert_type());
		testResultInfo.setCase_assert_value(caseInfo.getCase_assert_value());
		testResultInfo.setResponse_assert_value(responseAssertValue);
		
		// 开始请求		##########################################################################################
		// --1--开始处理请求
		try {
			// TODO
			httpResponseStr = HttpClientUtil.doPost(caseInfo.getMethod_address(), caseInfo.getCase_data(), tkStr);
			httpStatus = 10;
			testResultInfo.setHttp_status(httpStatus);
			testResultInfo.setResponse_data(httpResponseStr);
		} catch (Exception e) {
			LOGGER.error("", e);
			httpError = e.getMessage();
			httpStatus = 20;
			assertStatus = 20;
			testResultInfo.setHttp_status(httpStatus);
			testResultInfo.setHttp_error(httpError);
			testResultInfo.setAssert_status(assertStatus);
			testResultInfo.setAssert_error(assertError);
		}
		
		// --2--请求失败处理
		if (httpStatus != 10)
		{
			// throw 记录结果（执行ID，用例ID，用例data，返回结果，httpStatus,httpError）
			// httpStatue不等于1，说明请求不成功，直接执行不成功插入test_result结果表
			testResultInfo.setHttp_error("9999http请求异常，执行失败");
			return testResultInfo;
		}
		
		// --3--请求返回为空处理
		if (StringUtils.isEmpty(httpResponseStr))
		{
			// 如果http请求返回值为空，这里需要判断断言，如果不断言，则用例执行成功
			if (caseInfo.getCase_assert_type() == 0) {
				httpStatus = 10;
				assertStatus = 11;
				testResultInfo.setHttp_status(httpStatus);
				testResultInfo.setAssert_status(assertStatus);
				testResultInfo.setHttp_error("");
				testResultInfo.setAssert_error("1011 http响应无返回，用例无断言，成功");
			} else {
				httpStatus = 30;
				assertStatus = 30;
				testResultInfo.setHttp_status(httpStatus);
				testResultInfo.setAssert_status(assertStatus);
				testResultInfo.setHttp_error("http请求无返回值，异常");
				testResultInfo.setAssert_error("3030 http响应无返回值，用例有断言，无法断言，断言执行失败");
			}
			return testResultInfo;
		}
		
		// --4--请求返回不为空，解析返回值
		try {
			jsonobj = JSONObject.parseObject(httpResponseStr);
		} catch (Exception e) {
			LOGGER.error("", e);
			assertError = e.getMessage();
			assertStatus = 40;
			testResultInfo.setAssert_status(assertStatus);
			testResultInfo.setAssert_error(assertError);
			testResultInfo.setAssert_error("1040json解析异常，断言失败");
			return testResultInfo;
		}
		
		// --5--请求返回不为空，解析返回值为空处理
		if (jsonobj == null)
		{
			// 如果断言code值为空，这里需要判断断言，如果不断言，则用例执行成功
			if (caseInfo.getCase_assert_type() == 0) {
				assertStatus = 12;
				testResultInfo.setAssert_status(assertStatus);
				testResultInfo.setAssert_error("1012解析json为空，无断言，执行成功");
			} else {
				assertStatus = 41;
				testResultInfo.setAssert_status(assertStatus);
				testResultInfo.setAssert_error("1041解析json为空，断言失败");
			}
			return testResultInfo;
		}
		
		// 开始寻找token		##########################################################################################
		// 如果用例有执行后处理token的，需要截取token TODO
		if ((null != caseInfo.getAfter_run()) && (caseInfo.getAfter_run()) == 1) {
			
//			if (!jsonobj.containsKey("tk")){
//				// token找不到
//				testResultInfo.setAssert_status(44);
//				testResultInfo.setAssert_error("1044解析response的json中寻找tk值找不到");
//				return testResultInfo;
//			}
//			String tokenStr = jsonobj.getString("tk");
			 
			Map<String, Object> paramsMap = (Map<String, Object>) jsonobj.get("data");
			String tokenStr = (String) paramsMap.get("tk");
			
			
			if (StringUtils.isEmpty(tokenStr)){
				// token找不到
				testResultInfo.setAssert_status(44);
				testResultInfo.setAssert_error("1044解析response的json中寻找tk值找不到");
				return testResultInfo;
			}
			
			// tk的值既不为空，又不为null，那么我们把tk的值返回给上级
			testResultInfo.setTokenFlag(1); // 找到token，把testResultInfo中的tokenFlage设为1
			testResultInfo.setToken("tk="+tokenStr);
		}
		
		// 开始判断断言		##########################################################################################
		if (caseInfo.getCase_assert_type() == 1) {
			String[] a = StringUtil.splitList(caseInfo.getCase_assert_value());
			assertValue_k=a[0]; // 需要断言的字段
			assertValue_v=a[1]; // 需要断言字段的值
		}
		
		// --6--请求返回不为空，解析返回值不为空，code字段找不到处理
		if (!jsonobj.containsKey(assertValue_k))
		{
			if (caseInfo.getCase_assert_type() == 0) {
				assertStatus = 13;
				testResultInfo.setAssert_status(assertStatus);
				testResultInfo.setAssert_error("1013解析json的code字段找不到，无断言，执行成功");
			} else {
				assertStatus = 42;
				testResultInfo.setAssert_status(assertStatus);
				testResultInfo.setAssert_error("1042需要断言，断言值为：["+caseInfo.getCase_assert_value()+"]，解析json时"+assertValue_k+"字段找不到，执行失败");
			}
			return testResultInfo;
		}
		
		// --7--请求返回不为空，解析返回值不为空，code字段值为空处理
		responseAssertValue = jsonobj.getString(assertValue_k);
		testResultInfo.setResponse_assert_value(responseAssertValue);
		if (StringUtils.isEmpty(responseAssertValue))
		{
			if (caseInfo.getCase_assert_type() == 0) {
				assertStatus = 14;
				testResultInfo.setAssert_status(assertStatus);
				testResultInfo.setAssert_error("1014不需要断言["+caseInfo.getCase_assert_value()+"]，解析json时"+assertValue_k+"字段值为空，执行成功");
			} else if (caseInfo.getCase_assert_type() == 1 && caseInfo.getCase_assert_value().isEmpty()) {
				//断言类型是字符串断言，且断言值为空
				assertStatus = 15;
				testResultInfo.setAssert_status(assertStatus);
				testResultInfo.setAssert_error("1014需要断言["+caseInfo.getCase_assert_value()+"]，解析json时"+assertValue_k+"字段值为空，断言成功");
			}
			return testResultInfo;
		} 
		
		// --8--请求返回不为空，解析返回值不为空，code字段值不为空，做字符串比对处理
		if (caseInfo.getCase_assert_type() == 1 && assertValue_v.equals(responseAssertValue)) {
			//断言类型是字符串断言，且断言值相等
			assertStatus = 10;
			testResultInfo.setAssert_status(assertStatus);
			testResultInfo.setAssert_error("1010断言成功! 断言值为：["+caseInfo.getCase_assert_value()+"] ; json解析"+assertValue_k+"值为:["+ responseAssertValue +"]; ");
			return testResultInfo;
		} else {
			//断言类型是字符串断言，且断言值不相等
			assertStatus = 43;
			testResultInfo.setAssert_status(assertStatus);
			testResultInfo.setAssert_error("1043 断言失败! 断言值为：["+caseInfo.getCase_assert_value()+"] ; json解析"+assertValue_k+"值为:["+ responseAssertValue +"]; ");
			return testResultInfo;
		}
		// 断言结束	##########################################################################################
	}

	/**
	 * runBySuiteId
	 * executionInfo : 执行的实体对象
	 * suite_id : testSuite测试用例的ID
	 * author : qiupeng
	 * date: 2019-07-14
	 * */
	private TestExecutionInfo runBySuiteId(int suite_id) {
		String tokenStr = ""; // token
		
		if((testExecutionInfo == null)){
			return runFail();
		}
			
		int execution_id = testExecutionInfo.getExecution_id();
		List<TestCaseInfo> caseInfoList = testCaseService.selectBySuiteId(suite_id);
		// 如果caseInfoList是空
		if (caseInfoList.isEmpty()){
			return runFail();
		}
		// 开始执行suite下的测试用例
		for(TestCaseInfo caseInfo:caseInfoList){
			
			// --1--如有beforeRun，需要先执行beforeRun	##########################################################################################
			if ((null != caseInfo.getBefore_run()) && (caseInfo.getBefore_run()) > 0) {
				// --1--执行before
				TestCaseInfo beforeCaseInfo = testCaseService.selectByCaseId(caseInfo.getBefore_run());
				// 如果beforeCase找不到，执行suite失败返回
				if(beforeCaseInfo == null){
					return runFail();
				} 
				else {
					TestResultInfo beforeResultInfo = runByCaseId(beforeCaseInfo,tokenStr);
					beforeResultInfo.setExecution_id(execution_id);
					beforeResultInfo.setSuite_id(suite_id);
					testResultService.insert(beforeResultInfo);
					//判断执行结果，断言不成功，直接退出本次suite执行
					if (beforeResultInfo.getAssert_status() != 10) {
						return runFail();
					}
					//判断是否有tokenFlag
					if (beforeResultInfo.getTokenFlag() ==  1) {
						tokenStr = beforeResultInfo.getToken();
					}
					LOGGER.info("###--1-- execution_id :"+execution_id+"###runBySuiteId ： " + suite_id + "已执行 ### beforeResultInfo is :" + beforeResultInfo.toString());
				}
				
				// --2--执行beforeRun后再执行本case	##########################################################################################
				TestResultInfo testResultInfo1 = runByCaseId(caseInfo,tokenStr);
				testResultInfo1.setExecution_id(execution_id);
				testResultInfo1.setSuite_id(suite_id);
				testResultService.insert(testResultInfo1);
				LOGGER.info("###--2-- execution_id :"+execution_id+"###runBySuiteId ： " + suite_id + "### caseInfo is :" + caseInfo.toString());
				// 判断执行结果，断言不成功，直接退出本次suite执行
				if (testResultInfo1.getAssert_status() != 10) {
					return runFail();
				}
				
			} 
			else {
				// --1--执行本case
				LOGGER.info("###--0-- execution_id :"+execution_id+"###runBySuiteId ： " + suite_id + "### caseInfo is :" + caseInfo.toString());
				TestResultInfo testResultInfo2 = runByCaseId(caseInfo,tokenStr);
				testResultInfo2.setExecution_id(execution_id);
				testResultInfo2.setSuite_id(suite_id);
				testResultService.insert(testResultInfo2);
				if (testResultInfo2.getAssert_status() != 10) {
					return runFail();
				}
			}
		}
		
		return runTrue();
	}

	private ResultTool<String> runByMethodId(int method_id) {
		return null;
	}

	private ResultTool<String> runByServiceId(int service_id) {
		return null;
	}

	private TestExecutionInfo runFail(){
		testExecutionInfo.setTotal_num(testExecutionInfo.getTotal_num()+1);
		testExecutionInfo.setFailure_num(testExecutionInfo.getFailure_num()+1);
		LOGGER.info("###执行Error   testExecutionInfo：" + testExecutionInfo.toString());
		return testExecutionInfo;
	}
	
	private TestExecutionInfo runTrue(){
		testExecutionInfo.setTotal_num(testExecutionInfo.getTotal_num()+1);
		testExecutionInfo.setTrue_num(testExecutionInfo.getTrue_num()+1);
		LOGGER.info("###执行OK   testExecutionInfo：" + testExecutionInfo.toString());
		return testExecutionInfo;
	}

	/**
	 * APP INTERFACE AUTOMATION TEST MANAGEMENT AND PRACTICE
	 * execution_type : 1：testSuite测试用例 2：testMethod测试集 3：testService测试服务
	 * execution_type_value : testSuite测试用例的ID / testMethod测试集的ID / testService测试服务的ID
	 * execution_type_name : testSuite测试用例ID描述 / testMethod测试集ID描述 / testService测试服务ID描述
	 * */
	@Override
	public ResultTool<TestExecutionInfo> execution(int execution_type, int execution_type_value, String execution_type_name) {
		// --1--先插入1条记录(执行类型，执行类型的ID)
//		TestExecutionInfo testExecutionInfo = new TestExecutionInfo();
		testExecutionInfo.setExecution_type(execution_type);
		testExecutionInfo.setExecution_type_value(execution_type_value);
		testExecutionInfo.setExecution_type_name(execution_type_name);
		testExecutionMapper.insert(testExecutionInfo);
		LOGGER.info("### execution_type = ["+execution_type+"] Execution_id = ["+testExecutionInfo.getExecution_id()+"] execution_type_value = "+execution_type_value+"] ###");
		switch(execution_type){
	    case 1 :
	    	runBySuiteId(execution_type_value); // execution_type_value = suite_id
	    case 2 :
	    	runByMethodId(execution_type_value); // execution_type_value = method_id
	    case 3 :
	    	runByServiceId(execution_type_value); // execution_type_value = service_id
	    default : 
	    	testExecutionMapper.updateById(testExecutionInfo);
	    	return ResultTool.setResult("0000", String.valueOf(testExecutionInfo.getExecution_id())+"已执行完成！", testExecutionInfo);
		}
	}

	@Override
	public TestExecutionInfo selectByExecutionId(int method_id) {
		return testExecutionMapper.selectByExecutionId(method_id);
	}

	@Override
	public List<TestExecutionInfo> selectAll() {
		return testExecutionMapper.selectAll();
	}

	@Override
	public List<TestResultInfo> selectResultByExecutionId(int execution_id) {
		return testResultMapper.selectByExecutionId(execution_id);
	}

	

}
