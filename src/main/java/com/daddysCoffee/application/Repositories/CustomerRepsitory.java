package com.daddysCoffee.application.Repositories;

import com.daddysCoffee.application.Entities.Customers;
import org.springframework.data.jpa.repository.JpaRepository;

//CustomerRepsitory is extended JpaRepository
public interface CustomerRepsitory extends JpaRepository<Customers,Integer> {

}
