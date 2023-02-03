package com.example.segomarketnew.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BucketDto {
    private int amountProducts;
    private Double sum;
    private List<BucketDetailsDto> bucketDetailsDto = new ArrayList<>();

    public void infoBucket(){
        this.amountProducts = bucketDetailsDto.size();
        this.sum = bucketDetailsDto.stream()
                .map(BucketDetailsDto::getSum)
                .mapToDouble(Double::doubleValue)
                .sum();
    }
}
