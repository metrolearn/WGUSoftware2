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
import org.jetbrains.annotations.NotNull;
import wguSoftware2.DAO.CalendarViewMainDAO;
import wguSoftware2.models.Active_User;
import wguSoftware2.models.Appoinment_view_main;
import wguSoftware2.models.Customer_view_main;
import wguSoftware2.models.MyDateTime;
import wguSoftware2.utils.Database_v3;

import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.*;
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
    Customer_view_main selectedCVM = null;
    @FXML
    private Appoinment_view_main apv = null;
    @FXML
    private Active_User active_user = null;
    @FXML
    private Database_v3 curr_db = null;

    @FXML
    public Appoinment_view_main get_avm() {

        return this.apv;

    }

    public ComboBox<String> getStart_hour_cb() {
        return start_hour_cb;
    }

    public void setStart_hour_cb(ComboBox<String> start_hour_cb) {
        this.start_hour_cb = start_hour_cb;
    }

    public ComboBox<String> getStart_min_cb() {
        return start_min_cb;
    }

    public void setStart_min_cb(ComboBox<String> start_min_cb) {
        this.start_min_cb = start_min_cb;
    }

    public ComboBox<String> getEnd_min_cb() {
        return end_min_cb;
    }

    public void setEnd_min_cb(ComboBox<String> end_min_cb) {
        this.end_min_cb = end_min_cb;
    }

    public ComboBox<String> getEnd_hour_cb() {
        return end_hour_cb;
    }

    public void setEnd_hour_cb(ComboBox<String> end_hour_cb) {
        this.end_hour_cb = end_hour_cb;
    }

    public Label getStart_date_time_lbl() {
        return start_date_time_lbl;
    }

    public void setStart_date_time_lbl(Label start_date_time_lbl) {
        this.start_date_time_lbl = start_date_time_lbl;
    }

    public Label getEnd_date_time_lbl() {
        return end_date_time_lbl;
    }

    public void setEnd_date_time_lbl(Label end_date_time_lbl) {
        this.end_date_time_lbl = end_date_time_lbl;
    }

    public Label getBlank_lbl() {
        return blank_lbl;
    }

    public void setBlank_lbl(Label blank_lbl) {
        this.blank_lbl = blank_lbl;
    }

    public Button getAdd_apt_btn() {
        return add_apt_btn;
    }

    public void setAdd_apt_btn(Button add_apt_btn) {
        this.add_apt_btn = add_apt_btn;
    }

    public ComboBox<String> getApt_type_cb() {
        return apt_type_cb;
    }

    public void setApt_type_cb(ComboBox<String> apt_type_cb) {
        this.apt_type_cb = apt_type_cb;
    }

    public ResourceBundle getResources() {
        return resources;
    }

    public void setResources(ResourceBundle resources) {
        this.resources = resources;
    }

    public URL getLocation() {
        return location;
    }

    public void setLocation(URL location) {
        this.location = location;
    }

    public TextField getTitle_txt() {
        return title_txt;
    }

    public void setTitle_txt(TextField title_txt) {
        this.title_txt = title_txt;
    }

    public TextField getContact_txt() {
        return contact_txt;
    }

    public void setContact_txt(TextField contact_txt) {
        this.contact_txt = contact_txt;
    }

    public DatePicker getDate_pkr() {
        return date_pkr;
    }

    public void setDate_pkr(DatePicker date_pkr) {
        this.date_pkr = date_pkr;
    }

    public RadioButton getStart_pm() {
        return start_pm;
    }

    public void setStart_pm(RadioButton start_pm) {
        this.start_pm = start_pm;
    }

    public MenuButton getStart_hour() {
        return start_hour;
    }

    public void setStart_hour(MenuButton start_hour) {
        this.start_hour = start_hour;
    }

    public RadioButton getEnd_pm() {
        return end_pm;
    }

    public void setEnd_pm(RadioButton end_pm) {
        this.end_pm = end_pm;
    }

    public TextField getDesc_txt() {
        return desc_txt;
    }

    public void setDesc_txt(TextField desc_txt) {
        this.desc_txt = desc_txt;
    }

    public TextField getLocation_txt() {
        return location_txt;
    }

    public void setLocation_txt(TextField location_txt) {
        this.location_txt = location_txt;
    }

    public CalendarViewMainDAO getCalendarViewMainDAO() {
        return calendarViewMainDAO;
    }

    public void setCalendarViewMainDAO(CalendarViewMainDAO calendarViewMainDAO) {
        this.calendarViewMainDAO = calendarViewMainDAO;
    }

    public Customer_view_main getSelectedCVM() {
        return selectedCVM;
    }

    public Appoinment_view_main getApv() {
        return apv;
    }

    public void setApv(Appoinment_view_main apv) {
        this.apv = apv;
    }

    public Active_User getActive_user() {
        return active_user;
    }

    public void setActive_user(Active_User active_user) {
        this.active_user = active_user;
    }

    public Database_v3 getCurr_db() {
        return curr_db;
    }

    public void setCurr_db(Database_v3 curr_db) {
        this.curr_db = curr_db;
    }

    public void initialize(Database_v3 curr_db, Active_User active_user, ObservableList<Customer_view_main> obv_customer_list) {

        this.active_user = active_user;
        this.curr_db = curr_db;
        this.calendarViewMainDAO = new CalendarViewMainDAO(curr_db);

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

        date_pkr.setDayCellFactory(picker -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate today = LocalDate.now();

                setDisable(empty || date.compareTo(today) < 0 );
            }
        });

    }

    public void setStage(Stage addAppointmentStage) {
    }

    public void ADD_APT(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        String title = this.title_txt.getText();
        String description = this.desc_txt.getText();
        String location = this.location_txt.getText();
        String contact = this.contact_txt.getText();
        String apt_type = this.apt_type_cb.getValue();
        String start_hour_str = this.start_hour_cb.getValue();
        String start_min_str = this.start_min_cb.getValue();
        boolean start_pm = this.start_pm.isArmed();
        String end_hour_str = this.end_hour_cb.getValue();
        String end_min_str = this.end_min_cb.getValue();
        boolean end_pm = this.end_pm.isArmed();
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

        Timestamp startTS = new MyDateTime(startInput,this.active_user).getUTCTimeStamp();
        Timestamp endTS = new MyDateTime(endInput,this.active_user).getUTCTimeStamp();
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

    @NotNull
    private String getTimeDateInputStr(String start_hour_str, String start_min_str, String startPmString) {
        String startTime = start_hour_str + ":" + start_min_str + " " + startPmString;
        String date = date_pkr.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyy"));
        String startInput = date+" "+startTime;
        return startInput;
    }

    @NotNull
    private String appendZero(String start_hour_str) {
        if(Integer.parseInt(start_hour_str) < 10){
            start_hour_str = "0"+start_hour_str;
        }
        return start_hour_str;
    }

    public void setSelectedCVM(Customer_view_main selectedCVM) {

        this.selectedCVM = selectedCVM;

    }

}
