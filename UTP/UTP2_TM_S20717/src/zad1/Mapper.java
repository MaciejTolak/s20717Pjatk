/**
 * @author Tolak Maciej S20717
 */

package zad1;


public interface Mapper<T, K> { // Uwaga: interfejs musi być sparametrtyzowany

    K map(T zmienna);

}  
