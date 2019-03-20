package e002_challenge;

public class HotelKeeper {
    public Menus getVegMenu(){
        Hotel vegResto = new VegRestaurant();
        Menus vegMenu = vegResto.getMenus();
        return vegMenu;
    }

    public Menus getNonVegMenu(){
        Hotel nonVegResto = new NonVegRestaurant();
        Menus nonVegMenu = nonVegResto.getMenus();
        return nonVegMenu;
    }

    public Menus getBothMenu(){
        Hotel bothResto = new VegNonBothRestaurant();
        Menus bothMenu = bothResto.getMenus();
        return bothMenu;
    }


}
