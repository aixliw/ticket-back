package com.hnust.wx_ticket.Utils;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

//构建回复信息
@Component
public class BuildMessage {

    public static String buildTextMessage(Map<String, String> map, String content) {
        //发送方账号
        String fromUserName = map.get("FromUserName");

        //开发者账号
        String toUserName = map.get("ToUserName");

        return String.format(
                "<xml>" +
                        "<ToUserName><![CDATA[%s]]></ToUserName>" +
                        "<FromUserName><![CDATA[%s]]></FromUserName>" +
                        "<CreateTime>%s</CreateTime>" +
                        "<MsgType><![CDATA[text]]></MsgType>" +
                        "<Content><![CDATA[%s]]></Content>" + "</xml>",
                fromUserName, toUserName, getUtcTime(), content);
    }

    public static String buildPictureMessage(Map<String, String> map, String content) {
        //发送方账号
        String fromUserName = map.get("FromUserName");

        //开发者账号
        String toUserName = map.get("ToUserName");

        return String.format(
                "<xml>" +
                        "<ToUserName><![CDATA[%s]]></ToUserName>" +
                        "<FromUserName><![CDATA[%s]]></FromUserName>" +
                        "<CreateTime>%s</CreateTime>" +
                        "<MsgType><![CDATA[text]]></MsgType>" +
                        "<Content><![CDATA[%s]]></Content>" + "</xml>",
                fromUserName, toUserName, getUtcTime(), content);
    }

    private static String getUtcTime() {
        Date dt = new Date();// 如果不需要格式,可直接用dt,dt就是当前系统时间
        DateFormat df = new SimpleDateFormat("yyyyMMddhhmm");// 设置显示格式
        String nowTime = df.format(dt);
        long dd = (long) 0;
        try {
            dd = df.parse(nowTime).getTime();
        } catch (Exception e) {

        }
        return String.valueOf(dd);
    }
}
