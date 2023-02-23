package com.hnust.wx_ticket.Vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.hnust.wx_ticket.entity.Movie;
import lombok.Data;

@Data
public class TicketVo extends Movie {

    //座位排号
    private Integer seatp;

    //座位列号
    private Integer seatl;

    /**
     * 电影时长
     */
    private String duration;

    /**
     * 电影类型
     */
    private String type;

    /**
     * 电影名
     */
    private String filmName;
}
