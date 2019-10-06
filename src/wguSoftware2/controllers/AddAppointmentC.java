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
import java.sql.SQLException;
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


    public void initialize(Database_v3 curr_db, Active_User active_user, ObservableList<Customer_view_main> obv_customer_list) {

        this.active_user = active_user;
        this.curr_db = curr_db;
        this.calendarViewMainDAO = new CalendarViewMainDAO(curr_db);

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
        StringConverter<LocalDate> converter = this.date_pkr.getConverter();
        String start_hour_str = this.start_hour_cb.getValue();
        String start_min_str = this.start_min_cb.getValue();
        boolean start_pm = this.start_pm.isArmed();
        String end_hour_str = this.end_hour_cb.getValue();
        String end_min_str = this.end_min_cb.getValue();
        boolean end_pm = this.end_pm.isArmed();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss a z");
        TimeZone tz = TimeZone.getDefault();
        String zoneId = tz.toZoneId().toString();

        if (this.start_pm.isSelected()){
            int i = 12 + Integer.parseInt(start_hour_str);
            start_hour_str = String.valueOf(i);
        }

        if (this.end_pm.isSelected()){
            int i = 12 + Integer.parseInt(end_hour_str);
            end_hour_str = String.valueOf(i);
        }
        LocalDateTime start_ldt =  this.date_pkr.getValue().atStartOfDay().
                with(LocalTime.of(Integer.parseInt(start_hour_str),Integer.parseInt(start_min_str)));
        LocalDateTime end_ldt = this.date_pkr.getValue().atStartOfDay().
                with(LocalTime.of(Integer.parseInt(end_hour_str),Integer.parseInt(end_min_str)));
        ZoneId zone = ZonedDateTime.now().getZone();
        ZonedDateTime start_ztd = start_ldt.atZone(zone);
        ZonedDateTime end_ztd = end_ldt.atZone(zone);



        this.apv = new Appoinment_view_main(
                title,description,location,contact,apt_type,start_ztd,end_ztd);

        this.apv.setCustomerID(selectedCVM.getId());
        this.apv.setUserID(active_user.getActive_user_id());
        try {
            this.apv = calendarViewMainDAO.create(this.apv,active_user,selectedCVM);
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        this.add_apt_btn.getScene().getWindow().hide();



    }

    public void setSelectedCVM(Customer_view_main selectedCVM) {

        this.selectedCVM = selectedCVM;

    }


}
