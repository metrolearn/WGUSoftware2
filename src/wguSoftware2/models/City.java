package wguSoftware2.models;

import java.util.Date;
import java.util.Objects;

/**
 * The type City.
 * This class is intended to to abstract the following mysql db table.
 * TABLE `city` (
 *   `cityId` int(10) NOT NULL,
 *   `city` varchar(50) NOT NULL,
 *   `countryId` int(10) NOT NULL,
 *   `createDate` datetime NOT NULL,
 *   `createdBy` varchar(40) NOT NULL,
 *   `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
 *   `lastUpdateBy` varchar(40) NOT NULL,
 *   PRIMARY KEY (`cityId`),
 *   KEY `countryId` (`countryId`),
 *   CONSTRAINT `city_ibfk_1` FOREIGN KEY (`countryId`) REFERENCES `country` (`countryId`)
 * ) ENGINE=InnoDB DEFAULT CHARSET=latin1
 */
public class City {

  private Integer city_id;
  private String city_str;
  private Integer country_id;
  private Date create_date_time;
  private String last_update_time;
  private String last_update_by;

  /**
   * Instantiates a new City.
   */
  public City() {
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
   * Gets city str.
   *
   * @return the city str
   */
  public String getCity_str() {
    return city_str;
  }

  /**
   * Sets city str.
   *
   * @param city_str the city str
   */
  public void setCity_str(String city_str) {
    this.city_str = city_str;
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
    if (!(o instanceof City)) {
      return false;
    }
    City city = (City) o;
    return Objects.equals(getCity_id(), city.getCity_id()) &&
        Objects.equals(getCity_str(), city.getCity_str()) &&
        Objects.equals(getCountry_id(), city.getCountry_id()) &&
        Objects.equals(getCreate_date_time(), city.getCreate_date_time()) &&
        Objects.equals(getLast_update_time(), city.getLast_update_time()) &&
        Objects.equals(getLast_update_by(), city.getLast_update_by());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getCity_id(), getCity_str(), getCountry_id(), getCreate_date_time(),
        getLast_update_time(), getLast_update_by());
  }

  @Override
  public String toString() {
    return "City{" +
        "city_id=" + city_id +
        ", city_str='" + city_str + '\'' +
        ", country_id=" + country_id +
        ", create_date_time=" + create_date_time +
        ", last_update_time='" + last_update_time + '\'' +
        ", last_update_by='" + last_update_by + '\'' +
        '}';
  }

  /**
   * Create city db entry boolean.
   *
   * @param sql_statement the sql statement
   * @return the boolean
   */
  public Boolean create_city_db_entry(String sql_statement){
    Boolean r_val = Boolean.FALSE;
    String sql = "INSERT INTO city (cityId, city, countryId, createDate, createdBy, lastUpdate, "
        + "lastUpdateBy) VALUES (?,?,?,?,?,?,?)";

    return r_val;
  }

  /**
   * Read city db entry boolean.
   *
   * @param sql_statement the sql statement
   * @return the boolean
   */
  public Boolean read_city_db_entry(String sql_statement){
    Boolean r_val = Boolean.FALSE;
    String sql = "SELECT cityId, city, countryId, createDate, createdBy, lastUpdate, "
        + "lastUpdateBy FROM city WHERE cityId = ? ";


    return r_val;
  }

  /**
   * Update city db entry boolean.
   *
   * @param sql_statement the sql statement
   * @return the boolean
   */
  public Boolean update_city_db_entry(String sql_statement){
    Boolean r_val = Boolean.FALSE;
    String sql = "UPDATE city SET  city = ?, countryId = ?, createDate = ?, createdBy = ?, "
        + "lastUpdate = ?, lastUpdateBy = ? WHERE cityId = ? ";
    return r_val;
  }

  /**
   * Delete city db entry boolean.
   *
   * @param sql_statement the sql statement
   * @return the boolean
   */
  public Boolean delete_city_db_entry(String sql_statement){
    Boolean r_val = Boolean.FALSE;
    String sql = "DELETE FROM city WHERE cityId = ?";
    return r_val;
  }
  
}