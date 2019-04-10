package com.test.decorator_pattern;

public class CoffeeBean2 implements Beverage {

    private String description = "猫屎咖啡";

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public Double getPrices() {
        return 60.00;
    }
}
