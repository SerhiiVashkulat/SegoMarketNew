package com.example.segomarketnew.repository;

import com.example.segomarketnew.domain.model.Order;
import com.example.segomarketnew.domain.model.OrderDetails;
import com.example.segomarketnew.domain.model.Product;
import com.example.segomarketnew.domain.model.User;
import org.aspectj.weaver.ast.Or;
import org.mapstruct.control.MappingControl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    Order findOrderById(Long id);
    boolean existsOrderById(Long id);
    List<Order> findAllByUserId (Long id);

}
