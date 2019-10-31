package wguSoftware2.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import wguSoftware2.DAO.CalendarViewMainDAO;
import wguSoftware2.models.Active_User;
import wguSoftware2.models.Appoinment_view_main;
import wguSoftware2.models.Customer_view_main;
import wguSoftware2.models.MyDateTime;
import wguSoftware2.utils.Database_v3;
import wguSoftware2.utils.Utils;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.TimeZone;

/**
 * The type Update appointment c.
 */
public class UpdateAppointmentC {
    /**
     * The Customer txt.
     */
    @FXML
    public TextField customer_txt;
    @FXML
    private Label blank_lbl;
    @FXML
    private TextField title_txt;
    @FXML
    private TextField desc_txt;
    @FXML
    private TextField location_txt;
    @FXML
    private TextField contact_txt;
    @FXML
    private ComboBox<String> apt_type_cb;
    @FXML
    private DatePicker date_pkr;
    @FXML
    private ComboBox<String> start_hour_cb;
    @FXML
    private ComboBox<String> start_min_cb;
    @FXML
    private RadioButton start_pm;
    @FXML
    private ComboBox<String> end_min_cb;
    @FXML
    private ComboBox<String> end_hour_cb;
    @FXML
    private RadioButton end_pm;
    @FXML
    private Label start_date_time_lbl;
    @FXML
    private Label end_date_time_lbl;
    @FXML
    private Button updt_apt_btn = null;
    @FXML
    private Database_v3 curr_db;
    @FXML
    private Active_User active_user;
    @FXML
    private ObservableList<Customer_view_main> obv_customer_list;
    private Appoinment_view_main avm = null;
    private Stage curr_stage = null;
    private CalendarViewMainDAO calendarViewMainDAO = null;
    private Integer apt_id = null;
    private Utils utils =null;

    /**
     * Initialize.
     *
     * @param curr_db           the curr db
     * @param active_user       the active user
     * @param obv_customer_list the obv customer list
     * @throws SQLException           the sql exception
     * @throws ClassNotFoundException the class not found exception
     */
    public void initialize(Database_v3 curr_db, Active_User active_user, ObservableList<Customer_view_main> obv_customer_list) throws SQLException, ClassNotFoundException {

        this.curr_db = curr_db;
        this.obv_customer_list = obv_customer_list;
        this.active_user = active_user;
        this.calendarViewMainDAO = new CalendarViewMainDAO(this.curr_db);
        this.utils = new Utils();

    }

    /**
     * Sets fields.
     *
     * @param selectedItem the selected item
     * @throws SQLException           the sql exception
     * @throws ClassNotFoundException the class not found exception
     */
    public void set_fields(Appoinment_view_main selectedItem) throws SQLException, ClassNotFoundException {

        this.avm = selectedItem;


        title_txt.setText(selectedItem.getTitle());
        desc_txt.setText(selectedItem.getDescription());
        location_txt.setText(selectedItem.getLocation());
        customer_txt.setText(selectedItem.getCustomerName());
        customer_txt.setEditable(false);
        customer_txt.setDisable(true);
        desc_txt.setText(selectedItem.getDescription());
        System.out.println(desc_txt.getText());
        ObservableList<String> apt_types = null;
        apt_types = FXCollections.observableArrayList();

        apt_types.add("Initial");
        apt_types.add("Sales");
        apt_types.add("Follow up");

        apt_type_cb.setItems(apt_types);

        ObservableList<String> hour_items = end_hour_cb.getItems();
        ObservableList<String> min_items = start_hour_cb.getItems();

        for (int i = 1; i < 13 ; i++) {
            hour_items.add(String.valueOf(i));

        }
        min_items.add(String.valueOf(0));
        for (int i = 1; i < 61; i++) {

            if(i % 5 == 0) {
                min_items.add(String.valueOf(i));
            }
        }

        end_hour_cb.setItems(hour_items);
        start_hour_cb.setItems(hour_items);
        end_min_cb.setItems(min_items);
        start_min_cb.setItems(min_items);

        String endHourStr = selectedItem.getEnd_date_time().getSimpleTimeHourLocal();
        MyDateTime start_date_time = selectedItem.getStart_date_time();
        String  startHourStr = start_date_time.getSimpleTimeHourLocal();
        String endMinStr = selectedItem.getEnd_date_time().getSimpleTimeMinLocal();
        String  startMinStr = start_date_time.getSimpleTimeMinLocal();

        end_hour_cb.getSelectionModel().select(endHourStr);
        end_min_cb.getSelectionModel().select(endMinStr);
        start_hour_cb.getSelectionModel().select(startHourStr);
        start_min_cb.getSelectionModel().select(startMinStr);

        
        LocalDateTime ldt = start_date_time.getZonedLocalDateTime().toLocalDateTime();
        date_pkr.setValue(ldt.toLocalDate());
        date_pkr.setDayCellFactory(picker -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate today = LocalDate.now();

                setDisable(empty || date.compareTo(today) < 0 );
            }
        });

        ArrayList<String>list = new ArrayList<>();
        list = this.calendarViewMainDAO.getAppointmentTypeAndContact(selectedItem.getId());
        apt_type_cb.setValue(list.get(0));
        contact_txt.setText(list.get(1));
        this.apt_id = selectedItem.getId();

    }

    /**
     * Sets stage.
     *
     * @param addCustomerStage the add customer stage
     */
    public void setStage(Stage addCustomerStage) {

        this.curr_stage = addCustomerStage;

    }

    /**
     * Gets avm.
     *
     * @return the avm
     */
    public Appoinment_view_main get_avm() {

        return this.avm;

    }

    /**
     * Updt apt appoinment view main.
     *
     * @param actionEvent the action event
     * @return the appoinment view main
     * @throws SQLException           the sql exception
     * @throws ClassNotFoundException the class not found exception
     */
    public Appoinment_view_main UPDT_APT(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

//        Integer apt_id = this.apt_id;
        String title = title_txt.getText();
        String customer = customer_txt.getText();
        String description = desc_txt.getText();
        String location = location_txt.getText();
        String contact = contact_txt.getText();
        Integer customerID = this.avm.getCustomerID();

        String apt_type = apt_type_cb.getValue();
        String date = date_pkr.getValue().toString();
        String start_hour_str = start_hour_cb.getValue();
        String end_hour_str = end_hour_cb.getValue();
        String start_min_str = start_min_cb.getValue();
        String end_min_str = end_min_cb.getValue();


        String startPmString = "AM";
        String endPmString = "AM";


        if (this.start_pm.isSelected()) {
            startPmString = "PM";
        }
        if (this.end_pm.isSelected()) {
            endPmString = "PM";
        }
        start_hour_str = appendZero(start_hour_str);
        start_min_str = appendZero(start_min_str);
        end_hour_str = appendZero(end_hour_str);
        end_min_str = appendZero(end_min_str);

        String startInput = getTimeDateInputStr(start_hour_str, start_min_str, startPmString);
        String endInput = getTimeDateInputStr(end_hour_str, end_min_str, endPmString);

        MyDateTime myStartDateTime = new MyDateTime(startInput, this.active_user);
        Timestamp startTS = myStartDateTime.getUTCTimeStamp();
        MyDateTime myEndDateTime = new MyDateTime(endInput, this.active_user);
        Timestamp endTS = myEndDateTime.getUTCTimeStamp();


            /*
            Program Constraint:
            F. Write exception controls to prevent each of the following. You may use the same mechanism of exception control more than once, but you must incorporate at least  two different mechanisms of exception control.
            • scheduling an appointment outside business hours
         */
        try {
            utils.timeCheckError(myStartDateTime, myEndDateTime);
            utils.appointmentOverlapCheck(myStartDateTime, myEndDateTime);
            Appoinment_view_main avm = new Appoinment_view_main(
                    active_user,
                    customerID,
                    apt_id,
                    customer,
                    description,
                    title,
                    location,
                    startTS,
                    endTS,
                    "",
                    apt_type);
            avm.create_hyperlink();
            this.avm = avm;

            this.calendarViewMainDAO.update(this.avm, this.active_user);

            this.updt_apt_btn.getScene().getWindow().hide();
        }

        /*
            Program Constraint:
            F. Write exception controls to prevent each of the following. You may use the same mechanism of exception control more than once, but you must incorporate at least  two different mechanisms of exception control.
            • scheduling an appointment outside business hours
         */

        catch (Error error) {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Appointment Add Error!");
                alert.setHeaderText("Try again!");
                alert.setContentText(error.toString());
                alert.showAndWait();
            }
        /*
            Program Constraint:
            F. Write exception controls to prevent each of the following. You may use the same mechanism of exception control more than once, but you must incorporate at least  two different mechanisms of exception control.
            • scheduling overlapping appointments
         */

            catch (IllegalArgumentException i){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Appointment Add Error!");
                alert.setHeaderText("Try again!");
                alert.setContentText(i.toString());
                alert.showAndWait();
            }

        return avm;
    }


    /**
     * On contact change.
     */
    public void on_contact_change(){

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Please note: Per management");
        alert.setContentText("If you want to edit the customer name please delete this appointment. " +
                "Create a new customer and a new appointment!");

        alert.showAndWait();

    }

    private String getTimeDateInputStr(String start_hour_str, String start_min_str, String startPmString) {
        String startTime = start_hour_str + ":" + start_min_str + " " + startPmString;
        String date = date_pkr.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyy"));
        String startInput = date+" "+startTime;
        return startInput;
    }

    private String appendZero(String input) {
        if(Integer.parseInt(input) < 10){
            if(input.length() == 1)
                input = "0"+input;
        }
        return input;
    }


}
