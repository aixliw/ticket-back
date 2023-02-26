package com.hnust.wx_ticket.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class User {
    //主键id
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    //用户名
    @TableField("user_name")
    private String userName;
    //用户密码
    @TableField("user_psw")
    private String userPsw;
    //昵称
    @TableField("nick_name")
    private String nickName;
    //头像
    @TableField("avatar")
    private String avatar;
    //禁用
    @TableField("forbidden")
    private Integer forbidden;
}
