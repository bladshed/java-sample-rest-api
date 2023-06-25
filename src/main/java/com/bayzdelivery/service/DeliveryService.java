package com.bayzdelivery.service;

import com.bayzdelivery.model.Delivery;
import com.bayzdelivery.model.DeliveryReport;

import java.util.List;

public interface DeliveryService {

  public Delivery save(Delivery delivery);

  public Delivery save(long delivery);

  public Delivery findById(Long deliveryId);

  public List<DeliveryReport> getTopDeliveryMen(String startDate, String endDate);
}
