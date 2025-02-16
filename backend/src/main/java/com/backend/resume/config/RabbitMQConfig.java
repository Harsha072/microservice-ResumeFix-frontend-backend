package com.backend.resume.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue queue() {
        return new Queue("resume_queue", true); // Durable queue
    }

    @Bean
    public Queue feedbackQueue() {
        return new Queue("feedback_queue", true); // Durable queue
    }
}