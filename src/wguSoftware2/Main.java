package wguSoftware2;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import wguSoftware2.models.GeoIP;
import wguSoftware2.utils.Database;


public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws IOException {

        /*
          Gets and sets location, based on geo ip lookup.
         */
        GeoIP g = new GeoIP();
        Database d  = new Database(
            "52.206.157.109",
            "U05mJi",
            "U05mJi",
            "53688547099"
        );

        boolean b = d.seed_user_names_into_db();
        d.disconnect_from_mysql_db();

        Parent root = FXMLLoader.load(getClass().getResource("views/login_v.fxml"));
        primaryStage.setTitle("");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args)  {
        launch(args);






    }
}
