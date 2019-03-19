package e002_challenge;

public class Director {
    MealBuilder mealBuilder;

    public void construct(MealBuilder builder){
        mealBuilder = builder;
        mealBuilder.buildBurger();
        mealBuilder.buildDrink();
    }
}
