package com.hnust.wx_ticket.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hnust.wx_ticket.entity.User;
import com.hnust.wx_ticket.mapper.UserMapper;
import com.hnust.wx_ticket.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl  extends ServiceImpl<UserMapper, User> implements UserService {
}
