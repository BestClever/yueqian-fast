package com.ityueqiangu.common.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.ityueqiangu.core.constant.Constants;
import com.ityueqiangu.core.enums.CommonEnum;
import com.ityueqiangu.core.exception.BizException;
import com.ityueqiangu.core.util.ServletUtils;
import com.ityueqiangu.core.util.file.FileUploadUtils;
import com.ityueqiangu.core.web.result.ResultDataUtil;
import com.ityueqiangu.core.web.result.ResultInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Clever、xia
 * @title: FileController
 * @projectName art-exhibition-system
 * @description:
 * @date 2021-03-11 21:30
 */
@Controller
@RequestMapping(value = "/file")
public class FileController {

    /**
     * 通用上传请求
     */
    @PostMapping("/upload")
    @ResponseBody
    public ResultInfo uploadFile(MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            throw new BizException("文件不能为空");
        }
        // 上传文件路径
        String filePath = Constants.UPLOAD_FILE_PATH;
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
        String filePath = Constants.UPLOAD_FILE_PATH;
        // 上传并返回新文件名称
        String fileName = FileUploadUtils.upload(filePath, file);
        String url = ServletUtils.getUrl() + fileName;
        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("title", fileName);
        resultMap.put("src", url);
        return ResultDataUtil.createSuccess(CommonEnum.FILE_UPLOAD_SUCCESS_LAYUI).setData(resultMap);
    }


    /**
     * 图片下载
     */
    @RequestMapping("showImageByPath")
    public ResponseEntity<Object> showImageByPath(String path) {
        return FileUploadUtils.createResponseEntity(path);
    }


    public static void main(String[] args) {
        Map<String, Object> row1 = new LinkedHashMap<>();
        row1.put("姓名", "张三");
        row1.put("年龄", 23);
        row1.put("成绩", 88.32);
        row1.put("是否合格", true);
        row1.put("考试日期", DateUtil.date());

        Map<String, Object> row2 = new LinkedHashMap<>();
        row2.put("姓名", "李四");
        row2.put("年龄", 33);
        row2.put("成绩", 59.50);
        row2.put("是否合格", false);
        row2.put("考试日期", DateUtil.date());

        ArrayList<Map<String, Object>> rows = CollUtil.newArrayList(row1, row2);

        // 通过工具类创建writer
        ExcelWriter writer = ExcelUtil.getWriter("d:/writeMapTest.xlsx","表1");
        // 合并单元格后的标题行，使用默认标题样式
        writer.merge(rows.size() - 1, "一班成绩单");
        // 一次性写出内容，使用默认样式，强制输出标题
        writer.write(rows, true);

        writer.setSheet("表二");
        // 合并单元格后的标题行，使用默认标题样式
        writer.merge(rows.size() - 1, "一班成绩单");
        // 一次性写出内容，使用默认样式，强制输出标题
        writer.write(rows, true);
        // 关闭writer，释放内存
        writer.close();

    }
}
