package com.test.decorator_pattern;

public class Milk extends Decorator {

    private String description = "加牛奶";

    public Beverage beverage;

    public Milk(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + " \n " + description;
    }

    @Override
    public Double getPrices() {
        return beverage.getPrices() + 30;
    }
}
