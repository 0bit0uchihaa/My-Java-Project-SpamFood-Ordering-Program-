package SpamFood.LoginSignUp;

import SpamFood.Restaurants.HomeWindow;
import SpamFood.StartWindow;
import SpamFood.User.User;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
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
import SpamFood.User.UserValidatorClass;

public class SignUpWindow {

    private Stage stage;
    private StartWindow startWindow;
    private Label errorLabel;

    public SignUpWindow(Stage stage , StartWindow startWindow){
        this.stage = stage;
        this.startWindow = startWindow;
    }


    public VBox getSignUpForm(){
        return createSignUpLayout();
    }

    private VBox createSignUpLayout(){
        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(30));
        vbox.setMaxWidth(400);
        //
        Label SignUpLabel = new Label("Sign-Up");
        SignUpLabel.setFont(Font.loadFont(getClass()
                        .getResourceAsStream("/Poppins/Poppins-ExtraBold.ttf")
                ,40
        ));
        SignUpLabel.setTextFill(Color.RED);
        SignUpLabel.setTranslateY(-8);
        //
        //..............................Username Text Field.............................//
        Label usernameLabel = new Label("Username :");
        usernameLabel.setFont(Font.loadFont(getClass()
                        .getResourceAsStream("/Poppins/Poppins-Bold.ttf")
                ,20
        ));
        usernameLabel.setTextFill(Color.RED);
        usernameLabel.setTranslateY(-70);
        usernameLabel.setTranslateX(-105);
        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");
        usernameField.setPrefHeight(50);
        usernameField.setPrefWidth(100);
        usernameField.setTranslateY(18);
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
        passwordLabel.setTranslateY(-25);
        passwordLabel.setTranslateX(-105);
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordField.setPrefHeight(50);
        passwordField.setPrefWidth(100);
        passwordField.setTranslateY(-30);
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

        Label confirmPassword = new Label("Confirm Password :");
        confirmPassword.setFont(Font.loadFont(getClass()
                .getResourceAsStream("/Poppins/Poppins-Bold.ttf")
                ,20
        ));
        confirmPassword.setTextFill(Color.RED);
        confirmPassword.setTranslateX(-60);
        confirmPassword.setTranslateY(-30);
        PasswordField confirmField = new PasswordField();
        confirmField.setPromptText("Confirm Password");
        confirmField.setPrefHeight(50);
        confirmField.setPrefWidth(100);
        confirmField.setTranslateY(-35);
        confirmField.setBackground(new Background(
                new BackgroundFill(
                        Color.WHITE.brighter() ,new CornerRadii(20) ,Insets.EMPTY
                )
        ));
        confirmField.setFont(Font.loadFont(
                getClass()
                        .getResourceAsStream("/Poppins/Poppins-Regular.ttf")
                ,15
        ));
//.....................................Phone Number Field.....................//
        Label phoneNumberLabel = new Label("Phone Number :");
        phoneNumberLabel.setFont(Font.loadFont(getClass()
                        .getResourceAsStream("/Poppins/Poppins-Bold.ttf")
                ,20
        ));
        phoneNumberLabel.setTextFill(Color.RED);
        phoneNumberLabel.setTranslateY(-40);
        phoneNumberLabel.setTranslateX(-75);
        TextField phoneNumberField = new TextField();
        phoneNumberField.setPromptText("09---------");
        phoneNumberField.setPrefHeight(50);
        phoneNumberField.setPrefWidth(100);
        phoneNumberField.setTranslateY(-45);
        phoneNumberField.setBackground(new Background(
                new BackgroundFill(
                        Color.WHITE.brighter() ,new CornerRadii(20) ,Insets.EMPTY
                )
        ));
        phoneNumberField.setFont(Font.loadFont(
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
        errorLabel.setTranslateY(-20);
        errorLabel.setVisible(false);

//.....................................Buttons................................//
        Button SignUp = createSignUpButton();

        SignUp.setOnAction(e -> {

            String username = usernameField.getText();
            String password = passwordField.getText();
            String confPassword = confirmField.getText();
            String phoneNumber = phoneNumberField.getText();

            try{

                UserValidatorClass.validateUserName(username);
                UserValidatorClass.validatePassword(password);
                UserValidatorClass.validateConfirmPassword(password,confPassword);
                UserValidatorClass.validatePhoneNumber(phoneNumber);

                errorLabel.setVisible(false);

                User signedUpUser = new User(username, password , phoneNumber);

                startWindow.fadeOutRoot(() -> {
                    HomeWindow homeWindow = new HomeWindow(stage, startWindow, signedUpUser);
                    homeWindow.show();
                } );

            }catch(IllegalArgumentException er){

                errorLabel.setText(er.getMessage());
                errorLabel.setVisible(true);

                shakeField(usernameField);
                shakeField(passwordField);
                shakeField(confirmField);
                shakeField(phoneNumberField);

            }

        });


        vbox.getChildren().addAll(SignUpLabel
                , usernameField
                , usernameLabel
                , passwordLabel
                , passwordField
                , confirmPassword
                , confirmField
                , phoneNumberLabel
                , phoneNumberField
                , SignUp
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
    private  Button createSignUpButton(){
        Button btn = new Button("Sign-Up");
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

        btn.setOnMousePressed( e -> btn.setTranslateY(-29));
        btn.setOnMouseReleased( e -> btn.setTranslateY(-30));

        btn.setTranslateY(-30);

        return btn;
    }
}