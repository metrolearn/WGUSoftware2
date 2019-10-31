package wguSoftware2.utils;


import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import wguSoftware2.controllers.LoginWindowC;
import wguSoftware2.models.Active_User;
import wguSoftware2.models.Customer_view_main;
import wguSoftware2.models.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * The type Database.
 */
public class Database {

    private String database_driver = "com.mysql.jdbc.Driver";
    private String database_url = "jdbc:mysql://";
    private String database_name;
    private String username;
    private String password;
    private String max_pool = "300"; // set your own limit
    private Connection connection = null;
    private Properties properties;
    private Boolean init_users_loaded;
    private LoginWindowC lc;

    /**
     * Instantiates a new Database.
     *
     * @param database_ip   the database ip
     * @param database_name the database name
     * @param username      the username
     * @param password      the password
     * @throws SQLException the sql exception
     */
    public Database(String database_ip, String database_name, String username,
                    String password) throws SQLException {
        this.database_name = database_name;
        this.database_url += database_ip + "/" + database_name;

        this.username = username;
        this.password = password;
        this.properties = new Properties();
        this.properties.setProperty("user", this.username);
        this.properties.setProperty("password", this.password);
        this.properties.setProperty("MaxPooledStatements", this.max_pool);

        // check if database is loaded with init users.

        // we start by assuming it is not loaded.
        this.init_users_loaded = false;
        this.connection = this.create_mysql_db_connection();
        PreparedStatement statement = this.connection.prepareStatement(
                "SELECT EXISTS (SELECT 1 FROM user) as users_exist;"
        );
        statement.execute();
        ResultSet resultSet = statement.getResultSet();
        // check if there is more than one record in the user data.
        // if true, set init users loaded to true.
        Integer count = 0;
        while (resultSet.next()) {
            this.init_users_loaded = resultSet.getBoolean("users_exist");
            if (this.init_users_loaded) {
                System.out.println("Default Users Loaded");
                break;
            } else {
                System.out.println("Default Users Not Loaded.");
            }
        }
        disconnect_from_mysql_db();
    }

    /**
     * Create mysql db connection connection.
     *
     * @return the connection
     */
    public Connection create_mysql_db_connection() {


        if (connection == null) {
            try {
                Class.forName(this.database_driver).newInstance();
                connection = DriverManager.getConnection(this.database_url, this.properties);
            } catch (ClassNotFoundException | SQLException | IllegalAccessException | InstantiationException exception) {
                exception.printStackTrace();
            }
        }
        return connection;
    }

    /**
     * Disconnect from mysql db.
     */
    public void disconnect_from_mysql_db() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Send mysql command.
     *
     * @param sql the sql
     * @throws SQLException the sql exception
     */
    public void send_mysql_command(String sql) throws SQLException {
        try {
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * Gets mysql result set.
     *
     * @param sql the sql
     * @return the mysql result set
     * @throws SQLException the sql exception
     */
    public ResultSet get_mysql_resultSet(String sql) throws SQLException {
        PreparedStatement statement = null;
        try {
            this.connection = this.create_mysql_db_connection();
            statement = this.connection.prepareStatement(sql);
            statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ResultSet resultSet = statement.getResultSet();

        return resultSet;

    }

    /**
     * Gets mysql result set and pk.
     *
     * @param sql the sql
     * @return the mysql result set and pk
     * @throws SQLException the sql exception
     */
    public ResultSet get_mysql_resultSet_and_pk(String sql) throws SQLException {
        PreparedStatement statement = null;
        try {
            this.connection = this.create_mysql_db_connection();
            statement = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement.getGeneratedKeys();

    }

    /**
     * Seed user names into db boolean.
     *
     * @return the boolean
     */
    public boolean seed_user_names_into_db() {

        String csvFile = "src/wguSoftware2/seed_data/username_list.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        Integer user_count = 1;
        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] country = line.split(cvsSplitBy);
                String password = null;
                String user = null;
                Integer count = 0;
                for (String s : country) {
                    count += 1;
                    if (count % 2 != 0) {
                        user = s;
                    } else {
                        password = s;
                        System.out.println("Loading Default User into db-> "
                                + user + ", password->" + password);
                        this.connection = this.create_mysql_db_connection();
                        User u = new User(user_count, user, password);
                        user_count = user_count + 1;
                        String sql_statment = u.create_user_db_entry_str();
                        this.send_mysql_command(sql_statment);
                        this.disconnect_from_mysql_db();
                    }

                }
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }

    /**
     * Gets init users loaded.
     *
     * @return the init users loaded
     */
    public Boolean getInit_users_loaded() {
        return init_users_loaded;
    }

    /**
     * Check cred in db active user.
     *
     * @return the active user
     * @throws SQLException the sql exception
     */
    public Active_User check_cred_in_db() throws SQLException {

        Active_User r_val = null;

        String login_user_name = lc.getUser_txt_fld().getText();
        String login_user_password = lc.getPassword_txt_fld().getText();
        User u = new User(login_user_name, login_user_password);
        String select_str = u.get_user_password_select_db_str();
        final ResultSet mysql_resultSet = this.get_mysql_resultSet(select_str);

        if (mysql_resultSet.isBeforeFirst()) {
            while (mysql_resultSet.next()) {
                Integer active_user_id = mysql_resultSet.getInt(1);
                String active_user_name = mysql_resultSet.getString(2);
                r_val = new Active_User(active_user_id, active_user_name);
            }

        } else {
            System.out.println("User not found");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            String headerText = "User Not found.";
            String login_error = "Login Error.";
            String please_try_again = "Please try again";

            if (lc.getLang_chk_bx_selected()) {
                headerText = "Usuario no encontrado.";
                login_error = "Error de inicio de sesión.";
                please_try_again = "Inténtalo de nuevo.";
            }

            alert.setTitle(login_error);
            alert.setHeaderText(headerText);
            alert.setContentText(please_try_again);
            alert.showAndWait()
                    .filter(response -> response == ButtonType.OK);
        }

        return r_val;
    }

    /**
     * Sets lc.
     *
     * @param lc the lc
     */
    public void setLc(LoginWindowC lc) {
        this.lc = lc;
    }

    /*
    CREATE TABLE `customer` (
          `customerId` int(10) NOT NULL,
          `customerName` varchar(45) NOT NULL,
          `addressId` int(10) NOT NULL,
          `active` tinyint(1) NOT NULL,
          `createDate` datetime NOT NULL,
          `createdBy` varchar(40) NOT NULL,
          `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
          `lastUpdateBy` varchar(40) NOT NULL,
          PRIMARY KEY (`customerId`),
          KEY `addressId` (`addressId`),
          CONSTRAINT `customer_ibfk_1` FOREIGN KEY (`addressId`) REFERENCES `address` (`addressId`)
        ) ENGINE=InnoDB DEFAULT CHARSET=latin1

     */

//    public List<Customer_view_main> getAllCustomersFromDB() throws SQLException {
//        String sql = "SELECT customer.customerId, customer.customerName, address.address, address.phone " +
//                "FROM customer INNER JOIN address on customer.addressId =  address.addressId";
//        ResultSet rs = this.get_mysql_resultSet(sql);
//        List<Customer_view_main> cvm_list = new ArrayList<>();
//
//        while(rs.next()){
//             Integer customer_id = rs.getInt(1);
//             String customer_name = rs.getString(2);
//             String address = rs.getString(3);
//             String phone= rs.getString(4);
//             Customer_view_main cvm = new Customer_view_main(customer_id,customer_name,address,phone);
//             cvm_list.add(cvm);
//
//        }


//        return cvm_list;
//}
    }
