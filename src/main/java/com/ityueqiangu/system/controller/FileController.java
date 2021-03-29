package com.ityueqiangu.system.controller;

import cn.hutool.core.date.DateUtil;
import com.ityueqiangu.core.config.FrameworkConfig;
import com.ityueqiangu.core.constant.Constants;
import com.ityueqiangu.core.enums.CommonEnum;
import com.ityueqiangu.core.exception.BizException;
import com.ityueqiangu.core.utils.AppFileUtils;
import com.ityueqiangu.core.utils.ServletUtils;
import com.ityueqiangu.core.utils.file.FileUploadUtils;
import com.ityueqiangu.core.web.result.ResultDataUtil;
import com.ityueqiangu.core.web.result.ResultInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: clever、xia
 * @Date: 2019/12/15 23:46
 */
@RestController
@RequestMapping("/file")
public class FileController {


    /**
     * 通用上传请求
     */
    @PostMapping("/upload")
    @ResponseBody
    public ResultInfo upload(MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            throw new BizException("文件不能为空");
        }
        // 上传文件路径
        String filePath = FrameworkConfig.getUploadPath();
        // 上传并返回新文件名称
        String fileName = FileUploadUtils.upload(filePath, file);
        String url = ServletUtils.getUrl() + fileName;
        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("fileName", fileName);
        resultMap.put("url", url);
        return ResultDataUtil.createSuccess(CommonEnum.FILE_UPLOAD_SUCCESS).setData(resultMap);
    }

    /**
     * layui 文件上传
     */
    @PostMapping("/uploadImage")
    @ResponseBody
    public ResultInfo uploadImage(MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            throw new BizException("文件不能为空");
        }
        // 上传文件路径
        String filePath = FrameworkConfig.getUploadPath();
        // 上传并返回新文件名称
        String fileName = FileUploadUtils.upload(filePath, file);
        String url = ServletUtils.getUrl() + fileName;
        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("title", fileName);
        resultMap.put("src", url);
        return ResultDataUtil.createSuccess(CommonEnum.FILE_UPLOAD_SUCCESS_LAYUI).setData(resultMap);
    }



    /**
     * 文件上传
     * @param mf
     * @return
     */
    @RequestMapping("/uploadFile")
    public Map<String,Object> uploadFile(MultipartFile mf) {
        //1.得到文件名
        String oldName = mf.getOriginalFilename();
        //2.根据旧的文件名生成新的文件名
        String newName=AppFileUtils.createNewFileName(oldName);
        //3.得到当前日期的字符串
        String dirName= DateUtil.format(new Date(), "yyyy-MM-dd");
        //4.构造文件夹
        File dirFile=new File(AppFileUtils.UPLOAD_PATH,dirName);
        //5.判断当前文件夹是否存在
        if(!dirFile.exists()) {
            //如果不存在则创建新文件夹
            dirFile.mkdirs();
        }
        //6.构造文件对象
        File file=new File(dirFile, newName+"_temp");
        //7.把mf里面的图片信息写入file
        try {
            mf.transferTo(file);
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        }
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("path",dirName+"/"+newName+"_temp");
        return map;
    }

    /**
     * 图片下载
     */
    @RequestMapping("showImageByPath")
    public ResponseEntity<Object> showImageByPath(String path){
        return AppFileUtils.createResponseEntity(path);
    }

}
