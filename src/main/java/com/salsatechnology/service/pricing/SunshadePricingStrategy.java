package com.salsatechnology.service.pricing;

import com.salsatechnology.model.ProductType;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class SunshadePricingStrategy implements ProductPricingStrategy {

    @Override
    public ProductType getProductType() {
        return ProductType.SUNSHADE;
    }

    @Override
    public BigDecimal getPriceHour() {
        return new BigDecimal("40.00");
    }

    @Override
    public BigDecimal getEmployeePercentage() {
        return new BigDecimal("10.3");
    }
}
