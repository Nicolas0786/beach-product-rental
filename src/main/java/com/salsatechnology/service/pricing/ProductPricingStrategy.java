package com.salsatechnology.service.pricing;

import com.salsatechnology.model.ProductType;

import java.math.BigDecimal;

public interface ProductPricingStrategy {
    ProductType getProductType();
    BigDecimal getPriceHour();
    BigDecimal getEmployeePercentage();
}
