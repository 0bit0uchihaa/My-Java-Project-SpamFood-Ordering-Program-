package SpamFood.Foods;

public class FoodItems {
    private String name;
    private long price;
    private double rate;

    public FoodItems(String name, long price , double rate){
        this.name = name;
        this.price = price;
        this.rate = rate;
    }

    public String getName(){ return name; }
    public long getPrice(){ return price; }
    public double getRate(){ return rate; }
}
