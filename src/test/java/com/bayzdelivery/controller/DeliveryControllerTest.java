package com.bayzdelivery.controller;

import com.bayzdelivery.BayzDeliveryApplication;
import com.bayzdelivery.model.Delivery;
import com.bayzdelivery.model.DeliveryReport;
import com.bayzdelivery.model.Person;
import com.bayzdelivery.model.Role;
import com.bayzdelivery.service.DeliveryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;
import java.util.Arrays;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = {
                BayzDeliveryApplication.class,
                DeliveryController.class
        },
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@ActiveProfiles(value = "test")
public class DeliveryControllerTest {

  private Person customer = new Person(1L,"test1", "password", "Test1",
          "test1@yahoo.com", 'C', "123TST", new Role());

  private Person deliveryMan = new Person(2L,"test2", "password", "Test2",
          "test2@yahoo.com", 'D', "321TST", new Role());

  Delivery delivery = new Delivery(1L, Instant.now(), Instant.now(), 50,
          50.0F, 27.5F, deliveryMan, customer);

  @Autowired
  private TestRestTemplate restTemplate;

  @Autowired
  private JwtRequestHelper jwtRequestHelper;

  @MockBean
  private DeliveryService deliveryService;

  public class DeliveryReportImpl implements DeliveryReport {

    @Override
    public Long getId() {
      return null;
    }

    @Override
    public String getName() {
      return null;
    }

    @Override
    public Float getSum() {
      return null;
    }

    @Override
    public Float getAverage() {
      return null;
    }
  }

  @Test
  public void getDeliveryById() {
    when(deliveryService.findById(1L)).thenReturn(delivery);

    ResponseEntity<Delivery> response = restTemplate.exchange("/delivery/1",
            GET,
            new HttpEntity(null,jwtRequestHelper.withRole("ROLE_ADMIN")),
            Delivery.class);

    assertThat(response.getStatusCode().value(), is(200));
    assertThat(response.getBody().getDeliveryMan().getUsername(), is(deliveryMan.getUsername()));
    assertThat(response.getBody().getCustomer().getUsername(), is(customer.getUsername()));
  }

  @Test
  public void getTopDeliveryMen() {
    when(deliveryService.getTopDeliveryMen(Mockito.anyString(), Mockito.anyString()))
            .thenReturn(Arrays.asList(new DeliveryReportImpl(), new DeliveryReportImpl()));

    ResponseEntity<String> response = restTemplate.exchange("/delivery/top-delivery-men?startDate=2023-01-01&endDate=2023-12-01",
            GET,
            new HttpEntity(null,jwtRequestHelper.withRole("ROLE_ADMIN")),
            String.class);

    assertThat(response.getStatusCode().value(), is(200));
  }

  @Test
  public void testCreateNewDeliveryRequest() {
    when(deliveryService.save(delivery))
            .thenReturn(delivery);

    ResponseEntity<Delivery> response = restTemplate.exchange("/delivery",
            POST,
            new HttpEntity(delivery,jwtRequestHelper.withRole("ROLE_ADMIN")),
            Delivery.class);

    assertThat(response.getStatusCode().value(), is(200));
  }

}
