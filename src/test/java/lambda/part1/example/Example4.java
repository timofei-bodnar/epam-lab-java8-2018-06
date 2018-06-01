package lambda.part1.example;

import lambda.data.Person;
import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.function.Function;
import java.util.function.Supplier;

import static org.junit.Assert.assertEquals;

@SuppressWarnings({"Convert2Lambda", "Anonymous2MethodRef", "Convert2MethodRef"})
public class Example4 {

    private static String performInCurrentThread(Callable<String> task) throws Exception {
        return task.call();
    }

    static Callable<String> staticRef;

    @Test
    public void closureAnonymousClass() throws Exception {
        // FIXME effectively final
        Person person = new Person("Иван", "Мельников", 33);

        staticRef = new Callable<String>() {
            @Override
            public String call() {
                return person.getFirstName();
            }
        };

        String firstName = performInCurrentThread(new Callable<String>() {
            @Override
            public String call() {
                return person.getFirstName();
            }
        });

        assertEquals("Иван", firstName);
    }

    @Test
    public void closureStatementLambda() throws Exception {
        Person person = new Person("Иван", "Мельников", 33);

        String greeting = performInCurrentThread(() -> {
            String prefix = person.getAge() > 30 ? "Добрый день" : "Привет";
            return prefix + ", " + person.getFirstName();
        });

        assertEquals("Добрый день, Иван", greeting);
    }

    @Test
    public void closureExpressionLambda() throws Exception {
        Person person = new Person("Иван", "Мельников", 33);

        String firstName = performInCurrentThread(() -> person.getFirstName());

        assertEquals("Иван", firstName);
    }

    @Test
    public void closureObjectMethodReferenceLambda() throws Exception {
        Person person = new Person("Иван", "Мельников", 33);

        String firstName = performInCurrentThread(person::getFirstName);

        assertEquals("Иван", firstName);
    }

    private Person person;

    @Test
    public void closureReferenceByObjectMethodReferenceLambda() throws Exception {
        person = new Person("Иван", "Мельников", 33);

        String firstName = performInCurrentThread(person::getFirstName);

        assertEquals("Иван", firstName);
    }

    @Test
    public void closureThisReferenceByExpressionLambda() throws Exception {
        person = new Person("Иван", "Мельников", 33);

        String firstName = performInCurrentThread(() -> person.getFirstName());
        assertEquals("Иван", firstName);
    }

    @Test
    public void closureThisReferenceByLambda() throws Exception {
        person = new Person("Иван", "Мельников", 33);

        String firstName = performInCurrentThread(this.person::getFirstName);
        assertEquals("Иван", firstName);

        firstName = performInCurrentThread(new Callable<String>() {

            private final Person hiddenReference = Example4.this.person;

            @Override
            public String call() {
                return hiddenReference.getFirstName();
            }

        });

        assertEquals("Иван", firstName);
    }

    @Test
    public void overwriteReferenceClosuredByExpressionLambdaAfterUsing() throws Exception {
        person = new Person("Иван", "Мельников", 33);

        String firstName = performInCurrentThread(() -> person.getFirstName());

        person = new Person("Алексей", "Игнатенко", 25);

        // FIXME какое имя следует ожидать?
        assertEquals(null, firstName);
    }

    @Test
    public void overwriteReferenceClosuredByObjectMethodReferenceLambdaAfterUsing() throws Exception {
        person = new Person("Иван", "Мельников", 33);

        Callable<String> task = person::getFirstName;
        String firstName = performInCurrentThread(task);

        person = new Person("Алексей", "Игнатенко", 25);

        // FIXME какое имя следует ожидать?
        assertEquals(null, firstName);
    }

    private Callable<String> performLaterFromCurrentThread(Callable<String> task) {
        return new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("Некий код перед выполнением задачи...");
                return task.call();
            }
        };
    }

    @Test
    public void overwriteReferenceClosuredByExpressionLambdaBeforeUsing() throws Exception {
        person = new Person("Иван", "Мельников", 33);

        Callable<String> delayedTask = performLaterFromCurrentThread(() -> person.getFirstName());

        person = new Person("Алексей", "Игнатенко", 25);

        String firstName = delayedTask.call();

        // FIXME какое имя следует ожидать?
        assertEquals(null, firstName);
    }

    @Test
    public void overwriteReferenceClosuredByObjectMethodReferenceLambdaBeforeUsing() throws Exception {
        person = new Person("Иван", "Мельников", 33);

        Callable<String> delayedTask = performLaterFromCurrentThread(person::getFirstName);

        person = new Person("Алексей", "Игнатенко", 25);

        String firstName = delayedTask.call();

        // FIXME какое имя следует ожидать?
        assertEquals(null, firstName);

    }

    private Person getPerson() {
        return person;
    }

    @Test
    public void overwriteReferenceClosuredByObjectMethodReferenceGetPersonBeforeUsing() throws Exception {
        person = new Person("Иван", "Мельников", 33);

        Callable<String> delayedTask = performLaterFromCurrentThread(getPerson()::getFirstName);

        person = new Person("Алексей", "Игнатенко", 25);

        String firstName = delayedTask.call();

        // FIXME какое имя следует ожидать?
        assertEquals(null, firstName);
    }
}
