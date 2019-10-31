package wguSoftware2.models;

import wguSoftware2.utils.Converters;

import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * The type City.
 * This class is intended to to abstract the following mysql db table.
 * TABLE `city` (
 * `cityId` int(10) NOT NULL,
 * `city` varchar(50) NOT NULL,
 * `countryId` int(10) NOT NULL,
 * `createDate` datetime NOT NULL,
 * `createdBy` varchar(40) NOT NULL,
 * `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
 * `lastUpdateBy` varchar(40) NOT NULL,
 * PRIMARY KEY (`cityId`),
 * KEY `countryId` (`countryId`),
 * CONSTRAINT `city_ibfk_1` FOREIGN KEY (`countryId`) REFERENCES `country` (`countryId`)
 * ) ENGINE=InnoDB DEFAULT CHARSET=latin1
 */
public class City {

    private Integer city_id;
    private String city_name;
    private Integer country_id;
    private ZonedDateTime create_date_time;
    private String last_update_time;
    private String created_by;
    private String update_by;
    private Converters converter;

    /**
     * Instantiates a new City.
     *
     * @param city_name  the city name
     * @param country_id the country id
     */
    public City(String city_name, Integer country_id) {
        this.city_name = city_name;
        this.country_id = country_id;

    }

    /**
     * Instantiates a new City.
     *
     * @param city_id the city id
     */
    public City(Integer city_id) {
        this.city_id = city_id;
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
    public String getCity_name() {
        return city_name;
    }

    /**
     * Sets city str.
     *
     * @param city_name the city str
     */
    public void setCity_name(String city_name) {
        this.city_name = city_name;
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
     * Gets country id str.
     *
     * @return the country id str
     */
    public String getCountry_id_str() {
        return String.valueOf(country_id);
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
    public String getCreated_by() {
        return created_by;
    }

    /**
     * Sets last update by.
     *
     * @param created_by the last update by
     */
    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }


    /**
     * Create city db entry boolean.
     *
     * @param country_id_parm the country id parm
     * @return the boolean
     */
    public String get_city_db_create_str(Integer country_id_parm) {

        String city = this.city_name;
        String country_id = String.valueOf(country_id_parm);
        String create_date = this.converter.ldt_to_mysql_dt_str(ZonedDateTime.now());
        String created_by = "Carter";
        String last_update = "1987-10-21 21:15:35";
        String last_update_by = "Casimer Jones";
        String sql_str = "INSERT INTO city (city, countryId, createDate, createdBy, lastUpdate, lastUpdateBy) " +
                "VALUES ('" + city + "', " + country_id + ", '" + create_date + "', '" + created_by + "'," +
                " '" + last_update + "', '" + last_update_by + "');";

        return sql_str;
    }

    /**
     * Read city db entry boolean.
     *
     * @param sql_statement the sql statement
     * @return the boolean
     */
    public Boolean read_city_db_entry(String sql_statement) {
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
    public Boolean update_city_db_entry(String sql_statement) {
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
    public Boolean delete_city_db_entry(String sql_statement) {
        Boolean r_val = Boolean.FALSE;
        String sql = "DELETE FROM city WHERE cityId = ?";
        return r_val;
    }

    /**
     * Gets update by.
     *
     * @return the update by
     */
    public String getUpdate_by() {
        return update_by;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof City)) return false;
        City city = (City) o;
        return Objects.equals(getCity_id(), city.getCity_id()) &&
                Objects.equals(getCity_name(), city.getCity_name()) &&
                Objects.equals(getCountry_id(), city.getCountry_id()) &&
                Objects.equals(getCreate_date_time(), city.getCreate_date_time()) &&
                Objects.equals(getLast_update_time(), city.getLast_update_time()) &&
                Objects.equals(getCreated_by(), city.getCreated_by()) &&
                Objects.equals(getUpdate_by(), city.getUpdate_by());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCity_id(), getCity_name(), getCountry_id(), getCreate_date_time(), getLast_update_time(), getCreated_by(), getUpdate_by());
    }

    @Override
    public String toString() {
        return "City{" +
                "city_id=" + city_id +
                ", city_str='" + city_name + '\'' +
                ", country_id=" + country_id +
                ", create_date_time=" + create_date_time +
                ", last_update_time='" + last_update_time + '\'' +
                ", created_by='" + created_by + '\'' +
                ", update_by='" + update_by + '\'' +
                '}';
    }
}
