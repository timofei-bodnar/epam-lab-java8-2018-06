package generics;

import java.io.Serializable;
import java.util.Collection;

public class Example2 {

    @SuppressWarnings("all")
    public static void main(String[] args) {
        NumberHolder<Integer> intHolder = new NumberHolder<>(50);

        int value = intHolder.getValue();

        System.out.println(intHolder.getValue());





    }

    //TODO max value in collection
    private static Object max(Collection collection) {
        return null;
    }

    private static <T extends Object & Comparable<T>> T genericMax(Collection<T> collection) {
        return null;
    }

}

interface MySuperInterface {

}

abstract class MySuperNumber extends Number implements MySuperInterface {

}

class NumberHolder<T extends Number & Serializable> {

    private T value;

    public NumberHolder(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}