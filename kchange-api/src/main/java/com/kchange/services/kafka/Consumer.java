package com.kchange.services.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {
    private final Logger logger = LoggerFactory.getLogger(Consumer.class);

    //@KafkaListener(topics = "available_products", groupId = "available_product_group")
    public void consume(String message){
        logger.info(String.format("$$ -> Consumed Message -> %s",message));
    }
}
