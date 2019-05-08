package com.github.lifelab.ares.sso.server.service;

import com.github.lifelab.ares.sso.server.core.model.UserInfo;
import com.github.lifelab.ares.sso.server.core.result.ReturnT;

public interface UserService {

    public ReturnT<UserInfo> findUser(String username, String password);

}
