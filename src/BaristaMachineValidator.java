package Barista.src;

import java.util.Arrays;
import static Barista.src.BaristaConstants.*;

/*
 *  The validator class is a place to send user input to check for 
 *  errors.  The exceptions that are thrown are handled depending
 *  on the desired outcome.
 * 
 */
public final class BaristaMachineValidator {
    
    /*
     *  Do not allow creatiion of the Validator class; use as static.
     */
     private BaristaMachineValidator()
     {
         throw new AssertionError();
     }
    
    /*
     *  validateInput(String input) : this method will take in a string
     *  of user input. Thre only valid values (returning true) are those
     *  that are in the set { "q", "Q", "r", "R", 1..MENU_COUNT }.
     *  If an exception is thrown while attempting to parse the input,
     *  the method returns a failure, and the user is allowed to try again.
     */
    public static boolean validateInput(String input)
    {   
        if (Arrays.asList(KEYWORDS).contains(input)) {
            return PROCESS_SUCCESS;
            
        } else {
            
            try {
                
                return Integer.parseInt(input) <= MENU_COUNT;
                
            } catch (Exception e) {
                
                return PROCESS_FAILURE;
            }
        }
    }
}