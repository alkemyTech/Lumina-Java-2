package com.alkemy.wallet.utils;

import java.math.BigDecimal;

public abstract class SimpleInterestCalculator {
    protected Double divisor;
    public abstract Double calculate(Double principalAmount, Float interestRate, Integer timePeriod);
}
