package com.applet.trash.service.impl;

import com.applet.trash.entity.CategoryDetail;
import com.applet.trash.mapper.CategoryDetailMapper;
import com.applet.trash.service.CategoryDetailService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class CategoryDetailServiceImpl extends ServiceImpl<CategoryDetailMapper, CategoryDetail> implements CategoryDetailService {

    @Resource
    private CategoryDetailService categoryDetailService;

    /**
     * 根据大类id和关键词详细名称模糊查询列表
     * @param id
     * @param detailName
     * @return
     */
    @Override
    public List<CategoryDetail> getListByNameAndId(int id, String detailName) {
        LambdaQueryWrapper<CategoryDetail> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CategoryDetail::getCategoryId, id);
        queryWrapper.like(CategoryDetail::getDetailName, detailName);
        List<CategoryDetail> categoryDetails = baseMapper.selectList(queryWrapper);
        return categoryDetails;
    }

    @Override
    public List<CategoryDetail> getRandomExamList() {
        //编写算法读取10条不重复的命令
        //创建结果集
        List<CategoryDetail> result = new ArrayList<>();
        //查询小类个数
        List<CategoryDetail> categoryDetails = categoryDetailService.list();
        //创建随机对象
        Random r = new Random();
        //创建集合对象
        ArrayList<Integer> array = new ArrayList<Integer>();
        //产生随机数
        int count = 0;
        while (count<10){
            //产生随机数
            int number = r.nextInt(categoryDetails.size()) + 1;
            //判断是否重复
            if(!array.contains(number)){
                //没有，集合添加该随机数
                array.add(number);
                count++;
            }
        }
        //遍历
        for (Integer i : array){
            CategoryDetail categoryDetail = categoryDetails.get(i-1);
            result.add(categoryDetail);
        }
        return result;
    }
}
