package com.hnust.wx_ticket.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.hnust.wx_ticket.Utils.R;
import com.hnust.wx_ticket.Utils.StpUserUtil;
import com.hnust.wx_ticket.service.UploadService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@SaCheckLogin
@Slf4j
@RestController
@RequestMapping("/upload")
public class UploadController {

   @Autowired
    private  UploadService uploadService;
   private final Marker marker = MarkerFactory.getMarker("操作日志");

    @PostMapping
    public R upload(@RequestBody MultipartFile picture){
       String url = uploadService.upload(picture);
       if(url != null && url != ""){
           log.info(marker,String.format("上传图片成功`#`%s",url));
           return R.ok().data("url",url);
       }else {
           log.info(marker,String.format("上传图片失败`#`%s",picture.toString()));
           return R.error().message("上传失败");
       }
    }
}
