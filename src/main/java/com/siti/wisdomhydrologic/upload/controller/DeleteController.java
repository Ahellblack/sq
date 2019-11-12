package com.siti.wisdomhydrologic.upload.controller;

import com.alibaba.fastjson.JSONObject;
import com.siti.wisdomhydrologic.upload.entity.FileUploadInformation;
import com.siti.wisdomhydrologic.upload.mapper.FileUploadMapper;

import com.siti.wisdomhydrologic.user.entity.Role;
import com.siti.wisdomhydrologic.user.entity.User;
import com.siti.wisdomhydrologic.user.mapper.UserMapper;
import com.siti.wisdomhydrologic.user.service.UserInfoService;
import com.siti.wisdomhydrologic.user.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zyw on 2019/10/31.
 */
@RestController
@RequestMapping("/delete")
public class DeleteController {

    @Resource
    private FileUploadMapper fileUploadMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private UserInfoService userInfoService;

    @GetMapping("files")
    public JSONObject deleteFile(Integer id) {
        JSONObject jsonObject = new JSONObject();
        try {
            List<FileUploadInformation> files = fileUploadMapper.getFiles(id,null);

            //判断添加权限
            User loginUser = (User) userInfoService.get();
            List<Role> roles = userMapper.findRole(loginUser.getId());
            List<Integer> list = new ArrayList<>();
            roles.forEach(role -> {
                if (role.getId() == 1) {
                    list.add(role.getId());
                }
            });
            //当数据为登陆用户上传时,可以删除
            if(files.get(0).getUploadUser() == loginUser.getId()){
                list.add(1);
            }
            if (list.size() == 0) {
                jsonObject.put("status", -1);
                jsonObject.put("message", "无删除权限");
                return jsonObject;
            }

            //获取相对路径
            URL url = this.getClass().getClassLoader().getResource("");
            String logFilePath = url.getPath();
            String path = logFilePath + files.get(0).getFileUrl();
            clearFiles(path);
            fileUploadMapper.delete(id);
        } catch (Exception e) {
            jsonObject.put("msg", "删除文件异常");
            jsonObject.put("status", -1);
            return jsonObject;
        }
        jsonObject.put("status", 1);
        jsonObject.put("msg", "删除文件成功");
        return jsonObject;
    }


    private void clearFiles(String workspaceRootPath) {
        File file = new File(workspaceRootPath);
        deleteFile(file);
    }

    private void deleteFile(File file) {
        if (file.exists()) {
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                for (int i = 0; i < files.length; i++) {
                    deleteFile(files[i]);
                }
            }
        }
        file.delete();
    }
}
