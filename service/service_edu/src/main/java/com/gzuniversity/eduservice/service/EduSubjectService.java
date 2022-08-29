package com.gzuniversity.eduservice.service;

import com.gzuniversity.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gzuniversity.eduservice.entity.subject.OneSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author zzx
 * @since 2022-08-10
 */
public interface EduSubjectService extends IService<EduSubject> {
    void saveSubject(MultipartFile file, EduSubjectService subjectService);
    List<OneSubject> getAllOneTwoSubject();
}
