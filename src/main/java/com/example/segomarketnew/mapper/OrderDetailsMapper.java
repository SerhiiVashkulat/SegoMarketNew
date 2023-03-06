package com.example.segomarketnew.mapper;

import com.example.segomarketnew.domain.model.OrderDetails;
import com.example.segomarketnew.dto.OrderDetailsDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OrderDetailsMapper {
    OrderDetailsMapper mapper = Mappers.getMapper(OrderDetailsMapper.class);
    List<OrderDetailsDTO> toListDto(List<OrderDetails> orderDetails);
}