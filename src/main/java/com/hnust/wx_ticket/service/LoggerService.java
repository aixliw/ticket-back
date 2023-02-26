package com.hnust.wx_ticket.service;

import java.util.List;
import java.util.Map;

public interface LoggerService {
    List<Map<String,String>> getLoggerByDay(String date,int index) throws Exception;
}
