package SpamFood.Restaurants;

import SpamFood.OrderHandler.OrderedItems;
import SpamFood.OrderHandler.ShoppingBasket;
import SpamFood.StartWindow;
import SpamFood.User.User;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;

public class TopPanel {

    private StartWindow startWindow;
    private Stage stage;
    private VBox topPanel;
    private User userInformation;
    private Popup shoppingBasketPopup;
    private VBox shoppingInfo;
    private VBox itemsContainer;

    public TopPanel(User user , StartWindow startWindow , Stage stage){
        this.startWindow = startWindow;
        this.stage = stage;
        this.userInformation = user;
        topPanel = createTopPanel();
    }

    public VBox getTopPanel(){
        return topPanel;
    }

    private VBox createTopPanel(){
        VBox topVBox = new VBox();
        topVBox.setPadding(Insets.EMPTY);
        LinearGradient gradient = new LinearGradient(
                0 , 1,
                0 , 0,
                true,
                CycleMethod.NO_CYCLE,
                new Stop(0, Color.RED),
                new Stop(1,Color.ORANGE)
        );
        topVBox.setBackground(new Background(new BackgroundFill(
                gradient ,new CornerRadii(0 , 0 , 15 , 15 , false) , Insets.EMPTY)));
        topVBox.setMaxHeight(100);


        HBox headerUser = new HBox(15);
        headerUser.setAlignment(Pos.CENTER_LEFT);

//.......................................Avatar Logo....................................//
        String firstLetterOfUser = userInformation
                .getUserName()
                .substring(0,1)
                .toUpperCase();
        Label avatar = new Label(firstLetterOfUser);
        avatar.setTextFill(Color.WHEAT);
        avatar.setFont(Font.loadFont(getClass()
                .getResourceAsStream
                        ("/Poppins/Poppins-ExtraBold.ttf")
                , 30
        ));
        Image avatarCircle = new Image(getClass().getResourceAsStream("/Images/Circle.png"));
        ImageView avatarCir = new ImageView(avatarCircle);
        avatarCir.setFitHeight(50);
        avatarCir.setFitWidth(50);
        StackPane avatarLogo = new StackPane();
        avatarLogo.getChildren().addAll(avatarCir , avatar);
        avatarLogo.setTranslateX(20);
        avatarLogo.setTranslateY(20);
        avatarLogo.setOnMouseEntered( e -> {
            avatarLogo.setScaleX(1.02);
            avatarLogo.setScaleY(1.02);
            TranslateTransition up = new TranslateTransition(
                    Duration.seconds(0.4) ,
                    avatarLogo
            );
            up.setToY(17);
            up.play();
        });
        avatarLogo.setOnMouseExited( e -> {
            avatarLogo.setScaleX(1);
            avatarLogo.setScaleY(1);
            TranslateTransition down = new TranslateTransition(
                    Duration.seconds(0.3) ,
                    avatarLogo
            );
            down.setToY(20);
            down.play();
        });
        avatarLogo.setCursor(Cursor.HAND);
        //.............................Username display....................//
        String restUsername = userInformation
                .getUserName()
                .substring(1)
                .toUpperCase();
        Label restOfUsername = new Label(restUsername);
        restOfUsername.setTextFill(Color.WHEAT);
        restOfUsername.setFont(Font.loadFont(getClass()
                        .getResourceAsStream
                                ("/Poppins/Poppins-Bold.ttf")
                , 20
        ));
        restOfUsername.setTranslateX(15);
        restOfUsername.setTranslateY(25);
        VBox status = new VBox(5);
        status.setAlignment(Pos.CENTER_LEFT);
        Label loggedInDisplay = new Label("Logged In");
        loggedInDisplay.setTextFill(Color.WHEAT);
        loggedInDisplay.setFont(Font.loadFont(getClass()
                        .getResourceAsStream
                                ("/Poppins/Poppins-Regular.ttf")
                , 15
        ));
        loggedInDisplay.setTranslateX(15);
        loggedInDisplay.setTranslateY(10);
        status.getChildren().add(loggedInDisplay);
        VBox userInfoBoxInTop = new VBox(10);
        userInfoBoxInTop.setAlignment(Pos.CENTER_LEFT);
        userInfoBoxInTop.setPrefWidth(300);
        userInfoBoxInTop.setTranslateX(0);
        userInfoBoxInTop.setTranslateY(5);
        userInfoBoxInTop.getChildren().addAll(restOfUsername , status);
        //...........................pop up window..................................//
        Popup userInfoPopUp = new Popup();
        userInfoPopUp.setAutoHide(true);

        VBox infoPopUp = new VBox();
        infoPopUp.setPrefWidth(200);
        infoPopUp.setPrefHeight(180);
        infoPopUp.setPadding(Insets.EMPTY);
        infoPopUp.setBackground(new Background(new BackgroundFill(
                Color.WHEAT.brighter()
                , new CornerRadii(20)
                , Insets.EMPTY
        )));
        infoPopUp.setBorder(new Border(new BorderStroke(
                Color.WHEAT.darker()
                , BorderStrokeStyle.SOLID
                , new CornerRadii(20)
                , BorderWidths.EMPTY
        )));

        DropShadow shadow = new DropShadow();
        shadow.setRadius(8);
        shadow.setOffsetY(3);
        shadow.setColor(Color.rgb(0,0,0,0.5));
        infoPopUp.setEffect(shadow);

        Label profilelabel = new Label("Your Profile");
        profilelabel.setTextFill(Color.WHEAT.darker());
        profilelabel.setFont(Font.loadFont(getClass()
                        .getResourceAsStream
                                ("/Poppins/Poppins-ExtraBold.ttf")
                , 20
        ));
        profilelabel.setTranslateX(40);

        VBox userAllInformation = new VBox();
        userAllInformation.setAlignment(Pos.CENTER_LEFT);
        userAllInformation.setPadding(new Insets(7));
        String usernameForPopUp = userInformation.getUserName();
        Label usernameLabelPopUp = new Label("Username:\n" + usernameForPopUp);
        usernameLabelPopUp.setTextFill(Color.WHEAT.darker());
        usernameLabelPopUp.setFont(Font.loadFont(getClass()
                        .getResourceAsStream
                                ("/Poppins/Poppins-Bold.ttf")
                , 15
        ));

        String passwordForPopUp = userInformation.getPassword();
        String hidePasswordForPopUp = "*".repeat(passwordForPopUp.length());
        Label passwordLabelPopUp = new Label("Password:\n" + hidePasswordForPopUp);
        passwordLabelPopUp.setTextFill(Color.WHEAT.darker());
        passwordLabelPopUp.setFont(Font.loadFont(getClass()
                        .getResourceAsStream
                                ("/Poppins/Poppins-Bold.ttf")
                , 15
        ));

        passwordLabelPopUp.setOnMouseEntered(e -> {
            passwordLabelPopUp.setText("Password:\n" + passwordForPopUp);
        });
        String finalHidePasswordForPopUp = hidePasswordForPopUp;
        passwordLabelPopUp.setOnMouseExited(e -> {
            passwordLabelPopUp.setText("Password:\n" + finalHidePasswordForPopUp);
        });

        String phoneNumberForPopUp = userInformation.getPhoneNumber();
        Label phoneNumberLabel = new Label("Phone Number:\n" + phoneNumberForPopUp);
        phoneNumberLabel.setTextFill(Color.WHEAT.darker());
        phoneNumberLabel.setFont(Font.loadFont(getClass()
                        .getResourceAsStream
                                ("/Poppins/Poppins-Bold.ttf")
                , 15
        ));

        userAllInformation.getChildren().addAll(
                usernameLabelPopUp
                , passwordLabelPopUp
                , phoneNumberLabel
        );
        userAllInformation.setTranslateX(10);

        infoPopUp.getChildren().addAll(profilelabel
                , userAllInformation
        );

        userInfoPopUp.getContent().add(infoPopUp);

        avatarLogo.setOnMouseClicked( e -> {

            FadeTransition popUpFadeIn = new FadeTransition(
                Duration.seconds(0.5)
                , infoPopUp);
            popUpFadeIn.setFromValue(0);
            popUpFadeIn.setToValue(1);
            popUpFadeIn.play();
            Window currentWindow = avatarLogo.getScene().getWindow();
            double positionX = avatarLogo.localToScreen(avatarLogo.getBoundsInLocal()).getMinX();
            double positionY = avatarLogo.localToScreen(avatarLogo.getBoundsInLocal()).getMaxY();

            userInfoPopUp.show(currentWindow, positionX + 5, positionY + 5);
        });
        //....................Top Right.............................//
        HBox topRightIcons = new HBox(10);
        topRightIcons.setAlignment(Pos.CENTER_RIGHT);

        //...............................Log out Icon.....................//
        Image logOut = new Image(getClass()
                .getResourceAsStream
                        ("/Images/LogOut.png"));
        ImageView logOutIcon = new ImageView(logOut);
        StackPane logOutContainer = new StackPane(logOutIcon);
        logOutContainer.setPrefSize(30, 30);
        logOutContainer.setCursor(Cursor.HAND);
        logOutContainer.setStyle("-fx-background-color: transparent;");
        logOutIcon.setFitHeight(30);
        logOutIcon.setFitWidth(30);

        logOutContainer.setOnMouseEntered( e -> {
            logOutIcon.setScaleY(1.02);
            logOutIcon.setScaleX(1.02);

            TranslateTransition up = new TranslateTransition(
                    Duration.seconds(0.5)
                    , logOutIcon);

            up.setToY(-3);
            up.play();
        });
        logOutContainer.setOnMouseExited(e -> {
            logOutIcon.setScaleY(1);
            logOutIcon.setScaleX(1);

            TranslateTransition down = new TranslateTransition(
                    Duration.seconds(0.5)
                    , logOutIcon
            );

            down.setToY(0);
            down.play();
        });
        logOutContainer.setOnMouseClicked(e -> {
            FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.5)
                    , topPanel
                    .getScene()
                    .getRoot());
            fadeOut.setFromValue(1);
            fadeOut.setToValue(0);
            fadeOut.setOnFinished(ev -> {
                startWindow.show();
            });
            fadeOut.play();
        });
        logOutContainer.setCursor(Cursor.HAND);
        //...............................Shopping Icon...........................//
        Image shoppingBasket = new Image(
                getClass()
                .getResourceAsStream
                ("/Images/Shop.png"));
        ImageView shoppingBasketIcon = new ImageView(shoppingBasket);
        StackPane shoppingBasketContainer = new StackPane(shoppingBasketIcon);
        shoppingBasketContainer.setPrefSize(30, 30);
        shoppingBasketContainer.setCursor(Cursor.HAND);
        shoppingBasketContainer.setStyle("-fx-background-color: transparent;");
        shoppingBasketIcon.setFitWidth(45);
        shoppingBasketIcon.setFitHeight(45);
        //............................Pop Up...................................//
        shoppingBasketPopup = new Popup();
        shoppingBasketPopup.setAutoHide(true);

        shoppingInfo = new VBox(10);
        shoppingInfo.setPrefWidth(300);
        shoppingInfo.setPrefHeight(280);
        shoppingInfo.setBackground(new Background(new BackgroundFill(
                Color.WHEAT.brighter()
                , new CornerRadii(20)
                , Insets.EMPTY
        )));
        shoppingInfo.setBorder(new Border(new BorderStroke(
                Color.WHEAT.darker()
                , BorderStrokeStyle.SOLID
                , new CornerRadii(20)
                , BorderWidths.EMPTY
        )));
        shoppingInfo.setEffect(shadow);

        Label shopBasketLabel = new Label("Your Order");
        shopBasketLabel.setTranslateX(100);
        shopBasketLabel.setFont(Font.loadFont(getClass()
                        .getResourceAsStream
                                ("/Poppins/Poppins-ExtraBold.ttf")
                , 20
        ));
        shopBasketLabel.setTextFill(Color.WHEAT.darker());

        shoppingInfo.getChildren().addAll(shopBasketLabel);

        refreshShoppingPopup();

        shoppingBasketPopup.getContent().add(shoppingInfo);


        shoppingBasketContainer.setOnMouseEntered( e -> {
            shoppingBasketIcon.setScaleY(1.02);
            shoppingBasketIcon.setScaleX(1.02);

            TranslateTransition up = new TranslateTransition(
                    Duration.seconds(0.5)
                    , shoppingBasketIcon
            );

            up.setToY(-3);
            up.play();
        });
        shoppingBasketContainer.setOnMouseExited(e -> {
            shoppingBasketIcon.setScaleY(1);
            shoppingBasketIcon.setScaleX(1);

            TranslateTransition down = new TranslateTransition(
                    Duration.seconds(0.5)
                    ,shoppingBasketIcon
            );

            down.setToY(0);
            down.play();
        });

        shoppingBasketContainer.setOnMouseClicked( e -> {
            FadeTransition popUpFadeIn = new FadeTransition(
                    Duration.seconds(0.5)
                    , shoppingInfo);
            popUpFadeIn.setFromValue(0);
            popUpFadeIn.setToValue(1);
            popUpFadeIn.play();
            Window currentWindow2 = shoppingBasketContainer.getScene().getWindow();
            double positionX = shoppingBasketContainer.localToScreen(shoppingBasketContainer.getBoundsInLocal()).getMinX();
            double positionY = shoppingBasketContainer.localToScreen(shoppingBasketContainer.getBoundsInLocal()).getMaxY();

            shoppingBasketPopup.show(currentWindow2, positionX - 270, positionY - 5);
        });

        shoppingBasketIcon.setCursor(Cursor.HAND);

        topRightIcons.setTranslateX(400);
        topRightIcons.setTranslateY(20);

        topRightIcons.getChildren().addAll(
                logOutContainer
                , shoppingBasketContainer
        );
        StackPane allObjectsOnToPanel = new StackPane(
                userInfoBoxInTop
                , topRightIcons
        );



        headerUser.getChildren().addAll(
                  avatarLogo
                , allObjectsOnToPanel
        );

        topVBox.getChildren().add(headerUser);

        TopPanelHolder.setInstance(this);

        return topVBox;
    }

    public void refreshShoppingPopup(){
        if ( shoppingInfo == null) return;

        shoppingInfo.getChildren().clear();

        ShoppingBasket shop = ShoppingBasket.getInstance();

        Label shopBasketLabel = new Label("Your Order");
        shopBasketLabel.setTranslateX(100);
        shopBasketLabel.setFont(Font.loadFont(getClass()
                .getResourceAsStream("/Poppins/Poppins-ExtraBold.ttf"), 20));
        shopBasketLabel.setTextFill(Color.WHEAT.darker());

        itemsContainer = new VBox(10);
        itemsContainer.setPadding(new Insets(10));

        if (shop.isClear()){
            Label clear = new Label("Your Shop Basket is Empty");
            clear.setFont(Font.loadFont(getClass()
                    .getResourceAsStream("/Poppins/Poppins-Regular.ttf"), 14));
            clear.setTextFill(Color.GRAY);
            itemsContainer.getChildren().add(clear);
            shoppingInfo.getChildren().addAll(shopBasketLabel, itemsContainer);
        } else {
            for (OrderedItems item : shop.getItems()) {
                HBox itemRow = createItemRowForPopup(item);
                itemsContainer.getChildren().add(itemRow);
            }
            Separator separator = new Separator();
            separator.setPadding(new Insets(10, 0, 10, 0));

            HBox totalBox = createTotalBoxForPopup(shop);

            itemsContainer.getChildren().addAll(separator, totalBox);

            shoppingInfo.getChildren().addAll(shopBasketLabel , itemsContainer);
        }

    }

    private HBox createItemRowForPopup(OrderedItems item){

        HBox row = new HBox(10);
        row.setAlignment(Pos.CENTER);
        row.setPadding(new Insets(5));

        VBox infoBox = new VBox(5);
        Label nameLabel = new Label(item.getFoodName());
        nameLabel.setFont(Font.loadFont(getClass()
                .getResourceAsStream("/Poppins/Poppins-Bold.ttf"), 14));
        nameLabel.setTextFill(Color.WHEAT.darker());

        Label priceLabel = new Label(item.getPrice() + " T");
        priceLabel.setFont(Font.loadFont(getClass()
                .getResourceAsStream("/Poppins/Poppins-Regular.ttf"), 12));
        priceLabel.setTextFill(Color.GRAY);

        infoBox.getChildren().addAll(nameLabel, priceLabel);

        HBox quantityBox = new HBox(5);
        quantityBox.setAlignment(Pos.CENTER);

        Label quantityLabel = new Label(String.valueOf(item.getQuantity()));
        quantityLabel.setFont(Font.loadFont(getClass()
                .getResourceAsStream("/Poppins/Poppins-Bold.ttf"), 14));
        quantityLabel.setTextFill(Color.WHEAT.darker());
        quantityLabel.setMinWidth(30);
        quantityLabel.setAlignment(Pos.CENTER);

        quantityBox.getChildren().add(quantityLabel);

        Label totalLabel = new Label(item.getTotalPrice() + " T");
        totalLabel.setFont(Font.loadFont(getClass()
                .getResourceAsStream("/Poppins/Poppins-Bold.ttf"), 14));
        totalLabel.setTextFill(Color.ORANGE.darker());
        totalLabel.setMinWidth(80);
        totalLabel.setAlignment(Pos.CENTER_RIGHT);

        row.getChildren().addAll(infoBox, quantityBox, totalLabel);

        return row;
    }

    private HBox createTotalBoxForPopup(ShoppingBasket shop) {
        HBox totalBox = new HBox(10);
        totalBox.setAlignment(Pos.CENTER_LEFT);

        Label totalTitle = new Label("Total: ");
        totalTitle.setFont(Font.loadFont(getClass()
                .getResourceAsStream("/Poppins/Poppins-Bold.ttf"), 16));
        totalTitle.setTextFill(Color.WHEAT.darker());

        Label totalPrice = new Label(shop.getTotalPrice() + " T");
        totalPrice.setFont(Font.loadFont(getClass()
                .getResourceAsStream("/Poppins/Poppins-ExtraBold.ttf"), 18));
        totalPrice.setTextFill(Color.ORANGE.darker());

        totalBox.getChildren().addAll(totalTitle, totalPrice);
        totalBox.setTranslateX(20);

        return totalBox;
    }

}
