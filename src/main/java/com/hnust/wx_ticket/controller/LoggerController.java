package com.hnust.wx_ticket.controller;

import com.hnust.wx_ticket.Utils.R;
import com.hnust.wx_ticket.service.LoggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/logger")
public class LoggerController {

    @Autowired
    private LoggerService loggerService;

    @GetMapping(produces = "application/json")
    public R getLogger(@RequestParam String date){
        try{
            List<Map<String,String>> list = loggerService.getLoggerByDay(date);
            return R.ok().data("logs",list);
        }catch (Exception e){
            e.printStackTrace();
            return R.error().message(e.toString());
        }
    }
}
