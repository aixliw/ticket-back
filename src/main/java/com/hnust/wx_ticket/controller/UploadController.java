package com.hnust.wx_ticket.controller;

import com.hnust.wx_ticket.Utils.R;
import com.hnust.wx_ticket.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/upload")
public class UploadController {

   @Autowired
    private  UploadService uploadService;

    @PostMapping
    public R upload(@RequestBody MultipartFile picture){
       String url = uploadService.upload(picture);
       if(url != null && url != ""){
           return R.ok().data("url",url);
       }else {
           return R.error().message("上传失败");
       }
    }
}
