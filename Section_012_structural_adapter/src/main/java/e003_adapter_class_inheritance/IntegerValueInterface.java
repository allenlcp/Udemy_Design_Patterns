package e003_adapter_class_inheritance;

public interface IntegerValueInterface {
    int getInteger();
}

class IntegerValue implements IntegerValueInterface{
    @Override
    public int getInteger() {
        return 5;
    }
}

class ClassAdapter extends IntegerValue {
    @Override
    public int getInteger() {
        return 2 + super.getInteger();
    }
}

class ObjectAdapter {
    private IntegerValueInterface myInt;

    public ObjectAdapter(IntegerValueInterface myInt) {
        this.myInt = myInt;
    }

    public int getInteger(){
        return 2 + this.myInt.getInteger();
    }
}
