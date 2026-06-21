package SpamFood.Restaurants;

public class TopPanelHolder {
    private static TopPanel instance;


    public static void setInstance(TopPanel topPanel){
        instance = topPanel;
    }

    public static TopPanel getInstance(){
        return instance;
    }
}
