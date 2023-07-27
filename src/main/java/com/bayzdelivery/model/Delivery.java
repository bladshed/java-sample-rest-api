package com.bayzdelivery.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Instant;

@Entity
@Table(name = "delivery")
public class Delivery implements Serializable{

  private static final long serialVersionUID = 123765351514001L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @NotNull
  @Column(name = "start_time")
  Instant startTime;

  @Column(name = "end_time")
  Instant endTime;

  @Column(name = "distance")
  Integer distance;

  @Column(name = "price")
  Float price;

  @Column(name = "commission")
  Float commission;

  @ManyToOne
  @JoinColumn(name = "delivery_man_id", referencedColumnName = "id")
  Person deliveryMan;

  @ManyToOne
  @JoinColumn(name = "customer_id", referencedColumnName = "id")
  Person customer;

  public Delivery() {
  }

  public Delivery(Instant startTime, Instant endTime, Integer distance, Float price, Float commission, Person deliveryMan, Person customer) {
    this.startTime = startTime;
    this.endTime = endTime;
    this.distance = distance;
    this.price = price;
    this.commission = commission;
    this.deliveryMan = deliveryMan;
    this.customer = customer;
  }

  public Delivery(Long id, Instant startTime, Instant endTime, Integer distance, Float price, Float commission, Person deliveryMan, Person customer) {
    this.id = id;
    this.startTime = startTime;
    this.endTime = endTime;
    this.distance = distance;
    this.price = price;
    this.commission = commission;
    this.deliveryMan = deliveryMan;
    this.customer = customer;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Instant getStartTime() {
    return startTime;
  }

  public void setStartTime(Instant startTime) {
    this.startTime = startTime;
  }

  public Instant getEndTime() {
    return endTime;
  }

  public void setEndTime(Instant endTime) {
    this.endTime = endTime;
  }

  public Integer getDistance() {
    return distance;
  }

  public void setDistance(Integer distance) {
    this.distance = distance;
  }

  public Float getPrice() {
    return price;
  }

  public void setPrice(Float price) {
    this.price = price;
  }

  public Float getCommission() {
    return commission;
  }

  public void setCommission(Float commission) {
    this.commission = commission;
  }

  public Person getDeliveryMan() {
    return deliveryMan;
  }

  public void setDeliveryMan(Person deliveryMan) {
    this.deliveryMan = deliveryMan;
  }

  public Person getCustomer() {
    return customer;
  }

  public void setCustomer(Person customer) {
    this.customer = customer;
  }

  @Override
  public String toString() {
    return "Delivery [id=" + id + ", startTime=" + startTime + ", endTime=" + endTime + ", distance=" + distance + ", deliveryMan=" + deliveryMan + ", customer=" + customer + "]";
  }
}
