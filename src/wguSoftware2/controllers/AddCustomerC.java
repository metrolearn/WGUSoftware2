
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


        Customer_view_main cvm;
        cvm = getCustomer_view_main_sql(false);
        int index;
        if (!update) {
            obv_customer_list.add(cvm);

        } else {

            obv_customer_list.set(this.cvmIndex, cvm);
        }

        this.stage.close();
    }

    Customer_view_main getCustomer_view_main_sql(Boolean up) throws SQLException {
        Boolean update = up;
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
        Integer customer_id = null;
        return getCustomer_view_main_sql(customer_name_str, address_str, alt_address_str, zip_code_str,
                phone_str, city_str, country_str, active_user_name, country_id, city_id, address_id,
                customer_id);
    }

    private Customer_view_main getCustomer_view_main_sql(String customer_name_str, String address_str, String alt_address_str, String zip_code_str, String phone_str, String city_str, String country_str, String active_user_name, Integer country_id, Integer city_id, Integer address_id, Integer customer_id) throws SQLException {
        Country country = new Country(country_str, active_user_name);

        ResultSet country_rs_pk = null;
        ResultSet city_rs_pk = null;
        ResultSet address_pk = null;
        ResultSet customer_pk;

        Customer customer = null;
        City city = null;
        Address address  = null;

                Integer selected_customer_id = null;


        if (update) {


            String addressID = String.valueOf(address_id);
            String cityID = String.valueOf(city_id);
            String countryID = String.valueOf(country_id);
            customer_pk = curr_db.get_mysql_resultSet("SELECT c.countryId FROM customer" +
                    "    INNER JOIN address a on customer.addressId = a.addressId" +
                    "    INNER JOIN city c on a.cityId = c.cityId" +
                    "    INNER JOIN country c2 on c.countryId = c2.countryId" +
                    "WHERE a.addressId = " + addressID +
                    "AND  c.cityId =  " + cityID +
                    "AND  c2.countryId = " + countryID);

            while (customer_pk.next())
                selected_customer_id = customer_pk.getInt(1);

            System.out.println(selected_customer_id);


        } else {
            ResultSet country_rs = curr_db.get_mysql_resultSet(country.get_country_db_create_str());
            country_rs_pk = curr_db.get_mysql_resultSet("SELECT LAST_INSERT_ID();");
            while (country_rs_pk.next())
                country_id = country_rs_pk.getInt(1);
             city = new City(city_str, active_user_name, country_id);
            ResultSet city_rs = curr_db.get_mysql_resultSet(city.get_city_db_create_str(country_id));
            city_rs_pk = curr_db.get_mysql_resultSet("SELECT LAST_INSERT_ID();");
            while (city_rs_pk.next())
                city_id = city_rs_pk.getInt(1);
             address = new Address(address_str, alt_address_str, city_id, zip_code_str, phone_str,
                    active_user);
            ResultSet address_rs = curr_db.get_mysql_resultSet(address.get_address_db_create_str());
             address_pk = curr_db.get_mysql_resultSet("SELECT LAST_INSERT_ID();");
            while (address_pk.next())
                address_id = address_pk.getInt(1);
             customer = new Customer(customer_name_str,active_user_name,address_id);
            ResultSet customer_rs = curr_db.get_mysql_resultSet(customer.get_address_db_create_str());
             customer_pk = curr_db.get_mysql_resultSet("SELECT LAST_INSERT_ID();");
            while (customer_pk.next()) {
                customer_id = customer_pk.getInt(1);

            }

        }
        final Customer_view_main customer_view_main = new Customer_view_main(
                customer_id,
                customer.getCustomer_name(),
                address.getAddress() + ", "
                        + address.getAlt_address(),
                address.getPhone());
        return customer_view_main;
    }


    public void setStage(Stage addCustomerStage) {
        this.stage = addCustomerStage;

    }

    public void set_fields(Customer_view_main cmv) throws SQLException {

        ResultSet cvm_pk = curr_db.get_mysql_resultSet(cmv.get_cvm_db_create_str());
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
