package zad1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class ListCreator<T> {
    List<T> lista;

    public ListCreator(List<T> lista) {
        this.lista = lista;
    }

    static <T> ListCreator<T> collectFrom(List<T> listaNowa) {
        return new ListCreator<>(listaNowa);
    }

    ListCreator<T> when(Predicate<T> predicate) {
        List<T> listaSprawdzona = new ArrayList<>();
        for (T zmienna : lista) {

            if(predicate.test(zmienna))
                listaSprawdzona.add(zmienna);

        }
        return new ListCreator<>(listaSprawdzona);
    }

    <S> List<S> mapEvery(Function<T,S> function) {
        List<S> listaWynikowa = new ArrayList<>();
        for (int i = 0; i < lista.size(); i++) {
            listaWynikowa.add(function.apply(lista.get(i)));
        }
        return listaWynikowa;
    }
}
