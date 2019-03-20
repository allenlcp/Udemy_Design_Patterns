package e002_challenge;

import java.util.ArrayList;
import java.util.List;

public class Meal {
    List<Item> myMeal;

    public Meal() {
        this.myMeal = new ArrayList<>();
    }

    public void addItem(Item item){
        myMeal.add(item);
    }

    public double getCost(){
        double sum=0;
        for(Item i: myMeal){
            sum += i.getPrice();
        }
        return sum;
    }

    public void showItem(){
        for(Item i: myMeal){
            System.out.print("Item: " + i.getName());
            System.out.print(", Packing: " + i.getPacking().pack());
            System.out.println(", Price: " + i.getPrice());
        }
    }

}
