package e001_builder;

import java.util.LinkedList;

public class Product {
    private LinkedList<String> parts;

    public Product() {
        this.parts = new LinkedList<>();
    }

    public void add(String part){
        parts.addLast(part);
    }

    public void show(){
        System.out.println("\nProduct completed as below");
        for(String s : parts){
            System.out.println(s);
        }
    }
}
