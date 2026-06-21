package SpamFood.Foods;

import SpamFood.OrderHandler.OrderedItems;
import SpamFood.OrderHandler.ShoppingBasket;
import SpamFood.Restaurants.TopPanel;
import SpamFood.Restaurants.TopPanelHolder;
import SpamFood.User.UserValidatorClass;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class FoodMenu {

        private StackPane menuContainer;
        private VBox menu;
        private VBox contentBox;
        private VBox menuContentBox;
        private String restaurantName;
        private VBox checkOutOrder;
        private boolean isCheckOutMode = false;
        private Label errorLabel;
        private Label successfullyOrdered;

        public FoodMenu(String menuName){
                this.restaurantName = menuName.replace("'" , "").trim();

                menu = createMenu(menuName);
                checkOutOrder = createCheckOutOrderMenu();

                menuContainer = new StackPane(menu);
                menuContainer.setOpacity(0);
                //...........................Effects And Transition...................//
                DropShadow shadow = new DropShadow();
                shadow.setRadius(5);
                shadow.setOffsetY(3);
                shadow.setColor(Color.rgb(0, 0, 0, 0.3));
                menuContainer.setEffect(shadow);



                FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.8) , menuContainer);
                fadeIn.setFromValue(0);
                fadeIn.setToValue(1);
                fadeIn.play();
        }

        public StackPane getMenu(){ return menuContainer; }

        public void switchToCheckOut(){
                if (isCheckOutMode) return;
                isCheckOutMode = true;

                FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.3), contentBox);
                fadeOut.setFromValue(1);
                fadeOut.setToValue(0);
                fadeOut.setOnFinished(e -> {
                        contentBox.getChildren().clear();
                        VBox checkOutContent = createCheckOutOrderMenu();
                        contentBox.getChildren().addAll(checkOutContent.getChildren());
                        FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.3), contentBox);
                        fadeIn.setFromValue(0);
                        fadeIn.setToValue(1);
                        fadeIn.play();
                });
                fadeOut.play();
        }

        public void switchToMainMenu(){
                if (!isCheckOutMode) return;
                isCheckOutMode = false;

                FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.3), contentBox);
                fadeOut.setFromValue(1);
                fadeOut.setToValue(0);
                fadeOut.setOnFinished(e -> {
                        contentBox.getChildren().clear();
                        contentBox.getChildren().add(menuContentBox);
                        FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.3), contentBox);
                        fadeIn.setFromValue(0);
                        fadeIn.setToValue(1);
                        fadeIn.play();
                });
                fadeOut.play();
        }
        //.......................Order Menu......................//

        public VBox createCheckOutOrderMenu(){
                VBox checkOutOrder = new VBox(10);
                checkOutOrder.setAlignment(Pos.CENTER);
                checkOutOrder.setPadding(new Insets(5));

                ShoppingBasket shop = ShoppingBasket.getInstance();

                Label titleLabel = new Label("Your Orders");
                titleLabel.setTextFill(Color.WHEAT.darker());
                titleLabel.setFont(Font.loadFont(getClass()
                                .getResourceAsStream
                                        ("/Poppins/Poppins-Bold.ttf")
                        , 15
                ));
                HBox titleBox = new HBox(titleLabel);
                titleBox.setAlignment(Pos.TOP_CENTER);
                //........................Item Box......................//

                VBox itemsBox = new VBox(10);
                itemsBox.setPadding(new Insets(10));

                long total = 0;
                boolean hasItem = false;

                for (OrderedItems item : shop.getItems()){
                        if (item.getRestaurantName().equals(restaurantName)){
                                hasItem = true;
                                HBox itemRow = createItemRow(item);
                                itemsBox.getChildren().add(itemRow);
                                total += item.getTotalPrice();
                        }
                }

                if (!hasItem){
                        Label emptyBasket = new Label("No Item in Your Order");
                        emptyBasket.setTextFill(Color.WHEAT.darker());
                        emptyBasket.setFont(Font.loadFont(getClass()
                                        .getResourceAsStream
                                                ("/Poppins/Poppins-Bold.ttf")
                                , 15
                        ));
                        itemsBox.getChildren().add(emptyBasket);
                }

                Separator separator = new Separator();
                separator.setPadding(new Insets(10 , 0 , 10 , 0));

                HBox totalBox = new HBox();
                totalBox.setAlignment(Pos.CENTER_RIGHT);
                Label totalTitle = new Label("Total: ");
                totalTitle.setTextFill(Color.WHEAT.darker());
                totalTitle.setFont(Font.loadFont(getClass()
                                .getResourceAsStream
                                        ("/Poppins/Poppins-Bold.ttf")
                        , 15
                ));
                Label totalPriceTitle = new Label(total +" T");
                totalPriceTitle.setTextFill(Color.WHEAT.darker());
                totalPriceTitle.setFont(Font.loadFont(getClass()
                                .getResourceAsStream
                                        ("/Poppins/Poppins-Bold.ttf")
                        , 15
                ));

                totalBox.getChildren().addAll(totalTitle, totalPriceTitle);
                totalBox.setTranslateX(-10);

                ScrollPane scrollPane = new ScrollPane(itemsBox);
                scrollPane.setFitToWidth(true);
                scrollPane.setMaxWidth(500);
                scrollPane.setMaxHeight(100);
                scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
                scrollPane.setStyle(
                        "-fx-background-color: transparent;" +
                                "-fx-background: transparent;" +
                                "-fx-border-color: transparent;" +
                                "-fx-control-inner-background: transparent;" +
                                "-fx-box-border: transparent;"
                );
                scrollPane.setPickOnBounds(false);
                //........................Shadow...................//

                DropShadow shadow = new DropShadow();
                shadow.setRadius(8);
                shadow.setOffsetY(3);
                shadow.setColor(Color.rgb(0,0,0,0.5));
                //........................Address Field.............//

                VBox addressForm = new VBox();
                Label addressTitle = new Label("Address: ");
                addressTitle.setTranslateX(20);
                addressTitle.setFont(Font.loadFont(getClass()
                                .getResourceAsStream
                                        ("/Poppins/Poppins-Bold.ttf")
                        , 15
                ));
                addressTitle.setTextFill(Color.WHEAT.darker());

                TextField addressField = new TextField();
                addressField.setPromptText("Enter Your Address");
                addressField.setMaxWidth(450);
                addressField.setPrefHeight(100);
                addressField.setAlignment(Pos.TOP_LEFT);
                addressField.setTranslateX(20);
                addressField.setTranslateY(5);
                addressField.setBackground(new Background(
                        new BackgroundFill(
                                Color.WHITE.brighter() ,new CornerRadii(20) ,Insets.EMPTY
                        )
                ));
                addressField.setFont(Font.loadFont(
                        getClass()
                                .getResourceAsStream("/Poppins/Poppins-Regular.ttf")
                        ,15
                ));
                addressField.setEffect(shadow);

                addressForm.getChildren().addAll(addressTitle , addressField);
                addressForm.setTranslateY(-20);

                HBox buttonBox = new HBox(10);
                buttonBox.setAlignment(Pos.CENTER_LEFT);

                Button confirmButton = new Button("Confirm Order");
                confirmButton.setFont(Font.loadFont(getClass()
                                .getResourceAsStream("/Poppins/Poppins-ExtraBold.ttf")
                        ,15
                ));
                confirmButton.setTextFill(Color.BLACK);
                confirmButton.setBackground(new Background(new BackgroundFill(
                        Color.WHEAT,
                        new CornerRadii(40),
                        Insets.EMPTY
                )));

                confirmButton.setEffect(shadow);

                confirmButton.setCursor(Cursor.HAND);

                errorLabel = new Label();

                successfullyOrdered = new Label();


                confirmButton.setOnMouseEntered(e -> {
                        confirmButton.setBackground(new Background(new BackgroundFill(
                                Color.WHEAT.darker(),
                                new CornerRadii(40),
                                Insets.EMPTY
                        )));
                        shadow.setRadius(12);
                        shadow.setOffsetY(4);
                        confirmButton.setScaleX(1.02);
                        confirmButton.setScaleY(1.02);
                });
                confirmButton.setOnMouseExited(e -> {
                        confirmButton.setBackground(new Background(new BackgroundFill(
                                Color.WHEAT
                                ,new  CornerRadii(40)
                                ,Insets.EMPTY
                        )));
                        shadow.setRadius(8);
                        shadow.setOffsetY(3);
                        confirmButton.setScaleY(1);
                        confirmButton.setScaleX(1);
                });

                confirmButton.setOnAction( e -> {

                        String address = addressField.getText();

                        successfullyOrdered.setVisible(false);
                        errorLabel.setVisible(false);

                        try {
                                UserValidatorClass.validateAddress(address);
                                successfullyOrdered.setText("Your Order Successfully confirmed");
                                successfullyOrdered.setTextFill(Color.GREEN);
                                successfullyOrdered.setFont(Font.loadFont(getClass()
                                                .getResourceAsStream("/Poppins/Poppins-ExtraBold.ttf")
                                        ,15
                                ));
                                successfullyOrdered.setVisible(true);
                                successfullyOrdered.setMaxWidth(250);
                                successfullyOrdered.setMaxHeight(250);
                                successfullyOrdered.setWrapText(true);
                                shop.getItems().removeIf(items -> items.getRestaurantName().equals(restaurantName));

                                TopPanel topPanel = TopPanelHolder.getInstance();
                                if (topPanel != null) {
                                        topPanel.refreshShoppingPopup();
                                }

                        }
                        catch (IllegalArgumentException error){

                                errorLabel.setText("Invalid Address or No Address Entered");
                                errorLabel.setFont(Font.loadFont(getClass()
                                                .getResourceAsStream("/Poppins/Poppins-ExtraBold.ttf")
                                        ,15
                                ));
                                errorLabel.setTextFill(Color.RED);
                                errorLabel.setVisible(true);
                                errorLabel.setMaxWidth(250);
                                errorLabel.setMaxHeight(250);
                                errorLabel.setWrapText(true);
                                TranslateTransition shake = new TranslateTransition(Duration.seconds(0.05) ,addressField);
                                shake.setFromX(20);
                                shake.setToX(25);
                                shake.setAutoReverse(true);
                                shake.setCycleCount(4);
                                shake.setOnFinished(ev -> addressField.setTranslateX(20));
                                shake.play();
                        }
                });
                buttonBox.setTranslateX(20);
                buttonBox.setTranslateY(-20);
                StackPane messageLabel = new StackPane(errorLabel , successfullyOrdered);

                buttonBox.getChildren().addAll(
                          confirmButton
                        , messageLabel
                );

                checkOutOrder.getChildren().addAll(
                          titleBox
                        , scrollPane
                        , totalBox
                        , addressForm
                        , buttonBox
                );

                return checkOutOrder;
        }

        private HBox createItemRow(OrderedItems item){
                HBox row = new HBox(10);
                row.setAlignment(Pos.CENTER_LEFT);
                row.setPadding(new Insets(5));

                Label nameLabel = new Label(item.getFoodName());
                nameLabel.setFont(Font.loadFont(getClass()
                        .getResourceAsStream("/Poppins/Poppins-Regular.ttf"), 14));
                nameLabel.setTextFill(Color.WHEAT.darker());
                nameLabel.setPrefWidth(150);

                Region spacer = new Region();
                HBox.setHgrow(spacer, Priority.ALWAYS);

                Label qtyLabel = new Label("x" + item.getQuantity());
                qtyLabel.setFont(Font.loadFont(getClass()
                        .getResourceAsStream("/Poppins/Poppins-Regular.ttf"), 14));
                qtyLabel.setTextFill(Color.WHEAT.darker());

                Label priceLabel = new Label(item.getTotalPrice() + " T");
                priceLabel.setFont(Font.loadFont(getClass()
                        .getResourceAsStream("/Poppins/Poppins-Bold.ttf"), 14));
                priceLabel.setTextFill(Color.ORANGE.darker());

                row.getChildren().addAll(nameLabel, spacer, qtyLabel, priceLabel);

                return row;
        }

        private VBox createMenu(String name ){
                menu = new VBox(15);
                menu.setPickOnBounds(false);
                menu.setMaxWidth(500);
                menu.setMaxHeight(460);
                menu.setBackground(new Background(new BackgroundFill(
                        Color.WHITESMOKE,
                        new CornerRadii(20),
                        Insets.EMPTY
                )));

                contentBox = new VBox(15);
                menuContentBox = new VBox(15);

                Label menuName = new Label(name.replace("'" , "" ) + " Menu");
                menuName.setTextFill(Color.WHEAT.darker());
                menuName.setFont(Font.loadFont(getClass()
                                .getResourceAsStream
                                        ("/Poppins/Poppins-ExtraBold.ttf")
                        , 20
                ));
                HBox menuNameBox = new HBox(menuName);
                menuNameBox.setAlignment(Pos.CENTER);
                menuNameBox.setTranslateY(10);
                menuContentBox.getChildren().addAll(menuNameBox);

                FoodItems[] items = RestaurantItems.getItems(name.replace("'" , "").trim());
                for (FoodItems item : items){
                        menuContentBox.getChildren().add(createFoodRow(item));
                }

                contentBox.getChildren().addAll(menuContentBox);
                menu.getChildren().addAll(contentBox);

                return menu;
        }

        private VBox createFoodRow(FoodItems item) {
                VBox allRows = new VBox(5);
                allRows.setTranslateX(20);
                //.......................HBox For each Row.............//
                HBox row = new HBox(10);
                row.setAlignment(Pos.CENTER_LEFT);
                row.setPadding(new Insets(10));
                //.......................VBox For Information..........//
                VBox foodInfo = new VBox();
                foodInfo.setPrefWidth(220);
                //.......................Labels For Each Food.........//
                Label nameLabel = new Label(item.getName());
                nameLabel.setTextFill(Color.WHEAT.darker());
                nameLabel.setFont(Font.loadFont(getClass()
                                .getResourceAsStream
                                        ("/Poppins/Poppins-Bold.ttf")
                        , 15
                ));

                String forPriceLabel = String.valueOf(item.getPrice());
                Label priceLabel = new Label(forPriceLabel + " T");
                priceLabel.setTextFill(Color.WHEAT.darker());
                priceLabel.setFont(Font.loadFont(getClass()
                                .getResourceAsStream
                                        ("/Poppins/Poppins-Bold.ttf")
                        , 15
                ));

                HBox star = new HBox(10);
                Image icon1 = new Image(getClass().getResourceAsStream("/Images/Star.png"));
                ImageView starRate = new ImageView(icon1);
                starRate.setPreserveRatio(true);
                starRate.setFitHeight(20);
                starRate.setFitWidth(20);
                String forRatingLabel = String.valueOf(item.getRate());
                Label rateLabel = new Label(forRatingLabel);
                rateLabel.setTextFill(Color.WHEAT.darker());
                rateLabel.setFont(Font.loadFont(getClass()
                                .getResourceAsStream
                                        ("/Poppins/Poppins-Bold.ttf")
                        , 15
                ));
                star.getChildren().addAll(starRate , rateLabel);
                foodInfo.getChildren().addAll(
                        nameLabel
                        , priceLabel
                        , star
                );
                //....................HBox For Order Buttons.........//
                HBox orderBox = new HBox(10);
                orderBox.setAlignment(Pos.CENTER_RIGHT);
                orderBox.setPadding(new Insets(10));
                HBox.setHgrow(orderBox, Priority.ALWAYS);
                orderBox.setTranslateX(-20);
                Button minusOrder = createOrderButton("-","#0a0a0a");


                Label orderCount = new Label("0");
                orderCount.setTextFill(Color.WHEAT.darker());
                orderCount.setFont(Font.loadFont(getClass()
                                .getResourceAsStream
                                        ("/Poppins/Poppins-Bold.ttf")
                        , 17
                ));

                Button plusOrder = createOrderButton("+" ,"#0a0a0a");


                minusOrder.setVisible(false);

                plusOrder.setOnAction(e ->{
                        int count = Integer.parseInt(orderCount.getText());
                        orderCount.setText(String.valueOf(count + 1));
                        minusOrder.setVisible(true);

                        ShoppingBasket shop = ShoppingBasket.getInstance();
                        OrderedItems orderItem = new OrderedItems(
                                restaurantName,
                                item.getName(),
                                item.getPrice(),
                                item.getRate(),
                                1
                        );
                        shop.addItem(orderItem);


                        TopPanel topPanel = TopPanelHolder.getInstance();
                        if (topPanel != null) {
                                topPanel.refreshShoppingPopup();
                        }
                });

                minusOrder.setOnAction(e -> {
                        int count = Integer.parseInt(orderCount.getText());
                        if (count > 0){
                                int newCount = count - 1;
                                orderCount.setText(String.valueOf(newCount));
                                if (newCount == 0 ) minusOrder.setVisible(false);

                                ShoppingBasket shop = ShoppingBasket.getInstance();

                                if ( newCount == 0 ){
                                        shop.removeItem(item.getName(), restaurantName);
                                }
                                else {
                                        shop.updateQuantity(item.getName(), restaurantName , newCount);
                                }

                                TopPanel topPanel = TopPanelHolder.getInstance();
                                if (topPanel != null) {
                                        topPanel.refreshShoppingPopup();
                                }
                        }
                });

                orderBox.getChildren().addAll(minusOrder
                        , orderCount
                        , plusOrder
                 );
                row.getChildren().addAll(
                        foodInfo
                        , orderBox
                );
                allRows.getChildren().addAll(row);

                return allRows;
        }

        private Button createOrderButton(String text , String color){
                Button btn = new Button(text);
                btn.setPrefWidth(25);
                btn.setPrefHeight(25);
                btn.setFont(Font.loadFont(getClass()
                                .getResourceAsStream
                                        ("/Poppins/Poppins-Bold.ttf")
                        , 15
                ));
                btn.setTextFill(Color.WHEAT.darker());
                btn.setBackground(new Background(new BackgroundFill(
                        Color.ORANGE.darker(),
                        new CornerRadii(50),
                        Insets.EMPTY
                )));

                DropShadow shadow = new DropShadow();
                shadow.setRadius(8);
                shadow.setOffsetY(3);
                shadow.setColor(Color.rgb(0,0,0,0.5));
                btn.setEffect(shadow);

                btn.setOnMouseEntered(event -> {
                        System.out.println("Entered");
                        ScaleTransition scaleUp = new ScaleTransition(Duration.seconds(0.3) , btn);
                        scaleUp.setToY(1.02);
                        scaleUp.setToX(1.02);
                        shadow.setRadius(12);
                        shadow.setOffsetY(4);
                        scaleUp.play();
                });

                btn.setOnMouseExited(event1 -> {
                        ScaleTransition scaleDown = new ScaleTransition(Duration.seconds(0.3) , btn);
                        scaleDown.setToX(1.0);
                        scaleDown.setToY(1.0);
                        shadow.setRadius(8);
                        shadow.setOffsetY(3);
                        scaleDown.play();
                });

                btn.setCursor(Cursor.HAND);

                return btn;
        }
}
