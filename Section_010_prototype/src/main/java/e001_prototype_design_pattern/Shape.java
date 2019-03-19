package e001_prototype_design_pattern;

public abstract class Shape implements Cloneable {
    private String id;
    protected String type;

    abstract void draw();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    protected Object clone() {
        Object clone = null;

        try {
            clone = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return clone;
    }
}

class Rectangle extends Shape {
    public Rectangle() {
        type = "Rectangle";
    }

    public void draw() {
        System.out.println("Inside Rectangle::draw() method");
    }
}

class Square extends Shape {
    public Square() {
        type = "Square";
    }

    public void draw() {
        System.out.println("Inside Square::draw() method");
    }
}

class Circle extends Shape {
    public Circle() {
        type = "Circle";
    }

    public void draw() {
        System.out.println("Inside Circle::draw() method");
    }
}



