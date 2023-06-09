package com.hnust.wx_ticket.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hnust.wx_ticket.Utils.R;
import com.hnust.wx_ticket.Utils.StpUserUtil;
import com.hnust.wx_ticket.Vo.TicketDay;
import com.hnust.wx_ticket.Vo.TicketVo;
import com.hnust.wx_ticket.entity.Ticket;
import com.hnust.wx_ticket.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.namespace.QName;


/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author perfect imitator
 * @since 2023-02-22
 */
@SaCheckLogin
@RestController
@RequestMapping("/ticket")
public class TicketController {


    @Autowired
    private TicketService ticketService;

    //获取用于展示到电影票上的信息
    @GetMapping(produces = "application/json")
    public R getTicketInfo(@RequestParam Integer ticketId,
                           @RequestParam Integer filmId) {
        Ticket ticket = ticketService.getById(ticketId);
        int movieId = ticket.getMovieId();
        TicketVo ticketVo = ticketService.getTicket(ticketId, movieId, filmId);
        BeanUtil.copyProperties(ticket, ticketVo);
        System.out.println(ticketVo.toString());
        return R.ok().data("ticketVo", ticketVo);

    }

    //获取用于展示到电影票上的信息
    @GetMapping(value = "/buyday",produces= "application/json")
    public R getTicketInfo(@RequestParam Integer ticketId) {
        Ticket ticket = ticketService.getById(ticketId);
        TicketDay ticketDay = new TicketDay();
        BeanUtil.copyProperties(ticket, ticketDay);
        return R.ok().data("ticketDay", ticketDay);

    }

    //根据日期获取订单数量
    @GetMapping("/count")
    public R getCount(@RequestParam String date){
        QueryWrapper<Ticket>  qw = new QueryWrapper<>();
        qw.eq("buy_day",date);
        long count = ticketService.count(qw);
        return R.ok().data("count",count);
    }

}

