package com.test.decorator_pattern;

public class Decorator implements Beverage {

    private String description = "装饰者设计模式";

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public Double getPrices() {
        return 0.00;
    }
}
