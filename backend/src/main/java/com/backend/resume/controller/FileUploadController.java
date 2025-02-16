package com.backend.resume.controller;

import com.backend.resume.service.ResumeProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/api/resumes")
public class FileUploadController {

    @Autowired
    private ResumeProducer resumeProducer;


    @PostMapping("/upload-resume")
    public  ResponseEntity<String> uploadResume(@RequestParam("file") MultipartFile file) throws IOException {
        String resumeId = UUID.randomUUID().toString();
        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File is missing");
        }
        try {
            byte[] resumeBytes = file.getBytes();
            resumeProducer.sendResume(resumeBytes, resumeId);
            return ResponseEntity.ok("File uploaded successfully: " + file.getOriginalFilename());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to process the file");
        }
    }
}