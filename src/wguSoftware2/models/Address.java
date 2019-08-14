package wguSoftware2.models;

import java.util.Date;
import java.util.Objects;

/**
 * The type Address.
 * This class is intended to to abstract the following mysql db table.
 *  CREATE TABLE `address` (
 *   `addressId` int(10) NOT NULL,
 *   `address` varchar(50) NOT NULL,
 *   `address2` varchar(50) NOT NULL,
 *   `cityId` int(10) NOT NULL,
 *   `postalCode` varchar(10) NOT NULL,
 *   `phone` varchar(20) NOT NULL,
 *   `createDate` datetime NOT NULL,
 *   `createdBy` varchar(40) NOT NULL,
 *   `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
 *   `lastUpdateBy` varchar(40) NOT NULL,
 *   PRIMARY KEY (`addressId`),
 *   KEY `cityId` (`cityId`),
 *   CONSTRAINT `address_ibfk_1` FOREIGN KEY (`cityId`) REFERENCES `city` (`cityId`)
 * ) ENGINE=InnoDB DEFAULT CHARSET=latin1
 */
public class Address {

    private Integer address_id;
    private String address;
    private String address2;
    private Integer city_id;
    private String zip_code;
    private String phone;
    private Date create_date_time;
    private String created_by;
    private Date last_update_time;
    private String last_update_by;

  /**
   * Instantiates a new Address.
   */
  public Address() {
  }

  public Address(String address, String alt_address, String zip_code, String phone) {
    this.address = address;
    this.address2 = alt_address;
    this.zip_code = zip_code;
    this.phone =phone;

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
   * Gets address.
   *
   * @return the address
   */
  public String getAddress() {
    return address;
  }

  /**
   * Sets address.
   *
   * @param address the address
   */
  public void setAddress(String address) {
    this.address = address;
  }

  /**
   * Gets address 2.
   *
   * @return the address 2
   */
  public String getAddress2() {
    return address2;
  }

  /**
   * Sets address 2.
   *
   * @param address2 the address 2
   */
  public void setAddress2(String address2) {
    this.address2 = address2;
  }

  /**
   * Gets city id.
   *
   * @return the city id
   */
  public Integer getCity_id() {
    return city_id;
  }

  /**
   * Sets city id.
   *
   * @param city_id the city id
   */
  public void setCity_id(Integer city_id) {
    this.city_id = city_id;
  }

  /**
   * Gets postal code.
   *
   * @return the postal code
   */
  public String getZip_code() {
    return zip_code;
  }

  /**
   * Sets postal code.
   *
   * @param zip_code the postal code
   */
  public void setZip_code(String zip_code) {
    this.zip_code = zip_code;
  }

  /**
   * Gets phone.
   *
   * @return the phone
   */
  public String getPhone() {
    return phone;
  }

  /**
   * Sets phone.
   *
   * @param phone the phone
   */
  public void setPhone(String phone) {
    this.phone = phone;
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
  public Date getLast_update_time() {
    return last_update_time;
  }

  /**
   * Sets last update time.
   *
   * @param last_update_time the last update time
   */
  public void setLast_update_time(Date last_update_time) {
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
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Address)) {
      return false;
    }
    Address address1 = (Address) o;
    return Objects.equals(getAddress_id(), address1.getAddress_id()) &&
        Objects.equals(getAddress(), address1.getAddress()) &&
        Objects.equals(getAddress2(), address1.getAddress2()) &&
        Objects.equals(getCity_id(), address1.getCity_id()) &&
        Objects.equals(getZip_code(), address1.getZip_code()) &&
        Objects.equals(getPhone(), address1.getPhone()) &&
        Objects.equals(getCreate_date_time(), address1.getCreate_date_time()) &&
        Objects.equals(getCreated_by(), address1.getCreated_by()) &&
        Objects.equals(getLast_update_time(), address1.getLast_update_time()) &&
        Objects.equals(getLast_update_by(), address1.getLast_update_by());
  }

  @Override
  public int hashCode() {
    return Objects
        .hash(getAddress_id(), getAddress(), getAddress2(), getCity_id(), getZip_code(),
            getPhone(), getCreate_date_time(), getCreated_by(), getLast_update_time(),
            getLast_update_by());
  }

  @Override
  public String toString() {
    return "Address{" +
        "address_id=" + address_id +
        ", address='" + address + '\'' +
        ", address2='" + address2 + '\'' +
        ", city_id=" + city_id +
        ", postal_code='" + zip_code + '\'' +
        ", phone='" + phone + '\'' +
        ", create_date_time=" + create_date_time +
        ", created_by='" + created_by + '\'' +
        ", last_update_time='" + last_update_time + '\'' +
        ", last_update_by='" + last_update_by + '\'' +
        '}';
  }

  /**
   * Create address db entry boolean.
   *
   * @param sql_statement the sql statement
   * @return the boolean
   */
  public Boolean create_address_db_entry(String sql_statement){
    Boolean r_val = Boolean.FALSE;
    String sql = "INSERT INTO address "
        + "(addressId, address, address2, cityId, postalCode, phone, createDate,"
        + " createdBy, lastUpdate, lastUpdateBy) VALUES (?,?,?,?,?,?,?,?,?,?)";

    return r_val;
  }

  /**
   * Read address db entry boolean.
   *
   * @param sql_statement the sql statement
   * @return the boolean
   */
  public Boolean read_address_db_entry(String sql_statement){
    Boolean r_val = new Boolean(false);
    String sql = "SELECT addressId, address, address2, cityId, postalCode, phone,"
        + " createDate, createdBy, lastUpdate, lastUpdateBy FROM address WHERE addressId = ?";

    return r_val;
  }

  /**
   * Update address db entry boolean.
   *
   * @param sql_statement the sql statement
   * @return the boolean
   */
  public Boolean update_address_db_entry(String sql_statement){
    Boolean r_val = new Boolean(false);
    String sql = "UPDATE address SET  address = ?, address2 = ?, cityId = ?, postalCode = ?, "
        + "phone = ?, createDate = ?, createdBy = ?, lastUpdate = ?, "
        + "lastUpdateBy = ? WHERE addressId = ?";
    return r_val;
  }

  /**
   * Delete address db entry boolean.
   *
   * @param sql_statement the sql statement
   * @return the boolean
   */
  public Boolean delete_address_db_entry(String sql_statement){
    Boolean r_val = new Boolean(false);
    String sql = "DELETE FROM address WHERE addressId = ?";

    return r_val;
  }

}
