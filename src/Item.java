class Item {
    double price;
    int stock;
    int sales;
    boolean bestSeller;

    Item(double price, int numPieces) {
        this.price = price;
        this.stock = numPieces;
        sales = 0;
        bestSeller = false;
    }

    void restock(int amount) {
        this.stock = this.stock + amount;
    }

    void purchase(int amount) {
        if(this.stock - amount < 0 | this.stock - amount > this.stock) {
            System.out.println("purchase amount must be a positive number and less than stock");
            return;
        }
        this.stock = this.stock - amount;
        //As a User, I would like the vendor system to track customer purchases for each item,
        //providing insights on popular items and trends

        sales = sales + amount;
        if (sales >= 100) bestSeller = true;
    }

    void purchase(int amount, double discount){

    }

    void setPrice(double price) {
        this.price = price;
    }
}