package wguSoftware2.controllers;

import com.sun.xml.internal.bind.v2.runtime.output.StAXExStreamWriterOutput;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import wguSoftware2.DAO.CalendarViewMainDAO;
import wguSoftware2.DAO.CustomerViewMainDAO;
import wguSoftware2.models.Active_User;
import wguSoftware2.models.Appoinment_view_main;
import wguSoftware2.models.Customer;
import wguSoftware2.models.Customer_view_main;
import wguSoftware2.utils.DatabaseMain;
import wguSoftware2.utils.LoginLogger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

/**
 * The type Main window c.
 */
public class MainWindowC {

    private static final boolean TESTING = true;
    /**
     * The Apt id tbl cell.
     */
    @FXML
    public TableColumn<Appoinment_view_main, String> APT_ID_Tbl_Cell;
    /**
     * The Apt date tbl cell.
     */
    @FXML
    public TableColumn<Appoinment_view_main, String> APT_DATE_Tbl_Cell;
    /**
     * The Apt cust tbl cell.
     */
    @FXML
    public TableColumn<Appoinment_view_main, String> APT_CUST_Tbl_Cell;
    /**
     * The Apt title tbl cell.
     */
    @FXML
    public TableColumn<Appoinment_view_main, String> APT_TITLE_Tbl_Cell;
    /**
     * The Apt loc tbl cell.
     */
    @FXML
    public TableColumn<Appoinment_view_main, String> APT_LOC_Tbl_Cell;
    /**
     * The Apt start tbl cell.
     */
    @FXML
    public TableColumn<Appoinment_view_main, String> APT_START_Tbl_Cell;
    /**
     * The Apt end tbl cell.
     */
    @FXML
    public TableColumn<Appoinment_view_main, String> APT_END_Tbl_Cell;
    /**
     * The Apt url tbl cell.
     */
    @FXML
    public TableColumn<Appoinment_view_main, String> APT_URL_Tbl_Cell;
    /**
     * The Art update btn.
     */
    @FXML
    public Button Art_Update_Btn;
    /**
     * The Art delete btn.
     */
    @FXML
    public Button Art_Delete_Btn;
    /**
     * The Art add btn.
     */
    @FXML
    public Button Art_Add_Btn;
    /**
     * The Art mnt filter rad.
     */
    @FXML
    public RadioButton Art_Mnt_filter_rad;
    /**
     * The Art wk filter rad.
     */
    @FXML
    public RadioButton Art_Wk_filter_rad;
    /**
     * The Art all filter rad.
     */
    @FXML
    public RadioButton Art_All_filter_rad;
    /**
     * The Timezone picker.
     */
    @FXML
    public ChoiceBox<String> timezone_picker;
    /**
     * The Art tz filter rad.
     */
    @FXML
    public RadioButton Art_Tz_filter_rad;
    /**
     * The Curr tz lbl.
     */
    @FXML
    public Label curr_tz_lbl;
    /**
     * The Dst cbx.
     */
    @FXML
    public CheckBox dst_cbx;
    @FXML
    private TableView<Appoinment_view_main> apt_tbl;
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
    private List<Customer_view_main> all_customers = null;
    private List<Appoinment_view_main> all_apts = null;
    private ObservableList<Appoinment_view_main> obv_apt_list = null;
    private ObservableList<Appoinment_view_main> obv_apt_buffer_list = null;

    private DatabaseMain curr_db;
    private Active_User active_user;
    private CustomerViewMainDAO cvmDAO;
    private CalendarViewMainDAO avmDAO;

    private Appoinment_view_main avm;

    /**
     * Crt add.
     *
     * @throws IOException the io exception
     */
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
        obv_customer_list.add(addcc.get_cvm());
        customer_tbl.setItems(obv_customer_list);
        customer_tbl.refresh();

    }

    /**
     * Crt update.
     *
     * @throws IOException  the io exception
     * @throws SQLException the sql exception
     */
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
            Customer_view_main selectedItem = getSelectedCVM();
            updateCustomerC.set_fields(selectedItem);
            Stage addCustomerStage = new Stage();
            addCustomerStage.setTitle("Update Customer");
            Scene addPartScene = new Scene(main_root);
            addCustomerStage.setScene(addPartScene);
            updateCustomerC.setStage(addCustomerStage);
            addCustomerStage.showAndWait();
            obv_customer_list.remove(selectedItem);
            selectedItem = updateCustomerC.get_cvm();
            obv_customer_list.add(selectedItem);
            customer_tbl.setItems(obv_customer_list);
            customer_tbl.refresh();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private Customer_view_main getSelectedCVM() {
        return customer_tbl.getSelectionModel().getSelectedItem();
    }

    /**
     * Crt delete.
     *
     * @throws SQLException           the sql exception
     * @throws ClassNotFoundException the class not found exception
     */
    @FXML
    void CRT_DELETE() throws SQLException, ClassNotFoundException {
        Customer_view_main selectedItem = getSelectedCVM();

        cvmDAO.delete(selectedItem);
        obv_customer_list.remove(selectedItem);
        customer_tbl.setItems(obv_customer_list);
        customer_tbl.refresh();

    }

    /**
     * Initialize.
     *
     * @param curr_db     the curr db
     * @param active_user the active user
     * @throws SQLException           the sql exception
     * @throws ClassNotFoundException the class not found exception
     */
    @FXML
    void initialize(DatabaseMain curr_db, Active_User active_user) throws SQLException, ClassNotFoundException {
        this.curr_db = curr_db;
        this.active_user = active_user;
        this.cvmDAO = new CustomerViewMainDAO(curr_db, active_user.getActive_user_name());
        this.avmDAO = new CalendarViewMainDAO(curr_db, active_user);
        this.Art_All_filter_rad.setSelected(true);
        this.curr_tz_lbl.setText(this.active_user.getCurrent_location());

        String sql_stm = "SELECT customer.customerId, customer.customerName, address.address, address.phone\n" +
                "FROM customer\n" +
                "         INNER JOIN address on customer.addressId = address.addressId WHERE active = TRUE;";
        this.curr_db.dbConnect();
        Connection con = this.curr_db.getCon();
        PreparedStatement ps = con.prepareStatement(sql_stm);
        ResultSet rs = this.curr_db.dbExecuteQuery(ps);

        List<Customer_view_main> cvm_list = new ArrayList<>();

        while (rs.next()) {
            Integer customer_id = rs.getInt(1);
            String customer_name = rs.getString(2);
            String address = rs.getString(3);
            String phone = rs.getString(4);
            Customer_view_main cvm = new Customer_view_main(customer_id, customer_name, address, phone);
            cvm_list.add(cvm);

        }

        this.all_customers = cvm_list;
        this.obv_customer_list = FXCollections.observableArrayList(this.all_customers);
        TableView.TableViewSelectionModel<Customer_view_main> selectionModel = this.customer_tbl.getSelectionModel();
        selectionModel.setSelectionMode(SelectionMode.MULTIPLE);
        this.CRT_ID_Tbl_Cell.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.CRT_name_Tbl_Cell.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.CRT_Adress_Tbl_Cell.setCellValueFactory(new PropertyValueFactory<>("address"));
        this.CRT_Phone_Tbl_Cell.setCellValueFactory(new PropertyValueFactory<>("phone"));
        customer_tbl.setItems(obv_customer_list);

        sql_stm = "select * from appointment inner join customer c on appointment.customerId = c.customerId";
        this.curr_db.dbConnect();
        con = this.curr_db.getCon();
        ps = con.prepareStatement(sql_stm, Statement.RETURN_GENERATED_KEYS);
        rs = this.curr_db.dbExecuteQuery(ps);

        List<Appoinment_view_main> avm_list = new ArrayList<>();

        while (rs.next()) {
            Integer appointmentId = rs.getInt("appointmentId");
            String customerName = rs.getString("customerName");
            String title = rs.getString("title");
            String description = rs.getString("description");
            String location = rs.getString("location");
            Timestamp start = rs.getTimestamp("start");
            Timestamp end = rs.getTimestamp("end");
            String url = rs.getString("url");
            String apt_type = rs.getString("type");
            Integer customer_id = rs.getInt("customerId");

            Appoinment_view_main avm = new Appoinment_view_main(active_user, customer_id, appointmentId, customerName, description, title, location, start, end, url, apt_type);
            avm_list.add(avm);


        }

        this.all_apts = avm_list;
        this.obv_apt_list = FXCollections.observableArrayList(this.all_apts);
        this.APT_ID_Tbl_Cell.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.APT_DATE_Tbl_Cell.setCellValueFactory(new PropertyValueFactory<>("dateViewStr"));
        this.APT_CUST_Tbl_Cell.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        this.APT_TITLE_Tbl_Cell.setCellValueFactory(new PropertyValueFactory<>("title"));
        this.APT_LOC_Tbl_Cell.setCellValueFactory(new PropertyValueFactory<>("location"));
        this.APT_START_Tbl_Cell.setCellValueFactory(new PropertyValueFactory<>("startTimeViewStr"));
        this.APT_END_Tbl_Cell.setCellValueFactory(new PropertyValueFactory<>("endTimeViewStr"));
        this.APT_URL_Tbl_Cell.setCellValueFactory(new PropertyValueFactory<>("hl"));
        apt_tbl.setItems(obv_apt_list);
        Set<String> availableZoneIds = ZoneId.getAvailableZoneIds();

        List<String> zoneList = new ArrayList<>(availableZoneIds);
        Collections.sort(zoneList);
        ObservableList<String> stringObservableList = FXCollections.observableArrayList(zoneList);


        this.timezone_picker.setItems(stringObservableList);
        this.timezone_picker.getSelectionModel().select(this.active_user.getTz().getID());


        /*
        Program Constraint: Lambda 2
        G. Write two or more lambda expressions to make your program more
        efficient, justifying the use of each lambda expression with an
        in-line comment.

        This lambda is a nice way to add an action event listener to a combination
        menu.  It makes my program more efficient as there is not a provided action
        listener for combination menus in JavaFX.

         */


        timezone_picker.getSelectionModel()
                .selectedItemProperty()
                .addListener((ObservableValue<? extends String> observable, String oldValue, String newValue)
                        -> this.FILTER_BY_TZ()
                );



        /*
         Program Constraint:
         H. Write code to provide an alert if there is an
         appointment within 15 minutes of the user’s log-in.
        */

        sql_stm = "SELECT * FROM appointment WHERE NOW() BETWEEN start - INTERVAL 15 minute AND start";
        System.out.println(sql_stm);
        this.curr_db.dbConnect();
        con = this.curr_db.getCon();
        ps = con.prepareStatement(sql_stm, Statement.RETURN_GENERATED_KEYS);
        rs = this.curr_db.dbExecuteQuery(ps);



        this.obv_apt_buffer_list = this.obv_apt_list;

        for(Appoinment_view_main avm : obv_apt_buffer_list) {
            long minBetweenApts = ChronoUnit.MINUTES.between(LocalDateTime.now(), avm.getStart_date_time().getZonedLocalDateTime().toLocalDateTime());
            long betweenTest = 15;
            if (betweenTest >= Math.abs(minBetweenApts)) {

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);

                    alert.setTitle("Appointment Reminder!");
                    alert.setHeaderText("You have an appointment IN/AT " + avm.getLocation() + ".\n" +
                            "The appointment starts in less than " + minBetweenApts + " minutes.");
                    alert.showAndWait();


            }
        }



    }


    /**
     * Add apr.
     *
     * @param actionEvent the action event
     * @throws IOException            the io exception
     * @throws SQLException           the sql exception
     * @throws ClassNotFoundException the class not found exception
     */
    public void ADD_APR(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {

        URL add_customer_window = getClass().getClassLoader().getResource("wguSoftware2/views/add_appointment.fxml");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(add_customer_window);
        Parent main_root;
        main_root = loader.load();
        AddAppointmentC addAppointmentC = loader.getController();
        addAppointmentC.initialize(this.curr_db, this.active_user, obv_customer_list);
        Stage addAppointmentStage = new Stage();
        addAppointmentStage.setTitle("Add Appointment");
        Scene addAptScene = new Scene(main_root);
        Customer_view_main selectedCVM = null;
        selectedCVM = customer_tbl.getSelectionModel().getSelectedItem();
        addAppointmentC.setSelectedCVM(selectedCVM);
        if (selectedCVM == null) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Please note: Per management");
            alert.setContentText("If you want create an appointment please select a customer from the customer table," +
                    "press the add button.");
            alert.showAndWait();

        } else {
            addAppointmentStage.setScene(addAptScene);
            addAppointmentC.setStage(addAppointmentStage);
            addAppointmentStage.showAndWait();
            Appoinment_view_main avm = addAppointmentC.get_avm();
            obv_apt_list.add(avm);
            apt_tbl.setItems(obv_apt_list);
            apt_tbl.refresh();
        }

    }

    /**
     * Update apr.
     *
     * @param actionEvent the action event
     */
    public void UPDATE_APR(ActionEvent actionEvent) {

        Appoinment_view_main avm = null;
        try {
            avm = apt_tbl.getSelectionModel().getSelectedItem();
            URL update_apt_window = getClass().getClassLoader().getResource("wguSoftware2/views/update_appointment.fxml");
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(update_apt_window);
            Parent main_root;
            main_root = loader.load();
            UpdateAppointmentC updateAppointmentC = loader.getController();
            updateAppointmentC.initialize(this.curr_db, this.active_user, obv_customer_list);
            Appoinment_view_main selectedItem = getSelectedAVM();
            updateAppointmentC.set_fields(selectedItem);
            Stage updateAptStage = new Stage();
            updateAptStage.setTitle("Update Appointment");
            Scene updateAptScene = new Scene(main_root);
            updateAptStage.setScene(updateAptScene);
            updateAppointmentC.setStage(updateAptStage);
            updateAptStage.showAndWait();
            obv_apt_list.remove(selectedItem);
            selectedItem = updateAppointmentC.get_avm();
            obv_apt_list.add(selectedItem);
            apt_tbl.setItems(obv_apt_list);
            apt_tbl.refresh();


        } catch (Exception e) {
            System.out.println(e);
        }

    }

    private Appoinment_view_main getSelectedAVM() {
        return apt_tbl.getSelectionModel().getSelectedItem();
    }

    /**
     * Delete apr.
     *
     * @param actionEvent the action event
     * @throws SQLException           the sql exception
     * @throws ClassNotFoundException the class not found exception
     */
    public void DELETE_APR(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        Appoinment_view_main selected_apt = getSelectedAVM();
        avmDAO.delete(selected_apt);
        obv_apt_list.remove(selected_apt);
        apt_tbl.setItems(obv_apt_list);
        apt_tbl.refresh();

    }

    /**
     * Filter by month.
     */
    public void FILTER_BY_MONTH() {

        FilteredList<Appoinment_view_main> items = new FilteredList<>(obv_apt_buffer_list);
        apt_tbl.setItems(items);
        Predicate<Appoinment_view_main> predicate = p -> p.getStart_date_time().getZonedLocalDateTime().getMonth() == LocalDate.now().getMonth();

        items.setPredicate(predicate);
        obv_apt_buffer_list = obv_apt_list;
        this.Art_Wk_filter_rad.setSelected(false);
        this.Art_All_filter_rad.setSelected(false);


    }

    /**
     * Filter by week.
     */
    public void FILTER_BY_WEEK() {



        FilteredList<Appoinment_view_main> items = new FilteredList<>(obv_apt_buffer_list);
        apt_tbl.setItems(items);
        Predicate<Appoinment_view_main> predicate = p -> p.getStart_date_time().getZonedLocalDateTime().get(ChronoField.ALIGNED_WEEK_OF_MONTH) == LocalDate.now().get(ChronoField.ALIGNED_WEEK_OF_MONTH);

        items.setPredicate(predicate);
        obv_apt_buffer_list = obv_apt_list;
        this.Art_Mnt_filter_rad.setSelected(false);
        this.Art_All_filter_rad.setSelected(false);


    }

    /**
     * Filter by all.
     */
    public void FILTER_BY_ALL() {

        FilteredList<Appoinment_view_main> items = new FilteredList<>(obv_apt_buffer_list);
        apt_tbl.setItems(items);
        items.setPredicate(null);
        this.Art_Mnt_filter_rad.setSelected(false);
        this.Art_Wk_filter_rad.setSelected(false);

    }

    /**
     * Filter by tz.
     */
    public void FILTER_BY_TZ() {

        ZoneId zid = ZoneId.of(timezone_picker.getValue());

        for (Appoinment_view_main avm : obv_apt_list) {
            avm.getStart_date_time().setMenuZone(zid);
            avm.getEnd_date_time().setMenuZone(zid);
            avm.setStartTimeViewStr(avm.getStart_date_time().getSimpleDateMenuStringByTZ());
            avm.setEndTimeViewStr(avm.getEnd_date_time().getSimpleDateMenuStringByTZ());
        }

        apt_tbl.setItems(obv_apt_list);
        apt_tbl.refresh();


    }

    /**
     * On dst cbx action.
     *
     * @param actionEvent the action event
     */
    public void on_dst_cbx_action(ActionEvent actionEvent) {

        for (Appoinment_view_main avm : obv_apt_list) {

            avm.setStartTimeViewStr(avm.getStart_date_time().getSimpleTimeMenuZDTMinusDST());
            avm.setEndTimeViewStr(avm.getEnd_date_time().getSimpleTimeMenuZDTMinusDST());


            if (!dst_cbx.isSelected()) {
                avm.setStartTimeViewStr(avm.getStart_date_time().getSimpleTimeMenuZDTNoDST());
                avm.setEndTimeViewStr(avm.getEnd_date_time().getSimpleTimeMenuZDTNoDST());
            } else {
                avm.setStartTimeViewStr(avm.getStart_date_time().getSimpleTimeMenuZDTMinusDST());
                avm.setEndTimeViewStr(avm.getEnd_date_time().getSimpleTimeMenuZDTMinusDST());
            }
        }


        apt_tbl.setItems(obv_apt_list);
        apt_tbl.refresh();

    }

    /**
     * Directory chooser.
     *
     * @param defaultFileName the default file name
     * @param items           the items
     */
    public void directoryChooser(String defaultFileName, ObservableList<Object> items) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Choose location To Save Report");
        chooser.setInitialFileName(defaultFileName);
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        chooser.getExtensionFilters().add(extFilter);
        chooser.setSelectedExtensionFilter(extFilter);

        File selectedFile = null;
        selectedFile = chooser.showSaveDialog(null);


        File file = new File(String.valueOf(selectedFile));
        PrintWriter outFile = null;
        try {
            outFile = new PrintWriter(file);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        for (int i = 0; i < items.size(); i++) {
            outFile.println(items.get(i).toString());
        }
        outFile.close();
    }

    /**
     * Save report apps by month.
     *
     * @param actionEvent the action event
     * @throws SQLException           the sql exception
     * @throws ClassNotFoundException the class not found exception
     */
    public void saveReportAppsByMonth(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        ObservableList<Object> temp = FXCollections.observableArrayList();



        String line1 = "###################################################";
        String line2 = "######### Appointments Types by Month Report ######";
        temp.add(line1);
        temp.add(line2);
        temp.add("\n");

        String sql_stmt = "SELECT MONTHNAME(start) as 'Month', count(appointmentId) as count , appointment.type from appointment GROUP BY MONTHNAME(start)";
        this.curr_db.dbConnect();
        Connection con = this.curr_db.getCon();
        PreparedStatement ps = con.prepareStatement(sql_stmt, Statement.RETURN_GENERATED_KEYS);
        ResultSet rs = ps.executeQuery();

        temp.add("Month, count, type");
        String buffer ="";
        while (rs.next()){
            buffer += rs.getString(1);
            buffer += ", ";
            buffer += rs.getString(2);
            buffer += ", ";
            buffer += rs.getString(3);
            temp.add(buffer);
            buffer = "";
        }


        directoryChooser("AppointmentsTypeByMonth.txt", temp);

    }

    /**
     * Save report apps by consultant.
     *
     * @param actionEvent the action event
     * @throws SQLException           the sql exception
     * @throws ClassNotFoundException the class not found exception
     */
    public void saveReportAppsByConsultant(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        ObservableList<Object> temp = FXCollections.observableArrayList();

        String line1 = "###################################################";
        String line2 = "####### Report Schedule By Consultant #########";

        temp.add(line1);
        temp.add(line2);
        temp.add("\n");

        String sql_stmt = "SELECT contact as Consultant, LOCALTIMESTAMP(start) as \"Appointment Start Time\" , appointment.type as \"Appointment Type\" from appointment GROUP BY start, contact order by contact";
        this.curr_db.dbConnect();
        Connection con = this.curr_db.getCon();
        PreparedStatement ps = con.prepareStatement(sql_stmt, Statement.RETURN_GENERATED_KEYS);
        ResultSet rs = ps.executeQuery();

        temp.add("Consultant, Appointment Start Time, Appointment Type");
        String buffer ="";
        while (rs.next()){
            buffer += rs.getString(1);
            buffer += ", ";
            buffer += rs.getString(2);
            buffer += ", ";
            buffer += rs.getString(3);
            temp.add(buffer);
            buffer = "";
        }

        directoryChooser("ReportScheduleByConsultant.txt", temp);

    }


    /**
     * Save report all apps.
     *
     * @param actionEvent the action event
     */
    public void saveReportAllApps(ActionEvent actionEvent) {

        ObservableList<Object> temp = FXCollections.observableArrayList();

        String line1 = "###################################################";
        String line2 = "########### All Appointments Report ###############";

        temp.add(line1);
        temp.add(line2);
        temp.add("\n");

        Integer nth = 1;
        for (Object o : apt_tbl.getItems()) {
            String avmStr;
            avmStr = o.toString();
            String str = "Appointment ** " + nth.toString() + " **: " + avmStr;
            temp.add(str);
            nth += 1;
        }

        directoryChooser("AllAppointmentsReport.txt", temp);


    }




}
