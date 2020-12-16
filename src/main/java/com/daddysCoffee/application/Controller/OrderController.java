package com.daddysCoffee.application.Controller;

import com.daddysCoffee.application.Entities.Orders;
import com.daddysCoffee.application.Repositories.OrderRepository;
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
@RequestMapping("/order")
public class OrderController {


    //Creating Dependency Injection for OrderRepository, because we want to talk with database
    @Autowired
    private OrderRepository orderRepository;

    //Constructor
    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    //Get all Order details
    @GetMapping("/all")
    Collection<Orders> findallOrders(){
        return orderRepository.findAll();
    }


    //Get Order details from given Id
    @GetMapping("/all/{orderId}")
    ResponseEntity<?> findOrderbyId(@PathVariable  int orderId){
        Optional<Orders> result = orderRepository.findById(orderId);
        return result.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }



    //Creating a Neworder /Place an Order
    @PostMapping("/add")
    ResponseEntity<Orders> createOrder(@Validated @RequestBody Orders orders)throws URISyntaxException{
        Orders newOrder = orderRepository.save(orders);
        return ResponseEntity
                .created(new URI("order/add"+ newOrder.getOrderId())).body(newOrder);
    }

    //Updating existing Order
    @PutMapping("/update/{id}")
    public ResponseEntity<Orders> updateOrders(@PathVariable(value = "id") int orderId ,
                                                    @Validated @RequestBody Orders orders) throws ResourceNotFoundException {
        Orders result = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found for this id :: " + orderId));

        result.setOrderId(orders.getOrderId());
        result.setOrderDate(orders.getOrderDate());
        result.setOrderQuantity(orders.getOrderQuantity());
        final Orders updatedOrders = orderRepository.save(result);
        return ResponseEntity.ok(updatedOrders);
    }


    // Deleting existing Order
    @DeleteMapping("/delete/{id}")
    public Map<String, Boolean> deleteOrders(@PathVariable(value = "id") int orderId)
            throws ResourceNotFoundException {
        Orders result = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found for this id :: " + orderId));

        orderRepository.delete(result);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
