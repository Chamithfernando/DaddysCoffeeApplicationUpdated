package com.daddysCoffee.application.Repositories;

import com.daddysCoffee.application.Entities.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

//OrderRepository is extended JpaRepository
public interface OrderRepository extends JpaRepository<Orders,Integer> {


}
