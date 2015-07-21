package com.palmer.sample;

import static org.junit.Assert.*;
import java.util.logging.*;
import static com.palmer.sample.BaristaConstants.*;
import org.junit.Test;

/*
 * BaristaTest: JUnit test cases for Baristamatic
 *
 * Author: Michael Palmer
 *
 * 7/20/2015
 *
 * This is a test class for the Barsitamatic program.  It outlines expected
 * results for both valid and invalid input, as well as
 */
public class BaristaTest {

    private static final Logger LOGGER =
                                Logger.getLogger(BaristaTest.class.getName());


    @Test
    public void baristaDrinkFactoryTestValidInput()
    {
        LOGGER.info("Begin: TEST CASE - " +
                    "baristaDrinkFactoryTestValidInput()");

        BaristaDrinkFactory testBaristaDrinkFactory = new BaristaDrinkFactory();

        BaristaDrink testBaristaDrink =
                testBaristaDrinkFactory.makeDrink(COFFEE);

        assertNotNull("Drink should not be null",
                testBaristaDrink);

        assertTrue("SuccessOrFail should be true",
                testBaristaDrink.getSuccessOrFail());

        assertEquals("Coffee", 
                testBaristaDrink.getDrinkName());

        assertEquals(new Integer(5),
                testBaristaDrink.getMenuNumber());

        assertEquals(new Double(3),
                testBaristaDrink.getRecipe().get(COFFEE));

        LOGGER.info("Complete: TEST CASE - " +
                    "baristaDrinkFactoryTestValidInput()");
    }

    @Test
    public void baristaDrinkFactoryTestInvalidInput()
    {
        LOGGER.info("Begin: TEST CASE - " +
                    "baristaDrinkFactoryTestInvalidInput()");

        BaristaDrinkFactory testBaristaDrinkFactory = new BaristaDrinkFactory();

        BaristaDrink testBaristaDrink =
                            testBaristaDrinkFactory.makeDrink("invalid string");

        assertNotNull("Should not be null",
                testBaristaDrink);

        assertFalse("Success should be false",
                testBaristaDrink.getSuccessOrFail());

        assertNull("getDrinkName(): should be null",
                testBaristaDrink.getDrinkName());

        assertNull("getMenuNumber(): should be null",
                testBaristaDrink.getMenuNumber());

        assertNotNull("getRecipe(): should not be null",
                testBaristaDrink.getRecipe());

        LOGGER.info("Complete: TEST CASE - " +
                    "baristaDrinkFactoryTestInvalidInput()");
    }

    @Test
    public void baristamaticTest()
    {
        LOGGER.info("Begin: TEST CASE - baristamaticTest()");

        Baristamatic testBaristamatic =
                new Baristamatic();

        BaristaDrinkFactory testBaristaDrinkFactory =
                new BaristaDrinkFactory();

        BaristaDrink testBaristaDrink =
                testBaristaDrinkFactory.makeDrink(COFFEE);

        testBaristamatic.initializeMachine();

        assertEquals(new Double(10.0),
                testBaristamatic.getInventoryValue(COFFEE, INVENTORY_AMOUNT));

        assertEquals(new Double(0.75),
                testBaristamatic.getInventoryValue(COFFEE, INVENTORY_PRICE));

        assertEquals(new Double(0.0),
                testBaristamatic.getDrinkListValue(testBaristaDrink,
                                                    DRINK_LIST_AVAILABILITY));

        assertEquals(new Double(2.75),
                testBaristamatic.getDrinkInformation(testBaristaDrink,
                    CALCULATE_PRICE));

        assertEquals(new Double(0.0),
                testBaristamatic.getDrinkInformation(testBaristaDrink,
                    CALCULATE_IS_AVAILABLE));

        LOGGER.info("Complete: TEST CASE - " +
                    "baristamaticTest()");
    }
}
