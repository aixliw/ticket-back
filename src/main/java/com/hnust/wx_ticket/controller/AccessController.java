package com.hnust.wx_ticket.controller;


import com.hnust.wx_ticket.Utils.BuildMessage;
import com.hnust.wx_ticket.Utils.GetAccessToken;
import com.hnust.wx_ticket.Utils.R;
import com.hnust.wx_ticket.entity.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.lang.UsesSunHttpServer;
import org.springframework.web.bind.annotation.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;

@RestController
@RequestMapping("/access")
public class AccessController {

    private final String TOKEN = "ax123";

    @Autowired
    private BuildMessage buildMessage;

    @Autowired
    private GetAccessToken getAccessToken;

    @Autowired
    private RedisTemplate redisTemplate;

    //接入公众号
    @GetMapping
    public String testToken( Param param) {
        System.out.println("开始校验签名");
        String signature = param.getSignature();
        String timestamp = param.getTimestamp();
        String nonce = param.getNonce();
        String echostr = param.getEchostr();
        //排序
        String sortString = sort(TOKEN, timestamp, nonce);
        //加密
        String mySignature = sha1(sortString);
        //校验签名
        if (mySignature != null && mySignature != "" && mySignature.equals(signature)) {
            System.out.println("签名校验通过。");
            //如果检验成功输出echostr，微信服务器接收到此输出，才会确认检验完成。
            //response.getWriter().println(echostr);
            return echostr;
        } else {
            System.out.println("签名校验失败.");
        }
        return "";
    }

    //jsSDK
    @GetMapping("/jsApi")
    public R getJsApiTicket(String url) {

        String jsapi_ticket = (String) redisTemplate.opsForValue().get("jsapi_ticket");

        // System.out.println("jsapi_ticket:" + jsapi_ticket);
        if (jsapi_ticket == null || jsapi_ticket == "") {
            // System.out.println("redis没有js需要重新生成");
            jsapi_ticket = getAccessToken.getJsapi_ticket();
            //System.out.println("jsapi_ticket:" + jsapi_ticket);
        }
        String timestamp = "" + System.currentTimeMillis();
        String noncestr = randomAlphanumeric(12);
        Map<String, String> map = new HashMap<>();
        map.put("jsapi_ticket", jsapi_ticket);
        map.put("timestamp", timestamp);
        map.put("noncestr", noncestr);
        map.put("url", url);
        //排序
        String sortString = sort(map);
        //加密
        // System.out.println(sortString);

        String mySignature = sha1(sortString);
        // System.out.println(mySignature);
        Map<String,String> res = new HashMap<>();
        res.put("timestamp", timestamp);
        res.put("noncestr", noncestr);
        res.put("mySignature", mySignature);
        return R.ok().data("jsapi",res);
    }


    //接收xml
    @PostMapping(produces = "application/xml")
    public String getXml(@RequestBody Map<String, String> xmlContent) {

        for (String key : xmlContent.keySet()) {
            System.out.println();
            System.out.println("private String " + key + ";");
        }
        //System.out.println(xmlContent.toString());
        String ans = buildMessage.buildTextMessage(xmlContent, "hello");
        return ans;
    }


    public String sort(String token, String timestamp, String nonce) {
        String[] strArray = {token, timestamp, nonce};
        Arrays.sort(strArray);
        StringBuilder sb = new StringBuilder();
        for (String str : strArray) {
            sb.append(str);
        }

        return sb.toString();
    }

    //字符串对参数排序
    public String sort(Map<String, String> map) {
        String[] strArray = new String[4];
        int i = 0;
        for (String key : map.keySet()) {
            strArray[i++] = key;
        }
        Arrays.sort(strArray);
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < 4; j++) {
            sb.append(strArray[j] + "=" + map.get(strArray[j]) + "&");
        }

        return sb.toString();
    }

    public String sha1(String str) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.update(str.getBytes());
            byte messageDigest[] = digest.digest();
            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            // 字节数组转换为 十六进制 数
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

}
