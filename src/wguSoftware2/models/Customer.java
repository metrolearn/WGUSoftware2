package wguSoftware2.models;

import wguSoftware2.utils.Converters;

import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * The type Customer.
 */
public class Customer {

    private Integer customer_id;
    private String customer_name;
    private Integer address_id;
    private Boolean active;
    private ZonedDateTime create_date_time;
    private String created_by;
    private ZonedDateTime last_update_time;
    private String last_update_by;
    private Converters converter;

    /**
     * Instantiates a new Customer.
     *
     * @param customer_name_str the customer name str
     * @param active_user_name  the active user name
     * @param address_id        the address id
     */
    public Customer(String customer_name_str, String active_user_name, Integer address_id) {
        this.converter = new Converters();
        this.customer_name = customer_name_str;
        this.created_by = active_user_name;
        this.address_id = address_id;
        this.create_date_time = ZonedDateTime.now();
        this.last_update_time = create_date_time;
        this.last_update_by = created_by;


    }


    /**
     * Gets customer id.
     *
     * @return the customer id
     */
    public Integer getCustomer_id() {
        return customer_id;
    }

    /**
     * Sets customer id.
     *
     * @param customer_id the customer id
     */
    public void setCustomer_id(Integer customer_id) {
        this.customer_id = customer_id;
    }

    /**
     * Gets customer name.
     *
     * @return the customer name
     */
    public String getCustomer_name() {
        return customer_name;
    }

    /**
     * Sets customer name.
     *
     * @param customer_name the customer name
     */
    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    /**
     * Gets address id.
     *
     * @return the address id
     */
    public Integer getAddress_id() {
        return address_id;
    }

    /**
     * Sets address id.
     *
     * @param address_id the address id
     */
    public void setAddress_id(Integer address_id) {
        this.address_id = address_id;
    }

    /**
     * Gets active.
     *
     * @return the active
     */
    public Boolean getActive() {
        return active;
    }

    /**
     * Sets active.
     *
     * @param active the active
     */
    public void setActive(Boolean active) {
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
     * Gets created by.
     *
     * @return the created by
     */
    public String getCreated_by() {
        return created_by;
    }

    /**
     * Sets created by.
     *
     * @param created_by the created by
     */
    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    /**
     * Gets last update time.
     *
     * @return the last update time
     */
    public ZonedDateTime getLast_update_time() {
        return last_update_time;
    }

    /**
     * Sets last update time.
     *
     * @param last_update_time the last update time
     */
    public void setLast_update_time(ZonedDateTime last_update_time) {
        this.last_update_time = last_update_time;
    }

    /**
     * Gets last update by.
     *
     * @return the last update by
     */
    public String getLast_update_by() {
        return last_update_by;
    }

    /**
     * Sets last update by.
     *
     * @param last_update_by the last update by
     */
    public void setLast_update_by(String last_update_by) {
        this.last_update_by = last_update_by;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customer_id=" + customer_id +
                ", customer_name='" + customer_name + '\'' +
                ", address_id='" + address_id + '\'' +
                ", active=" + active +
                ", create_date_time=" + create_date_time +
                ", created_by='" + created_by + '\'' +
                ", last_update_time='" + last_update_time + '\'' +
                ", last_update_by='" + last_update_by + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Customer)) {
            return false;
        }
        Customer customer = (Customer) o;
        return Objects.equals(getCustomer_id(), customer.getCustomer_id()) &&
                Objects.equals(getCustomer_name(), customer.getCustomer_name()) &&
                Objects.equals(getAddress_id(), customer.getAddress_id()) &&
                Objects.equals(getActive(), customer.getActive()) &&
                Objects.equals(getCreate_date_time(), customer.getCreate_date_time()) &&
                Objects.equals(getCreated_by(), customer.getCreated_by()) &&
                Objects.equals(getLast_update_time(), customer.getLast_update_time()) &&
                Objects.equals(getLast_update_by(), customer.getLast_update_by());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCustomer_id(), getCustomer_name(), getAddress_id(), getActive(),
                getCreate_date_time(), getCreated_by(), getLast_update_time(), getLast_update_by());
    }

    /**
     * Create customer db entry boolean.
     *
     * @param sql_statement the sql statement
     * @return the boolean
     */
    public Boolean create_customer_db_entry(String sql_statement) {
        Boolean r_val = Boolean.FALSE;
        String sql = "INSERT INTO customer (customerId, customerName, addressId, active, createDate,"
                + " createdBy, lastUpdate, lastUpdateBy) VALUES (?,?,?,?,?,?,?,?)";
        return r_val;
    }

    /**
     * Read customer db entry boolean.
     *
     * @param sql_statement the sql statement
     * @return the boolean
     */
    public Boolean read_customer_db_entry(String sql_statement) {
        Boolean r_val = Boolean.FALSE;
        String sql = "SELECT customerId, customerName, addressId, active, createDate, createdBy, "
                + "lastUpdate, lastUpdateBy FROM customer WHERE customerId = ?";
        return r_val;
    }

    /**
     * Update customer db entry boolean.
     *
     * @param sql_statement the sql statement
     * @return the boolean
     */
    public Boolean update_customer_db_entry(String sql_statement) {
        Boolean r_val = Boolean.FALSE;
        String sql = "UPDATE customer SET  customerName = ?, addressId = ?, active = ?, createDate = ?,"
                + " createdBy = ?, lastUpdate = ?, lastUpdateBy = ? WHERE customerId = ?";
        return r_val;
    }

    /**
     * Delete customer db entry boolean.
     *
     * @param sql_statement the sql statement
     * @return the boolean
     */
    public Boolean delete_customer_db_entry(String sql_statement) {
        Boolean r_val = Boolean.FALSE;
        String sql = "DELETE FROM customer WHERE customerId = ?";
        return r_val;
    }


    /**
     * Gets address db create str.
     *
     * @return the address db create str
     */
    public String get_address_db_create_str() {
        String customer_name = this.customer_name;
        String address_id = String.valueOf(this.address_id);
        String active = String.valueOf(true);
        String create_date = converter.ldt_to_mysql_dt_str(ZonedDateTime.now());
        String created_by = this.created_by;
        String lastupdate_date = create_date;
        String lastupdate_by = this.created_by;
        String sql = "INSERT INTO customer (customerName, addressId, active, createDate, createdBy, " +
                "lastUpdate, lastUpdateBy) " +
                "VALUES ('" + customer_name + "', " + address_id + ", " + active + ", '" + create_date + "'," +
                " '" + created_by + "', '" + lastupdate_date + "', '" + lastupdate_by + "');";
        return sql;

    }
}
