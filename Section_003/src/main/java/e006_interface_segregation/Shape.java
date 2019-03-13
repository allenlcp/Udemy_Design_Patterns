package e006_interface_segregation;

public class Shape {

}


interface ShapeInterface{
    double area();
}

interface SolidShapeInterface{
    double volume();
}

interface ManagedShapeInterface{
    public double calculate();
}

class Cube implements  ShapeInterface, SolidShapeInterface, ManagedShapeInterface{
    @Override
    public double area() {
        // calculate the surface area of the cuboid
        return 1.0;
    }

    @Override
    public double volume() {
        // calculate the volume of the cuboid
        return 1.0;
    }

    @Override
    public double calculate() {
        return this.area() + this.volume();
    }
}

class Square implements ShapeInterface, ManagedShapeInterface{
    @Override
    public double area() {
        return 2.0;
    }

    @Override
    public double calculate() {
        return this.area();
    }
}