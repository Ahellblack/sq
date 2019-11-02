package com.siti.wisdomhydrologic.upload.controller;

import com.alibaba.fastjson.JSONObject;
import com.siti.wisdomhydrologic.config.FileUploadConfig;
import com.siti.wisdomhydrologic.config.SMSConfig;
import com.siti.wisdomhydrologic.upload.UploadUtil;
import com.siti.wisdomhydrologic.upload.mapper.FileUploadMapper;
import com.siti.wisdomhydrologic.user.entity.User;
import com.siti.wisdomhydrologic.user.service.UserInfoService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.awt.*;
import java.io.*;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/upload")
public class UploadController {

    @Resource
    private FileUploadConfig fileUploadConfig;
    @Resource
    private FileUploadMapper fileUploadMapper;
    @Resource
    private UserInfoService userInfoService;
/*
    private final String URL = fileUploadConfig.getUploadUrl();
    //设置文件最大限制
    private final long MAX_FILE_SIZE = fileUploadConfig.getMaxFileSize();*/



    /**
     * 单文件上传
     *
     * @param file
     * @param request
     * @return
     * @throws IllegalStateException
     * @throws IOException
     * @throws JSONException
     */
    @RequestMapping(value = "/file")
    public JSONObject krryupload(@RequestParam(value = "files", required = false) MultipartFile file, HttpServletRequest request) throws IllegalStateException, IOException, JSONException {

        //存储文件上传地址，文件上传人至数据库
        String ipAddress = getIpAddress(request);
        User user = (User) userInfoService.get();

        //调用工具类完成上传，返回相关数据到页面
        return uploadFile(file, fileUploadConfig.getMaxFileSize(),ipAddress,user);
    }

    /**
     * 多文件上传
     *
     * @param file
     * @param request
     * @return
     * @throws IllegalStateException
     * @throws IOException
     * @throws JSONException
     */
    // 这里的MultipartFile[] file表示前端页面上传过来的多个文件，file对应页面中多个file类型的input标签的name，但框架只会将一个文件封装进一个MultipartFile对象，
    // 并不会将多个文件封装进一个MultipartFile[]数组，直接使用会报[Lorg.springframework.web.multipart.MultipartFile;.<init>()错误，
    // 所以需要用@RequestParam校正参数（参数名与MultipartFile对象名一致），当然也可以这么写：@RequestParam("file") MultipartFile[] files。

    @RequestMapping(value = "/mutl")
    public List<HashMap<String, Object>> krryuploadMutl(@RequestParam("files") MultipartFile[] file, HttpServletRequest request) throws IllegalStateException, IOException, JSONException {
        //调用工具类完成上传，返回相关数据到页面
        return UploadUtil.mutlUpload(file, request);

    }

    public  JSONObject uploadFile(MultipartFile file, long maxPicSize,String ipAddress,User user) throws IOException {
        JSONObject jsonObject = new JSONObject();
        //首先判断文件的大小
        if (file == null || file.getSize() == 0) {
            jsonObject.put("msg", "请重新选择要上传的文件。");
            return jsonObject;
        }
        if (file.getSize() > maxPicSize) {
            jsonObject.put("msg", "文件过大。");
            return jsonObject;
        }
        //获得文件的名称
        String filename = file.getOriginalFilename();
       /* //获得文件的扩展名称
                String extensionName = FileTool.getExtensionName(filename).toLowerCase();
        //判断文件是否是你需要的支持的类型
                
        if (StringUtils.isNotBlank(extensionName) && !(extensionName.equals("jpg") || extensionName.equals("jpeg") || extensionName.equals("png") || extensionName.equals("bmp") || extensionName.equals("gif") || extensionName.equals("pdf"))) {
            return JSONTool.getReturnJSON(JSONTool.fail, "文件类型不是JPEG，PNG，BMP，PDF或者GIF。");
        }*/
        //接下来就是把文件存起来了

        URL url = this.getClass().getClassLoader().getResource("");
        String logFilePath = url.getPath();
        // 如果DISK_PATH路径不存在，则创建
        // 设置上传文件的相对路径
        File uploadFilePath = new File(logFilePath+"fileupload/");
        if (uploadFilePath.exists() == false) {
            uploadFilePath.mkdirs();
        }
       /* // 生成全局唯一的id
        UUID uuid = UUID.randomUUID();
*/
        Long timestamp = System.currentTimeMillis();
        //按照时间戳修改文件名称
        String localUrl = logFilePath+"fileupload/"+timestamp + "_" + file.getOriginalFilename();//防止重复上传文件出错，添加时间戳后缀
        //数据库记录相对路径
        String raletiveUrl = "fileupload/"+timestamp + "_" + file.getOriginalFilename();//防止重复上传文件出错，添加时间戳后缀
        String fileName = file.getOriginalFilename();
        boolean saveResult = saveFile(localUrl, file);
        if (!saveResult) {
            jsonObject.put("msg", "文件上传出错。");
            return jsonObject;
        }
        try {
            fileUploadMapper.saveFileMsg(raletiveUrl,fileName,user.getRealName(), ipAddress);
        }catch (Exception e){
            jsonObject.put("msg","信息入库异常。");
            return jsonObject;
        }
        jsonObject.put("msg", "文件上传成功。");
        jsonObject.put("fileName", filename);
        return jsonObject;
    }


    public boolean saveFile(String localUrl, MultipartFile file) throws IOException {

        File realFile = new File(localUrl);
        OutputStream outputstream = null;
        InputStream inputstream = null;
        try {
            inputstream = file.getInputStream();
            outputstream = new FileOutputStream(realFile);
            byte[] buffer = new byte[1024 * 5];
            int byteRead = -1;
            while ((byteRead = (inputstream.read(buffer))) != -1) {
                outputstream.write(buffer, 0, byteRead);
            }
            realFile.setExecutable(false);
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            if (outputstream != null) {
                outputstream.flush();
                outputstream.close();
            }
            if (inputstream != null) {
                inputstream.close();
            }
        }

    }

    private static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }


}