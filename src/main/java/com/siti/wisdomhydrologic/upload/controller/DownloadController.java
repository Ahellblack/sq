package com.siti.wisdomhydrologic.upload.controller;

import com.alibaba.fastjson.JSONObject;
import com.siti.wisdomhydrologic.upload.entity.FileUploadInformation;
import com.siti.wisdomhydrologic.upload.mapper.FileUploadMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;

import static org.aspectj.weaver.tools.cache.SimpleCacheFactory.path;

/**
 * Created by dell on 2019/10/31.
 */
@Controller
@RequestMapping("/download")
public class DownloadController {

    @Resource
    private FileUploadMapper fileUploadMapper;

    @ResponseBody
    @GetMapping("filename")
    public List<FileUploadInformation> getFileNames() {
        List<FileUploadInformation> files = fileUploadMapper.getFiles(null);
        return files;
    }

    /**
     * 文件下载
     *
     * @Param id       服务器上的文件路径
     * @param response
     * @return
     */
    @GetMapping("downloadFile")
    public int downloadFile(Integer id, HttpServletResponse response) {
        int result = 1;
        InputStream is = null;
        OutputStream os = null;
        try {
            List<FileUploadInformation> files = fileUploadMapper.getFiles(id);
            //获取file的url
            File file = new File(files.get(0).getFileUrl());
            // 清空response
            response.reset();
            // 设置response的Header
            response.setCharacterEncoding("utf-8");
            response.setContentType("multipart/form-data");
            response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(file.getName(), "utf-8"));
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            is = new FileInputStream(file);
            os = response.getOutputStream();
            byte[] b = new byte[2048];
            int length;
            while ((length = is.read(b)) > 0) {
                os.write(b, 0, length);
            }
            toClient.flush();
            toClient.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            result = 0;
        } finally {
            closeInputStream(is);
            closeOutputStream(os);
        }

        return result;
    }


    private static void closeInputStream(InputStream is) {
        try {
            if (is != null) {
                is.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void closeOutputStream(OutputStream os) {
        try {
            if (os != null) {
                os.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
