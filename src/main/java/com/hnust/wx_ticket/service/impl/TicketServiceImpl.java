package com.hnust.wx_ticket.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hnust.wx_ticket.Utils.R;
import com.hnust.wx_ticket.Vo.TicketVo;
import com.hnust.wx_ticket.entity.Film;
import com.hnust.wx_ticket.entity.Movie;
import com.hnust.wx_ticket.entity.Seat;
import com.hnust.wx_ticket.entity.Ticket;
import com.hnust.wx_ticket.mapper.TicketMapper;
import com.hnust.wx_ticket.service.FilmService;
import com.hnust.wx_ticket.service.MovieService;
import com.hnust.wx_ticket.service.SeatService;
import com.hnust.wx_ticket.service.TicketService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author perfect imitator
 * @since 2023-02-22
 */
@Service
public class TicketServiceImpl extends ServiceImpl<TicketMapper, Ticket> implements TicketService {

    @Autowired
    private FilmService filmService;

    @Autowired
    private MovieService movieService;

    @Autowired
    private SeatService seatService;

    public TicketVo getTicket(Integer ticketId,Integer movieId,Integer filmId) {
        TicketVo ticketVo = new TicketVo();
        Movie movie = movieService.getById(movieId);
        BeanUtil.copyProperties(movie,ticketVo);
        Film film = filmService.getById(filmId);
        BeanUtil.copyProperties(film,ticketVo);
        QueryWrapper<Seat> qw = new QueryWrapper<>();
        qw.eq("ticket_id",ticketId);
        Seat seat = seatService.getOne(qw);
        BeanUtil.copyProperties(seat,ticketVo);


        return ticketVo;

    }
}
