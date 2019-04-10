package com.test.decorator_pattern;

public class Mocha extends Decorator {

    public String description = "摩卡咖啡";

    public Beverage beverage;

    public Mocha(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + " \n " + description;
    }

    @Override
    public Double getPrices() {
        return beverage.getPrices() + 40;
    }
}
