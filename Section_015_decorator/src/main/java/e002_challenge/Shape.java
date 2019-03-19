package main.java.e002_challenge;

public interface Shape {
    void draw();
}


class Rectangle implements Shape{
    @Override
    public void draw() {
        System.out.println("Rectangle::draw()");
    }
}

class Circle implements Shape{
    @Override
    public void draw() {
        System.out.println("Circle::draw()");
    }
}