package com.example.segomarketnew.mapper;

import com.example.segomarketnew.domain.model.Order;
import com.example.segomarketnew.dto.OrderDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OrderMapper {
    OrderMapper mapper = Mappers.getMapper(OrderMapper.class);
    OrderDto toDto(Order order);
    List<OrderDto> toListDto (List<Order> orders);
}