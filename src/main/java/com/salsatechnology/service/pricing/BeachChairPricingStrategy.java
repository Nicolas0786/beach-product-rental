package com.salsatechnology.service.pricing;

import com.salsatechnology.model.ProductType;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class BeachChairPricingStrategy implements ProductPricingStrategy {

    @Override
    public ProductType getProductType() {
        return ProductType.BEACH_CHAIR;
    }

    @Override
    public BigDecimal getPriceHour() {
        return new BigDecimal("35.00");
    }

    @Override
    public BigDecimal getEmployeePercentage() {
        return new BigDecimal("5");
    }
}
