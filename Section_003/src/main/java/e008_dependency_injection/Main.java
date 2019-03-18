package e008_dependency_injection;

public class Main {
    private Service myService;

    public Main(Service service){
        this.myService = service;
    }

    public void doSomething(){
        myService.write("This is a message");
    }

    public void setService(Service service){
        this.myService = service;
    }

    public static void main(String[] args) {
        Service s = new ServiceA(); // the injector
        Main client = new Main(s); // injects via the constructor
        client.doSomething();

        client.setService(s);

    }
}


interface Service{
    void write(String message);
}

class ServiceA implements  Service{
    public void write(String message) {
        System.out.println("Hello World");
    }
}

