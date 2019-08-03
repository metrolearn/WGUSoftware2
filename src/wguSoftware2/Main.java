package wguSoftware2;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import wguSoftware2.models.GeoIP;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        GeoIP g = new GeoIP();



        Parent root = FXMLLoader.load(getClass().getResource("views/login_v.fxml"));
        primaryStage.setTitle("");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) throws IOException {
        launch(args);




    }
}
