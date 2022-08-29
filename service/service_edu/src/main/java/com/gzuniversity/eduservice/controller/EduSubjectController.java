package com.gzuniversity.eduservice.controller;


import com.gzuniversity.commonutils.R;
import com.gzuniversity.eduservice.entity.subject.OneSubject;
import com.gzuniversity.eduservice.service.EduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/eduservice/subject")
@CrossOrigin
public class EduSubjectController {

    @Autowired
    private EduSubjectService subjectService;


    @PostMapping("addSubject")
    public R addSubject(MultipartFile file) {
        subjectService.saveSubject(file,subjectService);
        return R.ok();
    }

    @GetMapping("getAllSubject")
    public R getAllSubject() {
        List<OneSubject> list = subjectService.getAllOneTwoSubject();
        return R.ok().data("list",list);
    }

}

