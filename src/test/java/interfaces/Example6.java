package interfaces;

@SuppressWarnings("all")
interface A1 {

    default Number getValue() {
        System.out.println("A1.getValue()");
        return 0;
    }
}

@SuppressWarnings("all")
interface B1 extends A1 {

    default Integer getValue() {
        System.out.println("B1.getValue()");
        return 1;
    }
}

@SuppressWarnings("all")
interface C1 extends A1 {

    default Integer getValue() {
        System.out.println("B1.getValue()");
        return 2;
    }
}

@SuppressWarnings("all")
class D1 implements B1, C1 {


    //             A1(method)
    // B1(method)              C1
    //             D1


    @Override
    public Integer getValue() {
        return B1.super.getValue() + C1.super.getValue();
    }

    public static void main(String[] args) {
//        System.out.println(new D1().getValue());
    }
}
