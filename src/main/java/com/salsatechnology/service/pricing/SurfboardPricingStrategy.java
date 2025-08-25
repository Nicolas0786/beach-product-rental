package com.salsatechnology.service.pricing;

import com.salsatechnology.model.ProductType;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class SurfboardPricingStrategy implements ProductPricingStrategy {

    @Override
    public ProductType getProductType() {
        return ProductType.SURFBOARD;
    }

    @Override
    public BigDecimal getPriceHour() {
        return new BigDecimal("50.00");
    }

    @Override
    public BigDecimal getEmployeePercentage() {
        return new BigDecimal("15.6");
    }
}
