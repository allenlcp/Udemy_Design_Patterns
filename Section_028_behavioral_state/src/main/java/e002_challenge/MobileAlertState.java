package e002_challenge;

public interface MobileAlertState {
    void alert();
}

class Vibration implements MobileAlertState{
    @Override
    public void alert() {
        System.out.println("Vibration...");
    }
}


class Silent implements MobileAlertState{
    @Override
    public void alert() {
        System.out.println("Silent...");
    }
}
