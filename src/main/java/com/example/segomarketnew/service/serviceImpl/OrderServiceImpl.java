package com.example.segomarketnew.service.serviceImpl;

import com.example.segomarketnew.domain.model.*;
import com.example.segomarketnew.dto.OrderDetailsDTO;
import com.example.segomarketnew.repository.OrderRepository;
import com.example.segomarketnew.repository.UserRepository;
import com.example.segomarketnew.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

   private final UserRepository userRepository;

    private final OrderRepository orderRepository;

    @Override
    public void saveOrder(Order order) {
        orderRepository.save(order);
    }

    @Override
    public List<Order> checkOrders(String name) {
        User user = userRepository.findByName(name).orElseThrow(() -> new RuntimeException("User not found"));
        List<Order> orderList = orderRepository.findAllByUserId(user.getId());
        return orderList;
    }

    @Override
    public void changeStatusOrder(Long id,OrderStatus status) {
        Order order = orderRepository.findOrderById(id);
        order.setStatus(status);
        saveOrder(order);
    }

    @Override
    public Order statusOrder( Long id) {
        if (!orderRepository.existsOrderById(id)){
            throw new RuntimeException("Order not found ");
        }

        return orderRepository.findOrderById(id);
    }

    @Override
    public List<Order> allOrder() {
        return orderRepository.findAll();
    }
}
