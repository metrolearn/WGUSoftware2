package wguSoftware2.models;

import wguSoftware2.utils.Converters;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Objects;

/**
 * The type Address.
 * This class is intended to to abstract the following mysql db table.
 * CREATE TABLE `address` (
 * `addressId` int(10) NOT NULL,
 * `address` varchar(50) NOT NULL,
 * `address2` varchar(50) NOT NULL,
 * `cityId` int(10) NOT NULL,
 * `postalCode` varchar(10) NOT NULL,
 * `phone` varchar(20) NOT NULL,
 * `createDate` datetime NOT NULL,
 * `createdBy` varchar(40) NOT NULL,
 * `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
 * `lastUpdateBy` varchar(40) NOT NULL,
 * PRIMARY KEY (`addressId`),
 * KEY `cityId` (`cityId`),
 * CONSTRAINT `address_ibfk_1` FOREIGN KEY (`cityId`) REFERENCES `city` (`cityId`)
 * ) ENGINE=InnoDB DEFAULT CHARSET=latin1
 */
public class Address {

    private Integer address_id;
    private String address;
    private String alt_address;
    private Integer city_id;
    private String zip_code;
    private String phone;
    private Date create_date_time;
    private String created_by;
    private Date last_update_time;
    private String last_update_by;
    private Converters converter;
    private Active_User active_user;

    public Address(String address, String alt_address, Integer city_id, String zip) {

        this.address = address;
        this.alt_address = alt_address;
        this.city_id = city_id;
        this.zip_code = zip;

    }

    public Active_User getActive_user() {
        return active_user;
    }

    public void setActive_user(Active_User active_user) {
        this.active_user = active_user;
    }

    /**
     * Instantiates a new Address.
     * @param address_str
     * @param alt_address_str
     * @param city_id
     * @param zip_code_str
     * @param phone_str
     * @param active_user
     */
    public Address(String address_str, String alt_address_str, Integer city_id, String zip_code_str, String phone_str,
                   Active_User active_user)
    {
    this.address = address_str;
    this.alt_address = alt_address_str;
    this.city_id = city_id;
    this.zip_code = zip_code_str;
    this.phone = phone_str;
    this.active_user = active_user;
        this.converter = new Converters();

    }

    public Address(String address, String alt_address, String zip_code, String phone, Integer city_id) {


        this.address = address;
        this.alt_address = alt_address;
        this.zip_code = zip_code;
        this.phone = phone;
        this.city_id = city_id;


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
    public String getAlt_address() {
        return alt_address;
    }

    /**
     * Sets address 2.
     *
     * @param alt_address the address 2
     */
    public void setAlt_address(String alt_address) {
        this.alt_address = alt_address;
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
                Objects.equals(getAlt_address(), address1.getAlt_address()) &&
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
                .hash(getAddress_id(), getAddress(), getAlt_address(), getCity_id(), getZip_code(),
                        getPhone(), getCreate_date_time(), getCreated_by(), getLast_update_time(),
                        getLast_update_by());
    }

    @Override
    public String toString() {
        return "Address{" +
                "address_id=" + address_id +
                ", address='" + address + '\'' +
                ", address2='" + alt_address + '\'' +
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
     * @return the boolean
     */
    public String  get_address_db_create_str() {
        String address = this.address;
        String alt_address = this.address;
        String city_id = String.valueOf(this.city_id);
        String zip_code = this.zip_code;
        String phone = this.phone;
        String create_date = null;
        create_date = this.converter.ldt_to_mysql_dt_str(ZonedDateTime.now());
        String created_by = this.active_user.getActive_user_name();
        String last_update = create_date;
        String last_update_by = created_by;
        String sql = "INSERT INTO address (address, address2, cityId, postalCode, phone, createDate, " +
                "createdBy, lastUpdate, lastUpdateBy) " +
                "VALUES ('" + address + "', '" + alt_address + "', " + city_id + ", '" + zip_code + "'," +
                " '" + phone + "', '" + create_date + "', '" + created_by + "', '" + last_update + "'," +
                " '" + last_update_by + "');";

        return sql;
    }

    /**
     * Read address db entry boolean.
     *
     * @param sql_statement the sql statement
     * @return the boolean
     */
    public Boolean read_address_db_entry(String sql_statement) {
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
    public Boolean update_address_db_entry(String sql_statement) {
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
    public Boolean delete_address_db_entry(String sql_statement) {
        Boolean r_val = new Boolean(false);
        String sql = "DELETE FROM address WHERE addressId = ?";

        return r_val;
    }

}
