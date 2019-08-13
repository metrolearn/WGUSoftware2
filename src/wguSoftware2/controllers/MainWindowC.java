package wguSoftware2.controllers;

        import javafx.collections.FXCollections;
        import javafx.collections.ObservableArray;
        import javafx.collections.ObservableList;
        import javafx.fxml.FXML;
        import javafx.scene.control.Button;
        import javafx.scene.control.SelectionMode;
        import javafx.scene.control.TableColumn;
        import javafx.scene.control.TableView;
        import javafx.scene.control.cell.PropertyValueFactory;
        import javafx.scene.layout.AnchorPane;
        import wguSoftware2.models.Address;
        import wguSoftware2.models.Customer;
        import wguSoftware2.models.Customer_view_main;
        import wguSoftware2.utils.Database;

        import java.sql.SQLException;
        import java.util.List;

public class MainWindowC {

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

    @FXML
    void CRT_ADD() {



    }

    @FXML
    void CRT_UPDATE() {

    }

    @FXML
    void CRT_DELETE() {

    }

    private ObservableList<Customer_view_main> obv_customer_list = null;
    private List<Customer_view_main> all_customers;
    private Database d;
    

    @FXML
    void initialize(Database curr_db) throws SQLException {
        this.d = curr_db;
        this.all_customers = d.getAllCustomersFromDB();
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
