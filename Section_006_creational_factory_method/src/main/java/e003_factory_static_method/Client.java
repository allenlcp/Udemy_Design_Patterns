package main.java.e003_factory_static_method;

public class Client {
    public static void main(String[] args) {
        // get an object of Circle and call its draw method
        Shape shape1 = ShapeFactory.getShape("CIRCLE");
        shape1.draw();

        // get an object of Circle and call its draw method
        Shape shape2 = ShapeFactory.getShape("RECTANGLE");
        shape2.draw();

        // get an object of Circle and call its draw method
        Shape shape3 = ShapeFactory.getShape("SQUARE");
        shape3.draw();
    }
}
