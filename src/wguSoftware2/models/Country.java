package wguSoftware2.models;

import java.util.Date;
import java.util.Objects;

/**
 * The type Country.
 */
public class Country {

  private Integer country_id;
  private String country_str;
  private Date create_date_time;
  private String create_by;
  private String last_update_time;
  private String last_update_by;

  /**
   * Instantiates a new Country.
   */
  public Country() {
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

  
}
