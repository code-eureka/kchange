package com.kchange.services.schedulers;


import com.kchange.services.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ProductMatchCheckerScheduler {
    private static final Logger log = LoggerFactory.getLogger(ProductMatchCheckerScheduler.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    OrderService orderService;

    @Scheduled(fixedRate = 5000)
    public void processUnMatchchedOrdersForNudge() {
        log.info("Looking for Unmatched orders");
        orderService.fetchOrdersWithNoMatches().stream().forEach(e->log.info(e));
    }
}
