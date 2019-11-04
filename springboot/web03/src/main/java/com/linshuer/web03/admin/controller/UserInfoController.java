package com.linshuer.web03.admin.controller;

import com.linshuer.web03.admin.dto.Result;
import com.linshuer.web03.admin.dto.StatusCode;
import com.linshuer.web03.admin.dto.UploadDownImage;
import com.linshuer.web03.admin.entity.User;
import com.linshuer.web03.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@ResponseBody
public class UserInfoController {
    @Autowired
    private UserService userService;

    //获取用户信息显示
    @RequestMapping(value = "/admin/userinfo/findUserInfo")
    public Result showUserInfo(@RequestBody User nowUser){
        String username = nowUser.getUsername();
        User user = userService.findByName(username);
        if (user != null){
            Map map = new HashMap();
            map.put("status", "登录成功");
            map.put("user", user);
            return new Result(StatusCode.SUCCESS,map);

        }else{
            Map map = new HashMap();
            map.put("status", "不存在该用户");
            return new Result(StatusCode.ERROR,map);
        }
    }

    //更新保存用户信息
    @RequestMapping(value = "/admin/userinfo/update")
    public Result updateUserInfo(@RequestBody User nowUser){
        String username = nowUser.getUsername();
        User user = userService.findByName(username);
        if (user != null){
            user.setNickname(nowUser.getNickname());
            user.setEmail(nowUser.getEmail());
            user.setSign(nowUser.getSign());
            userService.update(user);
            Map map = new HashMap();
            map.put("status", "登录成功");
            return new Result(StatusCode.SUCCESS,map);

        }else{
            Map map = new HashMap();
            map.put("status", "不存在该用户");
            return new Result(StatusCode.ERROR,map);
        }
    }

    //修改头像
    @RequestMapping(value = "/admin/userinfo/avatar")
    public Result updateAvatar(@RequestParam("avatar") MultipartFile avatar, HttpServletRequest request){
        if (avatar != null){
            //获取文件在服务器的储存位置
            String path = request.getSession().getServletContext().getRealPath("/avatar");
            //使用自己定义的图片UploadDownImage的静态函数
            return UploadDownImage.uploadDownUser(avatar, "avatar",path);
        }else {
            System.out.println("null");
            return new Result(StatusCode.ERROR,"失败") ;
        }
    }
    //保存头像
    @RequestMapping(value = "/admin/userinfo/saveAvatar")
    public Result saveAvatar(@RequestBody User nowUser){
        String username = nowUser.getUsername();
        User user = userService.findByName(username);
        if (user != null){
            user.setAvatar(nowUser.getAvatar());
            userService.update(user);
            return new Result(StatusCode.SUCCESS);

        }else{
            return new Result(StatusCode.ERROR);
        }
    }

    //修改背景图
    @RequestMapping(value = "/admin/userinfo/background")
    public Result updateBackground(@RequestParam("background") MultipartFile background, HttpServletRequest request){
        if (background != null){
            //获取文件在服务器的储存位置
            String path = request.getSession().getServletContext().getRealPath("/background");
            //使用自己定义的图片UploadDownImage的静态函数
            return UploadDownImage.uploadDownUser(background, "background",path);
        }else {
            System.out.println("null");
            return new Result(StatusCode.ERROR,"失败") ;
        }
    }

    //保存背景图
    @RequestMapping(value = "/admin/userinfo/saveBackground")
    public Result saveBackground(@RequestBody User nowUser){
        String username = nowUser.getUsername();
        User user = userService.findByName(username);
        if (user != null){
            user.setBackground(nowUser.getBackground());
            userService.update(user);
            return new Result(StatusCode.SUCCESS);

        }else{
            return new Result(StatusCode.ERROR);
        }
    }
}
