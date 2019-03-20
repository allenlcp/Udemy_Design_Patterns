package e001_abstract_factory_method;

public interface Shape {
    void draw();
}

class Rectangle implements Shape {
    public void draw() {
        System.out.println("Inside Rectangle::draw() method");
    }
}

class Circle implements Shape {
    public void draw() {
        System.out.println("Inside Circle::draw() method");
    }
}

class Square implements Shape {
    public void draw() {
        System.out.println("Inside Square::draw() method");
    }
}

