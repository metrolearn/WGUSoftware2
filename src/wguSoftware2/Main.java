package wguSoftware2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import wguSoftware2.models.GeoIP;
import wguSoftware2.utils.Database;

import java.io.IOException;
import java.sql.SQLException;


public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws IOException, Exception {

        GeoIP g = new GeoIP();
        Database d = init_database();
        load_users(d);
        start_login_window(primaryStage);
        d.disconnect_from_mysql_db();

    }

    private Database init_database() throws SQLException {
        return new Database(
                "52.206.157.109",
                "U05mJi",
                "U05mJi",
                "53688547099"
        );
    }

    private void start_login_window(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("views/login_v.fxml"));
        primaryStage.setTitle("");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

    private void load_users(Database d) {
        if (!d.getInit_users_loaded()) {
            d.seed_user_names_into_db();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
