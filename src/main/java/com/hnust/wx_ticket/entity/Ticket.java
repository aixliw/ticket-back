package com.hnust.wx_ticket.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import lombok.Data;

/**
 * <p>
 * 
 * </p>
 *
 * @author perfect imitator
 * @since 2023-02-22
 */
@Data
@TableName("ticket")
public class Ticket implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * seat表主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 座位排号
     */
    @TableField("movie_id")
    private Integer movieId;

    /**
     * 座位列号
     */
    @TableField("seat_id")
    private Integer seatId;



}
