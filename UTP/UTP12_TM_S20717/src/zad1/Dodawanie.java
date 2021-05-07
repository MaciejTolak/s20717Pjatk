package zad1;

import java.math.BigDecimal;

public class Dodawanie {
    public BigDecimal dodaj (BigDecimal bigDecimal1, BigDecimal bigDecimal2){
        return bigDecimal1.add(bigDecimal2).stripTrailingZeros();
    }
}
