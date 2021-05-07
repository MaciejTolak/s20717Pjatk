package zad1;

import java.math.BigDecimal;

public class Dzielenie {
    public BigDecimal podziel (BigDecimal bigDecimal1, BigDecimal bigDecimal2){
        return bigDecimal1.divide(bigDecimal2,7,BigDecimal.ROUND_HALF_UP).stripTrailingZeros();
    }
}
