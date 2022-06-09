package com.example.ex03.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
@Slf4j
@RequestMapping("/upload/*")
public class UploadController {
    @GetMapping("/uploadForm")
    public void uploadForm() {
        log.info("upload form");
    }

    @PostMapping("/uploadFormAction")
    public void upload(MultipartFile[] uploadFile) throws IOException {
        String uploadFolder = "C:/upload";
        for (MultipartFile file : uploadFile){
            log.info("-------------------");
            log.info("Upload File Name : " + file.getOriginalFilename());
            log.info("Upload File Size : " + file.getSize());

            File saveFile = new File(uploadFolder, file.getOriginalFilename());
            file.transferTo(saveFile);
        }
    }
}
