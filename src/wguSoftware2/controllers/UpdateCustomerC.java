package wguSoftware2.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import wguSoftware2.models.*;
import wguSoftware2.utils.Database;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class UpdateCustomerC extends AddCustomerC {

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

    private Customer customer;
    private Address address;
    private City city;
    private Country country;

    @FXML
    private Stage stage;

    private Active_User active_user;
    private Database curr_db;
    private Customer_view_main cvm;
    private List<Customer_view_main> obv_customer_list;
    private Boolean update;
    private Integer cvmIndex;


    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize(Database curr_db, Active_User active_user, List<Customer_view_main> obv_customer_list) {
        super.initialize(curr_db, active_user, obv_customer_list);
    }

    @FXML
    public void set_fields(Customer_view_main cmv) throws SQLException {
        super.set_fields(cmv);
    }

    @FXML
    void update_customer() throws SQLException {
        this.cvm = super.getCustomer_view_main_sql(true);

        Integer id = cvm.getId();
        Integer x = 4;

    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
