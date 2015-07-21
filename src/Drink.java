package com.palmer.sample;

import java.util.HashMap;

/*
 * Drink
 *
 * Author: Michael Palmer
 * 
 * 7/20/2015
 *
 * An interface for a class of Drinks, this describes methods that all
 * drinks should implement.
 */
public interface Drink {
    
    public Integer getMenuNumber();
    public void setMenuNumber(Integer menuNumber);
    public String getDrinkName();
    public void setDrinkName(String drinkName);
    public HashMap<String, Double> getRecipe();
    public void setRecipe(HashMap<String, Double> ingredients);
    public boolean getSuccessOrFail();
    public void setSuccessOrFail(Boolean bool);
}