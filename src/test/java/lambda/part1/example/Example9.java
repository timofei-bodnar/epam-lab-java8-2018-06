package lambda.part1.example;

import lambda.data.Person;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

@SuppressWarnings({"unused", "ComparatorCombinators"})
public class Example9 {

    private static class FullNameComparator implements Comparator<Person>, Serializable {

        @Override
        public int compare(Person o1, Person o2) {
            return o1.getFullName().compareTo(o2.getFullName());
        }
    }

    @FunctionalInterface
    private interface PersonFactory {

        Person create(String name, String lastName, int age);
    }

    @Test
    public void serializeTree() throws IOException {
        Set<Person> treeSet = new TreeSet<>(
                (Comparator<? super Person> & Serializable) (o1, o2) -> o1.getFullName().compareTo(o2.getFullName()));
        treeSet.add(new Person("Иван", "Мельников", 33));
        treeSet.add(new Person("Алексей", "Игнатенко", 1));
        treeSet.add(new Person("Сергей", "Лопатин", 3));

        System.out.println(treeSet);

        // TODO сериализовать дерево в массив байт
        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             ObjectOutputStream objectOutput = new ObjectOutputStream(out)) {
            objectOutput.writeObject(treeSet);
            objectOutput.flush();
            System.out.println(new String(out.toByteArray()));
        }
    }
}
