package interfaces.compatibility;

@SuppressWarnings("all")
public interface A {

    default void superMethod() {
        System.out.println("Super method from A interface");
    }
}
