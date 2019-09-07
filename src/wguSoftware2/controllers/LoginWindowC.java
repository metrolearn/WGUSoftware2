/**
 * Sample Skeleton for 'login_v.fxml' Controller Class
 */

package wguSoftware2.controllers;

import com.sun.rowset.CachedRowSetImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import wguSoftware2.models.Active_User;
import wguSoftware2.models.GeoIP;
import wguSoftware2.utils.Converters;
import wguSoftware2.utils.Database;
import wguSoftware2.utils.Database_v3;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginWindowC {
    private static final boolean TESTING = true;
    @FXML
    private GeoIP g;

    private URL main_window_url;

    @FXML
    private Converters c;

    @FXML
    private LoginWindowC lc;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="user_txt_fld"
    private TextField user_txt_fld; // Value injected by FXMLLoader

    @FXML // fx:id="sign_in_btn"
    private Button sign_in_btn; // Value injected by FXMLLoader

    @FXML // fx:id="password_txt_fld"
    private PasswordField password_txt_fld; // Value injected by FXMLLoader

    @FXML // fx:id="lang_chk_bx"
    private CheckBox lang_chk_bx; // Value injected by FXMLLoader

    @FXML // fx:id="current_location_lbl"
    private Label current_location_lbl; // Value injected by FXMLLoader

    @FXML //fx:id="welcome_lbl"
    private Label welcome_lbl;

    @FXML
    private Database_v3 curr_db;

    private Active_User ac;

    public LoginWindowC() {

        curr_db = null;
        this.c = new Converters();
    }


    @FXML
    void lang_chk_bx_clicked(ActionEvent event) throws IOException {

        if (lang_chk_bx.isSelected()) {
            this.welcome_lbl.setText("Bienvenido");
            this.user_txt_fld.setPromptText("Nombre de usuario");
            this.password_txt_fld.setPromptText("Contraseña");
            this.sign_in_btn.setText("Registrarse");
            this.lang_chk_bx.setText("Un cheque para Inglés");
            this.current_location_lbl.setText(g.getLoginLocationStringSpanish());
        }
        if (!lang_chk_bx.isSelected()) {
            this.welcome_lbl.setText("Welcomeback!");
            this.user_txt_fld.setPromptText("User");
            this.password_txt_fld.setPromptText("Password");
            this.sign_in_btn.setText("Sign in");
            this.lang_chk_bx.setText("Hablas Espanol");
            this.current_location_lbl.setText(g.getLoginLocationString());


        }


    }

    @FXML
    void sign_in() throws SQLException, IOException, ClassNotFoundException {
        try {
            try {
                String username_input = this.password_txt_fld.getText();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                String username_password = this.password_txt_fld.getText();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } finally {
            System.out.println("Checking provided user and password in database.");
//            Active_User active_user = curr_db.check_cred_in_db();

              curr_db.dbConnect();
              Connection con = curr_db.getCon();
              PreparedStatement ps = con.prepareStatement(
                      "SELECT * FROM user WHERE userName = ? AND password = ?;");
              ps.setString(1, this.user_txt_fld.getText());
              ps.setString(2,this.password_txt_fld.getText());
              ResultSet rs = curr_db.dbExecuteQuery(ps);

            if (rs.next()) {
                System.out.println("Logging in "+this.user_txt_fld.getText());
            }else {
                System.out.println("Bad username or password.");
            }


//            System.out.println("Currently logging in: "+active_user);
//            FXMLLoader loader = new FXMLLoader(this.main_window_url);
//            Parent main_root;
//            main_root = loader.load();
//            MainWindowC mwc = loader.getController();
//            mwc.initialize(curr_db,active_user);
//            Stage addPartStage = new Stage();
//            addPartStage.setTitle("Main Window");
//            Scene addPartScene = new Scene(main_root);
//            addPartStage.setScene(addPartScene);
//            addPartStage.showAndWait();

        }


    }

    @FXML
    public
        // This method is called by the FXMLLoader when initialization is complete
    void initialize(Database_v3 d, GeoIP g, URL main_resource) throws IOException {

        this.g = g;
        this.curr_db = d;
        this.main_window_url = main_resource;


        assert user_txt_fld != null : "fx:id=\"user_txt_fld\" was not injected: check your FXML file 'login_v.fxml'.";
        assert sign_in_btn != null : "fx:id=\"sign_in_btn\" was not injected: check your FXML file 'login_v.fxml'.";
        assert password_txt_fld != null : "fx:id=\"password_txt_fld\" was not injected: check your FXML file 'login_v.fxml'.";
        assert lang_chk_bx != null : "fx:id=\"lang_chk_bx\" was not injected: check your FXML file 'login_v.fxml'.";
        assert current_location_lbl != null : "fx:id=\"current_location_lbl\" was not injected: check your FXML file 'login_v.fxml'.";

        this.current_location_lbl.setText(g.getLoginLocationString());
        if(TESTING){
            this.testing();
        }
    }

    public TextField getUser_txt_fld() {
        return user_txt_fld;
    }

    public PasswordField getPassword_txt_fld() {
        return password_txt_fld;
    }

    public Boolean getLang_chk_bx_selected() {
        return lang_chk_bx.isSelected();
    }

    @FXML
    public void handleEnterPressed(KeyEvent event) throws IOException, SQLException, ClassNotFoundException {
        if (event.getCode() == KeyCode.ENTER) this.sign_in();
    }

    @FXML
    public void testing(){

        this.user_txt_fld.setText("test");
        this.password_txt_fld.setText("test");

    }
}
