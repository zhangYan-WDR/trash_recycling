package com.applet.trash.service.impl;

import com.applet.trash.Do.SaveExamDo;
import com.applet.trash.entity.CategoryDetail;
import com.applet.trash.entity.Exam;
import com.applet.trash.interceptor.UserInfoHolder;
import com.applet.trash.mapper.ExamMapper;
import com.applet.trash.service.*;
import com.applet.trash.util.OrderNoUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@Service
public class ExamServiceImpl extends ServiceImpl<ExamMapper, Exam> implements ExamService {

    @Resource
    private CategoryDetailService categoryDetailService;

    @Resource
    private UserService userService;

    @Resource
    private IntegralService integralService;

    @Override
    @Transactional
    public void saveExam(List<SaveExamDo> saveExamDos) {
        //创建分数和对的数量和错的数量
        int sumScore = 0;
        int successNum = 0;
        int wrongNum = 0;
        //接收到的集合进行遍历比较
        for (SaveExamDo saveExamDo : saveExamDos) {
            CategoryDetail categoryDetail = categoryDetailService.getById(saveExamDo.getId());
            if (categoryDetail.getCategoryName().equals(saveExamDo.getDetailName())) {
                successNum = successNum + 1;
                sumScore = sumScore + 10;
            }else {
                wrongNum = wrongNum + 1;
            }
        }
        //保存考试记录表
        Exam exam = new Exam();
        String id = UUID.randomUUID().toString();
        exam.setId(id);
        exam.setExamRecordNo(OrderNoUtils.getExamRecordNo());
        exam.setScore(sumScore);
        exam.setTestSuccessNum(successNum);
        exam.setTestWrongNum(wrongNum);
        //根据登录的用户设置用户编号
        exam.setUserCode(UserInfoHolder.userInfo.get());
        baseMapper.insert(exam);
        //根据用户积分进行添加，并保存到td_user中
        userService.addPointByExam(UserInfoHolder.userInfo.get());
        //形成积分记录表入库
        integralService.saveIntegralLog(UserInfoHolder.userInfo.get(),"exam");
    }
}
