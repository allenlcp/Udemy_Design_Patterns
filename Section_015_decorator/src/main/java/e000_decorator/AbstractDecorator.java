package main.java.e000_decorator;

public abstract class AbstractDecorator extends Component{
    protected Component com;

    public void setTheComponent(Component c){
        com = c;
    }

    @Override
    public void doJob() {
        if(com != null){
            com.doJob();
        }
    }
}

class ConcreteDecorator1 extends AbstractDecorator {
    @Override
    public void doJob() {
        super.doJob();
        System.out.println("I am explicitly from Example_1");
    }
}

class ConcreteDecorator2 extends AbstractDecorator {
    @Override
    public void doJob() {
        System.out.println("");
        System.out.println("**Start Ex-2**");
        super.doJob();
        System.out.println("Explicitly from Example_2");
        System.out.println("** End, Ex_2**");
    }
}
