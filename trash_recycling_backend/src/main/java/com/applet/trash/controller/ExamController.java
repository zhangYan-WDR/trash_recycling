package com.applet.trash.controller;

import com.applet.trash.Do.SaveExamDo;
import com.applet.trash.entity.CategoryDetail;
import com.applet.trash.service.CategoryDetailService;
import com.applet.trash.service.ExamService;
import com.applet.trash.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/exam")
@Api(tags = "考试相关接口")
@CrossOrigin
public class ExamController {

    @Resource
    private CategoryDetailService categoryDetailService;

    @Resource
    private ExamService examService;

    @GetMapping("/list")
    @ApiOperation("随机抽取10个类别组成考试题")
    public R getExamSubject(){
        List<CategoryDetail> categoryDetails = categoryDetailService.getRandomExamList();
        return R.ok().data("list",categoryDetails).setMessage("考试题目已经生成");
    }

    @PostMapping("/notify/save")
    @ApiOperation("根据考试结果进行添加考试日志并修改当前用户的积分")
    public R saveExam(@RequestBody List<SaveExamDo> saveExamDos){
        examService.saveExam(saveExamDos);
        return R.ok().setMessage("添加成功");
    }

}
