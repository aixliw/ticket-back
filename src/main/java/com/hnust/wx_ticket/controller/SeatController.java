package com.hnust.wx_ticket.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.hnust.wx_ticket.Utils.R;
import com.hnust.wx_ticket.Vo.SeatVo;
import com.hnust.wx_ticket.entity.Seat;
import com.hnust.wx_ticket.service.FilmService;
import com.hnust.wx_ticket.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author perfect imitator
 * @since 2023-02-22
 */
@RestController
@RequestMapping("/seat")
public class SeatController {

    @Autowired
    private SeatService seatService;

    @Autowired
    private FilmService filmService;

    //通过座位号获取座位信息
    @GetMapping(produces = "application/json")
    public R getSeat(@RequestParam Integer sp,
                     @RequestParam Integer sl,
                     @RequestParam Integer movieId) {
        QueryWrapper<Seat> qw = new QueryWrapper<>();
        qw.eq("seatp", sp);
        qw.eq("seatl", sl);
        qw.eq("movie_id", movieId);
        Seat seat = seatService.getOne(qw);
        if (seat == null) {
            seat.setState(1);
        }
        SeatVo seatVo = new SeatVo();
        BeanUtil.copyProperties(seat , seatVo);
        return  R.ok().data("seat",seatVo);
    }

/*    //根据ticketId查询座位的排，列等信息
    @GetMapping("/info")
    public R getSeatInfo(@RequestParam Integer   ticketId){
        QueryWrapper<Seat> qw = new QueryWrapper<>();
        qw.eq("ticket_id",ticketId);
        Seat seat = seatService.getOne(qw);
        return R.ok().data("seat",seat);
    }*/

    //座位状态的添加和修改（同一个方法同时具有两个功能）
    @PutMapping(produces = "application/json")
    public R editSeatState(@RequestBody Seat seat){
       boolean res =  seatService.saveOrUpdate(seat);
        if(res){
            return R.ok().message("修改成功");
        }else{
            return R.error().message("修改失败");
        }
    }


}

