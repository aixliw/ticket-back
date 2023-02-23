package com.hnust.wx_ticket.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hnust.wx_ticket.entity.Movie;

public interface MovieService extends IService<Movie> {

    Integer addMovie(Movie movie);

    Integer editMovie(Movie movie);
}

