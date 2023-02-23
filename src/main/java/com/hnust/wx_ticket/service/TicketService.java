package com.hnust.wx_ticket.service;

import com.hnust.wx_ticket.Utils.R;
import com.hnust.wx_ticket.Vo.TicketVo;
import com.hnust.wx_ticket.entity.Ticket;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author perfect imitator
 * @since 2023-02-22
 */
public interface TicketService extends IService<Ticket> {

    public TicketVo getTicket(Integer ticketId, Integer movieId, Integer filmId);

}
