package com.daddysCoffee.application.Controller;

import com.daddysCoffee.application.Entities.Customers;

import com.daddysCoffee.application.Repositories.CustomerRepsitory;
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
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerRepsitory customerRepsitory;

    public CustomerController(CustomerRepsitory customerRepsitory) {
        this.customerRepsitory = customerRepsitory;
    }

    //Get all customers details
    @GetMapping("/all")
    Collection<Customers> findallCustomers(){
        return customerRepsitory.findAll();
    }



    //Get customer details from given Id
    @GetMapping("/all/{CustomerId}")
    ResponseEntity<?> findCustomerbyId(@PathVariable int CustomerId){
        Optional<Customers> result = customerRepsitory.findById(CustomerId);
        return result.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }



    //Creating a NewCustomer /Place NewCustomer
    @PostMapping("/add")
    ResponseEntity<Customers> createNewCustomer(@Validated @RequestBody Customers customers)throws URISyntaxException {
        Customers newCustomer = customerRepsitory.save(customers);
        return ResponseEntity
                .created(new URI("customer/add"+ newCustomer.getCustomerId())).body(newCustomer);
    }


    //Updating existing Customer
    @PutMapping("/update/{id}")
    public ResponseEntity<Customers> updateCustomer(@PathVariable(value = "id") int CustomerId ,
                                                      @Validated @RequestBody Customers customers) throws ResourceNotFoundException {
        Customers result = customerRepsitory.findById(CustomerId)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found for this id :: " + CustomerId));

        result.setCustomerId(customers.getCustomerId());
        result.setCustomerUserName(customers.getCustomerUserName());
        result.setCustomerPassword(customers.getCustomerPassword());
        final Customers updatedAppoints = customerRepsitory.save(result);
        return ResponseEntity.ok(updatedAppoints);
    }



    // Deleting existing Customer
        @DeleteMapping("/delete/{id}")
        public Map<String, Boolean> deleteCustomer(@PathVariable(value = "id") int CustomerId)
            throws ResourceNotFoundException {
        Customers result = customerRepsitory.findById(CustomerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found for this id :: " + CustomerId));

        customerRepsitory.delete(result);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
