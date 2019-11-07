package wguSoftware2.models;

import wguSoftware2.utils.Converters;

import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * The type Country.
 */
public class Country {

  private Integer country_id;
  private String country_name;
  private ZonedDateTime create_date_time;
  private String create_by;
  private ZonedDateTime last_update_time;
  private String last_update_by;
  private Converters converter;
  private String active_user;

  /**
   * Instantiates a new Country.
   *
   * @param country_name the country name
   */
  public Country(String country_name) {

      this.country_name = country_name;

    }

  /**
   * Instantiates a new Country.
   *
   * @param countryId the country id
   */
  public Country(int countryId) {
      this.country_id = countryId;

  }

  /**
   * Gets converter.
   *
   * @return the converter
   */
  public Converters getConverter() {
    return converter;
  }

  /**
   * Instantiates a new Country.
   *
   * @param country_str      the country str
   * @param active_user_name the active user name
   */
  public Country(String country_str, String active_user_name) {
    this.country_name = country_str;
    this.active_user = active_user_name;
    this.converter = new Converters();
  }

  /**
   * Instantiates a new Country.
   *
   * @param object_id_buffer the object id buffer
   * @param active_user_name the active user name
   */
  public Country(Integer object_id_buffer, String active_user_name) {
    this.active_user = active_user_name;
    this.country_id = object_id_buffer;
    this.converter = new Converters();
  }

  /**
   * Instantiates a new Country.
   */
  public Country() {

  }

  /**
   * Gets active user.
   *
   * @return the active user
   */
  public String getActive_user() {
    return active_user;
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
   * Gets country name.
   *
   * @return the country name
   */
  public String getCountry_name() {
    return country_name;
  }

  /**
   * Sets country name.
   *
   * @param country_name the country name
   */
  public void setCountry_name(String country_name) {
    this.country_name = country_name;
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
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Country)) {
      return false;
    }
    Country country = (Country) o;
    return Objects.equals(getCountry_id(), country.getCountry_id()) &&
        Objects.equals(getCountry_name(), country.getCountry_name()) &&
        Objects.equals(getCreate_date_time(), country.getCreate_date_time()) &&
        Objects.equals(getCreate_by(), country.getCreate_by()) &&
        Objects.equals(getLast_update_time(), country.getLast_update_time()) &&
        Objects.equals(getLast_update_by(), country.getLast_update_by());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getCountry_id(), getCountry_name(), getCreate_date_time(), getCreate_by(),
        getLast_update_time(), getLast_update_by());
  }

  @Override
  public String toString() {
    return "Country{" +
        "country_id=" + country_id +
        ", country_str='" + country_name + '\'' +
        ", create_date_time=" + create_date_time +
        ", create_by='" + create_by + '\'' +
        ", last_update_time='" + last_update_time + '\'' +
        ", last_update_by='" + last_update_by + '\'' +
        '}';
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
