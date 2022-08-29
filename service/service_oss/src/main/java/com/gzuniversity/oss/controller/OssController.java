package com.gzuniversity.oss.controller;

import com.gzuniversity.oss.service.OssService;
import com.gzuniversity.commonutils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/eduoss/fileoss")
@CrossOrigin
public class OssController {
    @Autowired
    private OssService ossService;
    //上传头像
    @PostMapping
    public R uploadOssFile(MultipartFile file) {
        //获取上传文件，MultipartFile
        //返回上传oss的url
        String url = ossService.uploadFileAvatar(file);
        return R.ok().data("url", url);
    }
}