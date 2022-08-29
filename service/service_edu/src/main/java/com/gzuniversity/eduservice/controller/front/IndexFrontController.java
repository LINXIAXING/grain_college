package com.gzuniversity.eduservice.controller.front;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gzuniversity.commonutils.R;
import com.gzuniversity.eduservice.entity.EduCourse;
import com.gzuniversity.eduservice.entity.EduTeacher;
import com.gzuniversity.eduservice.service.EduCourseService;
import com.gzuniversity.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/eduservice/indexfront")
@CrossOrigin
public class IndexFrontController {

    @Autowired
    private EduCourseService courseService;

    @Autowired
    private EduTeacherService teacherService;

    @GetMapping("index")
    public R index() {
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        wrapper.last("limit 6");
        List<EduCourse> eduList = courseService.list(wrapper);

        QueryWrapper<EduTeacher> wrapperTeacher = new QueryWrapper<>();
        wrapperTeacher.orderByDesc("id");
        wrapperTeacher.last("limit 3");
        List<EduTeacher> teacherList = teacherService.list(wrapperTeacher);

        return R.ok().data("eduList",eduList).data("teacherList",teacherList);
    }

}
