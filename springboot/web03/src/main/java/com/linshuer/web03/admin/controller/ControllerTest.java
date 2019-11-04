package com.linshuer.web03.admin.controller;

import com.linshuer.web03.admin.entity.User;
import com.linshuer.web03.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ControllerTest {
    @Autowired
    private UserService userService;

    @RequestMapping("/findAll")
    public List<User> getUserAll(){
        return userService.findAll();
    }
}
