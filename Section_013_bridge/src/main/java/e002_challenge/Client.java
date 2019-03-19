package e002_challenge;

public class Client {
    public static void main(String[] args) {
        System.out.println("**Bridge Pattern**");

        // Coloring Green to Triangle
        System.out.println("Coloring Triangle");
        ColorInterface green = new GreenColor();
        Shape triangleShape = new Triangle(green);
        triangleShape.drawShape(20);
        triangleShape.modifyBorder(20, 3);

        // Coloring Red to Rectangle
        System.out.println("\nColoring Rectangle");
        ColorInterface red = new RedColor();
        Shape rectangleShape = new Rectangle(red);
        rectangleShape.drawShape(50);
        rectangleShape.modifyBorder(50, 3);

    }
}
