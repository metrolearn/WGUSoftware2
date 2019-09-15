
package wguSoftware2.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import wguSoftware2.DAO.CustomerViewMainDAO;
import wguSoftware2.models.*;
import wguSoftware2.utils.Database;
import wguSoftware2.utils.Database_v2;
import wguSoftware2.utils.Database_v3;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class AddCustomerC {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="phone_txt"
    private TextField phone_txt; // Value injected by FXMLLoader

    @FXML // fx:id="alt_address_txt"
    private TextField alt_address_txt; // Value injected by FXMLLoader

    @FXML // fx:id="add_customer_btn"
    private Button add_customer_btn; // Value injected by FXMLLoader

    @FXML // fx:id="country_txt"
    private TextField country_txt; // Value injected by FXMLLoader

    @FXML // fx:id="name_txt"
    private TextField name_txt; // Value injected by FXMLLoader

    @FXML // fx:id="city_txt"
    private TextField city_txt; // Value injected by FXMLLoader

    @FXML // fx:id="address_txt"
    private TextField address_txt; // Value injected by FXMLLoader

    @FXML // fx:id="zip_txt"
    private TextField zip_txt; // Value injected by FXMLLoader

    @FXML
    private Stage stage;

    private Active_User active_user;
    private Database_v3 curr_db;
    private List<Customer_view_main> obv_customer_list;
    private Customer_view_main cvm = null;
    private CustomerViewMainDAO cvmDAO = null;
    private Boolean update;
    private Integer cvmIndex;

    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize(Database_v3 curr_db, Active_User active_user, List<Customer_view_main> obv_customer_list) {
        this.active_user = active_user;
        this.curr_db = curr_db;
        this.obv_customer_list = obv_customer_list;
        this.update = false;
        this.cvmDAO = new CustomerViewMainDAO(curr_db,active_user.getActive_user_name());

    }

    public void setStage(Stage addCustomerStage) {
        this.stage = addCustomerStage;

    }

    @FXML
    void add_customer() throws SQLException, ClassNotFoundException {

        String name_txt = this.name_txt.getText();
        String address_txt = this.address_txt.getText();
        String alt_address_txt = this.alt_address_txt.getText();
        String city_txt = this.city_txt.getText();
        String zip_txt = this.zip_txt.getText();
        String country_txt = this.country_txt.getText();
        String phone_txt = this.phone_txt.getText();

        this.cvm = new Customer_view_main(
                name_txt,
                address_txt,
                alt_address_txt,
                city_txt,
                zip_txt,
                country_txt,
                phone_txt

        );

        this.cvm = cvmDAO.create(this.cvm);
        this.add_customer_btn.getScene().getWindow().hide();

    }

    public Customer_view_main get_cvm() {
        return this.cvm;
    }

}
