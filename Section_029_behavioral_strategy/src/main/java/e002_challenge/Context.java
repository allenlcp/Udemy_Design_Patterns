package e002_challenge;

public class Context {
    public int execute(Strategy strat, int num1, int num2){
        return strat.performOperation(num1, num2);
    }
}
