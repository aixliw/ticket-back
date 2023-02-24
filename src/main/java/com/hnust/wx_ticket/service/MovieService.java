package com.hnust.wx_ticket.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hnust.wx_ticket.Vo.MovieVo;
import com.hnust.wx_ticket.entity.Movie;

import java.util.List;

public interface MovieService extends IService<Movie> {

    Integer addMovie(Movie movie);

    Integer editMovie(Movie movie);

    List<MovieVo> getMovieAndFilm(Integer filmId);
}

