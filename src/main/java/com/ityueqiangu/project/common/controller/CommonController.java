package com.ityueqiangu.project.common.controller;

import cn.hutool.core.util.StrUtil;
import com.ityueqiangu.core.config.FrameworkConfig;
import com.ityueqiangu.core.config.ServerConfig;
import com.ityueqiangu.core.constant.Constants;
import com.ityueqiangu.core.exception.BusinessException;
import com.ityueqiangu.core.utils.StringUtils;
import com.ityueqiangu.core.utils.file.FileUploadUtils;
import com.ityueqiangu.core.utils.file.FileUtils;
import com.ityueqiangu.core.web.domain.ResponseInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

/**
 * 通用请求处理
 *
 * @author FlowerStone
 */
@Slf4j
@Controller
public class CommonController {

    @Autowired
    private ServerConfig serverConfig;


    /**
     * 通用下载请求
     *
     * @param fileName 文件名称
     * @param delete   是否删除
     */
    @GetMapping("common/download")
    public void fileDownload(String fileName, Boolean delete, HttpServletResponse response, HttpServletRequest request) {
        try {
            if (!FileUtils.checkAllowDownload(fileName)) {
                throw new Exception(StringUtils.format("文件名称({})非法，不允许下载。 ", fileName));
            }
            String realFileName = System.currentTimeMillis() + fileName.substring(fileName.indexOf("_") + 1);
            String filePath = FrameworkConfig.getDownloadPath() + fileName;

            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            FileUtils.setAttachmentResponseHeader(response, realFileName);
            FileUtils.writeBytes(filePath, response.getOutputStream());
            if (delete) {
                FileUtils.deleteFile(filePath);
            }
        } catch (Exception e) {
            log.error("下载文件失败", e);
        }
    }

    /**
     * 通用上传请求
     */
    @PostMapping("/common/upload")
    @ResponseBody
    public ResponseInfo uploadFile(MultipartFile file) throws Exception {
        try {
            // 上传文件路径
            String filePath = FrameworkConfig.getUploadPath();
            // 上传并返回新文件名称
            String fileName = FileUploadUtils.upload(filePath, file);
            String url = serverConfig.getUrl() + fileName;
            ResponseInfo responseInfo = ResponseInfo.success();
            responseInfo.put("fileName", fileName);
            responseInfo.put("url", url);
            return responseInfo;
        } catch (Exception e) {
            return ResponseInfo.error(e.getMessage());
        }
    }

    /**
     * layui 文件上传
     */
    @PostMapping("/layui/upload")
    @ResponseBody
    public ResponseInfo uploadImage(MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            throw new BusinessException("文件不能为空");
        }
        // 上传文件路径
        String filePath = FrameworkConfig.getUploadPath();
        // 上传并返回新文件名称
        String fileName = FileUploadUtils.upload(filePath, file);
        String url = serverConfig.getUrl() + fileName;
        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("title", fileName);
        resultMap.put("src", url);
        ResponseInfo responseInfo = new ResponseInfo(ResponseInfo.Type.ZERO.value(), "操作成功", resultMap);
        return responseInfo;
    }

    /**
     * 本地资源通用下载
     */
    @GetMapping("/common/download/resource")
    public void resourceDownload(String resource, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        try {
            if (!FileUtils.checkAllowDownload(resource)) {
                throw new Exception(StringUtils.format("资源文件({})非法，不允许下载。 ", resource));
            }
            // 本地资源路径
            String localPath = FrameworkConfig.getProfile();
            // 数据库资源地址
            String downloadPath = localPath + org.thymeleaf.util.StringUtils.substringAfter(resource, Constants.RESOURCE_PREFIX);
            // 下载名称
            //String downloadName = StringUtils.substringAfterLast(downloadPath, "/");
            String downloadName = StrUtil.subAfter(downloadPath, "/",true);
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            FileUtils.setAttachmentResponseHeader(response, downloadName);
            FileUtils.writeBytes(downloadPath, response.getOutputStream());
        } catch (Exception e) {
            log.error("下载文件失败", e);
        }
    }
}
