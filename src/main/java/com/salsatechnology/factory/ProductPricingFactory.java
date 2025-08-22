package com.salsatechnology.factory;

import com.salsatechnology.enums.ProductPricing;
import com.salsatechnology.enums.ProductPricingEnum;
import com.salsatechnology.model.ProductType;

import java.util.EnumMap;
import java.util.Map;

public final class ProductPricingFactory {

    private static final Map<ProductType, ProductPricing> REGISTRY = new EnumMap<>(ProductType.class);

    static {
        REGISTRY.put(ProductType.SURFBOARD, ProductPricingEnum.SURFBOARD);
        REGISTRY.put(ProductType.BEACH_CHAIR, ProductPricingEnum.BEACH_CHAIR);
        REGISTRY.put(ProductType.SUNSHADE, ProductPricingEnum.SUNSHADE);
        REGISTRY.put(ProductType.SAND_BOARD, ProductPricingEnum.SAND_BOARD);
        REGISTRY.put(ProductType.BEACH_TABLE, ProductPricingEnum.BEACH_TABLE);
    }

    private ProductPricingFactory() {}

    public static ProductPricing of(ProductType productType) {
        ProductPricing productPricing = REGISTRY.get(productType);
        if (productPricing == null) {
            throw new IllegalArgumentException("Tipo não suportado: " + productType);
        }
        return productPricing;
    }

}
