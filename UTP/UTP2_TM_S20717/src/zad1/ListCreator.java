/**
 * @author Tolak Maciej S20717
 */

package zad1;


import java.util.ArrayList;
import java.util.List;

public class ListCreator<T> {

    List<T> lista;

    public ListCreator(List<T> lista) {
        this.lista = lista;
    }

    static <T> ListCreator<T> collectFrom(List<T> listaNowa) {
        return new ListCreator<>(listaNowa);
    }

    ListCreator<T> when(Selector<T> sel) {
        List<T> listaSprawdzona = new ArrayList<>();
        for (T zmienna : lista) {
            if (sel.select(zmienna))
                listaSprawdzona.add(zmienna);
        }
        return new ListCreator<>(listaSprawdzona);
    }

    <S>List<S> mapEvery(Mapper<T, S> map) {
        List<S> listaWynikowa = new ArrayList<>();
        for (int i = 0; i < lista.size(); i++) {
            listaWynikowa.add(map.map(lista.get(i)));
        }
        return listaWynikowa;
    }
}  
