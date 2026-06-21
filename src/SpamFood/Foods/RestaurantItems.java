package SpamFood.Foods;

import java.util.HashMap;
import java.util.Map;

public class RestaurantItems {

    private static final Map<String , FoodItems[]> menus = new HashMap<>();

    static{
        menus.put("Jasem Sea Food" , new FoodItems[]{
            new FoodItems("Fish & Rice",274_000L , 4.1),
            new FoodItems("Fried Fish",210_000L , 4.5),
            new FoodItems("Fish Egg",195_000L , 4.2 ),
            new FoodItems("Fried Shrimp",255_000L , 4.6 )
        });
        menus.put("Larana Sushi" , new FoodItems[]{
           new FoodItems("Rice Roll", 310_000L , 4.0),
           new FoodItems("Salmon Roll",350_000L , 3.9),
            new FoodItems("Myssu Soap" , 280_000L , 4.3),
            new FoodItems("Adamame", 380_000L , 3.8)
        });
        menus.put("Burger And Drinks" , new FoodItems[]{
                new FoodItems("Hamburger", 180_000L , 4.4),
                new FoodItems("Fried Potato",110_000L , 4.5),
                new FoodItems("Hot-Dog" , 210_000L , 4.3),
                new FoodItems("Handmade Coca-Cola", 80_000L ,4.5)
        });
        menus.put("Garden Cafe" , new FoodItems[]{
                new FoodItems("Espresso", 60_000L , 4.1),
                new FoodItems("Benana Shake",150_000L , 4.5),
                new FoodItems("Ice-Mackyato" , 130_000L , 4.6),
                new FoodItems("Ice-Coffee", 80_000L ,3.9)
        });
    }

    public static FoodItems[] getItems(String restaurantName){
        return menus.getOrDefault(restaurantName , new FoodItems[]{});
    }

}
