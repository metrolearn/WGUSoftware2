package wguSoftware2.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import wguSoftware2.DAO.CustomerViewMainDAO;
import wguSoftware2.models.*;
import wguSoftware2.utils.DatabaseMain;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The type Update customer c.
 */
public class UpdateCustomerC {

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
    private DatabaseMain curr_db;
    private List<Customer_view_main> obv_customer_list;
    private Customer_view_main cvm = null;
    private CustomerViewMainDAO cvmDAO = null;
    private Boolean update;
    private Integer cvmIndex;
    private Integer customer_id;

    /**
     * Initialize.
     *
     * @param curr_db           the curr db
     * @param active_user       the active user
     * @param obv_customer_list the obv customer list
     */
    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize(DatabaseMain curr_db, Active_User active_user, List<Customer_view_main> obv_customer_list) {
        this.active_user = active_user;
        this.curr_db = curr_db;
        this.obv_customer_list = obv_customer_list;
        this.update = false;
        this.cvmDAO = new CustomerViewMainDAO(curr_db,active_user.getActive_user_name());

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
     * Gets cvm.
     *
     * @return the cvm
     */
    public Customer_view_main get_cvm() {
        this.cvm.setId(this.customer_id)    ;
        return this.cvm;
    }

    /**
     * Sets fields.
     *
     * @param selectedItem the selected item
     * @throws SQLException           the sql exception
     * @throws ClassNotFoundException the class not found exception
     */
    public void set_fields(Customer_view_main selectedItem) throws SQLException, ClassNotFoundException {

        this.cvm = this.cvmDAO.read(selectedItem);
        this.name_txt.setText(this.cvm.getName());
        this.address_txt.setText(this.cvm.getAddress());
        this.alt_address_txt.setText(this.cvm.getAlt_address());
        this.city_txt.setText(this.cvm.getCity_name());
        this.zip_txt.setText(this.cvm.getZip());
        this.country_txt.setText(this.cvm.getCountry_name());
        this.phone_txt.setText(this.cvm.getPhone());
        this.customer_id = this.cvm.getId();

    }

    /**
     * Update customer customer view main.
     *
     * @param actionEvent the action event
     * @return the customer view main
     * @throws SQLException           the sql exception
     * @throws ClassNotFoundException the class not found exception
     */
    public Customer_view_main update_customer(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
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

            this.cvm.setId(this.customer_id);

            this.cvm = cvmDAO.update(this.cvm);
            this.add_customer_btn.getScene().getWindow().hide();
        } catch (Error error) {
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

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (NullPointerException e){

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
        return  this.cvm;

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

}
