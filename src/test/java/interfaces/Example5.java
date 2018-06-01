package interfaces;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

abstract class BaseIterable implements Iterable<String> {

    BaseIterable() {
        super();
        System.out.println(iterator().next());
    }

    @NotNull
    @Override
    public Iterator<String> iterator() {
        return null;
    }
}

class ChildIterable extends BaseIterable {

    private Collection<String> collection = Arrays.asList("hello", "from", "Child1");

    public ChildIterable() {
        super();
        System.out.println("Constructor ChildIterable");
    }

    @NotNull
    @Override
    public Iterator<String> iterator() {
        return collection.iterator();
    }
}

public class Example5 {

    public static void main(String[] args) {
        ChildIterable strings = new ChildIterable();
        for (String string : strings) {
            System.out.println(string);
        }
    }
}
