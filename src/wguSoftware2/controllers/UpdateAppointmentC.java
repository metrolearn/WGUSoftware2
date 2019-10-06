package wguSoftware2.controllers;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import wguSoftware2.models.Active_User;
import wguSoftware2.models.Appoinment_view_main;
import wguSoftware2.models.Customer_view_main;
import wguSoftware2.utils.Database_v3;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UpdateAppointmentC {
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
    private Button add_apt_btn;
    @FXML
    private Database_v3 curr_db;
    @FXML
    private Active_User active_user;
    @FXML
    private ObservableList<Customer_view_main> obv_customer_list;
    private Appoinment_view_main avm = null;
    private Stage curr_stage = null;

    public void initialize(Database_v3 curr_db, Active_User active_user, ObservableList<Customer_view_main> obv_customer_list) {

        this.curr_db = curr_db;
        this.obv_customer_list = obv_customer_list;
        this.active_user = active_user;

    }

    public void set_fields(Appoinment_view_main selectedItem) {


        title_txt.setText(selectedItem.getTitle());
        desc_txt.setText(selectedItem.getDescription());
        location_txt.setText(selectedItem.getLocation());
        contact_txt.setText(selectedItem.getCustomerName());
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

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");

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

        apt_type_cb.setValue(selectedItem.getAppointment_type());




        System.out.println("test");



    }

    public void setStage(Stage addCustomerStage) {

        this.curr_stage = addCustomerStage;

    }

    public Appoinment_view_main get_avm() {

        return this.avm;

    }

    public void UPDT_APT(ActionEvent actionEvent) {
    }
}
