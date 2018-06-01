package interfaces;

@SuppressWarnings("all")
interface A {

    default int getValue() {
        return 50;
    }
}

@SuppressWarnings("all")
class Example4 {

    private int getValue() {
        return 999;
    }
}

class ChildExample4 extends Example4 implements A {

    public static void main(String[] args) {
        ChildExample4 object = new ChildExample4();
        object.getValue();
        System.out.println();

        A a = null;
    }
}
