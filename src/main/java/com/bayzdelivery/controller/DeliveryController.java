package com.bayzdelivery.controller;

import com.bayzdelivery.model.Delivery;
import com.bayzdelivery.model.DeliveryReport;
import com.bayzdelivery.service.DeliveryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Delivery service", description = "Delivery APIs with description tag annotation")
@RestController
@RequestMapping("/delivery")
public class DeliveryController {

  @Autowired
  DeliveryService deliveryService;

  @GetMapping(path = "/{delivery-id}")
  @Operation(summary = "Get Delivery by id")
  public ResponseEntity<Delivery> getDeliveryById(@PathVariable(name="delivery-id",required=true)Long deliveryId){
    return ResponseEntity.ok(deliveryService.findById(deliveryId));
  }

  @GetMapping(path = "/top-delivery-men")
  @Operation(summary = "Get top delivery men")
  public ResponseEntity<List<DeliveryReport>> getTopDeliveryMen(
          @RequestParam String startDate,
          @RequestParam String endDate
  ){
    return ResponseEntity.ok(
            deliveryService.getTopDeliveryMen(startDate,endDate)
    );
  }

  @PostMapping
  @Operation(summary = "Create new Delivery request")
  public ResponseEntity<Delivery> createNewDelivery(@RequestBody Delivery delivery) {
    return ResponseEntity.ok(deliveryService.save(delivery));
  }

  @PostMapping(path = "/{delivery-id}")
  @Operation(summary = "End Delivery request")
  public ResponseEntity<Delivery> endDelivery(@PathVariable(name="delivery-id",required=true)Long deliveryId) {
    return ResponseEntity.ok(deliveryService.save(deliveryId));
  }
}
