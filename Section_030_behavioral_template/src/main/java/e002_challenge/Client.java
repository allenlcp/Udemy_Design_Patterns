package e002_challenge;

public class Client {
    public static void main(String[] args) {
        OrderProcessTemplate opt = new NetOrder();
        opt.processOrder(true);

        opt = new StoreOrder();
        opt.processOrder(true);
    }

}
