package com.bayzdelivery.jobs;

import com.bayzdelivery.model.LateDelivery;
import com.bayzdelivery.repositories.DeliveryRepository;
import com.bayzdelivery.service.UtilService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

@Configuration
@EnableScheduling
@Profile("!test")
public class DelayedDeliveryNotifier {

    private static final Logger LOG = LoggerFactory.getLogger(DelayedDeliveryNotifier.class);
    @Autowired
    DeliveryRepository deliveryRepository;

    /**
     *  Use this method for the TASK 3
     */
    @Scheduled(fixedDelay = 60000)
    public void checkDelayedDeliveries() {
        String date = UtilService.formatLateDate();
        List<LateDelivery> lateDeliveries = deliveryRepository.getLateDeliveries(date);
        if (lateDeliveries.size() > 0){
            System.out.println("============LATE DELIVERIES============");
            lateDeliveries.stream().forEach(obj ->
                System.out.println("Name: " + obj.getName() + " | Start time: " + obj.getStartTime())
            );
            notifyCustomerSupport();
        }
    }


    /**
     * This method should be called to notify customer support team
     * It just writes notification on console but it may be email or push notification in real.
     * So that this method should run in an async way.
     */
    @Async
    public void notifyCustomerSupport() {
        LOG.info("Customer support team is notified!");
    }
}
