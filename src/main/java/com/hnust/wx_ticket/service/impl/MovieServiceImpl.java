package com.hnust.wx_ticket.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hnust.wx_ticket.Utils.R;
import com.hnust.wx_ticket.Vo.MovieVo;
import com.hnust.wx_ticket.entity.Film;
import com.hnust.wx_ticket.entity.Movie;
import com.hnust.wx_ticket.mapper.MovieMapper;
import com.hnust.wx_ticket.service.FilmService;
import com.hnust.wx_ticket.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class MovieServiceImpl extends ServiceImpl<MovieMapper, Movie> implements MovieService {

    @Autowired
    private MovieMapper movieMapper;

    @Autowired
    private FilmService filmService;

    public Integer addMovie(Movie movie) {
        //根据日期，播放厅，电影起始时间判断是否冲突
        String begin = movie.getBegin();
        String end = movie.getEnd();
        Integer room = movie.getRoom();
        Integer movieId = movie.getId();
        String date = movie.getToday();
        QueryWrapper<Movie> qw1 = new QueryWrapper<>();
        qw1.le("begin", end)
                .ge("end", end)
                .eq("room", room)
                .eq("today", date);

        Movie mv1 = getBaseMapper().selectOne(qw1);
        QueryWrapper<Movie> qw2 = new QueryWrapper<>();
        qw2.le("begin", begin)
                .ge("end", begin)
                .eq("room", room)
                .eq("today", date);

        Movie mv2 = getBaseMapper().selectOne(qw2);
        int state = 0;
        if (mv1 != null || mv2 != null) {
            state = 1;
        } else {
            boolean res = save(movie);

            if (res) {
                state = 2;
            } else {
                state = 3;
            }
        }
        return state;
    }

    @Override
    public Integer editMovie(Movie movie) {
        //根据日期，播放厅，电影起始时间判断是否冲突
        String begin = movie.getBegin();
        String end = movie.getEnd();
        Integer room = movie.getRoom();
        Integer movieId = movie.getId();
        String date = movie.getToday();
        QueryWrapper<Movie> qw1 = new QueryWrapper<>();
        qw1.le("begin", end)
                .ge("end", end)
                .eq("room", room)
                .eq("today", date)
                .ne("id", movieId);
        Movie mv1 = getBaseMapper().selectOne(qw1);
        QueryWrapper<Movie> qw2 = new QueryWrapper<>();
        qw2.le("begin", begin)
                .ge("end", begin)
                .eq("room", room)
                .eq("today", date)
                .ne("id", movieId);
        Movie mv2 = getBaseMapper().selectOne(qw2);
        int state = 0;
        if (mv1 != null || mv2 != null) {
            state = 1;
            System.out.println(mv2.toString());
        } else {
            boolean res = updateById(movie);
            if (res) {
                state = 2;
            } else {
                state = 3;
            }
        }
        return state;
    }

    @Override
    public Map<String, Object> getMovieAndFilm(Integer filmId) {
        QueryWrapper<Movie> qw1 = new QueryWrapper<>();
        qw1.eq("film_id", filmId);
        List<Movie> movies = list(qw1);
        QueryWrapper<Movie> qw2 = new QueryWrapper<>();
        qw2.eq("film_id", filmId);
        Film film = filmService.getById(filmId);
        Map<String, Object> mp = new HashMap<>();
        mp.put("film", film);
        mp.put("movies", movies);
        return mp;
    }

    @Override
    public List<MovieVo> getMovies(Integer filmId) {
        List<MovieVo> movieVos = new ArrayList<>();
        QueryWrapper<Movie> qw = new QueryWrapper<>();
        qw.eq("film_id", filmId);
        List<Movie> movies = list(qw);
        QueryWrapper<Movie> qw2 = new QueryWrapper<>();
        qw2.eq("film_id", filmId);
        Film film = filmService.getById(filmId);
        for(Movie movie : movies){
            MovieVo mv = new MovieVo();
            BeanUtil.copyProperties(movie, mv);
            BeanUtil.copyProperties(film, mv);
            movieVos.add(mv);
        }
        return movieVos;
    }


    //获取总金额
    @Override
    public Double getTotalMoney(String date) {
      return  movieMapper.getTotalMoney(date);
    }


    //根据日期获取movieVo
    public List<MovieVo> getMovieVo(String date){
        List<MovieVo> movieVos = new ArrayList<>();
        QueryWrapper<Movie> qw = new QueryWrapper<>();
        qw.eq("today", date);
        List<Movie> movies = list(qw);
        if(movies != null && movies.size() != 0){
            for(Movie movie : movies){
                Integer filmId = movie.getFilmId();
                System.out.println(filmId);
                Film film = filmService.getById(filmId);
                MovieVo mv = new MovieVo();
                BeanUtil.copyProperties(movie, mv);
                BeanUtil.copyProperties(film, mv);
                movieVos.add(mv);
            }
            return movieVos;
        }else {
            return null;
        }

    }




}
