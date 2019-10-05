package wguSoftware2.controllers;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import wguSoftware2.models.Active_User;
import wguSoftware2.models.Appoinment_view_main;
import wguSoftware2.models.Customer_view_main;
import wguSoftware2.utils.Database_v3;

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
    private ComboBox start_hour_cb;
    @FXML
    private ComboBox start_min_cb;
    @FXML
    private RadioButton start_pm;
    @FXML
    private ComboBox end_min_cb;
    @FXML
    private ComboBox end_hour_cb;
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

        ObservableList<String> apt_types = null;
        apt_types = FXCollections.observableArrayList();

        apt_types.add("Initial");
        apt_types.add("Sales");
        apt_types.add("Follow up");


        this.apt_type_cb.getSelectionModel().select(selectedItem.getAppointment_type());


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

        end_hour_cb.getSelectionModel().select(3);
        end_min_cb.getSelectionModel().select(3);
        start_hour_cb.getSelectionModel().select(3);
        end_hour_cb.getSelectionModel().select(3);



    }

    public void setStage(Stage addCustomerStage) {

        this.curr_stage = addCustomerStage;

    }

    public Appoinment_view_main get_avm() {

        return this.avm;

    }
}
