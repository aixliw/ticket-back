package com.hnust.wx_ticket.controller;

import com.hnust.wx_ticket.Utils.GetAccessToken;
import com.hnust.wx_ticket.Utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author lixl
 * @description
 * @date 2022/1/28
 */
@RestController
@RequestMapping("/token")
public class TokenController {
    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    GetAccessToken getAccessToken;

    //获取access_Token
    @GetMapping(produces = "application/json")
    public R getToken() {

        String acToken = (String) redisTemplate.opsForValue().get("accessToken");

        System.out.println(acToken);
        if(acToken == null || acToken == ""){
            System.out.println("redis没有");
            acToken = getAccessToken.getAccessToken();
            System.out.println("acToken:"+acToken);

        }
        return R.ok().data("access_token",acToken);
    }


}
