package SpamFood.OrderHandler;

public class OrderedItems {

    private String restaurantName;
    private String foodName;
    private long price;
    private double rating;
    private int quantity;

    public OrderedItems(String restaurantName, String foodName, long price, double rating, int quantity) {
        this.restaurantName = restaurantName;
        this.foodName = foodName;
        this.price = price;
        this.rating = rating;
        this.quantity = quantity;
    }
    //.................................Getters............................//
    public String getRestaurantName() {
        return restaurantName;
    }

    public String getFoodName() {
        return foodName;
    }

    public long getPrice() {
        return price;
    }

    public double getRating() {
        return rating;
    }

    public int getQuantity() {
        return quantity;
    }
    //..........................Total Price per Items.....................//
    public long getTotalPrice(){
        return price * quantity;
    }
    //.................................Setters............................//
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
