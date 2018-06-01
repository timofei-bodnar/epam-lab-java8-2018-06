package generics;

public class Example5 {

    private static int staticField = 30;
    private int field = 20;

    // inner
    class A {

        private int field = 10;

        void method() {
            System.out.println(field);
            System.out.println(Example5.this.field);
        }
    }

    static class B {

        private double doubleField = 5.0;

        //void method() {
        //   System.out.println(staticField);
        //}
    }

    public static void main(String[] args) {
        // local
        double doubleField = new B().doubleField;


        class C {

            private int methodGetValue() {
                return -42;
            }
        }
        new C().methodGetValue();


    }

    // exceptions

    // anonymous classes
//        Runnable myRunnable = new Runnable<T>() {
//            @Override
//            public void run() {
//
//            }
//        };

    // enums
//    enum MyEnum<T> {
//
//    }
}

class Outer {

    public static void main(String[] args) {
        Example5.A a = new Example5().new A();
        a.method();
    }
}