package com.hnust.wx_ticket.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hnust.wx_ticket.Utils.R;
import com.hnust.wx_ticket.Vo.MovieVo;
import com.hnust.wx_ticket.entity.Film;
import com.hnust.wx_ticket.entity.Movie;
import com.hnust.wx_ticket.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;


    //根据id查询本场电影的信息
    @GetMapping(produces = "application/json")
    public R getMovieById(@RequestParam Integer movieId) {
        Movie movie = movieService.getById(movieId);
        return R.ok().data("movie", movie);
    }

    //根据日期查询当天的电影日程
    @GetMapping(value = "/day",produces = "application/json")
    public R getMovieByDay(@RequestParam String date) {

        QueryWrapper<Movie> qw = new QueryWrapper<>();
        qw.eq("today", date);
        List<Movie> movies = movieService.list(qw);
        return R.ok().data("movie", movies);
    }

    @PostMapping(produces = "application/json")
    public R addMovie(@RequestBody Movie movie) {
        //根据日期，播放厅，电影起始时间判断是否冲突
        Integer state = movieService.addMovie(movie);
        if (state == 1) {
            return R.error().message("此时间此播放厅已被其它电影占用");
        } else if (state == 2) {
            return R.ok().message("电影日程添加成功");
        } else {
            return R.error().message("电影日程添加出错");
        }
    }

    @PostMapping(value = "/edit",produces = "application/json")
    public R editMovie(@RequestBody Movie movie) {
        //根据日期，播放厅，电影起始时间判断是否冲突
        Integer state = movieService.editMovie(movie);
        if (state == 1) {
            return R.error().message("此时间此播放厅已被其它电影占用");
        } else if (state == 2) {
            return R.ok().message("电影日程修改成功");
        } else {
            return R.error().message("电影日程修改出错");
        }
    }

    @GetMapping(value = "/filmid",produces = "application/json")
    public R getMovieByFilmId(@RequestParam Integer filmId) {

        Map<String, Object> mp = movieService.getMovieAndFilm(filmId);
        return R.ok().data(mp);
    }

    @GetMapping(value="/get",produces = "application/json")
    public R getMovies(@RequestBody Integer filmId){
        List<MovieVo> list = movieService.getMovies(filmId);
        return R.ok().data("moviesVos",list);
    }
}
