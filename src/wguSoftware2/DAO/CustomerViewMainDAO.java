package wguSoftware2.DAO;

import wguSoftware2.models.*;
import wguSoftware2.utils.DatabaseMain;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

/**
 * The type Customer view main dao.
 */
public class CustomerViewMainDAO {

    private ResultSet rs = null;
    private DatabaseMain curr_db = null;
    private Customer_view_main cvm = null;
    private String active_user_name = null;

    private Integer country_id;
    private Integer city_id;
    private Integer address_id = null;
    private Integer customer_id = null;
    private boolean activate_customer = Boolean.parseBoolean(null);

    private Timestamp sql_create_now_ts = null;

    /**
     * Instantiates a new Customer view main dao.
     *
     * @param curr_db          the curr db
     * @param active_user_name the active user name
     */
    public CustomerViewMainDAO(DatabaseMain curr_db, String active_user_name) {
        this.curr_db = curr_db;
        this.active_user_name = active_user_name;
        this.activate_customer = false;
    }

    /**
     * Create customer view main.
     *
     * @param customer_view_main the customer view main
     * @return the customer view main
     * @throws SQLException           the sql exception
     * @throws ClassNotFoundException the class not found exception
     */
    public Customer_view_main create(Customer_view_main customer_view_main) throws SQLException, ClassNotFoundException {

        String customer_name = customer_view_main.getName();
        String address = customer_view_main.getAddress();
        String alt_address = customer_view_main.getAlt_address();
        String city_name = customer_view_main.getCity_name();
        String zip = customer_view_main.getZip();
        String country_name = customer_view_main.getCountry_name();
        String phone = customer_view_main.getPhone();
        LocalDateTime current_time_ldt = ZonedDateTime.now().toLocalDateTime();
        sql_create_now_ts = Timestamp.valueOf(current_time_ldt);

        add_country_to_db(country_name);
        add_city_to_db(city_name);
        add_address_to_db(address, alt_address, zip, phone);
        add_customer_to_db(customer_name);

        Customer_view_main cvm = new Customer_view_main(
                customer_id, customer_name, address, alt_address, city_name, zip, country_name, phone);
        return cvm;
    }

    private void add_country_to_db(String country_name) throws SQLException, ClassNotFoundException {

        if (!country_exists(country_name)) {
            // country not found
            add_new_country(country_name);

        }

    }

    private boolean country_exists(String country_name) throws SQLException, ClassNotFoundException {
        // check if country already exists
        Boolean r_val = false;
        String sql_country_exists = "select countryId from country where country = ?";
        this.curr_db.dbConnect();
        Connection con = this.curr_db.getCon();
        PreparedStatement ps = con.prepareStatement(sql_country_exists);
        ps.setString(1, country_name);
        ResultSet rs = curr_db.dbExecuteQuery(ps);

        if (rs.first()) {
            // getting existing country id
            ResultSetMetaData rsmd = rs.getMetaData();
            String name = rsmd.getColumnName(1);
            String type = rsmd.getColumnTypeName(1);
            this.country_id = rs.getInt(1);
            r_val = true;

        }

        return r_val;
    }

    private void add_new_country(String country_name) throws SQLException, ClassNotFoundException {
        String sql_stmt = "INSERT INTO country (country, createDate, createdBy, lastUpdate, lastUpdateBy)" +
                " VALUES (?,?,?,?,?);";

        this.curr_db.dbConnect();
        Connection con = this.curr_db.getCon();
        PreparedStatement ps = con.prepareStatement(sql_stmt, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, country_name);
        ps.setTimestamp(2, sql_create_now_ts);
        ps.setString(3, this.active_user_name);
        ps.setTimestamp(4, sql_create_now_ts);
        ps.setString(5, this.active_user_name);
        rs = curr_db.dbExecuteUpdate(ps);

        if (rs.next()) {
            this.country_id = rs.getInt("GENERATED_KEY");
        }
    }

    private void add_city_to_db(String city_name) throws SQLException, ClassNotFoundException {
        if (!city_exists(city_name)) {
            add_new_city(city_name);
        }
    }

    private boolean city_exists(String city_name) throws SQLException, ClassNotFoundException {
        boolean r_val = false;
        Connection con;
        PreparedStatement ps;
        ResultSet rs;

        String sql_city_exists = "select cityId from city where city = ? and city.countryId = ?";
        this.curr_db.dbConnect();
        con = this.curr_db.getCon();
        ps = con.prepareStatement(sql_city_exists);
        ps.setString(1, city_name);
        ps.setInt(2, country_id);
        rs = curr_db.dbExecuteQuery(ps);

        if (rs.first()) {
            // found existing city
            ResultSetMetaData rsmd = rs.getMetaData();
            String name = rsmd.getColumnName(1);
            String type = rsmd.getColumnTypeName(1);
            this.city_id = rs.getInt(1);
            r_val = true;
        }
        return r_val;
    }

    private void add_new_city(String city_name) throws SQLException, ClassNotFoundException {
        // create new city.

        String sql_stmt = "INSERT INTO city " +
                "(city, countryId, createDate, createdBy, lastUpdate, lastUpdateBy)" +
                " VALUES (?,?,?,?,?,?);";

        this.curr_db.dbConnect();
        Connection con = this.curr_db.getCon();
        PreparedStatement ps = con.prepareStatement(sql_stmt, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, city_name);
        ps.setInt(2, this.country_id);
        ps.setTimestamp(3, sql_create_now_ts);
        ps.setString(4, this.active_user_name);
        ps.setTimestamp(5, sql_create_now_ts);
        ps.setString(6, this.active_user_name);
        rs = curr_db.dbExecuteUpdate(ps);

        if (rs.next()) {
            this.city_id = rs.getInt("GENERATED_KEY");
        }
    }

    private void add_address_to_db(String address, String alt_address, String zip, String phone) throws SQLException, ClassNotFoundException {
        Connection con;
        PreparedStatement ps;
        ResultSet rs;

        if (!address_exists(address, alt_address, zip, phone)) {

            add_new_address(address, alt_address, zip, phone);

        }

    }

    private boolean address_exists(String address, String alt_address, String zip, String phone) throws SQLException, ClassNotFoundException {
        boolean r_val = false;
        Connection con;
        PreparedStatement ps;
        ResultSet rs;
        String sql_address_exists = "select addressId " +
                "from address " +
                "where address = ? " +
                "  and address2 = ? " +
                "  and postalCode = ? " +
                "  and phone = ? " +
                "  and cityId = ? ;";

        this.curr_db.dbConnect();
        con = this.curr_db.getCon();
        ps = con.prepareStatement(sql_address_exists);
        ps.setString(1, address);
        ps.setString(2, alt_address);
        ps.setString(3, zip);
        ps.setString(4, phone);
        ps.setInt(5, this.city_id);
        rs = curr_db.dbExecuteQuery(ps);

        if (rs.first()) {
            // found existing address
            ResultSetMetaData rsmd = rs.getMetaData();
            String name = rsmd.getColumnName(1);
            String type = rsmd.getColumnTypeName(1);
            this.address_id = rs.getInt(1);
            r_val = true;
        }
        return r_val;
    }

    private void add_new_address(String address, String alt_address, String zip, String phone) throws SQLException, ClassNotFoundException {
        Connection con;
        PreparedStatement ps;
        ResultSet rs;
        String sql_stmt = "INSERT INTO address " +
                "(address, address2, cityId, postalCode, phone, createDate, createdBy, lastUpdate, lastUpdateBy) " +
                "VALUES (?,?,?,?,?,?,?,?,?);";

        this.curr_db.dbConnect();
        con = this.curr_db.getCon();
        ps = con.prepareStatement(sql_stmt, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, address);
        ps.setString(2, alt_address);
        ps.setInt(3, this.city_id);
        ps.setString(4, zip);
        ps.setString(5, phone);
        ps.setTimestamp(6, sql_create_now_ts);
        ps.setString(7, this.active_user_name);
        ps.setTimestamp(8, sql_create_now_ts);
        ps.setString(9, this.active_user_name);
        rs = curr_db.dbExecuteUpdate(ps);

        if (rs.next()) {
            this.address_id = rs.getInt("GENERATED_KEY");
        }
    }

    private void add_customer_to_db(String customer_name) throws SQLException, ClassNotFoundException {
        Connection con;
        PreparedStatement ps;
        ResultSet rs;// check if customer already exists .

        if(!customer_exists(customer_name)){

            add_new_customer(customer_name);
        }

        activate_customer();
    }

    private boolean customer_exists(String customer_name) throws SQLException, ClassNotFoundException {
        boolean r_val = false;
        Connection con;
        PreparedStatement ps;
        ResultSet rs;
        String sql_customer_exists = "select customerId from customer where customerName = ? and addressId = ? ;";

        this.curr_db.dbConnect();
        con = this.curr_db.getCon();
        ps = con.prepareStatement(sql_customer_exists);
        ps.setString(1, customer_name);
        ps.setInt(2, address_id);
        rs = curr_db.dbExecuteQuery(ps);
        if (rs.first()) {
            // found existing address
            ResultSetMetaData rsmd = rs.getMetaData();
            String name = rsmd.getColumnName(1);
            String type = rsmd.getColumnTypeName(1);
            this.customer_id = rs.getInt(1);
            activate_customer = true;
            r_val = true;

        }
        return r_val;
    }

    private void add_new_customer(String customer_name) throws SQLException, ClassNotFoundException {
        Connection con;
        PreparedStatement ps;
        ResultSet rs;
        String sql_stmt = "INSERT INTO customer " +
                "(customerName, addressId, active, createDate, " +
                "createdBy, lastUpdate, lastUpdateBy) VALUES (?,?,?,?,?,?,?);";

        this.curr_db.dbConnect();
        con = this.curr_db.getCon();
        ps = con.prepareStatement(sql_stmt, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, customer_name);
        ps.setInt(2, this.address_id);
        ps.setBoolean(3, true);
        ps.setTimestamp(4, sql_create_now_ts);
        ps.setString(5, this.active_user_name);
        ps.setTimestamp(6, sql_create_now_ts);
        ps.setString(7, this.active_user_name);
        rs = curr_db.dbExecuteUpdate(ps);

        if (rs.next()) {
            this.customer_id = rs.getInt("GENERATED_KEY");
        }
    }

    private void activate_customer() throws SQLException, ClassNotFoundException {
        Connection con;
        PreparedStatement ps;
        ResultSet rs;
        if (activate_customer) {

            String sql_activate_customer = "UPDATE customer SET  " +
                    "active = ?, " +
                    "lastUpdate = ?, " +
                    "lastUpdateBy = ? " +
                    "WHERE customerId = ?;";
            this.curr_db.dbConnect();
            con = this.curr_db.getCon();
            ps = con.prepareStatement(sql_activate_customer, Statement.RETURN_GENERATED_KEYS);
            ps.setBoolean(1, true);
            ps.setTimestamp(2, sql_create_now_ts);
            ps.setString(3, active_user_name);
            ps.setInt(4, this.customer_id);
            rs = curr_db.dbExecuteUpdate(ps);

        }
    }

    /**
     * Update customer view main.
     *
     * @param customer_view_main the customer view main
     * @return the customer view main
     * @throws SQLException           the sql exception
     * @throws ClassNotFoundException the class not found exception
     */
    public Customer_view_main update(Customer_view_main customer_view_main) throws SQLException, ClassNotFoundException {

        String customer_name = customer_view_main.getName();
        String address = customer_view_main.getAddress();
        String alt_address = customer_view_main.getAlt_address();
        String city_name = customer_view_main.getCity_name();
        String zip = customer_view_main.getZip();
        String country_name = customer_view_main.getCountry_name();
        String phone = customer_view_main.getPhone();
        LocalDateTime current_time_ldt = ZonedDateTime.now().toLocalDateTime();
        sql_create_now_ts = Timestamp.valueOf(current_time_ldt);

        add_country_to_db(country_name);
        add_city_to_db(city_name);
        add_address_to_db(address, alt_address, zip, phone);
        update_customer_to_db(customer_view_main.getId(),customer_name);

        Customer_view_main cvm = new Customer_view_main(
                customer_id, customer_name, address, alt_address, city_name, zip, country_name, phone);
        return cvm;

    }

    private void update_customer_to_db(Integer customer_id, String customer_name) throws SQLException, ClassNotFoundException {
        Connection con;
        PreparedStatement ps;
        ResultSet rs;// check if customer already exists .
        String sql_customer_update = "update customer\n" +
                "set customerName = ?, addressId = ?, lastUpdate = ?, lastUpdateBy =? where customerId = ?\n";

        this.curr_db.dbConnect();
        con = this.curr_db.getCon();
        ps = con.prepareStatement(sql_customer_update,Statement.RETURN_GENERATED_KEYS);
        ps.setString(1,customer_name);
        ps.setInt(2,address_id);
        ps.setTimestamp(3,sql_create_now_ts);
        ps.setString(4,active_user_name);
        ps.setInt(5,customer_id);
        rs = curr_db.dbExecuteUpdate(ps);

    }

    /**
     * Delete boolean.
     *
     * @param selectedItem the selected item
     * @return the boolean
     * @throws SQLException           the sql exception
     * @throws ClassNotFoundException the class not found exception
     */
    public Boolean delete(Customer_view_main selectedItem) throws SQLException, ClassNotFoundException {

        return deactivate_customer(selectedItem.getId());

    }

    /**
     * Deactivate customer boolean.
     *
     * @param customer_id the customer id
     * @return the boolean
     * @throws SQLException           the sql exception
     * @throws ClassNotFoundException the class not found exception
     */
    public boolean deactivate_customer(Integer customer_id) throws SQLException, ClassNotFoundException {
        boolean r_val = false;
        Connection con;
        PreparedStatement ps;
        ResultSet rs;
        String sql_deactivate_customer = "UPDATE customer SET active = false WHERE customerId = ?;";

        this.curr_db.dbConnect();
        con = this.curr_db.getCon();
        ps = con.prepareStatement(sql_deactivate_customer,Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, customer_id);
        rs = curr_db.dbExecuteUpdate(ps);

        return rs.next();
    }

    /**
     * Read customer view main.
     *
     * @param selectedItem the selected item
     * @return the customer view main
     * @throws SQLException           the sql exception
     * @throws ClassNotFoundException the class not found exception
     */
    public Customer_view_main read(Customer_view_main selectedItem) throws SQLException, ClassNotFoundException {
        boolean r_val = false;
        Connection con;
        PreparedStatement ps;
        ResultSet rs;
        String sql_update_view = "select * from customer " +
                "    inner join address a on customer.addressId = a.addressId " +
                "inner join city c on a.cityId = c.cityId " +
                "inner join country c2 on c.countryId = c2.countryId " +
                "where customerId = ?;";

        this.curr_db.dbConnect();
        con = this.curr_db.getCon();
        ps = con.prepareStatement(sql_update_view, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, selectedItem.getId());

        rs = ps.executeQuery();

            if (rs.next()) {
                this.cvm = new Customer_view_main(rs.getInt("customerId"));
                this.cvm.setName(rs.getString("customerName"));
                this.cvm.setAddress(rs.getString("address"));
                this.cvm.setAlt_address(rs.getString("address2"));
                this.cvm.setZip(rs.getString("postalCode"));
                this.cvm.setPhone(rs.getString("phone"));
                this.cvm.setCity_name(rs.getString("city"));
                this.cvm.setCountry_name(rs.getString("country"));

            }

        return this.cvm;

    }

}

