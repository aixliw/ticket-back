package com.hnust.wx_ticket.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hnust.wx_ticket.Utils.R;
import com.hnust.wx_ticket.entity.Film;
import com.hnust.wx_ticket.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author aixiong
 * @since 2023-02-22
 */
@RestController
@RequestMapping("/film")
public class FilmController {

    @Autowired
    private FilmService filmService;

    //分页查询
    @GetMapping(produces = "application/json")
    public R getFilmByName(@RequestParam long current,
                           @RequestParam long limit,
                           @RequestParam String filmName){
        //创建page对象
        Page<Film> pageForm = new Page<>(current,limit);
        //构建条件
        QueryWrapper<Film> wrapper = new QueryWrapper<>();
        //判断条件值是否为空，如果不为空拼接条件
        if(!StringUtils.isEmpty(filmName)) {
            //构建条件
            wrapper.like("film_name",filmName);
        }
        filmService.page(pageForm,wrapper);
        long total = pageForm.getTotal();//总记录数
        List<Film> records = pageForm.getRecords(); //数据list集合
        return R.ok().data("total",total).data("rows",records);
    }

    //向电影库添加电影
    @PostMapping(produces = "application/json")
    public R addFilm(@RequestBody Film film){
        boolean res = filmService.save(film);
        if(res){
            return R.ok().message("添加成功");
        }
        else{
            return R.error().message("添加出错");
        }
    }

    //根据id删除电影
    @DeleteMapping(value = "{id}",produces = "application/json")
    public R deleteFilm(@PathVariable Integer id){
        boolean res = filmService.removeById(id);
        if(res){
            return R.ok().message("删除成功");
        }else{
            return R.error().message("删除失败");
        }
    }

    //修改电影
    @PutMapping(produces = "application/json")
    public R editFilm(@RequestParam Integer filmId,
                      @RequestBody Film film){
        UpdateWrapper<Film> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", filmId);
        boolean res = filmService.update(film,updateWrapper);
        if(res){
            return R.ok().message("修改成功");
        }else{
            return R.error().message("修改失败");
        }
    }

}

