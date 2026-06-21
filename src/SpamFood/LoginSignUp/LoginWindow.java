package SpamFood.LoginSignUp;

import SpamFood.Restaurants.HomeWindow;
import SpamFood.StartWindow;
import SpamFood.User.User;
import SpamFood.User.UserValidatorClass;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Node;

public class LoginWindow {

    private Stage stage;
    private StartWindow startWindow;
    private Label errorLabel;
    private VBox vbox;

    public LoginWindow(Stage stage , StartWindow startWindow){
        this.stage = stage;
        this.startWindow = startWindow;
    }

    public LoginWindow() {

    }


    public VBox getLoginForm(){
        return createLoginLayout();
    }

    private VBox createLoginLayout(){
        vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(30));
        vbox.setMaxWidth(400);
        //
        Label loginLabel = new Label("Log-In");
        loginLabel.setFont(Font.loadFont(getClass()
                .getResourceAsStream("/Poppins/Poppins-ExtraBold.ttf")
                ,40
        ));
        loginLabel.setTextFill(Color.RED);
        loginLabel.setTranslateY(-80);

 //..............................Username Text Field.............................//
        Label usernameLabel = new Label("Username :");
        usernameLabel.setFont(Font.loadFont(getClass()
                        .getResourceAsStream("/Poppins/Poppins-Bold.ttf")
                ,20
        ));
        usernameLabel.setTextFill(Color.RED);
        usernameLabel.setTranslateY(-95);
        usernameLabel.setTranslateX(-105);
        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");
        usernameField.setPrefHeight(50);
        usernameField.setPrefWidth(100);
        usernameField.setBackground(new Background(
                new BackgroundFill(
                        Color.WHITE.brighter() ,new CornerRadii(20) ,Insets.EMPTY
                )
        ));
        usernameField.setFont(Font.loadFont(
                getClass()
                .getResourceAsStream("/Poppins/Poppins-Regular.ttf")
                ,15
        ));
//...............................Password Text Field............................//
        Label passwordLabel = new Label("Password :");
        passwordLabel.setFont(Font.loadFont(getClass()
                        .getResourceAsStream("/Poppins/Poppins-Bold.ttf")
                ,20
        ));
        passwordLabel.setTextFill(Color.RED);
        passwordLabel.setTranslateY(-45);
        passwordLabel.setTranslateX(-105);
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordField.setPrefHeight(50);
        passwordField.setPrefWidth(100);
        passwordField.setTranslateY(-50);
        passwordField.setBackground(new Background(
                new BackgroundFill(
                        Color.WHITE.brighter() ,new CornerRadii(20) ,Insets.EMPTY
                )
        ));
        passwordField.setFont(Font.loadFont(
                getClass()
                        .getResourceAsStream("/Poppins/Poppins-Regular.ttf")
                ,15
        ));
//.....................................Error Label............................//
        errorLabel = new Label();
        errorLabel.setTextFill(Color.RED.darker());
        errorLabel.setFont(Font.loadFont(
                getClass()
                        .getResourceAsStream("/Poppins/Poppins-Regular.ttf")
                ,20
        ));
        errorLabel.setWrapText(true);
        errorLabel.setPrefWidth(300);
        errorLabel.setTranslateY(30);
        errorLabel.setVisible(false);

//.....................................Buttons................................//
        Button login = createLoginButton();

        login.setOnAction( e -> {

            String username = usernameField.getText();
            String password = passwordField.getText();

            try{
                UserValidatorClass.validateUserName(username);
                UserValidatorClass.validatePassword(password);

                errorLabel.setVisible(false);

                User loggedInUser = new User(username , password);

                startWindow.fadeOutRoot(() -> {
                    HomeWindow homeWindow = new HomeWindow(stage, startWindow, loggedInUser);
                    homeWindow.show();
                });

                FadeTransition fadeOutForm = new FadeTransition(Duration.seconds(0.5) , vbox);
                fadeOutForm.setFromValue(1);
                fadeOutForm.setToValue(0);
                fadeOutForm.setOnFinished( event -> {
                    HomeWindow homeWindow = new HomeWindow(stage, startWindow , loggedInUser);
                    homeWindow.show();
                });
                fadeOutForm.play();

            }catch (IllegalArgumentException er){

                errorLabel.setText(er.getMessage());
                errorLabel.setVisible(true);

                shakeField(usernameField);
                shakeField(passwordField);

            }
        });

        vbox.getChildren().addAll(loginLabel
                , usernameField
                , usernameLabel
                , passwordLabel
                , passwordField
                , login
                , errorLabel
        );

        vbox.setOpacity(0);
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.5), vbox);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);
        fadeIn.play();

        return vbox;
    }
//...................................Shake Fields When Error.................//
    private void shakeField(Node node){
        TranslateTransition shake = new TranslateTransition(Duration.seconds(0.05) ,node);
        shake.setFromX(0);
        shake.setToX(5);
        shake.setAutoReverse(true);
        shake.setCycleCount(4);
        shake.setOnFinished(e -> node.setTranslateX(0));
        shake.play();
    }

//...................................Button Style.............................//
    private  Button createLoginButton(){
        Button btn = new Button("Log-In");
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
                Color.RED.brighter()
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
                    Color.RED.brighter()
                    ,new  CornerRadii(40)
                    ,Insets.EMPTY
            )));
            shadow.setRadius(8);
            shadow.setOffsetY(3);
            btn.setScaleY(1);
            btn.setScaleX(1);
        });

        btn.setOnMousePressed( e -> btn.setTranslateY(-44));
        btn.setOnMouseReleased( e -> btn.setTranslateY(-45));

        btn.setTranslateY(-45);

        return btn;
    }
}