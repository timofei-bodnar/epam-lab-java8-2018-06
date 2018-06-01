package interfaces;

@SuppressWarnings("all")
interface First {

    int value = 100;

//    int getValueFromFirst();

//    int getValue();

    default int getValue() {
        return value;
    }
}

@SuppressWarnings("all")
interface Second {

    int value = -100;

//    int getValueFromSecond();

//    int getValue();

    default int getValue() {
        return value;
    }
}

@SuppressWarnings("all")
class B implements First, Second {

    @Override
    public int getValue() {
        return First.super.getValue() + Second.super.getValue();
    }

}

