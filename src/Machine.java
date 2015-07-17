package Barista.src;

public interface Machine {

    void listInventory();
    void initializeInventory();
    void resetInventory();
    void decrementInventoryItemBy(String item, int howMany);
}
