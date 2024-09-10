package com.example.order.service;

import com.example.order.dto.OrderDTO;
import com.example.order.model.Orders;
import com.example.order.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<OrderDTO> getALLOrders(){
        List<Orders> orders = orderRepository.findAll();
        return modelMapper.map(orders,new TypeToken<List<OrderDTO>>(){}.getType());
    }

    public OrderDTO getOrderById(Integer orderID){
        Orders order = orderRepository.getOrderById(orderID);
        return modelMapper.map(order,OrderDTO.class);
    }

    public OrderDTO addOrder(OrderDTO orderDTO){
        orderRepository.save(modelMapper.map(orderDTO,Orders.class));
        return orderDTO;
    }
    public OrderDTO updateOrder(OrderDTO orderDTO){
        orderRepository.save(modelMapper.map(orderDTO,Orders.class));
        return orderDTO;
    }
    public  String deleteOrder(Integer orderID){
        orderRepository.deleteById(orderID);
        return "Order "+orderID+" Deleted Successfully";
    }
}
