package com.hnust.wx_ticket.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("movie")
public class Movie {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * film表的逻辑外键
     */
    @TableField("film_id")
    private Integer filmId;



    /**
     * 几号放映厅
     */
    @TableField("room")
    private Integer room;

    /**
     * 放映厅简介，如vip，max，3D巨幕，普通等等
     */
    @TableField("room_profile")
    private String roomProfile;

    /**
     * 播放类型，如国语3D，双语2D等
     */
    @TableField("play_type")
    private String playType;

    /**
     * 影城名
     */
    @TableField("site")
    private String site;

    /**
     * 影城地点
     */
    @TableField("place")
    private String place;

    /**
     * 票价
     */
    @TableField("price")
    private String price;

    /**
     * 电影开始时间
     */
    @TableField("begin")
    private String begin;

    /**
     * 电影结束时间
     */
    @TableField("end")
    private String end;

    /**
     * 当天日期
     */
    @TableField("today")
    private String today;

}
