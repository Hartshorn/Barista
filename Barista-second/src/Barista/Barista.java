package Barista;

/*
 *******************************************
 *  The Barista-matic!
 */
public class Barista {

    public static void main(String[] args)
    {
          BaristaMachine baristaMachine = new BaristaMachine();
          
          do 
          { 
              baristaMachine.displayMenu();
              
          } while (baristaMachine.isAbleToProcessInput());
    }
}
