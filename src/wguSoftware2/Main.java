package wguSoftware2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import wguSoftware2.controllers.LoginCtrl;
import wguSoftware2.models.GeoIP;
import wguSoftware2.utils.Database;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;


public class Main extends Application {


    private Database d;
    private GeoIP g;

    @Override
    public void start(Stage primaryStage) throws IOException, Exception {

        g = new GeoIP();
        d = this.init_database();

        load_users(d);

        URL resource = getClass().getResource("views/login_v.fxml");
        FXMLLoader loader = new FXMLLoader(resource);
        Parent root = loader.load();
        LoginCtrl lc = loader.getController();
        d.setLc(lc);
        lc.initialize(d,g);
        primaryStage.setTitle("Hello World");
        Scene mainScene = new Scene(root);
        primaryStage.setScene(mainScene);
        primaryStage.show();

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

//    private void start_login_window(Stage primaryStage) throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("views/login_v.fxml"));
//        Parent root = loader.load();
//        LoginCtrl lc = loader.getController();
//        lc.initialize(this.d);
//        primaryStage.setTitle("");
//        primaryStage.setScene(new Scene(root, 300, 275));
//        primaryStage.show();
//    }

    private void load_users(Database d) {
        if (!d.getInit_users_loaded()) {
            d.seed_user_names_into_db();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
