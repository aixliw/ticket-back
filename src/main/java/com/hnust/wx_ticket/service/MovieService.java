package com.hnust.wx_ticket.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hnust.wx_ticket.Vo.MovieVo;
import com.hnust.wx_ticket.entity.Movie;

import java.util.List;
import java.util.Map;

public interface MovieService extends IService<Movie> {

    Integer addMovie(Movie movie);

    Integer editMovie(Movie movie);

    Map<String, Object> getMovieAndFilm(Integer filmId);

    List<MovieVo> getMovies(Integer filmId);

    Double getTotalMoney(String date);

    List<MovieVo> getMovieVo(String date);
}

