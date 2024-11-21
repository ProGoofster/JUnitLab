import java.util.HashMap;


/**
 * Class for a Vending Machine.  Contains a hashtable mapping item names to item data, as
 * well as the current balance of money that has been deposited into the machine.
 */
class Vendor {
    private static HashMap<String, Item> Stock = new HashMap<String, Item>();
    private double balance;

    Vendor(int numCandy, int numGum) {
        Stock.put("Candy", new Item(1.25, numCandy));
        Stock.put("Gum", new Item(.5, numGum));
        this.balance = 0;
    }

    Vendor() {
        this.balance = 0;
    }

    //restock items on a vendor, supports new items
    void stockVendor(String name, int count, double price) {
        if (Stock.containsKey(name) && price < 0) {
            Stock.get(name).restock(count);
            return;
        }
        if (Stock.containsKey(name)) {
            Stock.get(name).restock(count);
            Stock.get(name).setPrice(price);
            return;
        }
        Stock.put(name, new Item(price, count));
    }

    void restockVendor(String name, int count) {
        if (Stock.containsKey(name)) {
            stockVendor(name, count, -1);
            return;
        }
        System.out.println("Please specify a price when");
    }

    void changeItemName(String currentName, String newName) {
        if (Stock.containsKey(currentName)) {
            Item item = Stock.remove(currentName);
            Stock.put(newName, item);
            return;
        }
        System.out.println("Item " + currentName + " is not in stock. Please enter an item name that is in stock");
    }

    /**
     * resets the Balance to 0
     */
    void resetBalance() {
        this.balance = 0;
    }

    /**
     * returns the current balance
     */
    double getBalance() {
        return this.balance;
    }


    /**
     * adds money to the machine's balance
     *
     * @param amt how much money to add
     */
    void addMoney(double amt) {
        this.balance = this.balance + amt;
    }

    /**
     * attempt to purchase named item.  Message returned if
     * the balance isn't sufficient to cover the item cost.
     *
     * @param name The name of the item to purchase ("Candy" or "Gum")
     */
    void select(String name) {
        if (Stock.containsKey(name)) {
            Item item = Stock.get(name);
            if (balance >= item.price) {
                item.purchase(1);
                this.balance = this.balance - item.price;
            } else
                System.out.println("Gimme more money");
        } else System.out.println("Sorry, don't know that item");
    }

    void restock() {

    }

    void printStock() {
        Stock.forEach((name, item) -> {
            System.out.println("Item: "+name);
            System.out.println("Price: "+item.price);
            System.out.println("Amount: "+item.stock);
        });
    }
}

