package e001_proxy;

public class Client {
    public static void main(String [] args) {
        Image image  = new ProxyImage("someFilename.jpg");

        // image will be loaded from disk
        image.display();
        System.out.println("");

        image.display();
    }
}
