package com.hnust.wx_ticket.Utils;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;
import org.springframework.stereotype.Component;


@Component
public class LogFilter extends Filter<ILoggingEvent> {
    @Override
    public FilterReply decide(ILoggingEvent iLoggingEvent) {
        if(iLoggingEvent.getMarker().contains("操作日志")) return FilterReply.ACCEPT;
        else return FilterReply.DENY;
    }
}
