package com.daddysCoffee.application.Controller;

import com.daddysCoffee.application.Entities.Products;
import com.daddysCoffee.application.Repositories.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
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

    //Creating a NewoProduct
    @PostMapping("/add")
    ResponseEntity<Products> createProduct(@Validated @RequestBody Products products)throws URISyntaxException {
        Products newProduct = productsRepository.save(products);
        return ResponseEntity
                .created(new URI("products/add"+ newProduct.getProductId())).body(newProduct);
    }

    // Updating existing Products
    @PutMapping("/update/{id}")
    public ResponseEntity<Products> updateProducts(@PathVariable(value = "id") int ProductId ,
                                               @Validated @RequestBody Products products) throws ResourceNotFoundException {
        Products result = productsRepository.findById(ProductId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + ProductId));

        result.setProductId(products.getProductId());
        result.setProductName(products.getProductName());
        result.setProductDiscription(products.getProductDiscription());
        final Products updatedProducts = productsRepository.save(result);
        return ResponseEntity.ok(updatedProducts);
    }


    // Deleting existing Products
    @DeleteMapping("/delete/{id}")
    public Map<String, Boolean> deleteProducts(@PathVariable(value = "id") int ProductId)
            throws ResourceNotFoundException {
        Products result = productsRepository.findById(ProductId)
                .orElseThrow(() -> new ResourceNotFoundException("ProductId not found for this id :: " + ProductId));

        productsRepository.delete(result);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
