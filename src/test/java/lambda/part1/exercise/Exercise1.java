package lambda.part1.exercise;

import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import java.util.Comparator;
import lambda.data.Person;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class Exercise1 {

    @Test
    public void sortPersonsByAgeUsingArraysSortComparator() {
        Person[] persons = getPersons();

        class PersonComparator implements Comparator<Person> {

            @Override
            public int compare(Person leftPerson, Person rightPerson) {
                return Integer.compare(leftPerson.getAge(), rightPerson.getAge());
            }
        }

        Arrays.sort(persons, new PersonComparator());

        assertArrayEquals(new Person[]{
            new Person("Иван", "Мельников", 20),
            new Person("Николай", "Зимов", 30),
            new Person("Алексей", "Доренко", 40),
            new Person("Артем", "Зимов", 45)
        }, persons);
    }

    @SuppressWarnings("Convert2Lambda")
    @Test
    public void sortPersonsByAgeUsingArraysSortAnonymousComparator() {
        Person[] persons = getPersons();

        Arrays.sort(persons, new Comparator<Person>() { //NOSONAR
            @Override
            public int compare(Person leftPerson, Person rightPerson) {
                return Integer.compare(leftPerson.getAge(), rightPerson.getAge());
            }
        });

        assertArrayEquals(new Person[]{
            new Person("Иван", "Мельников", 20),
            new Person("Николай", "Зимов", 30),
            new Person("Алексей", "Доренко", 40),
            new Person("Артем", "Зимов", 45)
        }, persons);
    }

    @SuppressWarnings("Convert2Lambda")
    @Test
    public void sortPersonsByLastNameThenFirstNameUsingArraysSortAnonymousComparator() {
        Person[] persons = getPersons();

        Arrays.sort(persons, new Comparator<Person>() { //NOSONAR
            @Override
            public int compare(Person leftPerson, Person rightPerson) {
                int lastNameComparing = leftPerson.getLastName()
                    .compareTo(rightPerson.getLastName());
                return lastNameComparing == 0 ? leftPerson.getFirstName()
                    .compareTo(rightPerson.getFirstName())
                    : lastNameComparing;
            }
        });

        assertArrayEquals(new Person[]{
            new Person("Алексей", "Доренко", 40),
            new Person("Артем", "Зимов", 45),
            new Person("Николай", "Зимов", 30),
            new Person("Иван", "Мельников", 20)
        }, persons);
    }

    @SuppressWarnings({"Guava", "Convert2Lambda"})
    @Test
    public void findFirstWithAge30UsingGuavaPredicate() {
        List<Person> persons = Arrays.asList(getPersons());

        class PersonAge30Predicate implements Predicate<Person> {

            @Override
            public boolean apply(Person person) {
                return person.getAge() == 30;
            }
        }

        Person person = FluentIterable.from(persons)
            .firstMatch(new PersonAge30Predicate())
            .orNull();

        assertEquals(new Person("Николай", "Зимов", 30), person);
    }

    @SuppressWarnings({"Guava", "Convert2Lambda"})
    @Test
    public void findFirstWithAge30UsingGuavaAnonymousPredicate() {
        List<Person> persons = Arrays.asList(getPersons());

        Predicate<Person> personWithAge30 = new Predicate<Person>() { //NOSONAR
            @Override
            public boolean apply(Person person) {
                return person.getAge() == 30;
            }
        };

        Person person = FluentIterable.from(persons).firstMatch(personWithAge30).orNull();

        assertEquals(new Person("Николай", "Зимов", 30), person);
    }

    private Person[] getPersons() {
        return new Person[]{
            new Person("Иван", "Мельников", 20),
            new Person("Алексей", "Доренко", 40),
            new Person("Николай", "Зимов", 30),
            new Person("Артем", "Зимов", 45)
        };
    }
}
