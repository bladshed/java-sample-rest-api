package com.bayzdelivery.controller;

import com.bayzdelivery.model.Delivery;
import com.bayzdelivery.model.Person;
import com.bayzdelivery.service.DeliveryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.Instant;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(DeliveryController.class)
public class DeliveryControllerTest {

  @Autowired
  MockMvc mockMvc;

  @MockBean
  private DeliveryService deliveryService;

  @Test
  public void endDelivery() throws Exception {

    Person deliveryMan = new Person();
    deliveryMan.setId(2L);

    Person customer = new Person();
    customer.setId(1L);

    Delivery delivery = new Delivery();
    delivery.setId(1L);
    delivery.setDistance(50);
    delivery.setPrice(50F);
    delivery.setCommission(27.5F);
    delivery.setStartTime(Instant.now());
    delivery.setEndTime(Instant.now());
    delivery.setDeliveryMan(deliveryMan);
    delivery.setCustomer(customer);

    when(deliveryService.save(1L))
            .thenReturn(delivery);

    RequestBuilder request = MockMvcRequestBuilders
            .post("/delivery/1")
            .contentType(MediaType.APPLICATION_JSON);

    MvcResult result = mockMvc.perform(request)
            .andExpect(status().isOk())
            .andReturn();
  }

  @Test
  public void getDeliveryById() throws Exception {
    Person deliveryMan = new Person();
    deliveryMan.setId(2L);

    Person customer = new Person();
    customer.setId(1L);

    Delivery delivery = new Delivery();
    delivery.setDistance(50);
    delivery.setCommission(27.5F);
    delivery.setStartTime(Instant.now());
    delivery.setEndTime(Instant.now());
    delivery.setDeliveryMan(deliveryMan);
    delivery.setCustomer(customer);

    when(deliveryService.findById(1L))
            .thenReturn(delivery);

    RequestBuilder request = MockMvcRequestBuilders
            .get("/delivery/1")
            .accept(MediaType.APPLICATION_JSON);

    MvcResult result = mockMvc.perform(request)
            .andExpect(status().isOk())
            .andReturn();
  }

}
