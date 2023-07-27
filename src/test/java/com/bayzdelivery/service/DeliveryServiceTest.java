package com.bayzdelivery.service;

import com.bayzdelivery.model.Delivery;
import com.bayzdelivery.model.DeliveryReport;
import com.bayzdelivery.repositories.PersonRepository;
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
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(value = "test")
@Sql({"/schema.sql", "/data.sql"})
@Transactional
public class DeliveryServiceTest {

    @Autowired
    DeliveryService deliveryService;

    @Autowired
    PersonRepository personRepository;

    @Test
    public void testTopDeliveryMenSize(){
        List<DeliveryReport> deliveryReports = deliveryService.getTopDeliveryMen("2023-01-01", "2023-12-01");

        Assert.assertTrue(deliveryReports.size() > 0);
    }

    @Test
    public void findDeliveryById(){
        Delivery delivery = deliveryService.findById(1L);

        Assert.assertEquals("Agnes", delivery.getCustomer().getName());
    }

    @Test
    public void createNewDeliveryRequest(){
        Delivery delivery = new Delivery();
        delivery.setPrice(11.0F);
        delivery.setDistance(10);;
        delivery.setDeliveryMan(personRepository.findById(1L).get());
        delivery.setCustomer(personRepository.findById(7L).get());
        delivery.setStartTime(Instant.now());

        Delivery newDelivery = deliveryService.save(delivery);

        Assert.assertEquals(Optional.of(15L).get(), newDelivery.getId());
    }
}
