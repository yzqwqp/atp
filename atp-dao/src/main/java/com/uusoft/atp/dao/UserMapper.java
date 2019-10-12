package com.uusoft.atp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.uusoft.atp.model.TestCaseInfo;
import com.uusoft.atp.model.UserInfo;

/**
 * @author xiaoli
 *
 */
public interface UserMapper {
	
	int insert(UserInfo userInfo);
	
	UserInfo selectByUserId(@Param("user_id") int user_id);
	
	UserInfo selectByLoginName(@Param("login_name")String login_name);
	
	List<UserInfo> selectAll();
	
	int update(UserInfo userInfo);
	
	int deleteById(@Param("user_id") int user_id);
	
}
