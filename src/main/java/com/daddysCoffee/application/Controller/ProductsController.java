package com.daddysCoffee.application.Controller;

import com.daddysCoffee.application.Entities.Products;
import com.daddysCoffee.application.Repositories.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductsController {


    //Creating Dependency Injection for ProductsRepository, because we want to talk with database
    @Autowired
    private ProductsRepository productsRepository;

    //Constructor
    public ProductsController(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    //view all products in the shop
    @GetMapping("/all")
    Collection<Products> getAllProducts(){
       return productsRepository.findAll();
    }



    //View products from given productId
    @GetMapping("/all/{productId}")
    ResponseEntity<?> getProductsById(@PathVariable  int productId){
        Optional<Products> result = productsRepository.findById(productId);
        return result.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

}
