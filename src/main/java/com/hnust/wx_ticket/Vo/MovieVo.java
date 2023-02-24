package com.hnust.wx_ticket.Vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.hnust.wx_ticket.entity.Movie;
import lombok.Data;

@Data
public class MovieVo  extends Movie {

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
    /**
     * 演员
     */
    private String actors;

    /**
     * 电影评分
     */

    private String mark;

    /**
     * 电影简介
     */

    private String profile;

    /**
     * 电影图片
     */

    private String picture;

}
