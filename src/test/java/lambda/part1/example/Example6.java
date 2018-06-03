package lambda.part1.example;

import org.junit.Test;

import java.util.concurrent.Callable;

@SuppressWarnings("unused")
public class Example6 {

    @Test
    public void runNormalRunnable() {
        Runnable r = () -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
        };
    }

    // TODO is it functional?
    private interface ThrowableRunnable {

        void run() throws Exception;
    }

    @Test
    public void runThrowableRunnable() throws Exception {
        ThrowableRunnable throwableRunnable = () -> Thread.sleep(100);
        throwableRunnable.run();
    }

    @Test
    public void callNormalCallable() throws Exception {
        Callable<Integer> callable = () -> {
            Thread.sleep(100);
            return 42;
        };
        callable.call();
    }

    @Test
    public void throwRuntimeExceptionFromRunnable() {
        Runnable runnable = () -> {
            throw new IllegalStateException("Какое-то исключение времени исполнения");
        };
        try {
            runnable.run();
        } catch (IllegalStateException ex) {
            ex.printStackTrace();
        }
    }
}
