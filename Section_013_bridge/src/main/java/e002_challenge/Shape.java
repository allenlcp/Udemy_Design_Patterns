package e002_challenge;

public abstract class Shape {
    protected ColorInterface color;

    public Shape(ColorInterface color) {
        this.color = color;
    }

    abstract void drawShape(int border);
    abstract void modifyBorder(int border, int increment);
}

class Triangle extends Shape{
    public Triangle(ColorInterface color) {
        super(color);
    }

    // Implementer specific method
    @Override
    void drawShape(int border) {
        System.out.println("This Triangle is colored with ");
        color.fillWithColor(border);
    }

    // Abstraction specific method
    @Override
    void modifyBorder(int border, int increment) {
        System.out.println("Now are are changing border length " + increment + " times");
        border = border + increment;
        drawShape(border);
    }
}

class Rectangle extends Shape{
    public Rectangle(ColorInterface color) {
        super(color);
    }

    // Implementer specific method
    @Override
    void drawShape(int border) {
        System.out.println("This Rectangle is colored with ");
        color.fillWithColor(border);
    }

    // Abstraction specific method
    @Override
    void modifyBorder(int border, int increment) {
        System.out.println("Now are are changing border length " + increment + " times");
        border = border + increment;
        drawShape(border);
    }
}