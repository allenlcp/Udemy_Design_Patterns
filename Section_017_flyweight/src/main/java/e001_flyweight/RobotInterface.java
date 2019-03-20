package e001_flyweight;

public interface RobotInterface {
    void print();

    // extrinsic data is passed as arguments
    void setColor(String colorOfRobot);
}

class Robot implements RobotInterface  {

    String robotType;
    public String colorOfRobot;

    public Robot(String robotType) {
        this.robotType=robotType;
    }
    public void setColor(String colorOfRobot) {
        this.colorOfRobot=colorOfRobot;
    }
    @Override
    public void print() {
        System.out.println(" This is a " +robotType+ " type robot with "+colorOfRobot+ "color");
    }
}


