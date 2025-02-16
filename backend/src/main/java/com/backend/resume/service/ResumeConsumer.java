package com.backend.resume.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.backend.resume.entity.Feedback;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ResumeConsumer {

    private final ObjectMapper objectMapper;

    // Constructor for dependency injection
    public ResumeConsumer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @RabbitListener(queues = "feedback_queue")
    public void receiveFeedback(String feedbackJson) {
        System.out.println(feedbackJson);
        try {
            // Parse the feedback JSON
            Feedback feedback = objectMapper.readValue(feedbackJson, Feedback.class);
            System.out.println("Received feedback for resume ID: " + feedback.getResumeId());
            System.out.println("Parsed Text: " + feedback.getParsedText());
            System.out.println("Typos: " + feedback.getTypos());
            System.out.println("Status: " + feedback.getStatus());

            // Process feedback (e.g., store in database or notify user)
        } catch (Exception e) {
            System.err.println("Error processing feedback: " + e.getMessage());
        }
    }    
}
