package com.uusoft.atp.service.impl;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.uusoft.atp.dao.TestExecutionMapper;
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
	TestCaseService testCaseService;
	@Resource
	TestResultService testResultService;
	
	private TestResultInfo runByCaseId(TestCaseInfo caseInfo) {
		
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
		
		// --1--开始处理请求
		try {
			// TODO
			httpResponseStr = HttpClientUtil.doPost(caseInfo.getMethod_address(), caseInfo.getCase_data(), "utf-8");
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
			// throw
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
	}

	
	public ResultTool<String> runBySuiteId(int execution_id, int suite_id) {
		
		List<TestCaseInfo> caseInfoList = testCaseService.selectBySuiteId(suite_id);
		// 如果caseInfoList是空 TODO
		
		// 开始执行suite下的测试用例
		for(TestCaseInfo caseInfo:caseInfoList){
			LOGGER.info("### execution_id :"+execution_id+"###runBySuiteId ： " + suite_id + "### caseInfo is :" + caseInfo.toString());
			
//			if (!StringUtil.isBlank(String.valueOf(caseInfo.getBefore_run())) && (caseInfo.getBefore_run() == 0)) {
			if ((null != caseInfo.getBefore_run()) && (caseInfo.getBefore_run()) > 0) {
				// --1--执行before
				TestCaseInfo beforeCaseInfo = testCaseService.selectByCaseId(caseInfo.getBefore_run());
				if(!beforeCaseInfo.equals(null)){
					TestResultInfo beforeResultInfo = runByCaseId(beforeCaseInfo);
					beforeResultInfo.setExecution_id(execution_id);
					beforeResultInfo.setSuite_id(suite_id);
					testResultService.insert(beforeResultInfo);
				}
				// --2--执行本case
				TestResultInfo testResultInfo1 = runByCaseId(caseInfo);
				testResultInfo1.setExecution_id(execution_id);
				testResultInfo1.setSuite_id(suite_id);
				testResultService.insert(testResultInfo1);
			} else {
				// --1--执行本case
				TestResultInfo testResultInfo2 = runByCaseId(caseInfo);
				testResultInfo2.setExecution_id(execution_id);
				testResultInfo2.setSuite_id(suite_id);
				testResultService.insert(testResultInfo2);
			}
		}
		return ResultTool.setResult("0000", execution_id+"已经执行", null);
	}

	public ResultTool<String> runByMethodId(int execution_id, int method_id) {
		// TODO Auto-generated method stub
		return null;
	}

	public ResultTool<String> runByServiceId(int execution_id, int service_id) {
		// TODO Auto-generated method stub
		return null;
	}



	/**
	 * 
	 * execution_type : 1：testSuite测试用例 2：testMethod测试集 3：testService测试服务
	 * execution_type_value : testSuite测试用例的ID / testMethod测试集的ID / testService测试服务的ID
	 * */
	@Override
	public ResultTool<String> execution(int execution_type, int execution_type_value, String execution_type_name) {
		// --1--先插入1条记录(执行类型，执行类型的ID)
		TestExecutionInfo testExecutionInfo = new TestExecutionInfo();
		testExecutionInfo.setExecution_type(execution_type);
		testExecutionInfo.setExecution_type_value(execution_type_value);
		testExecutionInfo.setExecution_type_name(execution_type_name);
		testExecutionMapper.insert(testExecutionInfo);
		LOGGER.info("### execution_type = ["+execution_type+"] Execution_id = ["+testExecutionInfo.getExecution_id()+"] execution_type_value = "+execution_type_value+"] ###");
		switch(execution_type){
	    case 1 :
	    	runBySuiteId(testExecutionInfo.getExecution_id(), execution_type_value);
	    case 2 :
	    	runByMethodId(testExecutionInfo.getExecution_id(), execution_type_value);
	    case 3 :
	    	runByServiceId(testExecutionInfo.getExecution_id(), execution_type_value);
	    default : 
	    	return ResultTool.setResult("000", String.valueOf(testExecutionInfo.getExecution_id()), null);
		}
	}


}
