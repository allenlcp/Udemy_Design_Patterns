package e002_challenge;

public abstract class OrderProcessTemplate {
    boolean isGift;

    final void processOrder(boolean isGift){
        doSelect();
        doPayment();
        if (isGift){
            giftWrapping();
        }
        delivery();
    }

    abstract void doSelect();
    abstract void doPayment();

    final void giftWrapping(){
        System.out.println("Gift wrap successful");
    };

    abstract void delivery();
}

class NetOrder extends OrderProcessTemplate{
    @Override
    void doSelect() {
        System.out.println("Item added to online shopping chart");
        System.out.println("Get gift wrap preference");
        System.out.println("Get delivery address");
    }

    @Override
    void doPayment() {
        System.out.println("Online Payment through NetBanking, card or Pay pal");
    }

    @Override
    void delivery() {
        System.out.println("Ship the item through post office to delivery address");
    }
}

class StoreOrder extends OrderProcessTemplate{
    @Override
    void doSelect() {
        System.out.println("Customer chooses the item from shelf");
    }

    @Override
    void doPayment() {
        System.out.println("Pays at counter through cash/pos");
    }

    @Override
    void delivery() {
        System.out.println("Item delivered to in delivery counter");
    }
}