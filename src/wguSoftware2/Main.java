package wguSoftware2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import wguSoftware2.controllers.LoginWindowC;
import wguSoftware2.models.GeoIP;
import wguSoftware2.utils.Converters;
import wguSoftware2.utils.DatabaseUserLoad;
import wguSoftware2.utils.DatabaseMain;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;


/**
 * The type Main.
 */
public class Main extends Application {
    private static final String ENGLISH_RB_FILEPATH = "language_files/rb_es.properties";
    private static final String NOR_RB_FILEPATH = "language_files/rb_no.properties";


    private DatabaseMain d;
    private GeoIP g;
    private Converters c;

    @Override
    public void start(Stage primaryStage) throws IOException, Exception {

        g = new GeoIP();
        d = new DatabaseMain(
                "52.206.157.109",
                "U05mJi",
                "U05mJi",
                "53688547099"
        );



        c = new Converters();

//        load_users(d);

        URL login_resource = getClass().getResource("views/login_v.fxml");
        System.out.println(c.get_fxml_file_name(login_resource));
        URL mainWindowURI = getClass().getResource("views/main_v.fxml");
        System.out.println(c.get_fxml_file_name(mainWindowURI));
        // TESTING ONLY!
        //        Locale.setDefault(new Locale("es", "MX"));
        ResourceBundle rb = ResourceBundle.getBundle("language_files/rb");
        FXMLLoader loader = new FXMLLoader(login_resource, ResourceBundle.getBundle("language_files/rb"));
        Parent root = loader.load();
        LoginWindowC lc = loader.getController();
//        d.setLc(lc);
        lc.initialize(d, g, mainWindowURI, rb);
        primaryStage.setTitle("");
        Scene mainScene = new Scene(root);
        primaryStage.setScene(mainScene);
        primaryStage.show();


    }

    private DatabaseUserLoad init_database() throws SQLException {
        return new DatabaseUserLoad(
                "52.206.157.109",
                "U05mJi",
                "U05mJi",
                "53688547099"
        );
    }

    private void load_users(DatabaseUserLoad d) {
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
