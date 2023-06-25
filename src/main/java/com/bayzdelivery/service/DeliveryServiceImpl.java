package com.bayzdelivery.service;

import com.bayzdelivery.exceptions.ApiRequestException;
import com.bayzdelivery.exceptions.NotFoundException;
import com.bayzdelivery.model.Delivery;
import com.bayzdelivery.model.DeliveryReport;
import com.bayzdelivery.repositories.DeliveryRepository;
import com.bayzdelivery.repositories.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class DeliveryServiceImpl implements DeliveryService {

  private static final Logger LOG = LoggerFactory.getLogger(DeliveryServiceImpl.class);

  @Autowired
  DeliveryRepository deliveryRepository;

  @Autowired
  PersonRepository personRepository;

  public Delivery save(Delivery delivery) {
    LOG.info("Creating new delivery request.");
    validateStart(delivery);
    delivery.setStartTime(Instant.now());

    return deliveryRepository.save(delivery);
  }

  public Delivery save(long id) {
    LOG.info("Ending delivery request.");
    Delivery delivery = validateEnd(id);

    delivery.setEndTime(Instant.now());
    UtilService.setCommission(delivery);

    return deliveryRepository.save(delivery);
  }

  public Delivery findById(Long deliveryId) {
    LOG.info("Finding delivery by id.");
    Optional<Delivery> optionalDelivery = deliveryRepository.findById(deliveryId);
    if(!optionalDelivery.isPresent())
      throw new NotFoundException("Delivery Request not found.");
    return optionalDelivery.get();
  }

  @Override
  public List<DeliveryReport> getTopDeliveryMen(String startDate, String endDate) {
    return deliveryRepository.getTopDeliveryMen(startDate, UtilService.incrementDate(endDate));
  }

  private void validateStart(Delivery delivery){
    LOG.info("Validating new request delivery.");
    personRepository
      .findByPersonByType(delivery.getDeliveryMan().getId(), 'D')
      .orElseThrow(()-> {
        return new NotFoundException("Delivery Man not found.");
      }
    );

    personRepository
      .findByPersonByType(delivery.getCustomer().getId(), 'C')
      .orElseThrow(()-> {
        return new NotFoundException("Customer not found.");
      }
    );

    if(deliveryRepository.findDeliveryManActiveOrder(
            delivery.getDeliveryMan().getId()).isPresent()
    )
      throw new ApiRequestException("Can not take more than one order.");
  }

  private Delivery validateEnd(long id){
    LOG.info("Validating exiting delivery.");
    Delivery delivery = deliveryRepository.findById(id).orElseThrow(
      ()-> {
        return new NotFoundException("No delivery request found.");
      }
    );

    if (delivery.getEndTime() != null)
      throw new ApiRequestException("Delivery request is done.");

    return delivery;
  }
}
