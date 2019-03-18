package e002_delegation_principles;

class RealPrinter{
    // the "delegate"
    void print(){
        System.out.println("The Delegate");
    }
}

class Printer{
    // the "delegator"
    RealPrinter p = new RealPrinter();

    // create the delegate
    void print(){
        p.print();
    }
}

public class DelegationExample {
    public static void main(String[] args) {
        // To the outside world it looks like Printer actually prints
        Printer printer = new Printer();
        printer.print();
    }
}
