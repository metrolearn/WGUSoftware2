/**
 * Sample Skeleton for 'login_v.fxml' Controller Class
 */

package wguSoftware2.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javax.swing.JInternalFrame;
import wguSoftware2.models.GeoIP;

public class LoginCtrl {

  private GeoIP g;

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
  void lang_chk_bx_clicked(ActionEvent event) throws IOException {

      if (lang_chk_bx.isSelected()){
        this.welcome_lbl.setText("Bienvenido");
        this.user_txt_fld.setPromptText("Nombre de usuario");
        this.password_txt_fld.setPromptText("Contraseña");
        this.sign_in_btn.setText("Registrarse");
        this.lang_chk_bx.setText("Un cheque para Inglés");
        this.current_location_lbl.setText(g.getLoginLocationStringSpanish());
      }
      if(!lang_chk_bx.isSelected()){
        this.welcome_lbl.setText("Welcomeback!");
        this.user_txt_fld.setPromptText("User");
        this.password_txt_fld.setPromptText("Password");
        this.sign_in_btn.setText("Sign in");
        this.lang_chk_bx.setText("Hablas Espanol");
        this.current_location_lbl.setText(g.getLoginLocationString());


      }


  }

  @FXML
  void sign_in(ActionEvent event) {

  }

  @FXML // This method is called by the FXMLLoader when initialization is complete
  void initialize() throws IOException {

   this.g = new GeoIP();


    assert user_txt_fld != null : "fx:id=\"user_txt_fld\" was not injected: check your FXML file 'login_v.fxml'.";
    assert sign_in_btn != null : "fx:id=\"sign_in_btn\" was not injected: check your FXML file 'login_v.fxml'.";
    assert password_txt_fld != null : "fx:id=\"password_txt_fld\" was not injected: check your FXML file 'login_v.fxml'.";
    assert lang_chk_bx != null : "fx:id=\"lang_chk_bx\" was not injected: check your FXML file 'login_v.fxml'.";
    assert current_location_lbl != null : "fx:id=\"current_location_lbl\" was not injected: check your FXML file 'login_v.fxml'.";

    this.current_location_lbl.setText(g.getLoginLocationString());

  }
}
