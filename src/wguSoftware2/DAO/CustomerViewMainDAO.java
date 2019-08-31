package wguSoftware2.DAO;

import wguSoftware2.models.Address;
import wguSoftware2.models.City;
import wguSoftware2.models.Country;
import wguSoftware2.models.Customer_view_main;
import wguSoftware2.utils.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZonedDateTime;

public class CustomerViewMainDAO {


    private String sql_stm = null;
    private ResultSet rs = null;
    private Database dbc = null;
    private Customer_view_main cvm = null;
    private String active_user_name = null;
    private String sql_smt = null;
    private Integer object_id_buffer = null;

    private Country country = null;
    private City city = null;
    private Address address = null;

    public CustomerViewMainDAO(Database dbc, String active_user_name) {
        this.dbc = dbc;
        this.active_user_name = active_user_name;
    }


    public void create(Customer_view_main customer_view_main) throws SQLException {

        String customer_name = customer_view_main.getName();
        String address = customer_view_main.getAddress();
        String alt_address = customer_view_main.getAlt_address();
        String city_name = customer_view_main.getCity_name();
        String zip = customer_view_main.getZip();
        String country_str = customer_view_main.getCountry_name();
        String phone = customer_view_main.getPhone();
        String current_time_str = String.valueOf(ZonedDateTime.now());

        this.sql_smt = "INSERT INTO `country` " +
                "(`country`, `createDate`, `createdBy`, `lastUpdate`, `lastUpdateBy`) " +
                "VALUES ('"
                + country_str + "', '"
                + current_time_str + "', '"
                + this.active_user_name + "', '"
                + current_time_str + "', '"
                + this.active_user_name + "');";

        exe_sql_create();

        this.country = new Country(this.object_id_buffer, country_str);

        String country_id_str = String.valueOf(this.country.getCountry_id());

        this.sql_smt = "INSERT INTO `city` " +
                "(`city`, `countryId`, `createDate`, `createdBy`, `lastUpdate`, `lastUpdateBy`) " +
                "VALUES ('"
                + city_name + "', "
                + country_id_str + ", '"
                + current_time_str + "', '"
                + this.active_user_name + "', '"
                + current_time_str + "', '"
                + this.active_user_name + "');";

        exe_sql_create();

        this.city = new City(this.object_id_buffer, city_name, this.country.getCountry_id());

        this.sql_smt = "INSERT INTO `address` " +
                "(`address`, `address2`, `cityId`, `postalCode`, `phone`, `createDate`, `createdBy`," +
                " `lastUpdate`, `lastUpdateBy`) " +
                "VALUES ('"
                + address + ", '"
                + alt_address + "', "
                + this.city.getCity_id() + " , '"
                + zip + "', '"
                + phone + "', '"
                + current_time_str + "', '"
                + active_user_name + "', '"
                + current_time_str + "', '"
                + active_user_name + "');";

        exe_sql_create();

        this.address = new Address(address, alt_address, city.getCity_id(), zip);


        this.sql_smt = "INSERT INTO `customer` " +
                "(`customerName`, `addressId`, `active`, `createDate`, `createdBy`, " +
                "`lastUpdate`, `lastUpdateBy`) " +
                "VALUES ('"
                + customer_name + "', "
                + this.address.getAddress_id() + ", "
                + true + ", '"
                + current_time_str + "', '"
                + this.active_user_name + "', '"
                + current_time_str + "', '"
                + this.active_user_name + "');";

        exe_sql_create();


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

    private void exe_sql_create() throws SQLException {
        execute_sql_stmt();
        set_last_insert_obj_id();
    }

    private void set_last_insert_obj_id() throws SQLException {
        this.sql_smt = "SELECT LAST_INSERT_ID()";
        while (this.rs.next())
            this.object_id_buffer = this.rs.getInt(1);
    }

    private void execute_sql_stmt() {
        try {
            this.rs = dbc.get_mysql_resultSet(sql_stm);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
