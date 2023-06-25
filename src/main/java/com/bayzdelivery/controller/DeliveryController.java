package com.bayzdelivery.controller;

import com.bayzdelivery.model.Delivery;
import com.bayzdelivery.model.DeliveryReport;
import com.bayzdelivery.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/delivery")
public class DeliveryController {

  @Autowired
  DeliveryService deliveryService;

  @PostMapping
  public ResponseEntity<Delivery> createNewDelivery(@RequestBody Delivery delivery) {
    return ResponseEntity.ok(deliveryService.save(delivery));
  }

  @PostMapping(path = "/{delivery-id}")
  public ResponseEntity<Delivery> endDelivery(@PathVariable(name="delivery-id",required=true)Long deliveryId) {
    return ResponseEntity.ok(deliveryService.save(deliveryId));
  }

  @GetMapping(path = "/{delivery-id}")
  public ResponseEntity<Delivery> getDeliveryById(@PathVariable(name="delivery-id",required=true)Long deliveryId){
    return ResponseEntity.ok(deliveryService.findById(deliveryId));
  }

  @GetMapping(path = "/top-delivery-men")
  public ResponseEntity<List<DeliveryReport>> getTopDeliveryMen(
          @RequestParam String startDate,
          @RequestParam String endDate
  ){
    return ResponseEntity.ok(
            deliveryService.getTopDeliveryMen(startDate,endDate)
    );
  }
}
