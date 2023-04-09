package com.alkemy.wallet.utils;

import java.math.BigDecimal;

public class SimpleInterestPerDayCalculator extends SimpleInterestCalculator{
    public SimpleInterestPerDayCalculator(){
        super.divisor = 100 * 365D;
    }
    @Override
    public Double calculate(Double principalAmount, Float interestRate, Integer timePeriod) {
        Double ret = (principalAmount * interestRate * timePeriod) / divisor;
        return ret;
    }
}
