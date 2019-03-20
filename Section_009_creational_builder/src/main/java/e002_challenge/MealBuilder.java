package e002_challenge;

public interface MealBuilder {
    void buildBurger();
    void buildDrink();
    Meal getMeal();
}

class VegMealBuilder implements MealBuilder{
    Meal myMeal = new Meal();

    Burger myBurger;
    ColdDrink coldDrink;

    public VegMealBuilder(Burger myBurger, ColdDrink coldDrink) {
        this.myBurger = myBurger;
        this.coldDrink = coldDrink;
    }

    @Override
    public void buildBurger() {
        myMeal.addItem(myBurger);
    }

    @Override
    public void buildDrink() {
        myMeal.addItem(coldDrink);
    }

    @Override
    public Meal getMeal() {
        return myMeal;
    }
}

class NonVegMealBuilder implements MealBuilder{
    Meal myMeal = new Meal();
    Burger myBurger;
    ColdDrink coldDrink;

    public NonVegMealBuilder(Burger myBurger, ColdDrink coldDrink) {
        this.myBurger = myBurger;
        this.coldDrink = coldDrink;
    }

    @Override
    public void buildBurger() {
        myMeal.addItem(myBurger);
    }

    @Override
    public void buildDrink() {
        myMeal.addItem(coldDrink);
    }

    @Override
    public Meal getMeal() {
        return myMeal;
    }
}