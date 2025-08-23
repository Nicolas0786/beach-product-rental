package com.salsatechnology.mapper;

import com.salsatechnology.dto.ProductOrderResponseDTO;
import com.salsatechnology.model.ProductOrder;
import org.springframework.stereotype.Component;

@Component
public class ProductOrderMapper {

    public static ProductOrderResponseDTO toProductOrderResponseDTO(ProductOrder productOrder) {
        if (productOrder == null) return null;
        ProductOrderResponseDTO productOrderResponseDTO = new ProductOrderResponseDTO();
        productOrderResponseDTO.setUserName(productOrder.getUserName());
        productOrderResponseDTO.setProductType(productOrder.getProductType());
        productOrderResponseDTO.setTimeHour(productOrder.getTimeHour());
        productOrderResponseDTO.setProductValue(productOrder.getProductValue());
        productOrderResponseDTO.setProductTotal(productOrder.getProductTotal());
        productOrderResponseDTO.setUserAmount(productOrder.getUserAmount());
        return productOrderResponseDTO;
    }

}
