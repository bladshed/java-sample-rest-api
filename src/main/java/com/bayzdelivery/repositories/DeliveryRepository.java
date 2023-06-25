package com.bayzdelivery.repositories;

import com.bayzdelivery.model.Delivery;
import com.bayzdelivery.model.DeliveryReport;
import com.bayzdelivery.model.LateDelivery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;
import java.util.Optional;

@RestResource(exported = false)
public interface DeliveryRepository extends CrudRepository<Delivery, Long> {

    @Query(
            value = "SELECT * FROM delivery " +
                    "WHERE delivery_man_id = ?1 " +
                    "AND end_time is null",
            nativeQuery = true
    )
    Optional<Delivery> findDeliveryManActiveOrder(long id);

    @Query(
            value = "SELECT d.delivery_man_id as id, p.name, ROUND(SUM(commission)\\:\\:numeric, 2) as sum, ROUND(AVG(commission)\\:\\:numeric, 2) as average " +
                    "FROM delivery d " +
                    "INNER JOIN person p ON p.id = d.delivery_man_id " +
                    "WHERE d.start_time >= to_timestamp( ?1, 'YYYY-MM-DD') " +
                    "AND d.end_time < to_timestamp( ?2, 'YYYY-MM-DD') " +
                    "GROUP BY d.delivery_man_id, p.name " +
                    "ORDER BY SUM(commission) DESC " +
                    "LIMIT 3",
            nativeQuery = true
    )
    List<DeliveryReport> getTopDeliveryMen(String startDate, String endDate);

    @Query(
            value = "SELECT d.delivery_man_id as id, p.name, d.start_time as StartTime " +
                    "FROM person p " +
                    "INNER JOIN " +
                    "(SELECT id, delivery_man_id, start_time, end_time FROM delivery " +
                    "WHERE end_time is null) as d " +
                    "ON d.delivery_man_id = p.id " +
                    "WHERE d.start_time <= to_timestamp(?1, 'YYYY-MM-DD HH24:MI:SS')",
            nativeQuery = true
    )
    List<LateDelivery> getLateDeliveries(String dateTimeNow);

}
