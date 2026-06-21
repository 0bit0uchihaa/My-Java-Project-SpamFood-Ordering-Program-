package SpamFood.OrderHandler;

import java.util.ArrayList;
import java.util.List;

public class ShoppingBasket {

    private static ShoppingBasket instance;
    private List<OrderedItems> items;

    private ShoppingBasket(){
        items = new ArrayList<>();
    }

    public static ShoppingBasket getInstance(){
        if (instance == null){
            instance = new ShoppingBasket();
        }
        return instance;
    }

    public void addItem(OrderedItems newItem){
        for (OrderedItems item : items){
            if (item.getFoodName().equals(newItem.getFoodName()) &&
                item.getRestaurantName().equals(newItem.getRestaurantName())){
                item.setQuantity(item.getQuantity() + newItem.getQuantity());
                return;
            }
        }
        items.add(newItem);
    }

    public void removeItem(String foodName , String restaurantName){
        items.removeIf( item ->
                item.getFoodName().equals(foodName) &&
                item.getRestaurantName().equals(restaurantName)
        );
    }

    public void updateQuantity(String foodName , String restaurantName , int quantity){
        for (OrderedItems item : items){
            if (item.getFoodName().equals(foodName) &&
            item.getRestaurantName().equals(restaurantName)){
                if ( quantity <= 0 ){
                    removeItem(foodName , restaurantName);
                }
                else{
                    item.setQuantity(quantity);
                }
                return;
            }
        }
    }

    public List<OrderedItems> getItems(){
        return new ArrayList<>(items);
    }

    public long getTotalPrice(){
        long total = 0;
        for (OrderedItems item : items){
            total += item.getTotalPrice();
        }
        return total;
    }

    public int getTotalItems(){
        int total = 0;
        for (OrderedItems item : items){
            total += item.getQuantity();
        }
        return total;
    }

    public void clearItems(){
        items.clear();
    }

    public boolean isClear(){
        return items.isEmpty();
    }

}
