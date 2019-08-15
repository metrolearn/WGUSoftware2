package wguSoftware2.models;

import wguSoftware2.utils.Converters;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Objects;

/**
 * The type Country.
 * This class is intended to to abstract the following mysql db table.
 *  TABLE `country` (
 *   `countryId` int(10) NOT NULL,
 *   `country` varchar(50) NOT NULL,
 *   `createDate` datetime NOT NULL,
 *   `createdBy` varchar(40) NOT NULL,
 *   `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
 *   `lastUpdateBy` varchar(40) NOT NULL,
 *   PRIMARY KEY (`countryId`)
 * ) ENGINE=InnoDB DEFAULT CHARSET=latin1
 */
public class Country {

  private Integer country_id;
  private String country_str;
  private ZonedDateTime create_date_time;
  private String create_by;
  private String last_update_time;
  private String last_update_by;
  private Converters converter;

  /**
   * Instantiates a new Country.
   * @param country_str
   * @param active_user_name
   */
  public Country(String country_str, String active_user_name) {
    this.country_str = country_str;
    this.create_by = active_user_name;
    this.converter = new Converters();
  }

    /**
   * Gets country id.
   *
   * @return the country id
   */
  public Integer getCountry_id() {
    return country_id;
  }

  /**
   * Sets country id.
   *
   * @param country_id the country id
   */
  public void setCountry_id(Integer country_id) {
    this.country_id = country_id;
  }

  /**
   * Gets country str.
   *
   * @return the country str
   */
  public String getCountry_str() {
    return country_str;
  }

  /**
   * Sets country str.
   *
   * @param country_str the country str
   */
  public void setCountry_str(String country_str) {
    this.country_str = country_str;
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
   * Gets create by.
   *
   * @return the create by
   */
  public String getCreate_by() {
    return create_by;
  }

  /**
   * Sets create by.
   *
   * @param create_by the create by
   */
  public void setCreate_by(String create_by) {
    this.create_by = create_by;
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
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Country)) {
      return false;
    }
    Country country = (Country) o;
    return Objects.equals(getCountry_id(), country.getCountry_id()) &&
        Objects.equals(getCountry_str(), country.getCountry_str()) &&
        Objects.equals(getCreate_date_time(), country.getCreate_date_time()) &&
        Objects.equals(getCreate_by(), country.getCreate_by()) &&
        Objects.equals(getLast_update_time(), country.getLast_update_time()) &&
        Objects.equals(getLast_update_by(), country.getLast_update_by());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getCountry_id(), getCountry_str(), getCreate_date_time(), getCreate_by(),
        getLast_update_time(), getLast_update_by());
  }

  @Override
  public String toString() {
    return "Country{" +
        "country_id=" + country_id +
        ", country_str='" + country_str + '\'' +
        ", create_date_time=" + create_date_time +
        ", create_by='" + create_by + '\'' +
        ", last_update_time='" + last_update_time + '\'' +
        ", last_update_by='" + last_update_by + '\'' +
        '}';
  }

  public String get_country_db_create_str(){
    String country_str = this.country_str;
    String create_date = this.converter.ldt_to_mysql_dt_str(ZonedDateTime.now());
    String created_by = this.create_by;
    String lastupdate = create_date;
    String lastupate_by = this.create_by;
    String sql_str = "INSERT INTO country (country, createDate, createdBy, lastUpdate, lastUpdateBy) " +
            "VALUES ('" + country_str + "', '" + create_date + "', '" + created_by + "'," +
            " '" + lastupdate + "', '" + lastupate_by + "')";

    return sql_str;
  }

  /**
   * Read country db entry boolean.
   *
   * @param sql_statement the sql statement
   * @return the boolean
   */
  public Boolean read_country_db_entry(String sql_statement){
    Boolean r_val = Boolean.FALSE;
    String sql = "SELECT customerId, customerName, addressId, active, createDate, createdBy, "
        + "lastUpdate, lastUpdateBy FROM customer WHERE customerId = ?";
    return r_val;
  }

  /**
   * Update country db entry boolean.
   *
   * @param sql_statement the sql statement
   * @return the boolean
   */
  public Boolean update_country_db_entry(String sql_statement){
    Boolean r_val = Boolean.FALSE;
    String sql = "UPDATE customer SET  customerName = ?, addressId = ?, active = ?, "
        + "createDate = ?, createdBy = ?, lastUpdate = ?, lastUpdateBy = ? WHERE customerId = ?";
    return r_val;
  }

  /**
   * Delete country db entry boolean.
   *
   * @param sql_statement the sql statement
   * @return the boolean
   */
  public Boolean delete_country_db_entry(String sql_statement){
    Boolean r_val = Boolean.FALSE;
    String sql = "DELETE FROM customer WHERE customerId = ?";
    return r_val;
  }



}
