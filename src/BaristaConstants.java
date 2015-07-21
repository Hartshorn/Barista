package com.palmer.sample;

import java.util.HashMap;

/*
 * BaristaConstants
 *
 * Author: Michael Palmer
 *
 * 7/20/2015
 *
 * The purpose of the constant class is to unify the location of all
 * values that may need to be changed. This can be benificial if (for
 * example) the price of espresso changed - it only needs to be changed here.
 */
public final class BaristaConstants {

    /*
     *  Do not allow creatiion of the Constant class; use as static.
     */
    private BaristaConstants(){}

    /* initial inventory value */
    public static final Double INIT_INVENTORY  = 10.0;

    /* price constants (minimum needed to make drink prices) */
    public static final Double TEN             = 0.10;
    public static final Double SEVENTYFIVE     = 0.75;
    public static final Double TWENTYFIVE      = 0.25;
    public static final Double ONEDOLLAR       = 1.00;

    /* amounts used in recipes */
    public static final Double AMOUNT_ONE      = 1.0;
    public static final Double AMOUNT_TWO      = 2.0;
    public static final Double AMOUNT_THREE    = 3.0;

    /*
     * flags for the getInventoryValue() and getDrinkListValue() methods;
     * this simplifies indexing
     */
    public static final int INVENTORY_AMOUNT         = 0;
    public static final int INVENTORY_PRICE          = 1;
    public static final int DRINK_LIST_PRICE         = 0;
    public static final int DRINK_LIST_AVAILABILITY  = 1;

    /* availability constants and error return values */
    public static final Double IS_AVAILABLE            =  0.0;
    public static final Double CAN_MAKE_DRINK          =  0.0;
    public static final Double CAN_NOT_MAKE_DRINK      = -1.0;
    public static final Double DRINK_INFORMATION_ERROR = -2.0;
    public static final Double ZERO                    =  0.0;
    public static final int CALCULATE_PRICE            =  1;
    public static final int CALCULATE_IS_AVAILABLE     =  2;

    /*
     * keywords used outside of the menu number selection: this limits possible
     * input values and could be extended for new functionality
     */
    public static final String QUIT     = "q";
    public static final String RESTOCK  = "r";
    public static final String[] MACHINE_KEYWORDS = { QUIT, RESTOCK };

    /*
     * success and failure - masking the simple booleans makes the code more
     * readable
     */
    public static final boolean PROCESS_FAILURE     = false;
    public static final boolean PROCESS_SUCCESS     = true;

    /*
     * String constants, used in the menu - simplifies editing.
     */
    public static final String INVENTORY            = "Inventory:\n";
    public static final String INVENTORY_LISTING    = "%s,%d\n\n";
    public static final String MENU                 = "Menu:\n";
    public static final String MENU_LISTING         = "%d,%s,$%.2f,%b\n\n";
    public static final String DRINK_NOT_FOUND      = "Drink not found";
    public static final String INVALID_SELECTION    = "\nInvalid selection: " +
                                                      "%s\n\n";
    public static final String DISPENSING           = "\nDispensing: %s\n\n";
    public static final String OUT_OF_STOCK         = "\nOut of stock: %s\n\n";
    public static final String INITIALIZATION_ERROR = "There were errors " +
                                                      "initializing drinks";

    /*
     * menu item constants - new drinks will need to be added here. The menu
     * numbers (stored in the Drink object) are used for selection and not
     * ordering as the menu is displayed.  This design decision relates to the
     * use of TreeMaps to simplify the storage of inventory and drinks (see
     * comments on the main Baristamatic class)
     */
    public static final Integer MENU_COUNT = 6;
    public static final Integer CAFFE_AMERICANO_MENU_NUMBER = 1;
    public static final Integer CAFFE_LATTE_MENU_NUMBER     = 2;
    public static final Integer CAFFE_MOCHA_MENU_NUMBER     = 3;
    public static final Integer CAPPUCCINO_MENU_NUMBER      = 4;
    public static final Integer COFFEE_MENU_NUMBER          = 5;
    public static final Integer DECAF_COFFEE_MENU_NUMBER    = 6;

    /* names of drinks and ingredients */
    public static final String COFFEE          = "Coffee";
    public static final String DECAF_COFFEE    = "Decaf Coffee";
    public static final String CAFFE_AMERICANO = "Caffe Americano";
    public static final String CAFFE_LATTE     = "Caffe Latte";
    public static final String CAFFE_MOCHA     = "Caffe Mocha";
    public static final String CAPPUCCINO      = "Cappuccino";
    public static final String SUGAR           = "Sugar";
    public static final String CREAM           = "Cream";
    public static final String STEAMED_MILK    = "Steamed Milk";
    public static final String ESPRESSO        = "Espresso";
    public static final String FOAMED_MILK     = "Faomed Milk";
    public static final String COCOA           = "Cocoa";
    public static final String WHIPPED_CREAM   = "Whipped Cream";

    /*
     * the list of drinks we use to initalize the drinkList
     * in the main machine
     */
    public static final String[] DRINK_LIST = {
                                    COFFEE, DECAF_COFFEE, CAPPUCCINO,
                                    CAFFE_MOCHA, CAFFE_LATTE, CAFFE_AMERICANO };

    /*
     * inventory amounts and values - if the unit price of an item changes, it
     * will only need to be altered here
     */
    public static final Double[] COFFEE_INIT_AMT_PRC        = { INIT_INVENTORY,
                                                            SEVENTYFIVE };
    public static final Double[] DECAF_COFFEE_INIT_AMT_PRC  = { INIT_INVENTORY,
                                                            SEVENTYFIVE };
    public static final Double[] SUGAR_INIT_AMT_PRC         = { INIT_INVENTORY,
                                                            TWENTYFIVE };
    public static final Double[] CREAM_INIT_AMT_PRC         = { INIT_INVENTORY,
                                                            TWENTYFIVE };
    public static final Double[] STEAMED_MILK_INIT_AMT_PRC  = { INIT_INVENTORY,
                                                            TWENTYFIVE + TEN };
    public static final Double[] ESPRESSO_INIT_AMT_PRC      = { INIT_INVENTORY,
                                                            ONEDOLLAR + TEN };
    public static final Double[] FOAMED_MILK_INIT_AMT_PRC   = { INIT_INVENTORY,
                                                            TWENTYFIVE + TEN };
    public static final Double[] COCOA_INIT_AMT_PRC         = { INIT_INVENTORY,
                                                            ONEDOLLAR - TEN };
    public static final Double[] WHIPPED_CREAM_INIT_AMT_PRC = { INIT_INVENTORY,
                                                            ONEDOLLAR };
}
