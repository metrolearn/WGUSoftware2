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
import wguSoftware2.utils.Database_v3;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class UpdateAppointmentC {
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

    public void initialize(Database_v3 curr_db, Active_User active_user, ObservableList<Customer_view_main> obv_customer_list) {

        this.curr_db = curr_db;
        this.obv_customer_list = obv_customer_list;
        this.active_user = active_user;
        this.calendarViewMainDAO = new CalendarViewMainDAO(this.curr_db);

    }

    public void set_fields(Appoinment_view_main selectedItem) throws SQLException, ClassNotFoundException {
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

        for (int i = 1; i < 61; i++) {

            if(i % 5 == 0) {
                min_items.add(String.valueOf(i));
            }
        }

        end_hour_cb.setItems(hour_items);
        start_hour_cb.setItems(hour_items);
        end_min_cb.setItems(min_items);
        start_min_cb.setItems(min_items);

        String timeViewStringStart = selectedItem.getTimeViewStringStart();
        String timeViewStringEnd = selectedItem.getTimeViewStringEnd();

        String start_time_values [] = timeViewStringStart.split(":");
        String end_time_values [] = timeViewStringEnd.split(":");

        Integer start_hour = Integer.valueOf(start_time_values[0]);
        Integer end_hour = Integer.valueOf(end_time_values[0]);

        if(start_hour>12){
            start_hour-=12;
        }

        if(end_hour>12){
            end_hour-=12;
        }

        String start_min_str = String.valueOf(start_time_values[1]);
        String end_hour_str = String.valueOf(end_hour);
        String end_min_str = String.valueOf(end_time_values[1]);
        String  start_hour_str = String.valueOf(start_hour);

        end_hour_cb.getSelectionModel().select(end_hour_str);
        end_min_cb.getSelectionModel().select(end_min_str);
        start_hour_cb.getSelectionModel().select(start_hour_str);
         start_min_cb.getSelectionModel().select(start_min_str);
         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/d/yyyy");

        //convert String to LocalDate
        LocalDate localDate = LocalDate.parse(selectedItem.getDateViewString(), formatter);

         date_pkr.setValue(localDate);

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

    public void setStage(Stage addCustomerStage) {

        this.curr_stage = addCustomerStage;

    }

    public Appoinment_view_main get_avm() {

        return this.avm;

    }

    public Appoinment_view_main UPDT_APT(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

//        Integer apt_id = this.apt_id;
        String title = title_txt.getText();
        String description = desc_txt.getText();
        String location = location_txt.getText();
        String contact = contact_txt.getText();

        String apt_type = apt_type_cb.getValue();
        String date = date_pkr.getValue().toString();
        String start_hour = start_hour_cb.getValue();
        String start_min = start_min_cb.getValue();
        String end_hour = end_hour_cb.getValue();
        String end_min = end_min_cb.getValue();
        Boolean s_pm = start_pm.isSelected();
        Boolean e_pm = end_pm.isSelected();

        String start_am_pm_str = "AM";

        if (s_pm){
            Integer start_val = Integer.valueOf(start_hour);
            start_val+=12;
            start_hour = String.valueOf(start_val);
            start_am_pm_str = "PM";
        }

        String end_am_pm_str = "AM";

        if (e_pm){
            Integer end_val = Integer.valueOf(end_hour);
            end_val+=12;
            start_hour = String.valueOf(end_val);
            end_am_pm_str = "PM";
        }

        DateTimeFormatter ts_format = DateTimeFormatter.ofPattern("yyyy-MM-d-h-mm-a");

        String start_date_time = date+"-"+start_hour+"-"+start_min+"-"+start_am_pm_str;
        String end_date_time = date+"-"+end_hour+"-"+end_min+"-"+end_am_pm_str;

        //convert String to LocalDate
        LocalDateTime local_start_date_time = LocalDateTime.parse(start_date_time, ts_format);
        LocalDateTime local_end_date_time = LocalDateTime.parse(end_date_time, ts_format);

        Timestamp start_timestamp = Timestamp.valueOf(local_start_date_time);
        Timestamp end_timestamp = Timestamp.valueOf(local_end_date_time);

        this.calendarViewMainDAO.update(this.avm,this.active_user);

        this.updt_apt_btn.getScene().getWindow().hide();
        return avm;

        }

    public void on_contact_change(){

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Please note: Per management");
        alert.setContentText("If you want to edit the customer name please delete this appointment. " +
                "Create a new customer and a new appointment!");

        alert.showAndWait();

    }


}
