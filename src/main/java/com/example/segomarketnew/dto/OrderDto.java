package com.example.segomarketnew.dto;

import com.example.segomarketnew.domain.model.Order;
import com.example.segomarketnew.domain.model.OrderDetails;
import com.example.segomarketnew.domain.model.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDto {

    private Long id;
    private OrderStatus status;
    private String address;
    private LocalDateTime updated;
    private BigDecimal sum;

    List<OrderDetailsDTO> products = new ArrayList<>();


}


