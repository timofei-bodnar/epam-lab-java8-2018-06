package lambda.part1.exercise;

import com.google.common.base.Optional;
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

    class AgeComparator implements Comparator<Person> {

      @Override
      public int compare(Person left, Person right) {
        return Integer.compare(left.getAge(), right.getAge());
      }
    }
    Arrays.sort(persons, new AgeComparator());

    assertArrayEquals(new Person[]{
        new Person("Иван", "Мельников", 20),
        new Person("Николай", "Зимов", 30),
        new Person("Алексей", "Доренко", 40),
        new Person("Артем", "Зимов", 45)
    }, persons);
  }

  @Test
  public void sortPersonsByAgeUsingArraysSortAnonymousComparator() {
    Person[] persons = getPersons();

    Arrays.sort(persons, new Comparator<Person>() {
      @Override
      public int compare(Person left, Person right) {
        return Integer.compare(left.getAge(), right.getAge());
      }
    });

    assertArrayEquals(new Person[]{
        new Person("Иван", "Мельников", 20),
        new Person("Николай", "Зимов", 30),
        new Person("Алексей", "Доренко", 40),
        new Person("Артем", "Зимов", 45)
    }, persons);
  }

  @Test
  public void sortPersonsByLastNameThenFirstNameUsingArraysSortAnonymousComparator() {
    Person[] persons = getPersons();

    Arrays.sort(persons, new Comparator<Person>() {
      @Override
      public int compare(Person left, Person right) {
        int lastNameCompare = left.getLastName().compareTo(right.getLastName());
        return lastNameCompare == 0 ?
            left.getFirstName().compareTo(right.getFirstName()) : lastNameCompare;
      }
    });

    assertArrayEquals(new Person[]{
        new Person("Алексей", "Доренко", 40),
        new Person("Артем", "Зимов", 45),
        new Person("Николай", "Зимов", 30),
        new Person("Иван", "Мельников", 20)
    }, persons);
  }

  @Test
  public void findFirstWithAge30UsingGuavaPredicate() {
    List<Person> persons = Arrays.asList(getPersons());

    class Age30Predicate implements Predicate<Person> {

      @Override
      public boolean apply(Person person) {
        return Integer.valueOf(30).equals(person.getAge());
      }
    }

    Optional<Person> personOptional = FluentIterable.from(persons).firstMatch(new Age30Predicate());
    Person person = personOptional.get();

    assertEquals(new Person("Николай", "Зимов", 30), person);
  }

  @Test
  public void findFirstWithAge30UsingGuavaAnonymousPredicate() {
    List<Person> persons = Arrays.asList(getPersons());

    Optional<Person> personOptional = FluentIterable.from(persons)
        .firstMatch(new Predicate<Person>() {
          @Override
          public boolean apply(Person person) {
            return Integer.valueOf(30).equals(person.getAge());
          }
        });
    Person person = personOptional.get();

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
