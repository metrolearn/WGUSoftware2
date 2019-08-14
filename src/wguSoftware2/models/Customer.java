package wguSoftware2.models;

import java.util.Date;
import java.util.Objects;

/**
 * The type Customer.
 * This class is intended to to abstract the following mysql db table.
 *  TABLE `customer` (
 *   `customerId` int(10) NOT NULL,
 *   `customerName` varchar(45) NOT NULL,
 *   `addressId` int(10) NOT NULL,
 *   `active` tinyint(1) NOT NULL,
 *   `createDate` datetime NOT NULL,
 *   `createdBy` varchar(40) NOT NULL,
 *   `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
 *   `lastUpdateBy` varchar(40) NOT NULL,
 *   PRIMARY KEY (`customerId`),
 *   KEY `addressId` (`addressId`),
 *   CONSTRAINT `customer_ibfk_1` FOREIGN KEY (`addressId`) REFERENCES `address` (`addressId`)
 * ) ENGINE=InnoDB DEFAULT CHARSET=latin1
 *
 */
public class Customer {

  private Integer customer_id;
  private String customer_name;
  private String address_id;
  private Boolean active;
  private Date create_date_time;
  private String created_by;
  private String last_update_time;
  private String last_update_by;

  /**
   * Instantiates a new Customer.
   */
  public Customer() {
  }

  public Customer(String customer_name) {
    this.customer_name = customer_name;
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
  public String getAddress_id() {
    return address_id;
  }

  /**
   * Sets address id.
   *
   * @param address_id the address id
   */
  public void setAddress_id(String address_id) {
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
  public Date getCreate_date_time() {
    return create_date_time;
  }

  /**
   * Sets create date time.
   *
   * @param create_date_time the create date time
   */
  public void setCreate_date_time(Date create_date_time) {
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
  public String getLast_update_time() {
    return last_update_time;
  }

  /**
   * Sets last update time.
   *
   * @param last_update_time the last update time
   */
  public void setLast_update_time(String last_update_time) {
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
  public Boolean create_customer_db_entry(String sql_statement){
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
  public Boolean read_customer_db_entry(String sql_statement){
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
  public Boolean update_customer_db_entry(String sql_statement){
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
  public Boolean delete_customer_db_entry(String sql_statement){
    Boolean r_val = Boolean.FALSE;
    String sql = "DELETE FROM customer WHERE customerId = ?";
    return r_val;
  }


}
