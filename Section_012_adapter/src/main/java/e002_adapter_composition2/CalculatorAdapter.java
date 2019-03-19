package e002_adapter_composition2;

public class CalculatorAdapter implements CalculatorInterface {
    Calculator calculator;
    Triangle triangle;

    public CalculatorAdapter(Triangle triangle) {
        this.triangle = triangle;
    }

    @Override
    public double getArea(Rectangle r) {
        calculator = new Calculator();
        Rectangle rectangle = new Rectangle();

        rectangle.length = triangle.base;
        rectangle.width = 0.5 * triangle.height;

        return calculator.getArea(rectangle);

    }
}
