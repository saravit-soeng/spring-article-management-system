package com.example.ams.controller.restapi;

import com.example.ams.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class FileUploadRestController {

    @Value("${url.path}")
    private String urlPath;

    @Autowired
    private FileUploadService fileUploadService;

    @PostMapping("/upload")
    public Map<String, Object> uploadImage(MultipartFile file){
        Map<String, Object> map = new HashMap<>();
        map.put("message", "upload success");
        map.put("data", urlPath + fileUploadService.upload(file));
        return map;
    }
}
