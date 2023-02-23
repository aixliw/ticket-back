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
@TableName("film")
public class Film implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 电影时长
     */
    @TableField("duration")
    private String duration;

    /**
     * 电影类型
     */
    @TableField("type")
    private String type;

    /**
     * 电影名
     */
    @TableField("film_name")
    private String filmName;

    /**
     * 演员
     */
    @TableField("actors")
    private String actors;

    /**
     * 电影评分
     */
    @TableField("mark")
    private String mark;

    /**
     * 电影简介
     */
    @TableField("profile")
    private String profile;

    /**
     * 电影图片
     */
    @TableField("picture")
    private String picture;


}
