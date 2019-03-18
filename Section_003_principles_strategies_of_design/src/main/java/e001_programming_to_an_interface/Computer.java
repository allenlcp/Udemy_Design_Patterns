package e001_programming_to_an_interface;

interface displayModule{
    public void display();
}

class Monitor implements  displayModule {
    public void display() {
        System.out.println("Display through Monitor");
    }
}

class Projector implements  displayModule {
    public void display() {
        System.out.println("Display through Projector");
    }
}

public class Computer{
    displayModule dm;

    public void setDisplayModule(displayModule dm) {
        this.dm = dm;
    }

    public void display(){
        dm.display();
    }

    public static void main(String[] args) {
        Computer cm = new Computer();
        displayModule dm1 = new Monitor();
        displayModule dm2 = new Projector();
        cm.setDisplayModule(dm1);
        cm.display();
        cm.setDisplayModule(dm2);
        cm.display();
    }
}
