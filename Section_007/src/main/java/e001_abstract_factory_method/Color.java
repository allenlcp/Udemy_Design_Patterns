package e001_abstract_factory_method;

public interface Color {
    void fill();
}


class Red implements Color{
    public void fill() {
        System.out.println("Red:: fill() method");
    }
}

class Blue implements Color{
    public void fill() {
        System.out.println("Blue:: fill() method");
    }
}

class Green implements Color{
    public void fill() {
        System.out.println("Green:: fill() method");
    }
}