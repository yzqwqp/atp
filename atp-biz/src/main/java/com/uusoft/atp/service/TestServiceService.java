package com.uusoft.atp.service;
/** 
* ��˵�� ��
* 	TestServiceService�ӿ�
* @author ����
* @email qiupeng@toutoujinrong.com
* @since 2016��12��13�� ����10:19:12 
*/
import java.util.List;

import com.uusoft.atp.model.TestServiceInfo;
import com.uusoft.atp.utils.ResultTool;

public interface TestServiceService {
	
	int insert(TestServiceInfo testServiceInfo);
	
	List<TestServiceInfo> selectAll();
	
	int selectSeviceIdByName(String service_name);
	
	ResultTool<TestServiceInfo> selectById(int service_id);
	
	int updateById(TestServiceInfo testServiceInfo);
	
	int deleteById(int service_id);
	
	ResultTool<List<String>> selectUnCreateService();

	ResultTool<List<String>> selectCreateService();
}
