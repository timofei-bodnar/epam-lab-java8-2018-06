package generics;

import java.lang.reflect.Method;
import java.util.Arrays;

public class Example4 {

    @SuppressWarnings("all")
    public static void main(String[] args) throws NoSuchMethodException {
        // getMethodsUsingReflection
        Arrays.stream(Person.class.getDeclaredMethods())
              .forEach(System.out::println);

        Method method = Person.class.getDeclaredMethod("compareTo", Object.class);
        System.out.println(method.isSynthetic());
        System.out.println(method.isBridge());
    }

    // compareTo
    private static class Person implements Comparable<Person> {

        private String name;
        private String surname;
        private int age;

        @Override
        public int compareTo(Person person) {
            return name.compareTo(person.name);
        }

        // bridge method
//        public int compareTo(Object object) {
//            return compareTo((Person)object);
//        }
    }
}
