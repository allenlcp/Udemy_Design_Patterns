package e002_challenge;

public class Client {
    public static void main(String[] args) {
        System.out.println("***Meal Pattern Demo***\n");

        VegBurger vegBurger = new VegBurger("Soya Burger", 3.40);
        ChickenBurger chickenBurger = new ChickenBurger("Spicy Chicken Burger", 4.50);
        Pepsi pepsi = new Pepsi("Pepsi", 0.45);
        Coke coke = new Coke("Coke", 0.50);

        Director director = new Director();

        // Making veg meal
        MealBuilder vegMealBuilder = new VegMealBuilder(vegBurger, pepsi);
        director.construct(vegMealBuilder);
        Meal vegMeal = vegMealBuilder.getMeal();
        System.out.println("Veg Meal");
        vegMeal.showItem();
        System.out.println("Total Cost: " + vegMeal.getCost());


        // Making non veg meal
        MealBuilder nonVegMealBuilder = new NonVegMealBuilder(chickenBurger, coke);
        director.construct(nonVegMealBuilder);
        Meal nonVegMeal = nonVegMealBuilder.getMeal();
        System.out.println("Non Veg Meal");
        nonVegMeal.showItem();
        System.out.println("Total Cost: " + nonVegMeal.getCost());
    }
}
