import java.util.ArrayList;
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
        System.out.println("Please specify a valid item to restock");
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
        if(amt < 0) return;
        double newBalance = this.balance + amt;
        if(newBalance < 0) return;
        this.balance = newBalance;
    }

    /**
     * attempt to purchase named item.  Message returned if
     * the balance isn't sufficient to cover the item cost.
     *
     * @param name The name of the item to purchase ("Candy" or "Gum")
     */
    void select(String name, int amount) {
        selectWithDiscount(name, amount, 100);
    }

    void select(String name){
        select(name, 1);
    }

    void printStock() {
        Stock.forEach((name, item) -> {
            getItemInfo(name);
        });
    }

    int getStock(String itemName){
        if (Stock.containsKey(itemName)) {
            return Stock.get(itemName).stock;
        } else {
            System.out.println("Sorry, don't know that item");
            return 0;
        }
    }

    //As a User, I would like to check an item’s description or details before purchasing, so I
    //can make informed choices on item benefits and uses.
    void getItemInfo(String name){
        Item item = Stock.get(name);
        System.out.println("\nItem: " + name + "\nPrice: " + item.price + "\nStock: " + item.stock + "\nLifetime Sales: " + item.sales );
        if(item.bestSeller) System.out.println("This item is a bestseller!");
    }


    //As a User, I would like to apply discounts to specific items or categories within the vendor’s
    //inventory, allowing for seasonal sales or promotions.
    void selectWithDiscount(String name, int amount, float discount){
        if(discount < 0 | discount > 100) {
            System.out.println("please select a valid discount");
            return;
        }
        if (Stock.containsKey(name)) {
            Item item = Stock.get(name);
            if (balance < item.price) {
                System.out.println("Gimme more money");
                return;
            }
            item.purchase(amount);
            this.balance = this.balance - ((item.price * discount)/100);

            //As a User, I would like to remove an item from the vendor’s inventory if it is discontinued
            //or no longer available.
            if (item.stock < 1){
                Stock.remove(name);
            }
        } else System.out.println("Sorry, don't know that item");

    }
}

