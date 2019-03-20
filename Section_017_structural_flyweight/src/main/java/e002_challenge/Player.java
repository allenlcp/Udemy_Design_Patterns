package e002_challenge;

public interface Player {
    // extrinsic data
    void assignWeapon(String weapon);
    void mission();
}

class T implements Player{
    // intrinsic attribute
    private final String TASK;
    // extrinsic attribute
    private String weapon;

    public T() {
        this.TASK = "Task is to plant a b";
    }

    @Override
    public void assignWeapon(String weapon) {
        this.weapon = weapon;
    }

    @Override
    public void mission() {
        System.out.println("T with weapon " + this.weapon + " | " + this.TASK);
    }

}

class CT implements Player{
    // intrinsic attribute
    private final String TASK;
    // extrinsic attribute
    private String weapon;

    public CT() {
        this.TASK = "Task is to diffuse a b";
    }

    @Override
    public void assignWeapon(String weapon) {
        this.weapon = weapon;
    }

    @Override
    public void mission() {
        System.out.println("CT with weapon " + this.weapon + " | " + this.TASK);
    }
}
