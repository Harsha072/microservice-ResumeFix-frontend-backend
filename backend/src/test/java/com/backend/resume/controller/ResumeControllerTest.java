package com.backend.resume.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.backend.resume.service.ResumeProducer;

import java.io.IOException;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ResumeControllerTest {

    @Mock
    private ResumeProducer resumeProducer;

    @InjectMocks
    private FileUploadController resumeController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
    }

    @Test
    public void testUploadResume_Success() throws IOException {
        // Arrange
        MultipartFile file = new MockMultipartFile(
                "file",
                "resume.pdf",
                "application/pdf",
                "Mock file content".getBytes()
        );

        // Act
        ResponseEntity<String> response = resumeController.uploadResume(file);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("File uploaded successfully: resume.pdf", response.getBody());
        verify(resumeProducer, times(1)).sendResume(any(byte[].class), any(String.class));
    }

    @Test
    public void testUploadResume_EmptyFile() throws IOException {
        // Arrange
        MultipartFile file = new MockMultipartFile(
                "file",
                "resume.pdf",
                "application/pdf",
                new byte[0] // Empty file
        );

        // Act
        ResponseEntity<String> response = resumeController.uploadResume(file);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("File is missing", response.getBody());
        verify(resumeProducer, never()).sendResume(any(byte[].class), any(String.class));
    }

    @Test
    public void testUploadResume_IOException() throws IOException {
        // Arrange
        MultipartFile file = mock(MultipartFile.class);
        when(file.isEmpty()).thenReturn(false);
        when(file.getBytes()).thenThrow(new IOException("Failed to read file"));

        // Act
        ResponseEntity<String> response = resumeController.uploadResume(file);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Failed to process the file", response.getBody());
        verify(resumeProducer, never()).sendResume(any(byte[].class), any(String.class));
    }
}