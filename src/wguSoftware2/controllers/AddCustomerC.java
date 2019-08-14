
package wguSoftware2.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import wguSoftware2.models.Address;
import wguSoftware2.models.Country;
import wguSoftware2.models.Customer;

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
    void add_customer() {

        String customer_name_str = this.name_txt.getText();
        String address_str = this.address_txt.getText();
        String alt_address_str = this.alt_address_txt.getText();
        String zip_code_str = this.zip_txt.getText();
        String phone_str = this.phone_txt.getText();
        String country_str  =this.country_txt.getText();

        Customer customer = new Customer(customer_name_str);
        Address add = new Address(address_str,alt_address_str,zip_code_str,phone_str);
        Country country = new Country(country_str);


    }

    @FXML void testing(){

        this.name_txt.setText("Some Name");
        this.address_txt.setText("Some Address");
        this.alt_address_txt.setText("Some Alt Address txt");
        this.city_txt.setText("Some City Txt");
        this.zip_txt.setText("Some Zip Txt");
        this.country_txt.setText("Some Country Txt");
        this.phone_txt.setText("Some Phone Txt");

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert phone_txt != null : "fx:id=\"phone_txt\" was not injected: check your FXML file 'add_customer.fxml'.";
        assert alt_address_txt != null : "fx:id=\"alt_address_txt\" was not injected: check your FXML file 'add_customer.fxml'.";
        assert add_customer_btn != null : "fx:id=\"add_customer_btn\" was not injected: check your FXML file 'add_customer.fxml'.";
        assert country_txt != null : "fx:id=\"country_txt\" was not injected: check your FXML file 'add_customer.fxml'.";
        assert name_txt != null : "fx:id=\"name_txt\" was not injected: check your FXML file 'add_customer.fxml'.";
        assert city_txt != null : "fx:id=\"city_txt\" was not injected: check your FXML file 'add_customer.fxml'.";
        assert address_txt != null : "fx:id=\"address_txt\" was not injected: check your FXML file 'add_customer.fxml'.";
        assert zip_txt != null : "fx:id=\"zip_txt\" was not injected: check your FXML file 'add_customer.fxml'.";

    }
}
