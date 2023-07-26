package com.bayzdelivery.repositories;

import com.bayzdelivery.BayzDeliveryApplication;
import com.bayzdelivery.model.Delivery;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BayzDeliveryApplication.class)
@ActiveProfiles(value = "test")
@Sql({"/schema.sql", "/data.sql"})
@Transactional
public class DeliveryRepositoryTest {

    @Autowired
    DeliveryRepository deliveryRepository;

    @Autowired
    PersonRepository personRepository;

    @Test
    public void findDeliveryManActiveOrder(){
        Optional<Delivery> delivery = deliveryRepository.findDeliveryManActiveOrder(4L);

        Assert.assertEquals("lebron@yahoo.com", delivery.get().getDeliveryMan().getEmail());
    }

    @Test
    public void findDeliveryById(){
        Optional<Delivery> delivery = deliveryRepository.findById(1L);

        Assert.assertEquals("Agnes", delivery.get().getCustomer().getName());
    }

    @Test
    public void createNewDeliveryRequest(){
        Delivery delivery = new Delivery();
        delivery.setPrice(11.0F);
        delivery.setDistance(10);;
        delivery.setDeliveryMan(personRepository.findById(4L).get());
        delivery.setCustomer(personRepository.findById(8L).get());
        delivery.setStartTime(Instant.now());

        Delivery newDelivery = deliveryRepository.save(delivery);

        Assert.assertEquals(Optional.of(15L).get(), newDelivery.getId());
    }
}
