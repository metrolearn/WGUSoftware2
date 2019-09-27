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
import javafx.util.StringConverter;
import wguSoftware2.DAO.CalendarViewMainDAO;
import wguSoftware2.models.Active_User;
import wguSoftware2.models.Appoinment_view_main;
import wguSoftware2.models.Customer_view_main;
import wguSoftware2.utils.Database_v3;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.TimeZone;

public class AddAppointmentC {

    @FXML
    public ComboBox<String> start_hour_cb;
    @FXML
    public ComboBox<String> start_min_cb;
    @FXML
    public ComboBox<String> end_min_cb;
    @FXML
    public ComboBox<String> end_hour_cb;
    @FXML
    public Label start_date_time_lbl;
    @FXML
    public Label end_date_time_lbl;
    @FXML
    public Label blank_lbl;
    @FXML
    public Button add_apt_btn;
    @FXML
    public ComboBox apt_type_cb;
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
    private CalendarViewMainDAO calendarViewMainDAO;

    @FXML
    private Appoinment_view_main apv;


    public static void get_cvm() {
    }

    public void initialize(Database_v3 curr_db, Active_User active_user, ObservableList<Customer_view_main> obv_customer_list) {

        ObservableList<String> hour_items = end_hour_cb.getItems();
        ObservableList<String> end_minItems =  end_min_cb.getItems();

        ObservableList<String> min_items = start_hour_cb.getItems();
        ObservableList<String> start_minItems = start_min_cb.getItems();

        for (int i = 1; i < 13 ; i++) {
            hour_items.add(String.valueOf(i));

        }

        for (int i = 1; i < 61; i++) {

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


        //unfocus pathField
        this.blank_lbl.setFocusTraversable(true);


    }


    public void setStage(Stage addAppointmentStage) {
    }

    public void ADD_APT(ActionEvent actionEvent) {

        String title = this.title_txt.getText();
        String description = this.desc_txt.getText();
        String location = this.location_txt.getText();
        String contact = this.contact_txt.getText();
        String apt_type = this.apt_type_cb.getTypeSelector();
        StringConverter<LocalDate> converter = this.date_pkr.getConverter();
        String start_hour_str = this.start_hour_cb.getValue();
        String start_min_str = this.start_min_cb.getValue();
        boolean start_pm = this.start_pm.isArmed();
        String end_hour_str = this.start_hour_cb.getValue();
        String end_min_str = this.start_min_cb.getValue();
        boolean end_pm = this.end_pm.isArmed();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss a z");
        TimeZone tz = TimeZone.getDefault();
        String zoneId = tz.toZoneId().toString();

        LocalDateTime ldt = LocalDateTime.parse(converter.toString());

        String year = String.valueOf(ldt.getYear());
        String month = String.valueOf(ldt.getMonth());
        String day = String.valueOf(ldt.getMonth());
        String start_hour = String.valueOf(ldt.getHour());
        String start_min = String.valueOf(ldt.getMinute());
        String start_seconds = "00";
        String am_pm = "AM";

        if (start_pm){
            am_pm = "PM";
        }


        String zone_offset = String.valueOf(ZonedDateTime.now().getOffset().getTotalSeconds());

        ZonedDateTime dateTime = ZonedDateTime.parse(year + "-"
                + month + "-"
                + day + " "
                + start_hour
                + ":"
                + start_min
                + ":"
                + start_seconds
                + " " + am_pm + " " + zone_offset, formatter);


//
//        this.apv = new Appoinment_view_main(
//                title,description,location,contact,apt_type,converter.toString(),)

        System.out.println("test");

    }
}
