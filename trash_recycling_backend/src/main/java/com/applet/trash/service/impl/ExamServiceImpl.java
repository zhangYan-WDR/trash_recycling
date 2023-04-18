package com.applet.trash.service.impl;

import com.applet.trash.bo.SaveExamDo;
import com.applet.trash.entity.Exam;
import com.applet.trash.mapper.ExamMapper;
import com.applet.trash.service.ExamService;
import com.applet.trash.util.OrderNoUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class ExamServiceImpl extends ServiceImpl<ExamMapper, Exam> implements ExamService {
    @Override
    @Transactional
    public void saveExam(SaveExamDo saveExamDo) {
        //保存考试记录表
        Exam exam = new Exam();
        String id = UUID.randomUUID().toString();
        exam.setId(id);
        exam.setExamRecordNo(OrderNoUtils.getExamRecordNo());
        exam.setScore(saveExamDo.getScore());
        exam.setTestSuccessNum(saveExamDo.getTestSuccessNum());
        exam.setTestWrongNum(saveExamDo.getTestWrongNum());
        //TODO 根据登录的用户设置用户编号
        exam.setUserCode("111");
        baseMapper.insert(exam);
        //TODO 根据用户积分进行添加，并保存到td_user中

        //TODO 形成积分记录表入库
    }
}
