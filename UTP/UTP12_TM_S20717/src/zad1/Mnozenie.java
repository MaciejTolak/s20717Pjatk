package zad1;

import java.math.BigDecimal;

public class Mnozenie {
    public BigDecimal pomnoz (BigDecimal bigDecimal1, BigDecimal bigDecimal2){
        return bigDecimal1.multiply(bigDecimal2).stripTrailingZeros();
    }
}
