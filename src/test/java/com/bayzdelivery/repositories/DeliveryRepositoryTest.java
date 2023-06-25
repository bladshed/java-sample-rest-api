package com.bayzdelivery.repositories;

import com.bayzdelivery.model.Delivery;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DeliveryRepositoryTest {

    @Autowired
    DeliveryRepository deliveryRepository;

    @Test
    public void findDeliveryManActiveOrder(){
        Optional<Delivery> delivery = deliveryRepository.findDeliveryManActiveOrder(2L);

        Assert.assertEquals("mateo@yahoo.com", delivery.get().getDeliveryMan().getEmail());
    }
}
