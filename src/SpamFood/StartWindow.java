package SpamFood;

import SpamFood.LoginSignUp.LoginWindow;
import SpamFood.LoginSignUp.SignUpWindow;
import javafx.animation.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class StartWindow {


    private Stage stage;
    private HBox root;
    private VBox leftPanel;
    private VBox rightPanel;
    private Button loginButton;
    private Button signUpButton;
    private Button backBtn;
    private Label welcomeLabel;


    Font customFont = Font.loadFont(getClass().getResourceAsStream("/Poppins/Poppins-Bold.ttf"),60);
    public StartWindow(Stage stage){
        this.stage = stage;
    }


    public void show(){
        root = new HBox();
        root.setPrefSize(800 , 600);
        root.setMaxWidth(800);
        root.setMaxHeight(600);
        root.setBackground(new Background(
                new BackgroundFill
                        (Color.WHEAT
                                , null
                                , null
                        )));

        leftPanel = createLeftLayout();
        leftPanel.setPrefWidth(800);

        rightPanel = new VBox();
        rightPanel.setPrefWidth(0);
        rightPanel.setOpacity(0);
        rightPanel.setAlignment(Pos.CENTER);
        rightPanel.setPadding(new Insets(0));

        root.getChildren().addAll(leftPanel , rightPanel);

        Scene scene = new Scene(root , 800 , 600);
        stage.setScene(scene);
        stage.setTitle("Spam Food Order");
        stage.setResizable(false);
        stage.show();
    }

    private VBox createLeftLayout(){
//....................................VBox...............................................//
        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(30));
//......................................Gradient.........................................//
        LinearGradient gradient = new LinearGradient(
                0 , 1,
                0 , 0,
                true,
                CycleMethod.NO_CYCLE,
        new Stop(0, Color.RED),
        new Stop(1,Color.ORANGE)
        );

        vbox.setBackground(new Background(new BackgroundFill(
                gradient , CornerRadii.EMPTY , Insets.EMPTY)));

//...................................Program Logo.....................................//
        Rectangle logoBox1 = new Rectangle(240 , 75);
        logoBox1.setArcWidth(35);
        logoBox1.setArcHeight(35);
        logoBox1.setFill(Color.WHEAT);

        //.............Logo Label 1.................//
        Label labelSpam = new Label("SPAM");
        labelSpam.setTextFill(Color.RED);
        labelSpam.setFont(customFont);

        //.............StackPane 1...............//
        StackPane stackTop = new StackPane();
        stackTop.getChildren().addAll(logoBox1, labelSpam);
        stackTop.setTranslateY(-50);
        stackTop.setRotate(-1.5);
        stackTop.setOpacity(0);

        //.............Logo Box 2..............//
        Rectangle logoBox2 = new Rectangle(300 , 75);
        logoBox2.setArcWidth(35);
        logoBox2.setArcHeight(35);
        logoBox2.setFill(Color.RED);

        //.............Logo Label 2.............//
        Label labelFood = new Label("FOOD");
        labelFood.setFont(customFont);
        labelFood.setTextFill(Color.WHEAT);

        //.............Stack Bottom............//
        StackPane stackBottom = new StackPane();
        stackBottom.getChildren().addAll(logoBox2 , labelFood);
        stackBottom.setTranslateY(-75);
        stackBottom.setRotate(1.5);
        stackBottom.setOpacity(0);

        //..............Animation for Logo...........//
        FadeTransition fade1 = new FadeTransition(Duration.seconds(2) , stackTop);
        fade1.setFromValue(0);
        fade1.setToValue(1);

        FadeTransition fade2 = new FadeTransition(Duration.seconds(2) , stackBottom);
        fade2.setFromValue(0);
        fade2.setToValue(1);
        fade2.setDelay(Duration.seconds(0.1));

        ParallelTransition parallelTransition = new ParallelTransition(fade1 , fade2);
        parallelTransition.play();

        VBox logoContainer = new VBox(-10);
        logoContainer.setAlignment(Pos.CENTER);
        logoContainer.getChildren().addAll(stackTop , stackBottom);

//.......................................Welcome Label........................................//
        welcomeLabel = new Label("Chose Your Food , Enjoy Your Food");
        welcomeLabel.setTextFill(Color.WHEAT);
        welcomeLabel.setFont(Font.loadFont(getClass().
                getResourceAsStream("/Poppins/Poppins-Bold.ttf")
                , 30
        ));
        welcomeLabel.setTranslateY(-60);
        welcomeLabel.setOpacity(0);
        FadeTransition labelFade = new FadeTransition(Duration.seconds(2),welcomeLabel);
        labelFade.setFromValue(0);
        labelFade.setToValue(1);
        labelFade.setDelay(Duration.seconds(0.2));
        ParallelTransition labelParallel = new ParallelTransition(labelFade);
        labelParallel.play();

//.......................................Button Part........................................//
        //...........................Login Button..........................//
        loginButton = createStyledButton("Log-in" ,"#FF6B35" ,"/Images/Login.png" );
        loginButton.setOpacity(0);
        FadeTransition fadeLoginBtn = new FadeTransition(Duration.seconds(2), loginButton);
        fadeLoginBtn.setFromValue(0);
        fadeLoginBtn.setToValue(1);
        fadeLoginBtn.setDelay(Duration.seconds(0.4));
        ParallelTransition parallelLoginBtn = new ParallelTransition(fadeLoginBtn);
        parallelLoginBtn.play();
        loginButton.setOnAction( e -> {
            System.out.println("Login Window");
        });
        //...........................Sign Up Button........................//
        signUpButton = createStyledButton("Sign-Up" , "#FF4500" , "/Images/SignUp.png");
        signUpButton.setOpacity(0);
        FadeTransition fadeSignUpBtn = new FadeTransition(Duration.seconds(2), signUpButton);
        fadeSignUpBtn.setFromValue(0);
        fadeSignUpBtn.setToValue(1);
        fadeSignUpBtn.setDelay(Duration.seconds(0.6));
        ParallelTransition parallelSignUpBtn = new ParallelTransition(fadeSignUpBtn);
        parallelSignUpBtn.play();
        signUpButton.setOnAction( e -> {
            System.out.println("Sign Up Window");
        });
        //............................Back Button..........................//
        backBtn = backButtonStyle("Back" , "#FF6B35");
        backBtn.setVisible(false);
        backBtn.setOpacity(0);

//..................................Clicking Actions...............................................//
        loginButton.setOnAction( e -> morphToLogin());
        signUpButton.setOnAction(e -> morphToSingUp());
        backBtn.setOnAction(e -> morphBackToMain());

//..................................Put Objects on VBox....................................//
        vbox.getChildren().addAll(stackTop
                ,stackBottom
                ,welcomeLabel
                ,loginButton
                ,signUpButton
                ,backBtn
        );

        return vbox;
    }
//................................Fade Objects in left Panel............................//
    private void fadeObjectsLeft(Runnable onFinished){
        FadeTransition fadeLogin = new FadeTransition(Duration.seconds(0.3), loginButton);
        fadeLogin.setFromValue(1);
        fadeLogin.setToValue(0);

        FadeTransition fadeSignup = new FadeTransition(Duration.seconds(0.3), signUpButton);
        fadeSignup.setFromValue(1);
        fadeSignup.setToValue(0);

        FadeTransition fadeWelcome = new FadeTransition(Duration.seconds(0.3), welcomeLabel);
        fadeWelcome.setFromValue(1);
        fadeWelcome.setToValue(0);



        ParallelTransition parallel = new ParallelTransition(fadeLogin, fadeSignup, fadeWelcome);
        parallel.setOnFinished(e -> {
            loginButton.setVisible(false);
            signUpButton.setVisible(false);
            welcomeLabel.setVisible(false);

            if (onFinished != null) {
                onFinished.run();
            }
        });
        parallel.play();
    }

    private void showBackButton() {
        backBtn.setVisible(true);
        backBtn.setOpacity(0);

        FadeTransition fadeBack = new FadeTransition(Duration.seconds(0.4), backBtn);
        fadeBack.setFromValue(0);
        fadeBack.setToValue(1);

        ScaleTransition scaleBack = new ScaleTransition(Duration.seconds(0.3), backBtn);
        scaleBack.setFromX(0.8);
        scaleBack.setFromY(0.8);
        scaleBack.setToX(1);
        scaleBack.setToY(1);

        ParallelTransition parallel = new ParallelTransition(fadeBack, scaleBack);
        parallel.play();
    }


    private void hideBackButtonAndRestore() {
        FadeTransition fadeBack = new FadeTransition(Duration.seconds(0.3), backBtn);
        fadeBack.setFromValue(1);
        fadeBack.setToValue(0);
        fadeBack.setOnFinished(e -> {
            backBtn.setVisible(false);
            loginButton.setVisible(true);
            signUpButton.setVisible(true);
            welcomeLabel.setVisible(true);

            FadeTransition fadeLogin = new FadeTransition(Duration.seconds(0.4), loginButton);
            fadeLogin.setFromValue(0);
            fadeLogin.setToValue(1);

            FadeTransition fadeSignup = new FadeTransition(Duration.seconds(0.4), signUpButton);
            fadeSignup.setFromValue(0);
            fadeSignup.setToValue(1);

            FadeTransition fadeWelcome = new FadeTransition(Duration.seconds(0.4), welcomeLabel);
            fadeWelcome.setFromValue(0);
            fadeWelcome.setToValue(1);


            ParallelTransition parallel = new ParallelTransition(fadeLogin, fadeSignup, fadeWelcome);
            parallel.setOnFinished(e2 -> {
            });
            parallel.play();
        });
        fadeBack.play();
    }
//..................................Fade out all root....................................//
public void fadeOutRoot(Runnable onFinished) {
    FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.5), root);
    fadeOut.setFromValue(1);
    fadeOut.setToValue(0);
    fadeOut.setOnFinished(e -> {
        if (onFinished != null) {
            onFinished.run();
        }
    });
    fadeOut.play();
}
//..................................Morph Panels.........................................//
    private void morphToLogin(){
        fadeObjectsLeft(() -> {
            showBackButton();

            Timeline shrinkToLeft = new Timeline(
                    new KeyFrame(Duration.ZERO,
                            new KeyValue(leftPanel.prefWidthProperty() , 800))
                    , new KeyFrame(Duration.seconds(0.8)
                    , new KeyValue(leftPanel.prefWidthProperty() , 400))
            );
            Timeline expandsRight = new Timeline(
                    new KeyFrame(Duration.ZERO,
                            new KeyValue(rightPanel.prefWidthProperty(),0)),
                    new KeyFrame(Duration.seconds(0.8),
                            new KeyValue(rightPanel.prefWidthProperty(),400))
            );
            FadeTransition fadeRightIn = new FadeTransition(Duration.seconds(0.6),rightPanel);
            fadeRightIn.setFromValue(0);
            fadeRightIn.setToValue(1);

            Timeline displayForm = new Timeline(
                    new KeyFrame(Duration.seconds(0.4), e -> {
                        rightPanel.getChildren().clear();
                        LoginWindow loginWindow = new LoginWindow(stage , this);
                        rightPanel.getChildren().add(loginWindow.getLoginForm());
                    })
            );


            ParallelTransition parallel = new ParallelTransition(
                      shrinkToLeft
                    , expandsRight
                    , displayForm
                    , fadeRightIn
            );
            parallel.play();
        });
    }

    private void morphToSingUp(){
        fadeObjectsLeft(() -> {
            showBackButton();

        Timeline shrinkToLeft = new Timeline(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(leftPanel.prefWidthProperty() , 800))
                , new KeyFrame(Duration.seconds(0.8)
                , new KeyValue(leftPanel.prefWidthProperty() , 400))
        );
        Timeline expandsRight = new Timeline(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(rightPanel.prefWidthProperty(),0)),
                new KeyFrame(Duration.seconds(0.8),
                        new KeyValue(rightPanel.prefWidthProperty(),400))
        );
        FadeTransition fadeRightIn = new FadeTransition(Duration.seconds(0.6),rightPanel);
        fadeRightIn.setFromValue(0);
        fadeRightIn.setToValue(1);

        Timeline displayForm = new Timeline(
                new KeyFrame(Duration.seconds(0.4), e -> {
                    rightPanel.getChildren().clear();
                    SignUpWindow SignUpWindow = new SignUpWindow(stage , this);
                    rightPanel.getChildren().add(SignUpWindow.getSignUpForm());
                })
        );


        ParallelTransition parallel = new ParallelTransition(
                  shrinkToLeft
                , expandsRight
                , displayForm
                , fadeRightIn);

        parallel.play();
        });
    }

    private void morphBackToMain(){
        Timeline expandLeft = new Timeline(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(leftPanel.prefWidthProperty(), 400)),
                new KeyFrame(Duration.seconds(0.6),
                        new KeyValue(leftPanel.prefWidthProperty(), 800))
        );

        Timeline shrinkRight = new Timeline(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(rightPanel.prefWidthProperty(), 400)),
                new KeyFrame(Duration.seconds(0.6),
                        new KeyValue(rightPanel.prefWidthProperty(), 0))
        );

        FadeTransition fadeRight = new FadeTransition(Duration.seconds(0.4), rightPanel);
        fadeRight.setToValue(0);

        ParallelTransition parallel = new ParallelTransition(expandLeft, shrinkRight, fadeRight);
        parallel.setOnFinished(e -> {
            rightPanel.getChildren().clear();
            hideBackButtonAndRestore();
        });
        parallel.play();
    }
//...................................Styling Button....................................//
    private Button createStyledButton(String text , String colorCode , String iconFolder){
        Image icon1 = new Image(getClass().getResourceAsStream(iconFolder));
        ImageView icon2 = new ImageView(icon1);
        icon2.setFitWidth(20);
        icon2.setFitHeight(20);

        Button btn = new Button(text , icon2);

        btn.setPrefHeight(30);
        btn.setPrefWidth(230);
        btn.setFont(Font.loadFont(getClass()
                .getResourceAsStream("/Poppins/Poppins-ExtraBold.ttf")
                ,20
        ));
        btn.setTextFill(Color.BLACK);
        //.........................Shadow Effect.................//
        DropShadow shadow = new DropShadow();
        shadow.setRadius(8);
        shadow.setOffsetY(3);
        shadow.setColor(Color.rgb(0,0,0,0.5));
        btn.setEffect(shadow);

        //.........................Background Arrange............//
        btn.setBackground(new Background(new BackgroundFill(
                Color.WHEAT
                ,new  CornerRadii(40)
                ,Insets.EMPTY
        )));
        btn.setCursor(Cursor.HAND);

        //.........................Hover Effect....................//
        btn.setOnMouseEntered(e ->{
            btn.setBackground(new Background(new BackgroundFill(
                    Color.WHEAT.darker(),
                    new CornerRadii(40),
                    Insets.EMPTY
            )));
            shadow.setRadius(12);
            shadow.setOffsetY(4);
            btn.setScaleX(1.02);
            btn.setScaleY(1.02);
        });
        btn.setOnMouseExited(e -> {
            btn.setBackground(new Background(new BackgroundFill(
                    Color.WHEAT
                    ,new  CornerRadii(40)
                    ,Insets.EMPTY
            )));
            shadow.setRadius(8);
            shadow.setOffsetY(3);
            btn.setScaleY(1);
            btn.setScaleX(1);
        });

        btn.setOnMousePressed( e -> btn.setTranslateY(-29));
        btn.setOnMouseReleased( e -> btn.setTranslateY(-30));

        btn.setTranslateY(-30);

        return btn;
    }

    private Button backButtonStyle(String text , String colorCode){
        Image backIcon = new Image(getClass().getResourceAsStream("/Images/Back.png"));
        ImageView backImage = new ImageView(backIcon);
        backImage.setFitHeight(20);
        backImage.setFitWidth(20);
        Button btn = new Button(text , backImage);
        btn.setPrefHeight(30);
        btn.setPrefWidth(230);
        btn.setFont(Font.loadFont(getClass()
                        .getResourceAsStream("/Poppins/Poppins-ExtraBold.ttf")
                ,20
        ));
        btn.setTextFill(Color.BLACK);
        //.........................Shadow Effect.................//
        DropShadow shadow = new DropShadow();
        shadow.setRadius(8);
        shadow.setOffsetY(3);
        shadow.setColor(Color.rgb(0,0,0,0.5));
        btn.setEffect(shadow);

        //.........................Background Arrange............//
        btn.setBackground(new Background(new BackgroundFill(
                Color.WHEAT
                ,new  CornerRadii(40)
                ,Insets.EMPTY
        )));
        btn.setCursor(Cursor.HAND);

        //.........................Hover Effect....................//
        btn.setOnMouseEntered(e ->{
            btn.setBackground(new Background(new BackgroundFill(
                    Color.WHEAT.darker(),
                    new CornerRadii(40),
                    Insets.EMPTY
            )));
            shadow.setRadius(12);
            shadow.setOffsetY(4);
            btn.setScaleX(1.02);
            btn.setScaleY(1.02);
        });
        btn.setOnMouseExited(e -> {
            btn.setBackground(new Background(new BackgroundFill(
                    Color.WHEAT
                    ,new  CornerRadii(40)
                    ,Insets.EMPTY
            )));
            shadow.setRadius(8);
            shadow.setOffsetY(3);
            btn.setScaleY(1);
            btn.setScaleX(1);
        });

        btn.setOnMousePressed( e -> btn.setTranslateY(-59));
        btn.setOnMouseReleased( e -> btn.setTranslateY(-60));

        btn.setTranslateY(-60);

        return btn;
    }
}