package Barista.src;

public class Barista {

    public static void main(String[] args)
    {
      BaristaMachine baristaMachine = new BaristaMachine();

      baristaMachine.initializeInventory();
      // baristaMachine.decrementInventoryItemBy("Coffee", 3);
      baristaMachine.listInventory();
      // baristaMachine.resetInventory();
      // baristaMachine.listInventory();
    }
}
