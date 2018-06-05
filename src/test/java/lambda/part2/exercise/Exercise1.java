package lambda.part2.exercise;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;
import lambda.data.Person;
import org.junit.Test;

@SuppressWarnings({"unused", "ConstantConditions"})
public class Exercise1 {

    // TODO метод getFullName: Person -> String, извлекающий из объекта Person строку в формате "имя фамилия".
    private static String getFullName(Person person) {
        return person.getFirstName() + " " + person.getLastName();
    }

    // TODO метод createExtractorAgeOfPersonWithTheLongestFullName: (Person -> String) -> ((Person, Person) -> int),
    // TODO - принимающий способ извлечения полного имени из объекта Person
    // TODO - возвращающий BiFunction, сравнивающий два объекта Person и возвращающий возраст того, чье полное имя длиннее.
    private static BiFunction<Person, Person, Integer> createExtractorAgeOfPersonWithTheLongestFullName(
        Function<Person, String> fullNameExtractor) {
        return (lhv, rhv) ->
            fullNameExtractor.apply(lhv).length() >= fullNameExtractor.apply(rhv).length()
                ? lhv.getAge() : rhv.getAge();
    }

    @Test
    public void ageExtractorFromPersonUsingMethodReference() {
        Person person = new Person("Иван", "Мельников", 33);

        // TODO создать переменную ageExtractor: Person -> Integer, используя Function и ссылку на метод

        Function<Person, Integer> ageExtractor = Person::getAge;
        assertEquals(33, ageExtractor.apply(person).intValue());

        // FIXME удалить при реализации
        //throw new UnsupportedOperationException("Not implemented");
    }

    @Test
    public void sameAgesCheckerUsingBiPredicate() {
        Person person1 = new Person("Иван", "Мельников", 33);
        Person person2 = new Person("Дмитрий", "Гущин", 33);
        Person person3 = new Person("Илья", "Жирков", 22);

        // TODO создать переменную sameAgesChecker: (Person, Person) -> boolean, используя BiPredicate
        BiPredicate<Person, Person> sameAgesChecker = (lhv, rhv) ->
            lhv.getAge() == rhv.getAge();
        assertTrue(sameAgesChecker.test(person1, person2));
        assertFalse(sameAgesChecker.test(person1, person3));
        assertFalse(sameAgesChecker.test(person2, person3));

        // FIXME удалить при реализации
        //throw new UnsupportedOperationException("Not implemented");
    }

    @Test
    public void getAgeOfPersonWithTheLongestFullName() {
        Person person1 = new Person("Иван", "Мельников", 33);
        Person person2 = new Person("Илья", "Жирков", 22);

        // TODO воспользоваться ссылкой на метод getFullName
        Function<Person, String> getFullName = Exercise1::getFullName;

        // (Person, Person) -> Integer
        // TODO воспользоваться методом createExtractorAgeOfPersonWithTheLongestFullName
        BiFunction<Person, Person, Integer> extractorAgeOfPersonWithTheLongestFullName
            = Exercise1.createExtractorAgeOfPersonWithTheLongestFullName(getFullName);

        assertEquals(33,
            extractorAgeOfPersonWithTheLongestFullName.apply(person1, person2).intValue());
    }
}
