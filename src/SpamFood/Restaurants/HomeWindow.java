package SpamFood.Restaurants;

import SpamFood.Foods.FoodMenu;
import SpamFood.StartWindow;
import SpamFood.User.User;
import javafx.animation.*;
import javafx.geometry.BoundingBox;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class HomeWindow {

    private Stage stage;
    private StartWindow startWindow;
    private Pane overlayPane;
    private User loggedInUser;
    private StackPane root;
    private RestaurantCards[] restaurantCards;
    private TilePane cardsContainer;
    private VBox[] cards;
    private Button backButton;
    private double originalLocationX;
    private double originalLocationY;
    private VBox selectedCard;
    private int selectedIndex;
    private StackPane menu;
    private Button shoppingButton;
    private VBox contentArea;
    private StackPane topPanelContainer;
    private FoodMenu currentFoodMenu;
    private boolean isCheckOutMenu = false;


    public HomeWindow(Stage stage, StartWindow startWindow, User user) {
        this.stage = stage;
        this.startWindow = startWindow;
        this.loggedInUser = user;
    }

    public void show() {
        root = new StackPane();
        root.setPrefSize(800, 600);
        root.setMaxWidth(800);
        root.setMaxHeight(600);
        root.setBackground(new Background(new BackgroundFill(Color.WHEAT, null, null)));

        contentArea = new VBox(20);
        contentArea.setAlignment(Pos.TOP_CENTER);
        contentArea.setPadding(new Insets(20, 0, 0, 0));


        //.......................TopPanel.............................//
        TopPanel topPanel = new TopPanel(loggedInUser, startWindow, stage);
        topPanelContainer = new StackPane(topPanel.getTopPanel());
        topPanelContainer.setAlignment(Pos.TOP_CENTER);
        topPanelContainer.setPickOnBounds(false);

        overlayPane = new Pane();
        overlayPane.setPickOnBounds(false);
        overlayPane.setMouseTransparent(true);

        //.......................Back Button...........................//
        backButton = createBackButton();
        backButton.setVisible(false);
        backButton.setTranslateX(-240);
        backButton.setTranslateY(175);


        //....................Shopping Button.........................//
        shoppingButton = createShoppingButton();
        shoppingButton.setVisible(false);
        shoppingButton.setTranslateX(-290);
        shoppingButton.setTranslateY(175);


        //...................Restaurant Cards..........................//
        restaurantCards = new RestaurantCards[]{
                new RestaurantCards("'Jasem Sea Food'", 4.6F, 35, "/Images/RestaurantLogo1.png"),
                new RestaurantCards("'Larana Sushi'", 3.9F, 42, "/Images/Sushi.png"),
                new RestaurantCards("'Burger And Drinks'", 4.1F, 30, "/Images/Burger.png"),
                new RestaurantCards("'Garden Cafe'", 4.3F, 25, "/Images/Cafee.png")
        };

        cards = new VBox[restaurantCards.length];
        for (int i = 0; i < restaurantCards.length; i++) {
            cards[i] = restaurantCards[i].getCard();
            cards[i].setOpacity(0);
            final int index = i;
            cards[i].setOnMouseClicked(e -> selectRestaurantCard(index));
        }

        cardsContainer = new TilePane(20, 20);
        cardsContainer.setAlignment(Pos.CENTER_LEFT);
        cardsContainer.setPadding(new Insets(30));
        cardsContainer.setPrefColumns(3);
        cardsContainer.setMaxWidth(800);
        cardsContainer.setTranslateX(40);
        cardsContainer.setBackground(new Background(new BackgroundFill(
                Color.WHEAT, new CornerRadii(20), Insets.EMPTY
        )));
        cardsContainer.getChildren().addAll(cards);

        SequentialTransition sequentialTransition = new SequentialTransition();
        for (int i = 0; i < cards.length; i++) {
            FadeTransition fade = new FadeTransition(Duration.seconds(0.3), cards[i]);
            fade.setFromValue(0);
            fade.setToValue(1);
            fade.setDelay(Duration.seconds(i * 0.1));
            sequentialTransition.getChildren().add(fade);
        }
        sequentialTransition.play();

        ScrollPane scrollPane = new ScrollPane(cardsContainer);
        scrollPane.setFitToWidth(true);
        scrollPane.setTranslateY(80);
        scrollPane.setMaxHeight(500);
        scrollPane.setMaxWidth(800);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setViewportBounds(new BoundingBox(0, 0, 800, 500));
        scrollPane.setStyle(
                "-fx-background-color: transparent;" +
                        "-fx-background: transparent;" +
                        "-fx-border-color: transparent;" +
                        "-fx-control-inner-background: transparent;" +
                        "-fx-box-border: transparent;"
        );
        scrollPane.setPickOnBounds(false);

        contentArea.getChildren().addAll(scrollPane);
        contentArea.setPickOnBounds(false);

        //..............................Adding to StackPane..................//
        root.getChildren().addAll(
                contentArea
                , overlayPane
        );
        root.getChildren().addAll(
                topPanelContainer
                , backButton
                , shoppingButton
        );
        backButton.toFront();
        shoppingButton.toFront();
        backButton.setPickOnBounds(true);
        shoppingButton.setPickOnBounds(true);
        //..............................Fade Animation......................//
        root.setOpacity(0);
        FadeTransition fadeInWindow = new FadeTransition(Duration.seconds(0.5), root);
        fadeInWindow.setFromValue(0);
        fadeInWindow.setToValue(1);
        fadeInWindow.play();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    private void selectRestaurantCard(int index) {
        selectedIndex = index;
        selectedCard = cards[index];
        selectedCard.setOnMouseClicked(null);


        for (int i = 0; i < cards.length; i++) {
            if (i != index) {
                FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.3), cards[i]);
                fadeOut.setFromValue(1);
                fadeOut.setToValue(0);
                fadeOut.play();
            }
        }

        Point2D pos = selectedCard.localToScene(0, 0);
        Point2D overlayPos = overlayPane.sceneToLocal(pos);
        originalLocationX = overlayPos.getX();
        originalLocationY = overlayPos.getY();

        PauseTransition pause = new PauseTransition(Duration.seconds(0.3));
        pause.setOnFinished(e -> {

            cardsContainer.getChildren().remove(selectedCard);
            cardsContainer.setVisible(false);

            selectedCard.setLayoutX(originalLocationX);
            selectedCard.setLayoutY(originalLocationY);
            selectedCard.setTranslateX(0);
            selectedCard.setTranslateY(0);

            overlayPane.getChildren().add(selectedCard);

            TranslateTransition moveToLeft = new TranslateTransition(Duration.seconds(0.9), selectedCard);
            moveToLeft.setToY(120 - originalLocationY);
            moveToLeft.setToX(40 - originalLocationX);

            moveToLeft.setOnFinished(ev -> {
                currentFoodMenu = new FoodMenu(restaurantCards[selectedIndex].getName());
                menu = currentFoodMenu.getMenu();
                menu.setTranslateX(100);
                menu.setTranslateY(50);
                root.getChildren().add(menu);

                FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.5), menu);
                fadeIn.setFromValue(0);
                fadeIn.setToValue(1);
                fadeIn.play();

                backButton.setVisible(true);
                shoppingButton.setVisible(true);

                backButton.toFront();
                shoppingButton.toFront();
                backButton.setPickOnBounds(true);
                shoppingButton.setPickOnBounds(true);

                backButton.setOpacity(0);
                shoppingButton.setOpacity(0);

                FadeTransition fadeBack = new FadeTransition(Duration.seconds(0.3), backButton);
                fadeBack.setFromValue(0);
                fadeBack.setToValue(1);
                fadeBack.play();

                FadeTransition fadeShop = new FadeTransition(Duration.seconds(0.3), shoppingButton);
                fadeShop.setFromValue(0);
                fadeShop.setToValue(1);
                fadeShop.play();

                backButton.setOnAction( eve -> restoreToOriginalScene());
                isCheckOutMenu = false;
            });
            moveToLeft.play();
        });
        pause.play();
    }

    private Button createBackButton() {
        Image backIcon = new Image(getClass().getResourceAsStream("/Images/Back.png"));
        ImageView backImage = new ImageView(backIcon);
        backImage.setFitHeight(20);
        backImage.setFitWidth(20);

        Button btn = new Button();
        btn.setGraphic(backImage);
        btn.setPrefHeight(35);
        btn.setPrefWidth(35);

        DropShadow shadow = new DropShadow();
        shadow.setRadius(8);
        shadow.setOffsetY(3);
        shadow.setColor(Color.rgb(0, 0, 0, 0.5));
        btn.setEffect(shadow);

        btn.setBackground(new Background(new BackgroundFill(
                Color.WHITESMOKE,
                new CornerRadii(30),
                Insets.EMPTY
        )));

        btn.setOnMouseEntered(e -> {
            btn.setBackground(new Background(new BackgroundFill(
                    Color.WHITESMOKE.darker(),
                    new CornerRadii(30),
                    Insets.EMPTY
            )));
            shadow.setRadius(12);
            shadow.setOffsetY(4);
            btn.setScaleX(1.05);
            btn.setScaleY(1.05);
        });

        btn.setOnMouseExited(e -> {
            btn.setBackground(new Background(new BackgroundFill(
                    Color.WHITESMOKE,
                    new CornerRadii(30),
                    Insets.EMPTY
            )));
            shadow.setRadius(8);
            shadow.setOffsetY(3);
            btn.setScaleX(1.0);
            btn.setScaleY(1.0);
        });
        btn.setCursor(Cursor.HAND);
        btn.setOnAction(e -> restoreToOriginalScene());
        return btn;
    }

    private void restoreToOriginalScene() {
        FadeTransition fadeOutBack = new FadeTransition(Duration.seconds(0.2), backButton);
        fadeOutBack.setFromValue(1);
        fadeOutBack.setToValue(0);

        FadeTransition fadeOutShop = new FadeTransition(Duration.seconds(0.2), shoppingButton);
        fadeOutShop.setFromValue(1);
        fadeOutShop.setToValue(0);

        FadeTransition fadeOutMenu = new FadeTransition(Duration.seconds(0.2), menu);
        fadeOutMenu.setFromValue(1);
        fadeOutMenu.setToValue(0);

        fadeOutBack.setOnFinished(e -> {
            menu.setVisible(false);
            backButton.setVisible(false);
            shoppingButton.setVisible(false);
            root.getChildren().remove(menu);
        });

        fadeOutMenu.play();
        fadeOutBack.play();
        fadeOutShop.play();

        TranslateTransition moveBack = new TranslateTransition(Duration.seconds(0.9), selectedCard);
        moveBack.setToX(0);
        moveBack.setToY(0);
        moveBack.setInterpolator(Interpolator.EASE_BOTH);

        moveBack.setOnFinished(ev -> {
            overlayPane.getChildren().remove(selectedCard);
            selectedCard.setLayoutX(0);
            selectedCard.setLayoutY(0);
            selectedCard.setTranslateX(0);
            selectedCard.setTranslateY(0);

            int originalPlace = -1;
            for (int i = 0; i < cards.length; i++) {
                if (cards[i] == selectedCard) {
                    originalPlace = i;
                    break;
                }
            }
            cardsContainer.getChildren().add(originalPlace, selectedCard);
            cardsContainer.setVisible(true);
            overlayPane.setMouseTransparent(true);

            for (int i = 0; i < cards.length; i++) {
                VBox card = cards[i];
                if (card != selectedCard) {
                    FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.3), card);
                    fadeIn.setFromValue(0);
                    fadeIn.setToValue(1);
                    fadeIn.play();
                }
            }

            for (int i = 0; i < cards.length; i++) {
                final int idx = i;
                cards[i].setOnMouseClicked(e -> selectRestaurantCard(idx));
            }

            backButton.setOnAction( e -> restoreToOriginalScene() );
            isCheckOutMenu = false;

            selectedCard = null;
        });
        moveBack.play();
    }

    private Button createShoppingButton() {
        Image shIcon1 = new Image(getClass().getResourceAsStream("/Images/Shop.png"));
        ImageView shoppingIcon = new ImageView(shIcon1);
        shoppingIcon.setFitWidth(20);
        shoppingIcon.setFitHeight(20);

        Button shopBtn = new Button();
        shopBtn.setGraphic(shoppingIcon);
        shopBtn.setPrefHeight(35);
        shopBtn.setPrefWidth(35);

        DropShadow shadow = new DropShadow();
        shadow.setRadius(8);
        shadow.setOffsetY(3);
        shadow.setColor(Color.rgb(0, 0, 0, 0.5));
        shopBtn.setEffect(shadow);

        shopBtn.setBackground(new Background(new BackgroundFill(
                Color.WHITESMOKE,
                new CornerRadii(30),
                Insets.EMPTY
        )));

        shopBtn.setOnMouseEntered(e -> {
            shopBtn.setBackground(new Background(new BackgroundFill(
                    Color.WHITESMOKE.darker(),
                    new CornerRadii(30),
                    Insets.EMPTY
            )));
            shadow.setRadius(12);
            shadow.setOffsetY(4);
            shopBtn.setScaleX(1.05);
            shopBtn.setScaleY(1.05);
        });

        shopBtn.setOnMouseExited(e -> {
            shopBtn.setBackground(new Background(new BackgroundFill(
                    Color.WHITESMOKE,
                    new CornerRadii(30),
                    Insets.EMPTY
            )));
            shadow.setRadius(8);
            shadow.setOffsetY(3);
            shopBtn.setScaleX(1.0);
            shopBtn.setScaleY(1.0);
        });

        shopBtn.setOnMouseClicked( e -> {
                if ( currentFoodMenu != null){
                    currentFoodMenu.switchToCheckOut();
                    isCheckOutMenu = true;

                    backButton.setOnAction(ev -> {
                        if (currentFoodMenu != null){
                            currentFoodMenu.switchToMainMenu();
                            isCheckOutMenu = false;
                            backButton.setOnAction( eve -> restoreToOriginalScene());
                        }
                    });
                }
        });
        shopBtn.setCursor(Cursor.HAND);
        return shopBtn;
    }
}