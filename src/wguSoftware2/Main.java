package wguSoftware2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import wguSoftware2.controllers.LoginWindowC;
import wguSoftware2.models.GeoIP;
import wguSoftware2.utils.Converters;
import wguSoftware2.utils.Database;
import wguSoftware2.utils.Database_v3;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;


/**
 * The type Main.
 */
public class Main extends Application {


    private Database_v3 d;
    private GeoIP g;
    private Converters c;

    @Override
    public void start(Stage primaryStage) throws IOException, Exception {

        g = new GeoIP();
        d = new Database_v3(
                "52.206.157.109",
                "U05mJi",
                "U05mJi",
                "53688547099"
        );



        c = new Converters();

//        load_users(d);

        URL login_resource = getClass().getResource("views/login_v.fxml");
        System.out.println(c.get_fxml_file_name(login_resource));
        URL main_resource = getClass().getResource("views/main_v.fxml");
        System.out.println(c.get_fxml_file_name(main_resource));
        FXMLLoader loader = new FXMLLoader(login_resource);
        Parent root = loader.load();
        LoginWindowC lc = loader.getController();
//        d.setLc(lc);
        lc.initialize(d, g, main_resource);
        primaryStage.setTitle("");
        Scene mainScene = new Scene(root);
        primaryStage.setScene(mainScene);
        primaryStage.show();


    }

    private Database init_database() throws SQLException {
        return new Database(
                "52.206.157.109",
                "U05mJi",
                "U05mJi",
                "53688547099"
        );
    }

    private void load_users(Database d) {
        if (!d.getInit_users_loaded()) {
            d.seed_user_names_into_db();
        }
    }


    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
