package com.hnust.wx_ticket.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hnust.wx_ticket.Utils.R;
import com.hnust.wx_ticket.Utils.StpUserUtil;
import com.hnust.wx_ticket.entity.User;
import com.hnust.wx_ticket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    public static String adminLoginId = "";
    public static String userLoginId = "";

    @GetMapping(value = "/login", produces = "application/json")
    public R getLogin(@RequestParam String userName,
                      @RequestParam String userPsw) {

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (userName != null && userName != "" && userPsw != null && userPsw != "") {
            queryWrapper.eq("user_name", userName);
            String psw = userService.getOne(queryWrapper).getUserPsw();
            if (psw.equals(userPsw)) {

               if(userName.equals("admin")){
                   adminLoginId = System.currentTimeMillis()+"";
                   StpUtil.login(adminLoginId);
               }else {
                   userLoginId = System.currentTimeMillis()+"";
                   StpUtil.login(userLoginId);
               }
                return R.ok().message("登录成功！");
            } else {
                return R.error().message("用户名或密码错误！");
            }
        } else {
            return R.error().message("用户名或密码不能为空！");
        }


    }

    @SaCheckLogin
    @GetMapping(value = "/logout", produces = "application/json")
    public R logout() {
        StpUtil.logout();
        return R.ok().message("登出成功");
    }

    @SaCheckRole("admin")
    @PostMapping( value = "/addUser", produces = "application/json")
    public R addUser(@RequestBody  User user){
        System.out.println(StpUtil.getRoleList());
        boolean save = userService.save(user);
        if(save){
            return R.ok().message("添加成功");
        }
        return R.error().message("添加失败");
    }

    @SaCheckRole("admin")
    @PutMapping(value = "/editUser",produces = "application/json")
    public R editUser(@RequestBody User user){
        boolean update = userService.updateById(user);
        if(update){
            return R.ok().message("修改成功");
        }
        return R.error().message("修改失败");
    }




}
