package com.hnust.wx_ticket.service.impl;

import com.hnust.wx_ticket.service.LoggerService;
import org.springframework.stereotype.Service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LoggerServiceImpl implements LoggerService {
    @Override
    public List<Map<String, String>> getLoggerByDay(String date) throws Exception {
        String content = "";
        File file = new File(String.format("./logs/filmLog/%s.log",date));
        if(!file.exists()){
            return null;
        }
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
        byte[] buffer = new byte[1024];
        int flag = 0;
        while((flag=bufferedInputStream.read(buffer))!=-1){
            content =new String(buffer,0,flag);
        }
        bufferedInputStream.close();
        String[] strings = content.split("\n");
        List<Map<String,String>> list = new ArrayList<>();
        for(String s : strings){
            Map<String,String> mp = new HashMap<>();
            String[] arr = s.split("`#`");
            mp.put("time",arr[0]);
            mp.put("title",arr[1]);
            mp.put("content",arr[2]);
            list.add(mp);
        }
        return list;
    }
}
