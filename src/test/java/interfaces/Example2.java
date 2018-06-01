package interfaces;

@SuppressWarnings("all")
interface Left {

    int value = 100;

    static int getValue() {
        return value;
    }
}

@SuppressWarnings("all")
interface Right {

    int value = -100;

    static int getValue() {
        return value;
    }
}

@SuppressWarnings("all")
class GrandGrandChild extends GrandChild implements Left, Right {

    public static int getValue() {
        return 555;
    }

    GrandGrandChild() {
        int value = getValue();
        System.out.println(value);
    }
}

/**
 * 1. Статические методы в интерфейсе являются частью интерфейса.
 *    Их нельзя вызывать на объектах, реализующих интерфейс (в отличие от статических методов классов).
 *
 * 2. Статические методы в интерфейсе помогают обеспечивать безопасность.
 *    Не позволяют классам, которые реализуют интерфейс, переопределить их.
 *
 * 3. Нельзя определять статические методы, совпадающие по сигнатуре с методами класса Object.
 *
 * 4. Статические методы в интерфейсах хороши для обеспечения вспомогательных методов.
 */
@SuppressWarnings("all")
class Launcher2 {

    public static void main(String[] args) {
        GrandGrandChild grandGrandChild = new GrandGrandChild();
        System.out.println(grandGrandChild.getValue());

        Left left = grandGrandChild;
//        System.out.println(left.getValue());
        System.out.println(Left.getValue());
        System.out.println(Left.value);

        Right right = grandGrandChild;
//        System.out.println(right.getValue());
//        System.out.println(Right.getValue());
    }
}