
package wguSoftware2.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import wguSoftware2.models.*;
import wguSoftware2.utils.Database;

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
    private Database curr_db;
    private List<Customer_view_main> obv_customer_list;
    private Boolean update;
    private Integer cvmIndex;

    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize(Database curr_db, Active_User active_user, List<Customer_view_main> obv_customer_list) {
        this.active_user = active_user;
        this.curr_db = curr_db;
        this.obv_customer_list = obv_customer_list;
        this.update = false;
        assert phone_txt != null : "fx:id=\"phone_txt\" was not injected: check your FXML file 'add_customer.fxml'.";
        assert alt_address_txt != null : "fx:id=\"alt_address_txt\" was not injected: check your FXML file 'add_customer.fxml'.";
        assert add_customer_btn != null : "fx:id=\"add_customer_btn\" was not injected: check your FXML file 'add_customer.fxml'.";
        assert country_txt != null : "fx:id=\"country_txt\" was not injected: check your FXML file 'add_customer.fxml'.";
        assert name_txt != null : "fx:id=\"name_txt\" was not injected: check your FXML file 'add_customer.fxml'.";
        assert city_txt != null : "fx:id=\"city_txt\" was not injected: check your FXML file 'add_customer.fxml'.";
        assert address_txt != null : "fx:id=\"address_txt\" was not injected: check your FXML file 'add_customer.fxml'.";
        assert zip_txt != null : "fx:id=\"zip_txt\" was not injected: check your FXML file 'add_customer.fxml'.";

    }

    @FXML
    void add_customer() throws SQLException {

            System.out.println("test");

    }


    public void setStage(Stage addCustomerStage) {
        this.stage = addCustomerStage;

    }

    public void set_fields(Customer_view_main cmv) throws SQLException {

        ResultSet cvm_pk = curr_db.get_mysql_resultSet("e");
        this.update = true;
        this.cvmIndex = obv_customer_list.indexOf(cmv);

        String alt_add = "";
        String city = "";
        String zip = "";
        String country = "";

        while (cvm_pk.next()) {
            alt_add = cvm_pk.getString("address2");
            city = cvm_pk.getString("city");
            zip = cvm_pk.getString("postalCode");
            country = cvm_pk.getString("country");

        }

        this.name_txt.setText(cmv.getName());
        this.address_txt.setText(cmv.getAddress());
        this.alt_address_txt.setText(alt_add);
        this.city_txt.setText(city);
        this.zip_txt.setText(zip);
        this.country_txt.setText(country);
        this.phone_txt.setText(cmv.getPhone());


    }


}
