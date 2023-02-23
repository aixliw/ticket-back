package com.hnust.wx_ticket.controller;
import com.hnust.wx_ticket.Utils.R;
import com.hnust.wx_ticket.Vo.TicketVo;
import com.hnust.wx_ticket.entity.Ticket;
import com.hnust.wx_ticket.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author perfect imitator
 * @since 2023-02-22
 */
@RestController
@RequestMapping("/ticket")
public class TicketController {


    @Autowired
    private TicketService ticketService;

    //获取用于展示到电影票上的信息
    @GetMapping(produces = "application/json")
    public R getTicketInfo(@RequestParam Integer ticketId,
                           @RequestParam Integer filmId){
        Ticket ticket = ticketService.getById(ticketId);
        int movieId = ticket.getMovieId();
        TicketVo ticketVo = ticketService.getTicket(ticketId,movieId,filmId);
        System.out.println(ticketVo.toString());
        return R.ok().data("ticketVo",ticketVo);
    }

}

