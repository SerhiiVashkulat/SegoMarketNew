package com.example.segomarketnew.controller;

import com.example.segomarketnew.domain.model.Order;
import com.example.segomarketnew.domain.model.OrderStatus;
import com.example.segomarketnew.domain.response.Response;
import com.example.segomarketnew.dto.OrderDto;
import com.example.segomarketnew.mapper.OrderDetailsMapper;
import com.example.segomarketnew.mapper.OrderMapper;
import com.example.segomarketnew.service.serviceImpl.OrderServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderServiceImpl orderService;
    private final OrderMapper orderMapper = OrderMapper.mapper;
    private final OrderDetailsMapper orderDetailsMapper = OrderDetailsMapper.mapper;


    @GetMapping
    ResponseEntity <List<OrderDto>> checkAllOrdersByUser(Principal principal){
        List<Order> orderList = orderService.checkOrders(principal.getName());
        List<OrderDto> list = new ArrayList<>();
      for (int i = 0; i < orderList.size() ; i++){
          list.add(OrderDto.builder()
                          .status(orderList.get(i).getStatus())
                          .products(orderDetailsMapper.toListDto(orderList.get(i).getDetails()))
                          .id(orderList.get(i).getId())
                          .address(orderList.get(i).getAddress())
                                  .updated(orderList.get(i).getUpdated())
                                          .sum(orderList.get(i).getSum()).
                  build());
      }
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    ResponseEntity<OrderDto> checkOrderUserById(@PathVariable Long id){
        OrderDto orderDto = orderMapper.toDto(orderService.statusOrder(id));
       orderDto.setProducts( orderDetailsMapper.toListDto(orderService.statusOrder(id).getDetails()));
        return ResponseEntity.ok(orderDto);
    }
    @PreAuthorize("hasAuthority('MANAGER')")
    @PostMapping("/{id}")
    ResponseEntity<Response> changeOrderStatus(@RequestBody String orderStatus,
                                               @PathVariable Long id){

        orderService.changeStatusOrder(id,OrderStatus.valueOf(orderStatus.toUpperCase(Locale.ROOT)));
        return new ResponseEntity(Response.builder()
                .massage("Order status number " + id + " change to " + orderStatus)
                .build(), HttpStatus.ACCEPTED);

    }
    @PreAuthorize("hasAuthority('MANAGER')")
    @GetMapping("/all")
    ResponseEntity<List<OrderDto>> allOrder(){

        return ResponseEntity.ok(orderMapper.toListDto(orderService.allOrder()));
    }

}
