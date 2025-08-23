package com.salsatechnology.dto;

import lombok.Data;

@Data
public class ProductOrderResponseDTO extends ProductOrderDTO {

    private Long productValue;

    private Long productTotal;

    private Long userAmount;
}
