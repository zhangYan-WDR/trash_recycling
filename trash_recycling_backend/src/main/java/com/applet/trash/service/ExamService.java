package com.applet.trash.service;

import com.applet.trash.Do.SaveExamDo;
import com.applet.trash.entity.Exam;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface ExamService extends IService<Exam> {
    void saveExam(List<SaveExamDo> saveExamDos);
}
