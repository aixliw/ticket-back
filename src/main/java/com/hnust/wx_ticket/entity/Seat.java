package com.hnust.wx_ticket.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author perfect imitator
 * @since 2023-02-22
 */
@Getter
@Setter
@TableName("seat")
public class Seat implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * seat表主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 座位排号
     */
    @TableField("seatp")
    private Integer seatp;

    /**
     * 座位列号
     */
    @TableField("seatl")
    private Integer seatl;

    /**
     * 座位状态
     */
    @TableField("state")
    private Integer state;


    /**
     *  movie表的逻辑外键
    * */
    @TableField("movie_id")
    private String movieId;

    /**
     *  ticket表的逻辑外键
     * */
    @TableField("ticket_id")
    private String ticketId;
}
