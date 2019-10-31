
package wguSoftware2.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import wguSoftware2.DAO.CustomerViewMainDAO;
import wguSoftware2.models.*;
import wguSoftware2.utils.Database;
import wguSoftware2.utils.Database_v2;
import wguSoftware2.utils.Database_v3;

import java.awt.*;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The type Add customer c.
 */
public class AddCustomerC {

    /**
     * The Blank lbl.
     */
    public Label blank_lbl;
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

    /**
     * Initialize.
     *
     * @param curr_db           the curr db
     * @param active_user       the active user
     * @param obv_customer_list the obv customer list
     */
    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize(Database_v3 curr_db, Active_User active_user, List<Customer_view_main> obv_customer_list) {
        this.active_user = active_user;
        this.curr_db = curr_db;
        this.obv_customer_list = obv_customer_list;
        this.update = false;
        this.cvmDAO = new CustomerViewMainDAO(curr_db,active_user.getActive_user_name());
        this.blank_lbl.setFocusTraversable(true);

    }

    /**
     * Sets stage.
     *
     * @param addCustomerStage the add customer stage
     */
    public void setStage(Stage addCustomerStage) {
        this.stage = addCustomerStage;

    }

    /**
     * Add customer.
     *
     * @throws SQLException           the sql exception
     * @throws ClassNotFoundException the class not found exception
     */
    @FXML
    void add_customer() throws SQLException, ClassNotFoundException {

        try {
            String name_txt = testForBlankString(this.name_txt);
            String address_txt = testForBlankString(this.address_txt);
            String alt_address_txt = testForBlankString(this.alt_address_txt);
            String city_txt = testForBlankString(this.city_txt);
            String zip_txt = testForBlankString(this.zip_txt);
            String country_txt = testForBlankString(this.country_txt);
            String phone_txt = testForBlankString(this.phone_txt);

            if(!phoneNumberCheck(phone_txt)){
                throw new Error("Bad Phone Number");
            }

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

        }catch (Error e){

                        /*
            Program Constraint:
            F. Write exception controls to prevent each of the following. You may use the same mechanism of exception control more than once, but you must incorporate at least  two different mechanisms of exception control.
            • scheduling overlapping appointments
            */

            String message = "This program only accepts North American phone" +
                    "numbers. Valid Examples are as follows:\n" +
                    "1234567890\n" +
                    "123-456-7890\n" +
                    "123.456.7890\n" +
                    "123 456 7890\n" +
                    "(123) 456 7890";

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Customer Add Error!");
            alert.setHeaderText("Try again!");
            alert.setContentText(message);
            alert.showAndWait();
            System.out.println("Bad phone!");

        }

        catch (SQLException | ClassNotFoundException | NullPointerException e) {

            /*
            Program Constraint:
            F. Write exception controls to prevent each of the following. You may use the same mechanism of exception control more than once, but you must incorporate at least  two different mechanisms of exception control.
            • scheduling overlapping appointments
            */

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Appointment Add Error!");
            alert.setHeaderText("Try again!");
            alert.setContentText(e.toString());
            System.out.println("Blank fields not allowed. ");

            alert.showAndWait();

        }

    }

    private String testForBlankString(TextField inputStr) {
        if(inputStr.getText().equals("")){
            throw new NullPointerException("No fields can be blank");
        }else
            return inputStr.getText();
    }

    private Boolean phoneNumberCheck(String phone_txt) {

        Boolean rval = false;
        String regex = "^\\(?([0-9]{3})\\)?[-.\\s]?([0-9]{3})[-.\\s]?([0-9]{4})$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phone_txt);
        if(matcher.matches()) {
            rval = true;
        }
        return rval;
    }

    /**
     * Gets cvm.
     *
     * @return the cvm
     */
    public Customer_view_main get_cvm() {
        return this.cvm;
    }

}
