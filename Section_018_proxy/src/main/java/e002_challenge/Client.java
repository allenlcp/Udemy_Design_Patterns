package e002_challenge;

public class Client {
    public static void main(String[] args) {
        Internet internet = new ProxyInternet();
        try {
            internet.connectTo("google.com");
            internet.connectTo("hello.com");
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
