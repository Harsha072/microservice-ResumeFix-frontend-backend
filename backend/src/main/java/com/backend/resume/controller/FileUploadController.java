package com.backend.resume.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/api/resumes")
public class FileUploadController {

    @PostMapping("/upload")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
        try {
            // Define the absolute path to the uploads directory
            String uploadDir = "C:\\Users\\Admin\\IdeaProjects\\ResumeFix\\backend\\src\\main\\resources\\uploads";

            // Create the directory if it doesn't exist
            File dir = new File(uploadDir);
            if (!dir.exists()) {
                dir.mkdirs(); // Create all necessary parent directories
            }

            // Define the full file path
            String filePath = uploadDir + File.separator + file.getOriginalFilename();
            System.out.println("Saving file to: " + filePath);

            // Save the file
            File dest = new File(filePath);
            file.transferTo(dest);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok("File uploaded successfully: " + file.getOriginalFilename());

    }
}