package com.hnust.wx_ticket.service.impl;

import com.hnust.wx_ticket.service.LoggerService;
import org.springframework.stereotype.Service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class LoggerServiceImpl implements LoggerService {
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public List<Map<String, String>> getLoggerByDay(String date, int index) throws Exception {
        String content = "";
        String path;
        String today = simpleDateFormat.format(new Date());
        if (today.equals(date)) path = "./logs/filmLog/today.log";
        else path = String.format("./logs/filmLog/%s.%d.log", date, index);
        File file = new File(path);
        if (!file.exists()) {
            return null;
        }
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
        byte[] buffer = new byte[1024];
        int flag = 0;
        while ((flag = bufferedInputStream.read(buffer)) != -1) {
            content = new String(buffer, 0, flag);
        }
        bufferedInputStream.close();
        String[] strings = content.split("\n");
        List<Map<String, String>> list = new ArrayList<>();
        for (String s : strings) {
            Map<String, String> mp = new HashMap<>();
            String[] arr = s.split("`#`");
            if (arr.length < 3) {
                continue;
            }
            mp.put("time", arr[0]);
            mp.put("title", arr[1]);
            mp.put("content", arr[2]);
            list.add(mp);
        }
        return list;
    }
}
