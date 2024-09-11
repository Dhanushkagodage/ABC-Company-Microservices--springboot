package com.example.order.service;

import com.example.inventory.dto.InventoryDTO;
import com.example.order.common.ErrorOrderResponse;
import com.example.order.common.OrderResponse;
import com.example.order.common.SuccessOrderResponse;
import com.example.order.dto.OrderDTO;
import com.example.order.model.Orders;
import com.example.order.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.example.product.dto.ProductDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;

@Service
@Transactional
public class OrderService {
    private final WebClient inventoryWebClient;
    private final WebClient productWebClient;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ModelMapper modelMapper;

    public OrderService(WebClient inventoryWebClient,WebClient productWebClient, WebClient.Builder webClientBuilder, OrderRepository orderRepository,ModelMapper modelMapper) {
        this.inventoryWebClient = inventoryWebClient;
        this.productWebClient = productWebClient;
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
    }


    public List<OrderDTO> getALLOrders(){
        List<Orders> orders = orderRepository.findAll();
        return modelMapper.map(orders,new TypeToken<List<OrderDTO>>(){}.getType());
    }

    public OrderDTO getOrderById(Integer orderID){
        Orders order = orderRepository.getOrderById(orderID);
        return modelMapper.map(order,OrderDTO.class);
    }

    public OrderResponse addOrder(OrderDTO orderDTO){

        Integer itemId = orderDTO.getItemId();

        try{
            InventoryDTO inventoryResponse = inventoryWebClient.get()
                    .uri(uriBuilder -> uriBuilder.path("/getitem/{itemId}").build(itemId))
                    .retrieve()
                    .bodyToMono(InventoryDTO.class)
                    .block();

            assert inventoryResponse != null;

            Integer productId = inventoryResponse.getProductId();

            ProductDTO productResponse = productWebClient.get()
                    .uri(uriBuilder -> uriBuilder.path("/getproduct/{productId}").build(productId))
                    .retrieve()
                    .bodyToMono(ProductDTO.class)
                    .block();

            assert productResponse != null;
            if (inventoryResponse.getQuantity()>0){
                if (productResponse.getForSale()==1) {
                    orderRepository.save(modelMapper.map(orderDTO, Orders.class));
                    return new SuccessOrderResponse(orderDTO);
                }else {
                    return new ErrorOrderResponse("This item is not for sale");
                }
            }else {
                return new ErrorOrderResponse("Item is not available");
            }

        }catch (WebClientResponseException e){
            if (e.getStatusCode().is5xxServerError()){
                return  new ErrorOrderResponse("Item is not found");
            }
        }
        return null;
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
