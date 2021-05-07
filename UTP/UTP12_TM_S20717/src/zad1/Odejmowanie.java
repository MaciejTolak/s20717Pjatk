package zad1;

import java.math.BigDecimal;

public class Odejmowanie {
    public BigDecimal odejmowanie (BigDecimal bigDecimal1, BigDecimal bigDecimal2){
        return bigDecimal1.subtract(bigDecimal2).stripTrailingZeros();
    }




}
