package zad1;

import java.math.BigDecimal;

public class Dzialania {

    public BigDecimal dodaj (BigDecimal bigDecimal1, BigDecimal bigDecimal2){
        return bigDecimal1.add(bigDecimal2).stripTrailingZeros();
    }

    public BigDecimal podziel (BigDecimal bigDecimal1, BigDecimal bigDecimal2){
        return bigDecimal1.divide(bigDecimal2,7,BigDecimal.ROUND_HALF_UP).stripTrailingZeros();
    }

    public BigDecimal pomnoz (BigDecimal bigDecimal1, BigDecimal bigDecimal2){
        return bigDecimal1.multiply(bigDecimal2).stripTrailingZeros();
    }

    public BigDecimal odejmowanie (BigDecimal bigDecimal1, BigDecimal bigDecimal2){
        return bigDecimal1.subtract(bigDecimal2).stripTrailingZeros();
    }
}
