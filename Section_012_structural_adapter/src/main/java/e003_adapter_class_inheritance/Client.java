package e003_adapter_class_inheritance;

public class Client {
    public static void main(String[] args) {

        System.out.println("Class and Object Adapter Demo");
        ClassAdapter cal = new ClassAdapter();
        System.out.println("Class Adapter is returning: " + cal.getInteger());

        ObjectAdapter oa = new ObjectAdapter(new IntegerValue());
        System.out.println("Object Adapter is returning: " + oa.getInteger());

    }
}
