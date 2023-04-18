package com.applet.trash.service;

import com.applet.trash.bo.SaveExamDo;
import com.applet.trash.entity.Exam;
import com.baomidou.mybatisplus.extension.service.IService;

public interface ExamService extends IService<Exam> {
    void saveExam(SaveExamDo saveExamDo);
}
