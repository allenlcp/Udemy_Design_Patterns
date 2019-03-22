package e001_state;

public class TV {

    private RemoteControl state;

    public TV(RemoteControl state) {
        this.state=state;
    }

    public RemoteControl getState() {
        return state;
    }

    public void setState(RemoteControl state) {
        this.state = state;
    }

    public void pressButton() {
        state.pressSwitch(this);
    }
}
