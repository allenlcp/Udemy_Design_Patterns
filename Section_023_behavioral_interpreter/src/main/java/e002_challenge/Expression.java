package e002_challenge;

public interface Expression {
    boolean interpret(Context context);
}

class TerminalExpression implements Expression {
    private String data;  //robert, john

    public TerminalExpression(String data) {
        this.data = data;
    }

    @Override
    public boolean interpret(Context context) {
        return context.getResult(data);
    }
}
