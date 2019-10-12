package com.uusoft.atp.service.impl;
/** 
* 类说明 ：
* 	LoginService实现类
* @author xiaoli
* @email
* @since 2019年7月18日 上午10:57:12 
*/

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uusoft.atp.dao.UserMapper;
import com.uusoft.atp.model.UserInfo;
import com.uusoft.atp.service.LoginService;


@Service("LoginService")
@Transactional
public class LoginServiceImpl implements LoginService {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(LoginServiceImpl.class);
	
	@Resource
	private UserMapper mapper;

	@Override
	public UserInfo selectByLoginName(String loginName) {
		return mapper.selectByLoginName(loginName);
	}
	
	

}
