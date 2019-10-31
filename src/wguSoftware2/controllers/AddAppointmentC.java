package wguSoftware2.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import wguSoftware2.DAO.CalendarViewMainDAO;
import wguSoftware2.models.Active_User;
import wguSoftware2.models.Appoinment_view_main;
import wguSoftware2.models.Customer_view_main;
import wguSoftware2.models.MyDateTime;
import wguSoftware2.utils.Utils;
import wguSoftware2.utils.Database_v3;

import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.TimeZone;

/**
 * The type Add appointment c.
 */
public class AddAppointmentC {

    /**
     * The Start hour cb.
     */
    @FXML
    public ComboBox<String> start_hour_cb;
    /**
     * The Start min cb.
     */
    @FXML
    public ComboBox<String> start_min_cb;
    /**
     * The End min cb.
     */
    @FXML
    public ComboBox<String> end_min_cb;
    /**
     * The End hour cb.
     */
    @FXML
    public ComboBox<String> end_hour_cb;
    /**
     * The Start date time lbl.
     */
    @FXML
    public Label start_date_time_lbl;
    /**
     * The End date time lbl.
     */
    @FXML
    public Label end_date_time_lbl;
    /**
     * The Blank lbl.
     */
    @FXML
    public Label blank_lbl;
    /**
     * The Add apt btn.
     */
    @FXML
    public Button add_apt_btn;
    /**
     * The Apt type cb.
     */
    @FXML
    public ComboBox<String> apt_type_cb;
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private TextField title_txt;
    @FXML
    private TextField contact_txt;
    @FXML
    private DatePicker date_pkr;
    @FXML
    private RadioButton start_pm;
    @FXML
    private MenuButton start_hour;
    @FXML
    private RadioButton end_pm;
    @FXML
    private TextField desc_txt;
    @FXML
    private TextField location_txt;
    @FXML
    private CalendarViewMainDAO calendarViewMainDAO = null;
    @FXML
    private Customer_view_main selectedCVM = null;
    @FXML
    private Appoinment_view_main apv = null;
    @FXML
    private Active_User active_user = null;
    @FXML
    private Database_v3 curr_db = null;
    private Utils utils = null;

    /**
     * Gets avm.
     *
     * @return the avm
     */
    @FXML
    public Appoinment_view_main get_avm() {

        return this.apv;

    }

    /**
     * Gets resources.
     *
     * @return the resources
     */
    public ResourceBundle getResources() {
        return resources;
    }

    /**
     * Sets resources.
     *
     * @param resources the resources
     */
    public void setResources(ResourceBundle resources) {
        this.resources = resources;
    }

    /**
     * Gets location.
     *
     * @return the location
     */
    public URL getLocation() {
        return location;
    }

    /**
     * Sets location.
     *
     * @param location the location
     */
    public void setLocation(URL location) {
        this.location = location;
    }

    /**
     * Gets active user.
     *
     * @return the active user
     */
    public Active_User getActive_user() {
        return active_user;
    }

    /**
     * Sets active user.
     *
     * @param active_user the active user
     */
    public void setActive_user(Active_User active_user) {
        this.active_user = active_user;
    }


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

        this.active_user = active_user;
        this.curr_db = curr_db;
        this.calendarViewMainDAO = new CalendarViewMainDAO(curr_db);
        this.utils = new Utils();

        ObservableList<String> hour_items = end_hour_cb.getItems();

        ObservableList<String> min_items = start_hour_cb.getItems();

        for (int i = 1; i < 13 ; i++) {
            hour_items.add(String.valueOf(i));

        }

        min_items.add(String.valueOf(0));
        for (int i = 1; i < 60; i++) {

            if(i % 5 == 0) {
                min_items.add(String.valueOf(i));
            }
        }

        end_hour_cb.setItems(hour_items);
        start_hour_cb.setItems(hour_items);
        end_min_cb.setItems(min_items);
        start_min_cb.setItems(min_items);

        ObservableList<String> apt_types = null;
        apt_types = FXCollections.observableArrayList();

        apt_types.add("Initial");
        apt_types.add("Sales");
        apt_types.add("Follow up");

        this.apt_type_cb.setItems(apt_types);

        this.blank_lbl.setFocusTraversable(true);

        /*
        Program Constraint:
        G. Write two or more lambda expressions to make your program more
        efficient, justifying the use of each lambda expression with an
        in-line comment.

        This a concise way to make sure past dates cannot be entered.
        With out this there would be a lot more code to handle this issue.
        So the Lambda helps in this sense.

         */

        date_pkr.setDayCellFactory(picker -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate today = LocalDate.now();

                setDisable(empty || date.compareTo(today) < 0 );
            }
        });

    }

    /**
     * Sets stage.
     *
     * @param addAppointmentStage the add appointment stage
     */
    public void setStage(Stage addAppointmentStage) {
    }

    /**
     * Add apt.
     *
     * @param actionEvent the action event
     * @throws SQLException           the sql exception
     * @throws ClassNotFoundException the class not found exception
     */
    public void ADD_APT(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        String title = this.title_txt.getText();
        String description = this.desc_txt.getText();
        String location = this.location_txt.getText();
        String contact = this.contact_txt.getText();
        String apt_type = this.apt_type_cb.getValue();
        String start_hour_str = this.start_hour_cb.getValue();
        String start_min_str = this.start_min_cb.getValue();
        String end_hour_str = this.end_hour_cb.getValue();
        String end_min_str = this.end_min_cb.getValue();
        boolean end_pm = this.end_pm.isArmed();
        boolean start_pm = this.start_pm.isArmed();
        String startPmString = "AM";
        String endPmString = "AM";

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss a z");
            TimeZone tz = active_user.getTz();
            String zoneId = tz.toZoneId().toString();

            if (this.start_pm.isSelected()){
                startPmString = "PM";
            }
            if(this.end_pm.isSelected()){
                endPmString = "PM";
            }
            start_hour_str = appendZero(start_hour_str);
            start_min_str = appendZero(start_min_str);
            end_hour_str = appendZero(end_hour_str);
            end_min_str = appendZero(end_min_str);

            String startInput = getTimeDateInputStr(start_hour_str, start_min_str, startPmString);
            String endInput = getTimeDateInputStr(end_hour_str,end_min_str,endPmString);

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
                utils.timeCheckError(myStartDateTime,myEndDateTime);
                utils.appointmentOverlapCheck(myStartDateTime,myEndDateTime);
                Integer customerID = this.getSelectedCVM().getId();
                String customerName = this.getSelectedCVM().getName();
                Integer nextID = calendarViewMainDAO.getNextAppointmentID();
                Appoinment_view_main apv =
                        new Appoinment_view_main(active_user,customerID,nextID,customerName,
                                description,title,location,startTS,endTS,"test",apt_type);

                apv.create_hyperlink();

                apv = calendarViewMainDAO.create(apv,active_user,selectedCVM);
                this.apv = apv;
                this.add_apt_btn.getScene().getWindow().hide();
            }

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
            • scheduling an appointment outside business hours
         */

            catch (IllegalArgumentException i){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Appointment Add Error!");
                alert.setHeaderText("Try again!");
                alert.setContentText(i.toString());
                alert.showAndWait();
            }

        }

    private String appendZero(String start_hour_str) {
        if(Integer.parseInt(start_hour_str) < 10){
            start_hour_str = "0"+start_hour_str;
        }
        return start_hour_str;
    }

    private String getTimeDateInputStr(String start_hour_str, String start_min_str, String startPmString) {
        String startTime = start_hour_str + ":" + start_min_str + " " + startPmString;
        String date = date_pkr.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyy"));
        String startInput = date+" "+startTime;
        return startInput;
    }

    /**
     * Gets selected cvm.
     *
     * @return the selected cvm
     */
    public Customer_view_main getSelectedCVM() {
        return selectedCVM;
    }

    /**
     * Sets selected cvm.
     *
     * @param selectedCVM the selected cvm
     */
    public void setSelectedCVM(Customer_view_main selectedCVM) {

        this.selectedCVM = selectedCVM;

    }

}
