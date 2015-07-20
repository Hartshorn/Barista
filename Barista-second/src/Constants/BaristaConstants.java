package Constants;

import java.util.HashMap;

public final class BaristaConstants {

    /*
     *  Do not allow creatiion of the Constant class; use as static
     */
    private BaristaConstants()
    {
        throw new AssertionError();
    }

    public static final Double INIT_INVENTORY  = 10.0;
    public static final Double TEN             = 0.10;
    public static final Double SEVENTYFIVE     = 0.75;
    public static final Double TWENTYFIVE      = 0.25;
    public static final Double ONEDOLLAR       = 1.00;

    public static final Double AMOUNT_ONE      = 1.0;
    public static final Double AMOUNT_TWO      = 2.0;
    public static final Double AMOUNT_THREE    = 3.0;
    
    public static final Double DECREMENT_ONE   = -1.0;
    public static final Double INCREMENT_ONE   = 1.0;
    public static final Double NO_CHANGE       = 0.0;

    public static final String COFFEE          = "Coffee";
    public static final String DECAF_COFFEE    = "Decaf Coffee";
    public static final String SUGAR           = "Sugar";
    public static final String CREAM           = "Cream";
    public static final String STEAMED_MILK    = "Steamed Milk";
    public static final String ESPRESSO        = "Espresso";
    public static final String FOAMED_MILK     = "Faomed Milk";
    public static final String COCOA           = "Cocoa";
    public static final String WHIPPED_CREAM   = "Whipped Cream";

    public static final String CAFFE_AMERICANO = "Caffe Americano";
    public static final String CAFFE_LATTE     = "Caffe Latte";
    public static final String CAFFE_MOCHA     = "Caffe Mocha";
    public static final String CAPPUCCINO      = "Cappuccino";

    public static final String DRINK_NOT_FOUND = "Drink not found";

    public static final String QUIT     = "q";
    public static final String RESTOCK  = "r";

    public static final String[] KEYWORDS = { QUIT, RESTOCK };

    public static final boolean PROCESS_FAILURE = false;
    public static final boolean PROCESS_SUCCESS = true;

    public static final Double AMOUNT_ONE      = 1.0;
    public static final Double AMOUNT_TWO      = 2.0;
    public static final Double AMOUNT_THREE    = 3.0;

    public static final Integer MENU_COUNT = 6;
    public static final Integer CAFFE_AMERICANO_MENU_NUMBER = 1;
    public static final Integer CAFFE_LATTE_MENU_NUMBER     = 2;
    public static final Integer CAFFE_MOCHA_MENU_NUMBER     = 3;
    public static final Integer CAPPUCCINO_MENU_NUMBER      = 4;
    public static final Integer COFFEE_MENU_NUMBER          = 5;
    public static final Integer DECAF_COFFEE_MENU_NUMBER    = 6;

    public static final Double CAN_NOT_MAKE_DRINK   = -1.0;
    public static final Double CAN_MAKE_DRINK       = 0.0;
    public static final Double ZERO                 = 0.0;

    public static final Integer PRICE      = 0;
    public static final Integer IS_AVAILABLE  = 1;

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
