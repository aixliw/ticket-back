package com.hnust.wx_ticket.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hnust.wx_ticket.entity.Movie;
import com.hnust.wx_ticket.mapper.MovieMapper;
import com.hnust.wx_ticket.service.MovieService;
import org.springframework.stereotype.Service;


@Service
public class MovieServiceImpl extends ServiceImpl<MovieMapper, Movie> implements MovieService {


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
}
