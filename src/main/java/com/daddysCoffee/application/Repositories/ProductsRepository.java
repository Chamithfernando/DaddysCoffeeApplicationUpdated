package com.daddysCoffee.application.Repositories;

import com.daddysCoffee.application.Entities.Products;
import org.springframework.data.jpa.repository.JpaRepository;

//ProductsRepository is extended JpaRepository
public interface ProductsRepository extends JpaRepository<Products,Integer> {

}
