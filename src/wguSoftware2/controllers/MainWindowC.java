package wguSoftware2.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import wguSoftware2.models.Active_User;
import wguSoftware2.models.Customer;
import wguSoftware2.models.Customer_view_main;
import wguSoftware2.utils.Database;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;

public class MainWindowC {

    private static final boolean TESTING = true;
    @FXML
    private TableView<Customer_view_main> customer_tbl;

    @FXML
    private TableColumn<Customer, Integer> CRT_ID_Tbl_Cell;

    @FXML
    private TableColumn<Customer, String> CRT_name_Tbl_Cell;

    @FXML
    private TableColumn<Customer, String> CRT_Adress_Tbl_Cell;

    @FXML
    private TableColumn<Customer, String> CRT_Phone_Tbl_Cell;

    @FXML
    private Button CRT_Delete_Btn;

    @FXML
    private AnchorPane customer_records_lbl;


    @FXML
    private Button Crt_Add_Btn;

    @FXML
    private Button CRT_Update_Btn;

    private ObservableList<Customer_view_main> obv_customer_list = null;
    private List<Customer_view_main> all_customers;
    private Database curr_db;
    private Active_User active_user;

    @FXML
    void CRT_ADD() throws IOException {
        URL add_customer_window = getClass().getClassLoader().getResource("wguSoftware2/views/add_customer.fxml");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(add_customer_window);
        Parent main_root;
        main_root = loader.load();
        AddCustomerC addcc = loader.getController();
        addcc.initialize(this.curr_db, this.active_user, obv_customer_list);
        Stage addCustomerStage = new Stage();
        addCustomerStage.setTitle("Add Customer");
        Scene addPartScene = new Scene(main_root);
        addCustomerStage.setScene(addPartScene);
        addcc.setStage(addCustomerStage);
        addCustomerStage.showAndWait();
        customer_tbl.setItems(obv_customer_list);
        customer_tbl.refresh();


    }

    @FXML
    void CRT_UPDATE() throws IOException, SQLException {

        Customer_view_main cmv = null;
        try {
            cmv = customer_tbl.getSelectionModel().getSelectedItem();
            URL add_customer_window = getClass().getClassLoader().getResource("wguSoftware2/views/update_customer.fxml");
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(add_customer_window);
            Parent main_root;
            main_root = loader.load();
            UpdateCustomerC updateCustomerC = loader.getController();
            updateCustomerC.initialize(this.curr_db, this.active_user, obv_customer_list);
            updateCustomerC.set_fields(cmv);
            Stage addCustomerStage = new Stage();
            addCustomerStage.setTitle("Update Customer");
            Scene addPartScene = new Scene(main_root);
            addCustomerStage.setScene(addPartScene);
            updateCustomerC.setStage(addCustomerStage);
            addCustomerStage.showAndWait();
            customer_tbl.setItems(obv_customer_list);
            customer_tbl.refresh();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    void CRT_DELETE() {
        Customer_view_main selectedItem = customer_tbl.getSelectionModel().getSelectedItem();
        obv_customer_list.remove(selectedItem);
        customer_tbl.setItems(obv_customer_list);
        customer_tbl.refresh();

    }


    @FXML
    void initialize(Database curr_db, Active_User active_user) throws SQLException {
        this.curr_db = curr_db;
        this.active_user = active_user;
        this.all_customers = this.curr_db.getAllCustomersFromDB();
        //get all customers //this.all_customers
        // add to obv_list //this.obv_customer_list FXCollections.observableArrayList(this.allParts);
        this.obv_customer_list = FXCollections.observableArrayList(this.all_customers);
        TableView.TableViewSelectionModel<Customer_view_main> selectionModel = this.customer_tbl.getSelectionModel();
        selectionModel.setSelectionMode(SelectionMode.MULTIPLE);
        this.CRT_ID_Tbl_Cell.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.CRT_name_Tbl_Cell.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.CRT_Adress_Tbl_Cell.setCellValueFactory(new PropertyValueFactory<>("address"));
        this.CRT_Phone_Tbl_Cell.setCellValueFactory(new PropertyValueFactory<>("phone"));

        customer_tbl.setItems(obv_customer_list);
    }
}
