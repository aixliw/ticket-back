package com.hnust.wx_ticket.Utils;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class GetAccessToken {

    @Autowired
    private RedisTemplate redisTemplate;

    private RestTemplate restTemplate = new RestTemplate();



    public String getAccessToken() {
        // 带参GET url请求

      /*  Map<String,String> map = restTemplate.getForObject("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx2ca89ba483a08f91&secret=781153e5932048f8dc17675e077d76ae", String.class);
        System.out.println(map.get("access_token"));
        System.out.println(map.get("expires_in"));*/

        Map<String,String> mp = restTemplate.getForObject("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx2ca89ba483a08f91&secret=781153e5932048f8dc17675e077d76ae", Map.class);
        String ac = mp.get("access_token");
        redisTemplate.opsForValue().set("accessToken", ac, 7000, TimeUnit.SECONDS);
        return ac;

    }

    public String getJsapi_ticket() {

        String acToken = (String) redisTemplate.opsForValue().get("accessToken");
        System.out.println(acToken);
        // System.out.println(acToken);
        if (acToken == null || acToken == "") {
            System.out.println("redis没有");
            acToken = this.getAccessToken();
            System.out.println("ac:"+acToken);
        }
        Map<String,String> map = restTemplate.getForObject("https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + acToken + "&type=jsapi", Map.class);
        String js = map.get("ticket");
        System.out.println(js);
        redisTemplate.opsForValue().set("jsapi_ticket", js, 7000, TimeUnit.SECONDS);
        System.out.println("js:"+js);
        return js;

    }

}
