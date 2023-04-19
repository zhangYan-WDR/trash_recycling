package com.applet.trash.service.impl;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.applet.trash.entity.CategoryDetail;
import com.applet.trash.mapper.CategoryDetailMapper;
import com.applet.trash.service.CategoryDetailService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import javax.annotation.Resource;
import java.io.*;
import java.net.URLEncoder;
import java.util.*;

@Service
@Slf4j
public class CategoryDetailServiceImpl extends ServiceImpl<CategoryDetailMapper, CategoryDetail> implements CategoryDetailService {

    @Resource
    private CategoryDetailService categoryDetailService;

    @Value("${baidu.cloud.image.apiKey}")
    private String apiKey;

    @Value("${baidu.cloud.image.secretKey}")
    private String secretKey;

    static final OkHttpClient HTTP_CLIENT = new OkHttpClient().newBuilder().build();

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

    /**
     * 调用百度智能云接口根据图像识别物体
     * API文档参考地址:https://ai.baidu.com/ai-doc/IMAGERECOGNITION/Xk3bcxe21
     * @param file
     * @return
     * @throws IOException
     */
    @Override
    public String getCategoryDetailByImage(MultipartFile file) throws IOException {
        File toFile = MultipartFileToFile(file);
        String baseEncode = fileToBase(toFile);
        //图像数据，base64编码
        baseEncode = baseEncode.replace("data:image/jpg;base64,", "");
        //去掉编码头（data:image/jpg;base64,）后，再进行urlEncode编码
        String urlString = URLEncoder.encode(baseEncode, "utf-8");
        //获取百度智能云的auth_token
        String baiduAuthToken = this.getBaiduAuthToken();
        //组装请求路径
        String baseUrl = "https://aip.baidubce.com/rest/2.0/image-classify/v2/advanced_general?access_token=".concat(baiduAuthToken);
        String json = "image=".concat(urlString);
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, json);
        Request request = new Request.Builder()
                .url(baseUrl)
                .method("POST", body)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .addHeader("Accept", "application/json")
                .build();
        Response response = HTTP_CLIENT.newCall(request).execute();
        String responseString = response.body().string();
        log.info("获取到的数据为:->{}",responseString);
        return responseString;
    }

    /**
     * 将MultipartFile转换为File
     * @param multiFile
     * @return
     */
    public static File MultipartFileToFile(MultipartFile multiFile) {
        // 获取文件名
        String fileName = multiFile.getOriginalFilename();
        // 获取文件后缀
        String prefix = fileName.substring(fileName.lastIndexOf("."));
        // 若须要防止生成的临时文件重复,能够在文件名后添加随机码

        try {
            File file = File.createTempFile(fileName, prefix);
            multiFile.transferTo(file);
            return file;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static String fileToBase(File file) {
        String base64 = null;
        InputStream in = null;
        try {
            in = new FileInputStream(file);
            byte[] bytes=new byte[(int)file.length()];
            in.read(bytes);
            base64 = Base64.getEncoder().encodeToString(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return base64;
    }


    /**
     * 获取百度智能云的auth_token
     * Api文档参考地址:https://ai.baidu.com/ai-doc/REFERENCE/Ck3dwjhhu
     */
    public String getBaiduAuthToken() {
        String url = "https://aip.baidubce.com/oauth/2.0/token?grant_type=client_credentials&client_id=".concat(apiKey).concat("&client_secret=").concat(secretKey);
        String post = HttpUtil.post(url, "");
        JSONObject jsonObject = new Gson().fromJson(post, JSONObject.class);
        String accessToken = (String) jsonObject.get("access_token");
        return accessToken;
    }
}
