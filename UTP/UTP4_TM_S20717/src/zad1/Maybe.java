package zad1;

import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Maybe<T> {
    private T t;

    public Maybe() {

    }

    public Maybe(T t) {
        this.t = t;
    }

    public static <T> Maybe<T> of(T t) {

        return new Maybe<>(t);
    }

    void ifPresent(Consumer cons) {
        if (t != null) {
            cons.accept(t);
        }
    }

    <V> Maybe<V> map(Function<T, V> func) {
        if (t == null) {

            return new Maybe();
        } else {
            Maybe maybe = new Maybe(func.apply(t));
            return maybe;
        }
    }

    T get() {
        if (t == null) {
            throw new NoSuchElementException();
        } else {
            return t;
        }
    }

    boolean isPresent() {
        if (t == null) {
            return false;
        } else {
            return true;
        }
    }

    T orElse(T defVal) {
        if (t == null) {
            return defVal;
        } else {
            return t;
        }
    }

    Maybe<T> filter(Predicate<T> pred) {
        if (!pred.test(t) || isPresent()) {
            return new Maybe();
        }  {
            return new Maybe(t);
        }
    }

    @Override
    public String toString() {
        if (t == null) {
            return "Maybe is empty";
        } else {
            return "Maybe has value " + t;
        }
    }
}

