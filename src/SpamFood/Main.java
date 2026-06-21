package SpamFood;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;




public class Main extends Application {



    @Override
    public void start(Stage stage) throws Exception {

        Image icon = new Image(getClass().getResourceAsStream("Icon.png"));
        stage.getIcons().add(icon);
        StartWindow startWindow = new StartWindow(stage);
        startWindow.show();

    }

    public static void main(String[] args){
        launch(args);
    }
}