package com.test.decorator_pattern;

public class CoffeeBean1 implements Beverage{

    private String description = "第一种咖啡豆";

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public Double getPrices() {
        return 30.00;
    }
}
