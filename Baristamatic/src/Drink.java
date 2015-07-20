package com.palmer.sample;

import java.util.HashMap;

public class Drink implements Comparable<Drink> {

    // recipe entry: {String ingredient: Double amtNeeded}
    private HashMap<String, Double> recipe = new HashMap<>();
    private String drinkName;
    private Integer menuNumber;
    private boolean successOrFail;


    public Integer getMenuNumber()
    {
        return this.menuNumber;
    }

    public void setMenuNumber(Integer menuNumber)
    {
        this.menuNumber = menuNumber;
    }

    public String getDrinkName()
    {
        return this.drinkName;
    }

    public void setDrinkName(String drinkName)
    {
        this.drinkName = drinkName;
    }

    public HashMap<String, Double> getRecipe()
    {
        return this.recipe;
    }

    public void setRecipe(HashMap<String, Double> ingredients)
    {
        this.recipe = recipe;
    }

    public boolean getSuccessOrFail()
    {
        return this.successOrFail;
    }

    public void setSuccessOrFail(Boolean bool)
    {
        this.successOrFail = bool;
    }

    @Override
    public int compareTo(Drink drink) {
        return this.drinkName.compareTo(drink.getDrinkName());
    }
}
