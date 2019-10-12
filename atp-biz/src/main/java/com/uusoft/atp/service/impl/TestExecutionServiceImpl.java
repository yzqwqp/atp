package com.uusoft.atp.service.impl;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.uusoft.atp.dao.TestExecutionMapper;
import com.uusoft.atp.dao.TestResultMapper;
import com.uusoft.atp.model.TestCaseInfo;
import com.uusoft.atp.model.TestConstantInfo;
import com.uusoft.atp.model.TestExecutionInfo;
import com.uusoft.atp.model.TestMethodInfo;
import com.uusoft.atp.model.TestResultInfo;
import com.uusoft.atp.model.TestSuiteInfo;
import com.uusoft.atp.service.TestCaseService;
import com.uusoft.atp.service.TestConstantService;
import com.uusoft.atp.service.TestExecutionService;
import com.uusoft.atp.service.TestMethodService;
import com.uusoft.atp.service.TestResultService;
import com.uusoft.atp.service.TestRunsqlService;
import com.uusoft.atp.service.TestSuiteService;
import com.uusoft.atp.utils.HttpClientUtil;
import com.uusoft.atp.utils.ResultTool;
import com.uusoft.atp.utils.StringUtil;


@Service("TestExecutionService")
//@Transactional
public class TestExecutionServiceImpl implements TestExecutionService {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(TestExecutionServiceImpl.class);
	
	@Resource
	TestExecutionMapper testExecutionMapper;
	@Resource
	TestResultMapper testResultMapper;
	@Resource
	TestConstantService testConstantService;
	@Resource
	TestCaseService testCaseService;
	@Resource
	TestResultService testResultService;
	@Resource
	TestSuiteService testSuiteService;
	@Resource
	TestMethodService testMethodService;
	@Resource
	TestRunsqlService testRunsqlService;
	

	private JSONObject jsonobjResultDetail = new JSONObject();
	private TestExecutionInfo testExecutionInfo = new TestExecutionInfo();
	private String htttpUrlPrefix = "";
	
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
//		String urlConstant = "http://10.0.160.215:9980/v1";
		String httpurl = htttpUrlPrefix+caseInfo.getMethod_address();
		
		testResultInfo.setCase_id(caseInfo.getCase_id());
		testResultInfo.setCase_des(caseInfo.getCase_des());
		testResultInfo.setMethod_address(httpurl);
		testResultInfo.setCase_data(caseInfo.getCase_data());
		testResultInfo.setCase_assert_type(caseInfo.getCase_assert_type());
		testResultInfo.setCase_assert_value(caseInfo.getCase_assert_value());
		testResultInfo.setResponse_assert_value(responseAssertValue);
		
		// 开始请求		##########################################################################################
		// --1--开始处理请求
		try {
			httpResponseStr = HttpClientUtil.doPost(httpurl, caseInfo.getCase_data(), tkStr);
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
			
		} else {
			//断言类型是字符串断言，且断言值不相等
			assertStatus = 43;
			testResultInfo.setAssert_status(assertStatus);
			testResultInfo.setAssert_error("1043 断言失败! 断言值为：["+caseInfo.getCase_assert_value()+"] ; json解析"+assertValue_k+"值为:["+ responseAssertValue +"]; ");
			return testResultInfo;
		}
		// 断言结束	##########################################################################################
		
		// 执行后处理		##########################################################################################
		// 如果After_run_type不为空，不为null, 执行后处理
		if (null != caseInfo.getAfter_run_type()) {
			
			// After_run_type == 1   开始寻找token需要截取token
			if (caseInfo.getAfter_run_type().equals(1)) {
				@SuppressWarnings("unchecked")
				Map<String, Object> paramsMap = (Map<String, Object>) jsonobj.get("data");
				String tokenStr = (String) paramsMap.get("tk");
				if (StringUtils.isEmpty(tokenStr)){
					// token找不到, 抛错返回
					testResultInfo.setAssert_status(44);
					testResultInfo.setAssert_error("1044解析response的json中寻找tk值找不到");
					return testResultInfo;
				}
				// tk的值既不为空，又不为null，那么我们把tk的值返回给上级
				testResultInfo.setTokenFlag(1); // 找到token，把testResultInfo中的tokenFlage设为1
				testResultInfo.setToken("tk="+tokenStr);
			}
			
			// After_run_type == 2   处理业务变量更新 
			if (caseInfo.getAfter_run_type().equals(2)) {
				if (null == caseInfo.getAfter_run()) {
					// After_run找不到, 抛错返回
					testResultInfo.setAssert_status(45);
					testResultInfo.setAssert_error("1045,用例After_run字段为空");
					return testResultInfo;
				}
				// 传递json和after_run=constant_id，进行业务变量替换 
				ResultTool<String> afterRunResult = testConstantService.mofiyConstantInfoAfterRun(caseInfo,jsonobj);
				if(afterRunResult.getCode().equals("9999")) {
					// After_run更新失败, 抛错返回
					testResultInfo.setAssert_status(46);
					testResultInfo.setAssert_error("1046,"+afterRunResult.getMessage());
					return testResultInfo;
					
				} 
				if(afterRunResult.getCode().equals("0000"))  {
					// After_run更新成功, 啥都不用做也不返回
					LOGGER.info("######### "+afterRunResult.getMessage());
				}
				
//				if (!jsonobj.containsKey("tk")){
//					// token找不到
//					testResultInfo.setAssert_status(44);
//					testResultInfo.setAssert_error("1044解析response的json中寻找tk值找不到");
//					return testResultInfo;
//				}
			}
		}
		return testResultInfo;
	}
	
	private TestResultInfo runSqlByCaseId(TestCaseInfo caseInfo){
		Integer response = 0;
		TestResultInfo testResultInfo = new TestResultInfo();
		testResultInfo.setCase_id(caseInfo.getCase_id());
		testResultInfo.setCase_des(caseInfo.getCase_des());
		testResultInfo.setMethod_address(caseInfo.getMethod_address());
		testResultInfo.setCase_data(caseInfo.getCase_data());
		testResultInfo.setCase_assert_type(caseInfo.getCase_assert_type());
		testResultInfo.setCase_assert_value(caseInfo.getCase_assert_value());
		testResultInfo.setResponse_assert_value("");// SQL执行结果
		
		try {
			// TODO
			response = testRunsqlService.updateBySql(caseInfo.getCase_data());
			// 清空数据源选择
//			DynamicDataSource.clearCustomerType();
		} catch (Exception e) {
			LOGGER.error("", e);
			// 清空数据源选择
//			DynamicDataSource.clearCustomerType();
			String assertError = e.getMessage();
			testResultInfo.setAssert_status(20);
			testResultInfo.setAssert_error(assertError);// SQL执行异常，直接抛错误
			return testResultInfo;
		}
		// 如果无需断言，只要不报错直接返回成功
		if(caseInfo.getCase_assert_type() == 0) {
			testResultInfo.setAssert_status(10);
			testResultInfo.setResponse_data(String.valueOf(response));
			testResultInfo.setAssert_error("SQL执行正常，未断言");
			return testResultInfo;
		}
		if (response <= 0 || response.equals(null)) {
			testResultInfo.setAssert_status(20);
			testResultInfo.setResponse_data(String.valueOf(response));
			testResultInfo.setAssert_error("SQL执行异常，没有更新数据");
			return testResultInfo;
		} else {
			testResultInfo.setAssert_status(10);
			testResultInfo.setResponse_data(String.valueOf(response));
			testResultInfo.setAssert_error("SQL执行正常，断言执行结果大于0");
			return testResultInfo;
		}
	}
	
	/**
	 * runBySuiteId
	 * executionInfo : 执行的实体对象
	 * suite_id : testSuite测试用例的ID
	 * author : qiupeng
	 * date: 2019-07-14
	 * */
	private TestExecutionInfo runBySuiteId(int suite_id) {
		testExecutionInfo.setTotal_num(testExecutionInfo.getTotal_num()+1);
		LOGGER.info("###执行的ID是：["+testExecutionInfo.getExecution_id()+"]，开始执行第["+testExecutionInfo.getTotal_num()+"]个，suiteId=["+suite_id+"]");
		
		String tokenStr = ""; // token
		
		if((testExecutionInfo == null)){
			return runFail(suite_id, "1 info is null");
		}
			
		int execution_id = testExecutionInfo.getExecution_id();
		List<TestCaseInfo> caseInfoList = testCaseService.selectBySuiteId(suite_id);
		// 如果caseInfoList是空
		if (caseInfoList.isEmpty()){
			return unRun(execution_id, suite_id, "2 caseList is null");
		}
		// 开始执行suite下的测试用例
		// for loop begin	##########################################################################################
		for(TestCaseInfo caseInfo:caseInfoList){
			
			// beforeRun begin	##########################################################################################
			if (null != caseInfo.getBefore_run()) {
				
				// ######### 判断Before_run_type() == 【1】，执行其他用例  #########
				if (caseInfo.getBefore_run_type().equals(1)) {
					// 执行before
					TestCaseInfo beforeCaseInfo = testCaseService.selectByCaseId(caseInfo.getBefore_run());
					// 如果beforeCase找不到，执行suite失败返回
					if(beforeCaseInfo == null){
						return unRun(execution_id, suite_id, "3.1.1 beforeCaseInfo is null");
					} 
					else {
						TestResultInfo beforeResultInfo = new TestResultInfo();
						switch(beforeCaseInfo.getCase_type()){
					    case 0 :
					    	beforeResultInfo = runByCaseId(beforeCaseInfo,tokenStr); // Case_type = 0 执行正常步骤
					    	break;
					    case 1 :
					    	beforeResultInfo = runSqlByCaseId(beforeCaseInfo); // Case_type = 1  执行SQL步骤
					    	break;
						}
						beforeResultInfo.setExecution_id(execution_id);
						beforeResultInfo.setSuite_id(suite_id);
						testResultService.insert(beforeResultInfo);
						//判断执行结果，断言不成功，直接退出本次suite执行
						if (beforeResultInfo.getAssert_status() != 10) {
							return runFail(suite_id, "3.1.2 assert error ["+beforeResultInfo.getCase_assert_value()+"]["+beforeResultInfo.getResponse_assert_value()+"]");
						}
						//判断是否有tokenFlag
						if (beforeResultInfo.getTokenFlag() ==  1) {
							tokenStr = beforeResultInfo.getToken();
						}
					}
					
					// 执行beforeRun后再执行本case
					TestResultInfo testResultInfo1 = new TestResultInfo();
					switch(caseInfo.getCase_type()){
				    case 0 :
				    	testResultInfo1 = runByCaseId(caseInfo,tokenStr); // Case_type = 0 执行正常步骤
				    	break;
				    case 1 :
				    	testResultInfo1 = runSqlByCaseId(caseInfo); // Case_type = 1  执行SQL步骤
				    	break;
					}
					testResultInfo1.setExecution_id(execution_id);
					testResultInfo1.setSuite_id(suite_id);
					testResultService.insert(testResultInfo1);
					// 判断执行结果，断言不成功，直接退出本次suite执行
					if (testResultInfo1.getAssert_status() != 10) {
						return runFail(suite_id, "3.1.3 assert error ["+testResultInfo1.getCase_assert_value()+"]["+testResultInfo1.getResponse_assert_value()+"]");
					}
				}
				
				// ######### 判断Before_run_type() ==【2】，执行json替换  #########
				if (caseInfo.getBefore_run_type().equals(2)) {
					if (caseInfo.getBefore_run() == null || caseInfo.getBefore_run() == 0){
						return unRun(execution_id, suite_id, "3.2.1 caseInfo getBefore_run is null");
					}
					TestCaseInfo modifyCaseInfo = testConstantService.mofiyCaseInfo(caseInfo);
					if (modifyCaseInfo == null ) {
						return unRun(execution_id, suite_id, "3.2.2 modify CaseInfo is null");
					}
					
					// 执行beforeRun后再执行本case
					TestResultInfo modifyResultInfo = new TestResultInfo();
					switch(modifyCaseInfo.getCase_type()){
				    case 0 :
				    	modifyResultInfo = runByCaseId(modifyCaseInfo,tokenStr); // Case_type = 0 执行正常步骤
				    	break;
				    case 1 :
				    	modifyResultInfo = runSqlByCaseId(modifyCaseInfo); // Case_type = 1  执行SQL步骤
				    	break;
					}
					modifyResultInfo.setExecution_id(execution_id);
					modifyResultInfo.setSuite_id(suite_id);
					testResultService.insert(modifyResultInfo);
					//判断执行结果，断言不成功，直接退出本次suite执行
					if (modifyResultInfo.getAssert_status() != 10) {
						return runFail(suite_id, "3.2.3 assert error ["+modifyResultInfo.getCase_assert_value()+"]["+modifyResultInfo.getResponse_assert_value()+"]");
					}
					//判断是否有tokenFlag
					if (modifyResultInfo.getTokenFlag() ==  1) {
						tokenStr = modifyResultInfo.getToken();
					}
				}

				// ######### 判断Before_run_type() == 【3】，执行sql变量的替换  #########
				if (caseInfo.getBefore_run_type().equals(3)) {
					if (caseInfo.getBefore_run() == null || caseInfo.getBefore_run() == 0){
						return unRun(execution_id, suite_id, "3.3.1 caseInfo getBefore_run is null");
					}
					ResultTool<TestCaseInfo> modifyResult = testConstantService.mofiySqlCaseInfo(caseInfo);
					if(modifyResult.getCode() == "9999"){
						return unRun(execution_id, suite_id, "3.3.2"+modifyResult.getMessage());
					}
					TestCaseInfo modifySqlCaseInfo = modifyResult.getObj();
					if (modifySqlCaseInfo == null ) {
						return unRun(execution_id, suite_id, "3.3.3 modify CaseInfo is null");
					}
					
					// 执行beforeRun后再执行本case
					TestResultInfo modifyResultInfo = new TestResultInfo();
					switch(modifySqlCaseInfo.getCase_type()){
				    case 0 :
				    	modifyResultInfo = runByCaseId(modifySqlCaseInfo,tokenStr); // Case_type = 0 执行正常步骤  【一般执行SQL的不会执行这个】
				    	break;
				    case 1 :
				    	modifyResultInfo = runSqlByCaseId(modifySqlCaseInfo); // Case_type = 1  执行SQL步骤
				    	break;
					}
					modifyResultInfo.setExecution_id(execution_id);
					modifyResultInfo.setSuite_id(suite_id);
					testResultService.insert(modifyResultInfo);
					//判断执行结果，断言不成功，直接退出本次suite执行 
					if (modifyResultInfo.getAssert_status() != 10) {
						return runFail(suite_id, "3.3.4 断言失败");
					}
					//判断是否有tokenFlag，执行SQL一般不会有Token处理
//					if (modifyResultInfo.getTokenFlag() ==  1) {
//						tokenStr = modifyResultInfo.getToken();
//					}
				}
				
				// beforeRun end	##########################################################################################
			}
			else {
				// --1--执行本case
				TestResultInfo testResultInfo2 = new TestResultInfo();
				switch(caseInfo.getCase_type()){
			    case 0 :
			    	testResultInfo2 = runByCaseId(caseInfo,tokenStr); // Case_type = 0 执行正常步骤
			    	break;
			    case 1 :
			    	testResultInfo2 = runSqlByCaseId(caseInfo); // Case_type = 1  执行SQL步骤
			    	break;
				}
				testResultInfo2.setExecution_id(execution_id);
				testResultInfo2.setSuite_id(suite_id);
				testResultService.insert(testResultInfo2);
				if (testResultInfo2.getAssert_status() != 10) {
					return runFail(suite_id, "4 assert error ["+testResultInfo2.getCase_assert_value()+"]["+testResultInfo2.getResponse_assert_value()+"]");
				}
			}
		// for loop end	##########################################################################################
		} 
			
		return runTrue(suite_id);
	}

	private void runByMethodId(int method_id) {
		// 执行用例集
		// 按照用例集的id，查出所有用例
		List<TestSuiteInfo> testSuiteListInfo = testSuiteService.selectCanRunByMethodId(method_id);
		for (TestSuiteInfo t : testSuiteListInfo) {
			runBySuiteId(t.getSuite_id());
		}
//		return testExecutionInfo;
	}

	private void runByServiceId(int service_id) {
		// 执行测试模块
		// 按照模块的id，查出所有用例集
		List<TestMethodInfo> testServiceInfoList = testMethodService.selectByServiceId(service_id);
		for (TestMethodInfo t : testServiceInfoList) {
			runByMethodId(t.getMethod_id());
		}
//		return null;
	}
	

	private TestExecutionInfo runFail(int suiteId, String str){
		testExecutionInfo.setFailure_num(testExecutionInfo.getFailure_num()+1);
		String resultDetailStr1 = "ERROR_runFail runNum:["+testExecutionInfo.getTotal_num()+" ]suiteId:["+suiteId+"] errorMsg:[" + str+"]";
		
		jsonobjResultDetail.put(String.valueOf(testExecutionInfo.getTotal_num()), resultDetailStr1);
		LOGGER.info(resultDetailStr1);
		return testExecutionInfo;
	}
	
	private TestExecutionInfo unRun(int executionId, int suiteId, String str){
		TestResultInfo unrunResultInfo = new TestResultInfo();
		unrunResultInfo.setExecution_id(executionId);
		unrunResultInfo.setSuite_id(suiteId);
		unrunResultInfo.setHttp_status(00);
		unrunResultInfo.setAssert_status(00);
		testResultService.insert(unrunResultInfo);
		testExecutionInfo.setUnrun_num(testExecutionInfo.getUnrun_num()+1);
		String resultDetailStr1 = "ERROR_unRun runNum:["+testExecutionInfo.getTotal_num()+" ]suiteId:["+suiteId+"] errorMsg:[" + str+"]";
		jsonobjResultDetail.put(String.valueOf(testExecutionInfo.getTotal_num()), resultDetailStr1);
		LOGGER.info(resultDetailStr1);
		return testExecutionInfo;
	}
	
	private TestExecutionInfo runTrue(int suiteId){
		testExecutionInfo.setTrue_num(testExecutionInfo.getTrue_num()+1);
		String resultDetailStr2 = "PASS runNum:]"+testExecutionInfo.getTotal_num()+"] suiteId:["+suiteId+"]";
		jsonobjResultDetail.put(String.valueOf(testExecutionInfo.getTotal_num()), resultDetailStr2);
		LOGGER.info(resultDetailStr2);
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
		LOGGER.info("开始执行，执行类型是：["+execution_type+"]，执行id是：["+execution_type_value+"]，执行描述["+execution_type_name+"]");
		TestConstantInfo testConstantInfo = new TestConstantInfo();
		testConstantInfo = testConstantService.selectByConstantName("httpurl");
		htttpUrlPrefix = testConstantInfo.getConstant_value();
		// --1--先插入1条记录(执行类型，执行类型的ID)
//		TestExecutionInfo testExecutionInfo = new TestExecutionInfo();
		jsonobjResultDetail.clear();
		testExecutionInfo.setExecution_type(execution_type);
		testExecutionInfo.setExecution_type_value(execution_type_value);
		testExecutionInfo.setExecution_type_name(execution_type_name);
		testExecutionInfo.setTotal_num(0);
		testExecutionInfo.setFailure_num(0);
		testExecutionInfo.setTrue_num(0);
		testExecutionInfo.setUnrun_num(0);
		testExecutionMapper.insert(testExecutionInfo);
		switch(execution_type){
	    case 1 :
	    	runBySuiteId(execution_type_value); // execution_type_value = 1 suite_id
	    	break;
	    case 2 :
	    	runByMethodId(execution_type_value); // execution_type_value = 2 method_id
	    	break;
	    case 3 :
	    	runByServiceId(execution_type_value); // execution_type_value = 3 service_id
	    	break;
		}
		testExecutionInfo.setExecution_detail(jsonobjResultDetail.toString());
		testExecutionMapper.updateById(testExecutionInfo);
		return ResultTool.setResult("0000", "【"+execution_type_value+"】"+execution_type_name+"已执行完成！执行编号：【"+String.valueOf(testExecutionInfo.getExecution_id())+"】", testExecutionInfo);
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

	@Override
	public List<TestResultInfo> selectFailureResultByExecutionId(int execution_id) {
		return testResultMapper.selectFailureResultByExecutionId(execution_id);
	}

	@Override
	public List<TestResultInfo> selectUnrunResultByExecutionId(int execution_id) {
		return testResultMapper.selectUnrunResultByExecutionId(execution_id);
	}

//	@Transactional
//	public int updateBySql(String sqlStr) {
//		return testExecutionMapper.updateBySql(sqlStr);
//	}
	

}
