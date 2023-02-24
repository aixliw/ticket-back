package com.hnust.wx_ticket.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hnust.wx_ticket.Vo.MovieVo;
import com.hnust.wx_ticket.entity.Movie;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface MovieMapper extends BaseMapper<Movie> {

   // List<MovieVo> getMovieAndFilm(Integer filmId);

}
