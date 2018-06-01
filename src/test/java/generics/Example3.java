package generics;

import java.util.*;

public class Example3 {

    @SuppressWarnings({"UnnecessaryLocalVariable", "unchecked"})
    public static void main(String[] args) {
        // Ковариантность
        Integer[] ints = {1, 2, 3, 4};
        Object[] objects = ints;
        objects[0] = "123"; //ArrayStoreException


        // Инвариантность
        List<Integer> intValues = new ArrayList<>(Arrays.asList(1, 2, 3));
//        List objectValues = intValues;
//        objectValues.add("abc");
//
//        System.out.println(intValues.get(3));

        // checkedCollections
        List<Integer> checkedList = Collections.checkedList(intValues, Integer.class);
        List objectValues = checkedList;
        objectValues.add("13");
        System.out.println(objectValues.get(3));

    }
}
