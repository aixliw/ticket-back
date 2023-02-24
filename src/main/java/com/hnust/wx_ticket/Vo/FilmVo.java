package com.hnust.wx_ticket.Vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class FilmVo {
   // private Integer id;

    /**
     * 电影时长
     */
   // @TableField("duration")
    private String duration;

    /**
     * 电影类型
     */
 //   @TableField("type")
    private String type;

    /**
     * 电影名
     */
  //  @TableField("film_name")
    private String filmName;

    /**
     * 演员
     */
  //  @TableField("actors")
    private String actors;

    /**
     * 电影评分
     */
   // @TableField("mark")
    private String mark;

    /**
     * 电影简介
     */
  //  @TableField("profile")
    private String profile;

    /**
     * 电影图片
     */
   // @TableField("picture")
    private String picture;

}
