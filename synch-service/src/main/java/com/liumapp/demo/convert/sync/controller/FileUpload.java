package com.liumapp.demo.convert.sync.controller;

import com.alibaba.fastjson.JSON;
import com.liumapp.demo.convert.sync.entity.DocEntity;
import com.liumapp.demo.convert.sync.util.Base64File;
import com.liumapp.demo.convert.sync.util.FileManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author liumapp
 * @file FileUpload.java
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 7/3/18
 */
@RestController
@RequestMapping("upload")
public class FileUpload {

    @Autowired
    private FileManager fileManager;

    @RequestMapping("/")
    public String upload (@RequestParam("file") MultipartFile file) throws IOException {
        fileManager.save(file);
        return JSON.toJSONString("success");
    }

    @RequestMapping("/base64")
    public String base64Upload (@RequestBody DocEntity docEntity) {
        try {
            MultipartFile file = fileManager.base64toMultipart(docEntity.getBase64File());
            fileManager.save(file);
        } catch (IOException e) {
            e.printStackTrace();
            return JSON.toJSONString("save file failed");
        }
        return JSON.toJSONString("success");
    }

}
