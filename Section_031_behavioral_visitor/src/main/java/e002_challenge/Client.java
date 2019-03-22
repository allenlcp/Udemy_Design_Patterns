package e002_challenge;

import java.util.ArrayList;
import java.util.List;

public class Client {

    public static double calculateTotalCost(List<Visitable> myList, Visitor visitor){
        for (Visitable el : myList){
            el.accept(visitor);
        }
        double postage = visitor.getTotalPostage();
        return postage;

    }

    public static void main(String[] args) {
        List<Visitable> myList = new ArrayList<>();
        myList.add(new Book(10,10));
        myList.add(new CD(20,20));
        myList.add(new Book(30,30));
        myList.add(new DVD(40,40));

        Visitor usVisitor = new USPostageVisitor();
        Visitor saVisitor = new SouthAmericaSPostageVisitor();

        double usCost = calculateTotalCost(myList, usVisitor);
        double saCost = calculateTotalCost(myList, saVisitor);

        System.out.println("US cost: " + usCost);
        System.out.println("South America cost: " + saCost);
    }
}
