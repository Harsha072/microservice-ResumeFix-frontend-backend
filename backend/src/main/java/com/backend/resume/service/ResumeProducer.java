package com.backend.resume.service;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.amqp.core.Queue;

@Service
public class ResumeProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private Queue queue;

    public void sendResume(byte[] resumeBytes) {
        rabbitTemplate.convertAndSend(queue.getName(), resumeBytes);
        System.out.println("Sent resume to RabbitMQ");
    }
}