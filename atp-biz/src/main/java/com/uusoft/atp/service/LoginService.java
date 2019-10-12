package com.uusoft.atp.service;

import com.uusoft.atp.model.UserInfo;

public interface LoginService {
	
	UserInfo selectByLoginName(String loginName);

}
