package wguSoftware2.DAO;

import javafx.fxml.FXML;
import wguSoftware2.models.Address;
import wguSoftware2.models.City;
import wguSoftware2.models.Country;
import wguSoftware2.models.Customer_view_main;
import wguSoftware2.utils.Database_v3;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

public class CustomerViewMainDAO {


    private String sql_stm = null;
    private ResultSet rs = null;
    private Database_v3 curr_db = null;
    private Customer_view_main cvm = null;
    private String active_user_name = null;
    private String sql_smt = null;
    private Integer object_id_buffer = null;

    private Country country = null;
    private City city = null;
    private Address address = null;

    public CustomerViewMainDAO(Database_v3 curr_db, String active_user_name) {
        this.curr_db = curr_db;
        this.active_user_name = active_user_name;
    }


    public void create(Customer_view_main customer_view_main) throws SQLException, ClassNotFoundException {

        String customer_name = customer_view_main.getName();
        String address = customer_view_main.getAddress();
        String alt_address = customer_view_main.getAlt_address();
        String city_name = customer_view_main.getCity_name();
        String zip = customer_view_main.getZip();
        String country_name = customer_view_main.getCountry_name();
        String phone = customer_view_main.getPhone();
        LocalDateTime current_time_ldt = ZonedDateTime.now().toLocalDateTime();

        String sql_stmt = "INSERT INTO country (country, createDate, createdBy, lastUpdate, lastUpdateBy)" +
                " VALUES (?,?,?,?,?);";

        this.curr_db.dbConnect();
        Connection con = this.curr_db.getCon();
        PreparedStatement ps = con.prepareStatement(sql_stmt);
        ps.setString(1,country_name);
        ps.setTimestamp(2, Timestamp.valueOf(current_time_ldt));
        ps.setString(3,this.active_user_name);
        ps.setTimestamp(4,Timestamp.valueOf(current_time_ldt));
        ps.setString(5,this.active_user_name);

        Integer r_val = curr_db.dbExecuteUpdate(ps);

        if(r_val == 1){
            System.out.println("Successfully added country.");
        }

        curr_db.dbConnect();
        con = curr_db.getCon();
        ps = con.prepareStatement("VALUES('MySQL last_insert_id');");
        ResultSet rs = curr_db.dbExecuteQuery(ps);
        this.country = new Country(country_name);

        while (rs.next()){
          this.country.setCountry_id(rs.getInt(1));
        }

        System.out.println("test");

//        this.sql_smt = "INSERT INTO `country` " +
//                "(`country`, `createDate`, `createdBy`, `lastUpdate`, `lastUpdateBy`) " +
//                "VALUES ('"
//                + country_name + "', '"
//                + current_time_ldt + "', '"
//                + this.active_user_name + "', '"
//                + current_time_ldt + "', '"
//                + this.active_user_name + "');";
//
//        exe_sql_create(this.sql_smt);
//
//        this.country = new Country(this.object_id_buffer, country_name);
//
//        String country_id_str = String.valueOf(this.country.getCountry_id());
//
//        this.sql_smt = "INSERT INTO `city` " +
//                "(`city`, `countryId`, `createDate`, `createdBy`, `lastUpdate`, `lastUpdateBy`) " +
//                "VALUES ('"
//                + city_name + "', "
//                + country_id_str + ", '"
//                + current_time_ldt + "', '"
//                + this.active_user_name + "', '"
//                + current_time_ldt + "', '"
//                + this.active_user_name + "');";
//
//        exe_sql_create(this.sql_smt);
//
//        this.city = new City(this.object_id_buffer, city_name, this.country.getCountry_id());
//
//        this.sql_smt = "INSERT INTO `address` " +
//                "(`address`, `address2`, `cityId`, `postalCode`, `phone`, `createDate`, `createdBy`," +
//                " `lastUpdate`, `lastUpdateBy`) " +
//                "VALUES ('"
//                + address + ", '"
//                + alt_address + "', "
//                + this.city.getCity_id() + " , '"
//                + zip + "', '"
//                + phone + "', '"
//                + current_time_ldt + "', '"
//                + active_user_name + "', '"
//                + current_time_ldt + "', '"
//                + active_user_name + "');";
//
//        exe_sql_create(this.sql_smt);
//
//        this.address = new Address(address, alt_address, city.getCity_id(), zip);
//
//
//        this.sql_smt = "INSERT INTO `customer` " +
//                "(`customerName`, `addressId`, `active`, `createDate`, `createdBy`, " +
//                "`lastUpdate`, `lastUpdateBy`) " +
//                "VALUES ('"
//                + customer_name + "', "
//                + this.address.getAddress_id() + ", "
//                + true + ", '"
//                + current_time_ldt + "', '"
//                + this.active_user_name + "', '"
//                + current_time_ldt + "', '"
//                + this.active_user_name + "');";
//
//        exe_sql_create(this.sql_smt);


    }


    ;

    public void get() {
    }

    ;

    public void update() {
    }

    ;

    public void delete() {
    }

    ;

    private void exe_sql_create(String sql_smt) throws SQLException {
        execute_sql_stmt(sql_smt);
        set_last_insert_obj_id();
    }

    @FXML
    private void set_last_insert_obj_id() throws SQLException {
        this.sql_smt = "SELECT LAST_INSERT_ID()";
        while (this.rs.next())
            this.object_id_buffer = this.rs.getInt(1);
    }

    @FXML
    private void execute_sql_stmt(String sql_smt) {
        ResultSet resultSet = null;
//        try {
//            resultSet = curr_db.get_mysql_resultSet(sql_smt);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        this.rs = resultSet;
//    }

        }
    }
