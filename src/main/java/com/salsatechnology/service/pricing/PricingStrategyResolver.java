package com.salsatechnology.service.pricing;

import com.salsatechnology.model.ProductType;
import org.springframework.stereotype.Service;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Service
public class PricingStrategyResolver {

    private final Map<ProductType, ProductPricingStrategy> registry = new EnumMap<>(ProductType.class);

    public PricingStrategyResolver(List<ProductPricingStrategy> pricingStrategies) {
        for (ProductPricingStrategy strategy : pricingStrategies) registry.put(strategy.getProductType(), strategy);
    }

    public ProductPricingStrategy resolve(ProductType productType) {
        ProductPricingStrategy strategy = registry.get(productType);
        if (strategy == null) throw new IllegalArgumentException("Product type desconhecido: " + productType);
        return strategy;
    }
}
