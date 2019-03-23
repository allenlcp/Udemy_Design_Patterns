package e002_challenge;

public class Client {

    public static Expression getMaleExpression() {
        Expression robert = new TerminalExpression("Robert");
        Expression john = new TerminalExpression("John");
        Expression temp = new OrExpression(robert, john);
        Expression tom = new TerminalExpression("Tom");
        return new OrExpression(temp, tom);
    }

    public static void main (String[] args) {
        Expression isMale = getMaleExpression();

        Context ic = new Context("John");
        System.out.println("John is male? " + isMale.interpret(ic));

        Context ic4 = new Context("Tom");
        System.out.println("Tom is male? " + isMale.interpret(ic4));

        Context ic5 = new Context("Alice");
        System.out.println("Alice is male? " + isMale.interpret(ic5));

        Context ic3 = new Context("Lucy");
        System.out.println("Lucy is male? " + isMale.interpret(ic3));
    }
}
