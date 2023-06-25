package com.bayzdelivery.model;

import java.time.Instant;

public interface LateDelivery {
    Long getId();
    String getName();
    Instant getStartTime();
}
