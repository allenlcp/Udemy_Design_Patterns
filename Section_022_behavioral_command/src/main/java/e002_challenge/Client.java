package e002_challenge;

public class Client {
    public static void main(String[] args) {
        Stock AAA = new Stock("AAA", 100);
        Stock BBB = new Stock("BBB", 200);
        Stock CCC = new Stock("CCC", 300);

        BuyStock buyStockAAA1 = new BuyStock(AAA);
        BuyStock buyStockAAA2 = new BuyStock(AAA);
        BuyStock buyStockBBB1 = new BuyStock(BBB);
        BuyStock buyStockCCC1 = new BuyStock(CCC);
        SellStock sellStockAAA1 = new SellStock(AAA);
        SellStock sellStockBBB1 = new SellStock(BBB);

        Broker broker = new Broker();
        broker.takeOrder(buyStockAAA1);
        broker.takeOrder(buyStockAAA2);
        broker.takeOrder(buyStockBBB1);
        broker.takeOrder(buyStockCCC1);

        broker.takeOrder(sellStockAAA1);
        broker.takeOrder(sellStockBBB1);

        broker.placeOrders();
    }
}
