package com.linshuer.web03.admin.controller;

import com.linshuer.web03.admin.dto.Result;
import com.linshuer.web03.admin.dto.StatusCode;
import com.linshuer.web03.admin.entity.User;
import com.linshuer.web03.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.HashMap;
import java.util.Map;

@RestController
@ResponseBody
public class LoginController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/admin/login")
    public Result login(@RequestBody User loginUser){
            String username = loginUser.getUsername();
            String password = loginUser.getPassword();
            User user = userService.findByName(username);
        if (user != null){
            if (user.getPassword().equals(password)){
                ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
                attributes.getRequest().getSession().setAttribute("user", user);
                Map map = new HashMap();
                map.put("status", "登录成功");
                map.put("userName", user.getUsername());
                System.out.println(map.get("status"));
                System.out.println("你好");
                return new Result(StatusCode.SUCCESS,map);
            }else{
                Map map = new HashMap();
                map.put("status", "密码或账号错误！");
                return new Result(StatusCode.ERROR,map);
            }
        }else{
            Map map = new HashMap();
            map.put("status", "不存在该用户");
            return new Result(StatusCode.ERROR,map);
        }
    }
}
