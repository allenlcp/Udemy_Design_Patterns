package e001_bridge;

public abstract class Workshop {
    abstract void work();
}

class Produce extends  Workshop{
    @Override
    void work() {
        System.out.println("Produced");
    }
}


class Assemble extends  Workshop{
    @Override
    void work() {
        System.out.println("And Assembled");
    }
}

