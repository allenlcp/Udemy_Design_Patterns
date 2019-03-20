package e001_abstract_factory_method;

public abstract class AbstractFactory {
    abstract Color getColor(String color);
    abstract Shape getShape(String shape);
}

class ShapeFactory extends AbstractFactory{
    Shape getShape(String shapeType) {
        if(shapeType == null){
            return null;
        } else if (shapeType.equalsIgnoreCase("CIRCLE")){
            return new Circle();
        } else if (shapeType.equalsIgnoreCase("RECTANGLE")){
            return new Rectangle();
        } else if (shapeType.equalsIgnoreCase("SQUARE")){
            return new Square();
        }

        return  null;
    }

    Color getColor(String color) {
        return null;
    }

}

class ColorFactory extends AbstractFactory{
    Color getColor(String colorType) {
        if(colorType == null){
            return null;
        } else if (colorType.equalsIgnoreCase("RED")){
            return new Red();
        } else if (colorType.equalsIgnoreCase("GREEN")){
            return new Green();
        } else if (colorType.equalsIgnoreCase("BLUE")){
            return new Blue();
        }

        return  null;
    }

    Shape getShape(String shape) {
        return null;
    }
}