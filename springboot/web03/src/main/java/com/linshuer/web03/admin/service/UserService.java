package com.linshuer.web03.admin.service;

import com.linshuer.web03.admin.entity.User;

public interface UserService extends BaseService<User>{
    User findByName(String username);
}
