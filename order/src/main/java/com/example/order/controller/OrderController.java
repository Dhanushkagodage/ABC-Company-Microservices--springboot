package com.example.order.controller;


import com.example.order.dto.OrderDTO;
import com.example.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "api/ABC/")

public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/getorders")
    public List<OrderDTO> getOrders() {
        return orderService.getALLOrders();
    }
    @GetMapping("/getorder/{orderID}")
    public OrderDTO getOrderById(@PathVariable("orderID") Integer orderID) {
        return orderService.getOrderById(orderID);
    }

    @PostMapping("/addorder")
    public OrderDTO addOrder(@RequestBody OrderDTO orderDTO) {
        return orderService.addOrder(orderDTO);
    }

    @PutMapping("/updateorder")
    public OrderDTO updateOrder(@RequestBody OrderDTO orderDTO) {
        return orderService.updateOrder(orderDTO);
    }

    @DeleteMapping("/deleteorder/{orderID}")
    public String deleteOrder(@PathVariable("orderID") Integer orderID) {
        return orderService.deleteOrder(orderID);
    }
}
