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
    private long amountProducts;
    private Double sum;
    private List<BucketDetailsDto> bucketDetails = new ArrayList<>();

    public Double infoBucket(){
        this.amountProducts = bucketDetails.size();
         return this.sum = bucketDetails.stream()
                .map(BucketDetailsDto::getSum)
                .mapToDouble(Double::doubleValue)
                .sum();
    }
}
