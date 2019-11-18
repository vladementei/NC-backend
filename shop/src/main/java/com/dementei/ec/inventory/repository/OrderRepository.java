package com.dementei.ec.inventory.repository;

import com.dementei.ec.inventory.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
