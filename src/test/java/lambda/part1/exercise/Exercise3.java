package lambda.part1.exercise;

import com.google.common.collect.FluentIterable;
import lambda.data.Person;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

@SuppressWarnings({"ConstantConditions", "unused"})
public class Exercise3 {

    @SuppressWarnings("ComparatorCombinators")
    @Test
    public void sortPersonsByAgeUsingArraysSortExpressionLambda() {
        Person[] persons = getPersons();

        Arrays.sort(persons, (leftPerson, rightPerson) ->
            Integer.compare(leftPerson.getAge(), rightPerson.getAge()));

        assertArrayEquals(new Person[]{
            new Person("Иван", "Мельников", 20),    /*NOSONAR*/
            new Person("Николай", "Зимов", 30),     /*NOSONAR*/
            new Person("Алексей", "Доренко", 40),   /*NOSONAR*/
            new Person("Артем", "Зимов", 45)        /*NOSONAR*/
        }, persons);
    }

    @SuppressWarnings({"ComparatorCombinators", "CodeBlock2Expr"})
    @Test
    public void sortPersonsByLastNameThenFirstNameUsingArraysSortExpressionLambda() {
        Person[] persons = getPersons();

        Arrays.sort(persons, (leftPerson, rightPerson) ->
            leftPerson.getLastName().equals(rightPerson.getLastName()) ?
                leftPerson.getFirstName().compareTo(rightPerson.getFirstName())
                : leftPerson.getLastName().compareTo(rightPerson.getLastName()));

        assertArrayEquals(new Person[]{
            new Person("Алексей", "Доренко", 40),
            new Person("Артем", "Зимов", 45),
            new Person("Николай", "Зимов", 30),
            new Person("Иван", "Мельников", 20)
        }, persons);
    }

    @SuppressWarnings("Guava")
    @Test
    public void findFirstWithAge30UsingGuavaPredicateLambda() {
        List<Person> persons = Arrays.asList(getPersons());

        Person foundPerson = FluentIterable.from(persons)
            .firstMatch(person -> person.getAge() == 30)
            .orNull();

        assertEquals(new Person("Николай", "Зимов", 30), foundPerson);
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
