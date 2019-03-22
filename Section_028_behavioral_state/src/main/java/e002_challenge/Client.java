package e002_challenge;

public class Client {
    public static void main(String[] args) {
        AlertStateContext stateContext = new AlertStateContext();

        stateContext.alert();
        stateContext.alert();
        stateContext.setCurrentState(new Silent());
        stateContext.alert();
        stateContext.alert();
        stateContext.alert();
    }
}
