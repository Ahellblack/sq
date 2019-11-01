package com.siti.wisdomhydrologic.upload.controller;

import com.alibaba.fastjson.JSONObject;
import com.siti.wisdomhydrologic.upload.entity.FileUploadInformation;
import com.siti.wisdomhydrologic.upload.mapper.FileUploadMapper;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;

/**
 * Created by dell on 2019/10/31.
 */
@RestController
@RequestMapping("/delete")
public class DeleteController {

    @Resource
    private FileUploadMapper fileUploadMapper;


    @GetMapping("files")
    public JSONObject deleteFile(Integer id){
        JSONObject jsonObject = new JSONObject();
        try {
            List<FileUploadInformation> files = fileUploadMapper.getFiles(id);
            String path = files.get(0).getFileUrl();
            clearFiles(path);
            fileUploadMapper.delete(id);
        }catch (Exception e){
            jsonObject.put("msg","删除文件异常");
            jsonObject.put("status",-1);
            return jsonObject;
        }
        jsonObject.put("status",1);
        jsonObject.put("msg","删除文件成功");
        return jsonObject;
    }


    private void clearFiles(String workspaceRootPath){
        File file = new File(workspaceRootPath);
        deleteFile(file);
    }

    private void deleteFile(File file){
        if(file.exists()){
            if(file.isDirectory()){
                File[] files = file.listFiles();
                for(int i=0; i<files.length; i++){
                    deleteFile(files[i]);
                }
            }
        }
        file.delete();
    }
}
