package com.shijiu.controller;

import com.shijiu.utils.CosUploadUtils;
import com.shijiu.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("upload")
public class UploadController {

    @Autowired
    private CosUploadUtils cosUploadUtils;

    @PostMapping
    public Result upload(MultipartFile file) {
//        System.out.println(file.getOriginalFilename());
//        System.out.println(CosUploadUtils.url + file.getOriginalFilename());
        boolean upload = cosUploadUtils.upload(file);
        if (upload){
            return Result.success(CosUploadUtils.url + file.getOriginalFilename());
        }
        return Result.fail(20001,"上传失败");
    }
}
