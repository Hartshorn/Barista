package com.palmer.sample;

import static com.palmer.sample.BaristaConstants.*;

public class BaristaDrinkFactory {

    public Drink makeDrink(String drinkName)
    {
        Drink drink = new Drink();

        switch (drinkName) {
            case COFFEE:
                drink.setDrinkName(COFFEE);
                drink.setMenuNumber(COFFEE_MENU_NUMBER);
                applyCoffeeRecipe(drink);
                drink.setSuccessOrFail(true);
                break;
            case DECAF_COFFEE:
                drink.setDrinkName(DECAF_COFFEE);
                drink.setMenuNumber(DECAF_COFFEE_MENU_NUMBER);
                applyDecafCoffeeRecipe(drink);
                drink.setSuccessOrFail(true);
                break;
            case CAPPUCCINO:
                drink.setDrinkName(CAPPUCCINO);
                drink.setMenuNumber(CAPPUCCINO_MENU_NUMBER);
                applyCappuccinoRecipe(drink);
                drink.setSuccessOrFail(true);
                break;
            case CAFFE_MOCHA:
                drink.setDrinkName(CAFFE_MOCHA);
                drink.setMenuNumber(CAFFE_MOCHA_MENU_NUMBER);
                applyMochaRecipe(drink);
                drink.setSuccessOrFail(true);
                break;
            case CAFFE_LATTE:
                drink.setDrinkName(CAFFE_LATTE);
                drink.setMenuNumber(CAFFE_LATTE_MENU_NUMBER);
                applyCaffeLatteRecipe(drink);
                drink.setSuccessOrFail(true);
                break;
            case CAFFE_AMERICANO:
                drink.setDrinkName(CAFFE_AMERICANO);
                drink.setMenuNumber(CAFFE_AMERICANO_MENU_NUMBER);
                applyCaffeAmericanoRecipe(drink);
                drink.setSuccessOrFail(true);
                break;
            default:
                drink.setSuccessOrFail(false);
                break;
        }

        return drink;
    }

    private void applyCoffeeRecipe(Drink drink)
    {
        drink.getRecipe().put(COFFEE, AMOUNT_THREE);
        drink.getRecipe().put(SUGAR,  AMOUNT_ONE);
        drink.getRecipe().put(CREAM,  AMOUNT_ONE);
    }


    private void applyDecafCoffeeRecipe(Drink drink)
    {
        drink.getRecipe().put(DECAF_COFFEE, AMOUNT_THREE);
        drink.getRecipe().put(SUGAR,  AMOUNT_ONE);
        drink.getRecipe().put(CREAM,  AMOUNT_ONE);
    }

    private void applyCappuccinoRecipe(Drink drink)
    {
        drink.getRecipe().put(ESPRESSO, AMOUNT_TWO);
        drink.getRecipe().put(STEAMED_MILK,  AMOUNT_ONE);
        drink.getRecipe().put(FOAMED_MILK,  AMOUNT_ONE);
    }

    private void applyMochaRecipe(Drink drink)
    {
        drink.getRecipe().put(ESPRESSO, AMOUNT_ONE);
        drink.getRecipe().put(COCOA,  AMOUNT_ONE);
        drink.getRecipe().put(STEAMED_MILK,  AMOUNT_ONE);
        drink.getRecipe().put(WHIPPED_CREAM, AMOUNT_ONE);
    }

    private void applyCaffeLatteRecipe(Drink drink)
    {
        drink.getRecipe().put(ESPRESSO, AMOUNT_TWO);
        drink.getRecipe().put(STEAMED_MILK,  AMOUNT_ONE);
    }

    private void applyCaffeAmericanoRecipe(Drink drink)
    {
        drink.getRecipe().put(ESPRESSO, AMOUNT_THREE);
    }
}
