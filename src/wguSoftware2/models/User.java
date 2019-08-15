package wguSoftware2.models;

import javafx.fxml.FXML;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Objects;
import wguSoftware2.utils.Converters;

/**
 * The type User.
 * This class is intended to to abstract the following mysql db table.
 * TABLE `user` (
 * `userId` int(11) NOT NULL,
 * `userName` varchar(50) NOT NULL,
 * `password` varchar(50) NOT NULL,
 * `active` tinyint(4) NOT NULL,
 * `createDate` datetime NOT NULL,
 * `createdBy` varchar(40) NOT NULL,
 * `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
 * `lastUpdateBy` varchar(40) NOT NULL,
 * PRIMARY KEY (`userId`)
 * ) ENGINE=InnoDB DEFAULT CHARSET=latin1
 */
public class User {

    private Integer userID;
    private String username;
    private String password;
    boolean active;
    private ZonedDateTime create_date_time;
    private ZonedDateTime last_update_date_time;
    private Converters c = new Converters();

    /**
     * Instantiates a new User.
     *
     * @param user_count
     * @param username the username
     * @param password the password
     */
    public User(Integer user_count, String username, String password) {
        this.userID = user_count;
        this.username = username;
        this.password = password;
        this.active = true;
        this.create_date_time = ZonedDateTime.now();
        this.last_update_date_time = this.create_date_time;

    }

    public User(String name, String password) {

        this.username = name;
        this.password = password;

    }

    @Override
    @FXML
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", active=" + active +
                ", create_date_time=" + create_date_time +
                ", last_update_date_time=" + last_update_date_time +
                '}';
    }

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public Integer getUserID() {
        return userID;
    }

    /**
     * Sets user id.
     *
     * @param userID the user id
     */
    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    /**
     * Gets username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets username.
     *
     * @param username the username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Is active boolean.
     *
     * @return the boolean
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Sets active.
     *
     * @param active the active
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Gets create date time.
     *
     * @return the create date time
     */
    public ZonedDateTime getCreate_date_time() {
        return create_date_time;
    }

    /**
     * Sets create date time.
     *
     * @param create_date_time the create date time
     */
    public void setCreate_date_time(ZonedDateTime create_date_time) {
        this.create_date_time = create_date_time;
    }

    /**
     * Gets last update date time.
     *
     * @return the last update date time
     */
    public ZonedDateTime getLast_update_date_time() {
        return last_update_date_time;
    }

    /**
     * Sets last update date time.
     *
     * @param last_update_date_time the last update date time
     */
    public void setLast_update_date_time(ZonedDateTime last_update_date_time) {
        this.last_update_date_time = last_update_date_time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return isActive() == user.isActive() &&
                Objects.equals(getUserID(), user.getUserID()) &&
                getUsername().equals(user.getUsername()) &&
                getPassword().equals(user.getPassword()) &&
                getCreate_date_time().equals(user.getCreate_date_time()) &&
                Objects.equals(getLast_update_date_time(), user.getLast_update_date_time());
    }

    @Override
    public int hashCode() {
        return Objects
                .hash(getUserID(), getUsername(), getPassword(), isActive(), getCreate_date_time(),
                        getLast_update_date_time());
    }

    /**
     * Create user db entry boolean.
     *
     * @param userId
     * @param userName
     * @param usr_password
     * @param usr_active
     * @param createDate
     * @param createdBy
     * @param lastUpdate
     * @param lastUpdateBy
     * @return sql
     */
    public String create_user_db_entry_string(final String userId, final String userName, final String usr_password,
                                              final String usr_active, final String createDate, final String createdBy,
                                              final String lastUpdate, final String lastUpdateBy) {
        Boolean r_val = Boolean.FALSE;
        String sql = "INSERT INTO user (" + userId + ", " + userName + ", " + usr_password + ", " + usr_active +
                ", " + createDate + ", " + createdBy + ", "
                + lastUpdate + ", " + lastUpdateBy + ") VALUES (?,?,?,?,?,?,?,?)";
        return sql;
    }



    /**
     * Read user db entry boolean.
     *
     * @return the boolean
     */
    public String get_user_password_select_db_str() {
        String user_name = this.username;
        String user_password = this.password;
        String sql = "SELECT * from user where userName ='" + user_name + "' and password = '" + user_password + "';";
        return sql;
    }

    /**
     * Update user db entry boolean.
     *
     * @param sql_statement the sql statement
     * @return the boolean
     */
    public Boolean update_user_db_entry(String sql_statement) {
        Boolean r_val = Boolean.FALSE;
        String sql = "UPDATE user SET  userName = ?, password = ?, active = ?, createDate = ?, "
                + "createdBy = ?, lastUpdate = ?, lastUpdateBy = ? WHERE userId = ?";
        return r_val;
    }

    /**
     * Delete user db entry boolean.
     *
     * @param sql_statement the sql statement
     * @return the boolean
     */
    public Boolean delete_user_db_entry(String sql_statement) {
        Boolean r_val = Boolean.FALSE;
        String sql = "DELETE FROM user WHERE userId = ?";
        return r_val;
    }

    /**
     * Delete user count from db sql str string.
     *
     * @return the string
     */
    public String delete_user_count_from_db_sql_str() {
        return "SELECT COUNT(userId) AS users FROM user;";
    }

    public String  create_user_db_entry_str() {

        String userid_ = String.valueOf(this.userID);
        String username_ = this.username;
        String password_ = this.password;
        Integer active_ = this.active ? 1 : 0;
        String createdate_ = c.ldt_to_mysql_dt_str(this.create_date_time);
        String system_init_str = "SYSTEM_INIT";
        String createdby_ = system_init_str;
        String lastupdate_ = createdate_;
        String lastupdateby_ = system_init_str;
        return "INSERT INTO user (userId, userName, password, active, createDate, createdBy, lastUpdate, lastUpdateBy)"
                + " VALUES (" + userid_ + ",'" + username_ + "','" + password_ + "'," + active_ + ",'" + createdate_ +
                "','" + createdby_ + "','" + lastupdate_ + "','" + lastupdateby_ + "')";
    }
}



