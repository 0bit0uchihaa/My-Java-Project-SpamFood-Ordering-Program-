package SpamFood.Restaurants;


import javafx.animation.ScaleTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;


public class RestaurantCards {

    private VBox resCard;
    private String name;

    public RestaurantCards(
              String restaurantName
            , float restaurantRate
            , int deliveryTime
            , String imagePath
            ){
        this.name = restaurantName;
        resCard = createCard(
                  restaurantName
                , restaurantRate
                , deliveryTime
                , imagePath);
    }

    public VBox getCard() { return resCard; }

    public String getName(){return name;}

    private VBox createCard(String name , float rate , int time , String imagePath){
        VBox card = new VBox(10);
        card.setPrefWidth(200);
        card.setPrefHeight(320);
        card.setMaxHeight(320);
        card.setBackground(new Background(new BackgroundFill(
                  Color.WHITESMOKE
                , new CornerRadii(20)
                , Insets.EMPTY
        )));
        card.setAlignment(Pos.TOP_LEFT);
        //.............................Restaurant Logo Or Image....................//
        Image restaurantLogo = new Image(
                getClass()
                        .getResourceAsStream
                                (imagePath));
        ImageView imageView = new ImageView(restaurantLogo);
        imageView.setFitHeight(180);
        imageView.setFitWidth(180);
        imageView.setTranslateX(10);
        imageView.setTranslateY(10);
        imageView.setPreserveRatio(true);

        //..............................Restaurant Name............................//
        Label restaurantName = new Label(name);
        restaurantName.setFont(Font.loadFont(getClass()
                        .getResourceAsStream
                                ("/Poppins/Poppins-Bold.ttf")
                , 15
        ));
        restaurantName.setTextFill(Color.WHEAT.darker());
        restaurantName.setTranslateY(10);
        restaurantName.setTranslateX(10);

        //..............................Rate......................................//
        HBox star = new HBox(5);
        star.setAlignment(Pos.CENTER_LEFT);
        Image icon1 = new Image(getClass().getResourceAsStream("/Images/Star.png"));
        ImageView starRate = new ImageView(icon1);
        starRate.setFitWidth(20);
        starRate.setPreserveRatio(true);
        Label restaurantRate = new Label( ": " + rate);
        restaurantRate.setFont(Font.loadFont(getClass()
                        .getResourceAsStream
                                ("/Poppins/Poppins-Bold.ttf")
                , 15
        ));
        restaurantRate.setTextFill(Color.WHEAT.darker());
        star.getChildren().addAll(starRate , restaurantRate);
        star.setTranslateX(10);
        star.setTranslateY(3);

        //...............................Time......................................//
        HBox delTime = new HBox(5);
        delTime.setAlignment(Pos.CENTER_LEFT);
        Image icon2 = new Image(getClass().getResourceAsStream("/Images/Time.png"));
        ImageView clock = new ImageView(icon2);
        clock.setFitWidth(22);
        clock.setFitHeight(22);
        Label deliveryTime = new Label(": " + time + " min");
        deliveryTime.setFont(Font.loadFont(getClass()
                        .getResourceAsStream
                                ("/Poppins/Poppins-Bold.ttf")
                , 15
        ));
        deliveryTime.setTextFill(Color.WHEAT.darker());
        delTime.getChildren().addAll(clock , deliveryTime);
        delTime.setTranslateX(10);


        //.................................Scale Animation And Shadow Effect...........................//

        DropShadow shadow = new DropShadow();
        shadow.setRadius(5);
        shadow.setOffsetY(3);
        shadow.setColor(Color.rgb(0, 0, 0, 0.3));
        card.setEffect(shadow);

        card.setOnMouseEntered(e -> {
            ScaleTransition scaleUp = new ScaleTransition(Duration.seconds(0.3) , card);
            scaleUp.setToY(1.02);
            scaleUp.setToX(1.02);
            shadow.setRadius(10);
            shadow.setOffsetY(5);
            scaleUp.play();
        });

        card.setOnMouseExited(e -> {
            ScaleTransition scaleDown = new ScaleTransition(Duration.seconds(0.3) , card);
            scaleDown.setToX(1.0);
            scaleDown.setToY(1.0);
            shadow.setRadius(5);
            shadow.setOffsetY(3);
            scaleDown.play();
        });
        card.setCursor(Cursor.HAND);


        card.getChildren().addAll(
                  imageView
                , restaurantName
                , star
                , delTime
        );
        return card;
    }


}
