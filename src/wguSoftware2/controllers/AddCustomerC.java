
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
import java.util.WeakHashMap;

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

    @FXML
    void add_customer() throws SQLException {

        String customer_name_str = this.name_txt.getText();
        String address_str = this.address_txt.getText();
        String alt_address_str = this.alt_address_txt.getText();
        String zip_code_str = this.zip_txt.getText();
        String phone_str = this.phone_txt.getText();
        String city_str = this.city_txt.getText();
        String country_str = this.country_txt.getText();
        String active_user_name = active_user.getActive_user_name();
        Integer country_id = null;
        Integer city_id = null;
        Integer address_id = null;
        Country country = new Country(country_str, active_user_name);
        System.out.println(country.get_country_db_create_str());

        ResultSet country_rs = curr_db.get_mysql_resultSet(country.get_country_db_create_str());
        ResultSet country_rs_pk = curr_db.get_mysql_resultSet("SELECT LAST_INSERT_ID();");
        while (country_rs_pk.next())
            country_id = country_rs_pk.getInt(1);
        City city = new City(city_str, active_user_name, country_id);
        ResultSet city_rs = curr_db.get_mysql_resultSet(city.get_city_db_create_str(country_id));
        ResultSet city_rs_pk = curr_db.get_mysql_resultSet("SELECT LAST_INSERT_ID();");
        while (city_rs_pk.next())
            city_id = city_rs_pk.getInt(1);
        Address address = new Address(address_str, alt_address_str, city_id, zip_code_str, phone_str,
                active_user);
        ResultSet address_rs = curr_db.get_mysql_resultSet(address.get_address_db_create_str());
        ResultSet address_pk = curr_db.get_mysql_resultSet("SELECT LAST_INSERT_ID();");
        while (address_pk.next())
            address_id = address_pk.getInt(1);
        System.out.println("test");
        Customer customer = new Customer(customer_name_str,active_user_name,address_id);
        Customer_view_main cvm = new Customer_view_main(
                customer.getCustomer_id(),
                customer.getCustomer_name(),
                address.getAddress() +", "
                        +address.getAlt_address(),
                        address.getPhone());
        obv_customer_list.add(cvm);
        this.stage.close();





    }




    @FXML
    void testing() {

        this.name_txt.setText("Some Name");
        this.address_txt.setText("Some Address");
        this.alt_address_txt.setText("Some Alt Address txt");
        this.city_txt.setText("Some City Txt");
        this.zip_txt.setText("12345");
        this.country_txt.setText("Some Country Txt");
        this.phone_txt.setText("Some Phone Txt");

    }

    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize(Database curr_db, Active_User active_user, List<Customer_view_main> all_customers) {
        this.active_user = active_user;
        this.curr_db = curr_db;
        this.obv_customer_list = obv_customer_list;
        assert phone_txt != null : "fx:id=\"phone_txt\" was not injected: check your FXML file 'add_customer.fxml'.";
        assert alt_address_txt != null : "fx:id=\"alt_address_txt\" was not injected: check your FXML file 'add_customer.fxml'.";
        assert add_customer_btn != null : "fx:id=\"add_customer_btn\" was not injected: check your FXML file 'add_customer.fxml'.";
        assert country_txt != null : "fx:id=\"country_txt\" was not injected: check your FXML file 'add_customer.fxml'.";
        assert name_txt != null : "fx:id=\"name_txt\" was not injected: check your FXML file 'add_customer.fxml'.";
        assert city_txt != null : "fx:id=\"city_txt\" was not injected: check your FXML file 'add_customer.fxml'.";
        assert address_txt != null : "fx:id=\"address_txt\" was not injected: check your FXML file 'add_customer.fxml'.";
        assert zip_txt != null : "fx:id=\"zip_txt\" was not injected: check your FXML file 'add_customer.fxml'.";

    }

    public void setStage(Stage addCustomerStage) {
        this.stage = addCustomerStage;

    }
}
