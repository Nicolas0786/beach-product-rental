package com.salsatechnology.enums;

public enum ProductPricingEnum implements ProductPricing {
    SURFBOARD(50.00, 15.6),
    BEACH_CHAIR(35.00, 5),
    SUNSHADE(40.00, 10.3),
    SAND_BOARD(25.00, 9),
    BEACH_TABLE(25.00, 8.1);

    private final double priceHour;
    private final double employeePercentage;

    ProductPricingEnum(double priceHour, double employeePercentage) {
        this.priceHour = priceHour;
        this.employeePercentage = employeePercentage;
    }

    @Override
    public double getPriceHour() {
        return this.priceHour;
    }

    @Override
    public double getEmployeePercentage() {
        return this.employeePercentage;
    }
}
