package zad3;

import java.util.function.Function;

public class InputConverter<T> {
    private T t;

    public InputConverter(T t) {
        this.t = t;
    }

    public <R> R convertBy(Function<?, ?>... functions) {
        Object obj = t;
        for (int i = 0; i < functions.length; i++) {
            obj = ((Function<Object, Object>) functions[i]).apply(obj);
        }
        return (R) obj;
    }

}
