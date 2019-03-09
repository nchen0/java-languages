package com.example.demo2;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.message.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

// @Service // Gets Spring to run this constantly to consume the log
@Slf4j
public class LogConsumer {

    @RabbitListener(queues = Demo2Application.QUEUE_NAME)
    public void consumeMessage(final Message cm) {
        log.info("Received Message: {}", cm.toString());
    }
}
