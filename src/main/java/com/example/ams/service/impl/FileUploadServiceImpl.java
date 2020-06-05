package com.example.ams.service.impl;

import com.example.ams.service.FileUploadService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileUploadServiceImpl implements FileUploadService {

    @Value("${file.upload.client.path}")
    private String clientPath;

    @Value("${file.upload.server.path}")
    private String serverPath;

    @Override
    public String upload(MultipartFile file) {
        if (file == null) {
            return null;
        }

        File path = new File(this.serverPath);
        if(!path.exists()) path.mkdirs();

        String filename = file.getOriginalFilename();
        filename = UUID.randomUUID() + "." + filename.substring(filename.lastIndexOf(".")+1);

        try{
            Files.copy(file.getInputStream(), Paths.get(this.serverPath, filename));
        }catch (Exception e){
            e.printStackTrace();
        }

        return  this.clientPath + filename;
    }
}
