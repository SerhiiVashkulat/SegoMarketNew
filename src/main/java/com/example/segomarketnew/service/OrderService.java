package com.example.segomarketnew.service;

import com.example.segomarketnew.domain.model.Order;
import com.example.segomarketnew.domain.model.OrderStatus;

import java.util.List;

public interface OrderService {
    void saveOrder (Order order);
    List<Order> checkOrders (String name);
    Order statusOrder( Long id);
    void changeStatusOrder(Long id, OrderStatus status);
    List<Order> allOrder();
}
