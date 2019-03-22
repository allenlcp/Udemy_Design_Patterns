package e002_challenge;

public class AlertStateContext {
    private MobileAlertState currentState;

    public AlertStateContext() {
        this.currentState = new Vibration();
    }

    public void setCurrentState(MobileAlertState currentState) {
        this.currentState = currentState;
    }

    public void alert(){
        currentState.alert();
    }
}
