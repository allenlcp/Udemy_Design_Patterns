package e002_challenge;

public class Client {
    public static void main(String[] args) {
        Addition add = new Addition();
        Substration sub = new Substration();
        Multiplication mul = new Multiplication();

        Context context = new Context();
        System.out.println(context.execute(add, 10, 5));
        System.out.println(context.execute(sub, 10, 5));
        System.out.println(context.execute(mul, 10, 5));
    }
}
