package com.backend.resume.service;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.core.Queue;

@Service
public class ResumeProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private Queue queue;

    public void sendResume(byte[] resumeBytes, String resumeId) {
         Message message = MessageBuilder
                .withBody(resumeBytes)
                .setContentType(MessageProperties.CONTENT_TYPE_BYTES)
                .setCorrelationId(resumeId)
                .build();

        rabbitTemplate.send(queue.getName(), message);
        System.out.println("Sent resume to RabbitMQ with ID: " + resumeId);    
    }
}