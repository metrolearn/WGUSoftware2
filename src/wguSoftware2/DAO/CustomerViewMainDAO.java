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

    private Integer country_id;
    private Country country = null;
    private Integer city_id;
    private City city = null;
    private Address address = null;

    private Timestamp sql_create_now_ts = null;


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
        sql_create_now_ts = Timestamp.valueOf(current_time_ldt);

        // check if country already exists .

        String sql_country_exists = "select countryId from country where country = ?";
        this.curr_db.dbConnect();
        Connection con = this.curr_db.getCon();
        PreparedStatement ps = con.prepareStatement(sql_country_exists);
        ps.setString(1,country_name);
        ResultSet rs = curr_db.dbExecuteQuery(ps);

        if(rs.first()) {
            // getting existing country id
            ResultSetMetaData rsmd = rs.getMetaData();
            String name = rsmd.getColumnName(1);
            String type = rsmd.getColumnTypeName(1);
            this.country_id = rs.getInt(1);



        }else
            {
            // country not found
            String sql_stmt = "INSERT INTO country (country, createDate, createdBy, lastUpdate, lastUpdateBy)" +
                    " VALUES (?,?,?,?,?);";

            this.curr_db.dbConnect();
            con = null;
            con = this.curr_db.getCon();
            ps = null;
            ps = con.prepareStatement(sql_stmt,Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,country_name);
            ps.setTimestamp(2, sql_create_now_ts);
            ps.setString(3,this.active_user_name);
            ps.setTimestamp(4, sql_create_now_ts);
            ps.setString(5,this.active_user_name);
            rs = curr_db.dbExecuteUpdate(ps);

            if (rs.next()){
                this.country_id = rs.getInt("GENERATED_KEY");
                this.country = new Country(country_id);
                this.country.setCountry_name(country_name);
            }


        }




        this.city = new City(city_name,this.country_id);

        String sql_city_stm = "INSERT INTO city " +
                "(city, countryId, createDate, createdBy, lastUpdate, lastUpdateBy) " +
                "VALUES (?,?,?,?,?,?)";

        this.curr_db.dbConnect();
        con = this.curr_db.getCon();
        ps = null;
        ps = con.prepareStatement(sql_city_stm,Statement.RETURN_GENERATED_KEYS);

        ps.setString(1,this.city.getCity_name());
        ps.setInt(2,this.city.getCountry_id());
        ps.setTimestamp(3,sql_create_now_ts);
        ps.setString(4,active_user_name);
        ps.setTimestamp(5,sql_create_now_ts);
        ps.setString(6,active_user_name);
        rs = null;
        rs = curr_db.dbExecuteUpdate(ps);

        if (rs.next()){
            this.country = new Country(rs.getInt("GENERATED_KEY"));
            this.country.setCountry_name(country_name);
        }






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

//    private void exe_sql_create(String sql_smt) throws SQLException {
//        execute_sql_stmt(sql_smt);
//        set_last_insert_obj_id();
//    }

//    @FXML
//    private void set_last_insert_obj_id() throws SQLException {
//        this.sql_smt = "SELECT LAST_INSERT_ID()";
//        while (this.rs.next())
//            this.object_id_buffer = this.rs.getInt(1);
//    }

//    @FXML
//    private void execute_sql_stmt(String sql_smt) {
//        ResultSet resultSet = null;
////        try {
////            resultSet = curr_db.get_mysql_resultSet(sql_smt);
////        } catch (SQLException e) {
////            e.printStackTrace();
////        }
////        this.rs = resultSet;
////    }
//
//        }
    }
