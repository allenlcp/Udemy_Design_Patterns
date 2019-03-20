package e002_challenge;

public class Client {
    public static void main(String[] args) {
        HotelKeeper keeper = new HotelKeeper();

        Menus v = keeper.getVegMenu();
        v.showMenu();
        System.out.println("-----------------");
        Menus nv = keeper.getNonVegMenu();
        v.showMenu();
        System.out.println("-----------------");
        Menus b = keeper.getBothMenu();
        v.showMenu();

    }
}
