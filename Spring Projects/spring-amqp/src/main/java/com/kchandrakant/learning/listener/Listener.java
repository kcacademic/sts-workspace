package com.kchandrakant.learning.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.kchandrakant.learning.data.Message;

@Component
@RabbitListener(containerFactory="rabbitListenerContainerFactory", queues = {"myQueue"})
public class Listener
{
    private static final Logger logger = LoggerFactory.getLogger(Listener.class);

    @RabbitHandler
    public void receiveMessage(Message message)
    {
        logger.info("Received Message: [" + message + "]");
    }
}
