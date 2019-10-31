package wguSoftware2.models;

import wguSoftware2.utils.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

/**
 * The type Customer view main.
 */
public class Customer_view_main {

    private Integer id;
    private String name;
    private String address;
    private String alt_address;
    private String city_name;
    private String zip;
    private String country_name;
    private String phone;

    private Country country;

    private Database dbc = null;
    private String sql_stm = null;
    private ResultSet rs = null;


    /**
     * Instantiates a new Customer view main.
     *
     * @param id          the id
     * @param name        the name
     * @param address     the address
     * @param alt_address the alt address
     * @param city_name   the city name
     * @param zip         the zip
     * @param country     the country
     * @param phone       the phone
     */
    public Customer_view_main(Integer id, String name, String address, String alt_address, String city_name,
                              String zip, String country, String phone){
        this.id = id;
        this.name = name;
        this.address = address;
        this.alt_address = alt_address;
        this.city_name = city_name;
        this.zip = zip;
        this.country_name = country;
        this.phone = phone;


    }


    /**
     * Instantiates a new Customer view main.
     *
     * @param customer_id   the customer id
     * @param customer_name the customer name
     * @param address       the address
     * @param phone         the phone
     */
    public Customer_view_main(Integer customer_id, String customer_name, String address, String phone) {

        this.id = customer_id;
        this.name = customer_name;
        this.address = address;
        this.phone = phone;

    }

    /**
     * Instantiates a new Customer view main.
     *
     * @param name        the name
     * @param address     the address
     * @param alt_address the alt address
     * @param city_name   the city name
     * @param zip         the zip
     * @param country     the country
     * @param phone       the phone
     */
    public Customer_view_main(String name, String address, String alt_address, String city_name,
                              String zip, String country, String phone){
        this.name = name;
        this.address = address;
        this.alt_address = alt_address;
        this.city_name = city_name;
        this.zip = zip;
        this.country_name = country;
        this.phone = phone;


    }

    /**
     * Instantiates a new Customer view main.
     *
     * @param customerId the customer id
     */
    public Customer_view_main(int customerId) {
        this.id = customerId;
    }


    /**
     * Gets id.
     *
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
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
     * Gets alt address.
     *
     * @return the alt address
     */
    public String getAlt_address() {
        return alt_address;
    }

    /**
     * Sets alt address.
     *
     * @param alt_address the alt address
     */
    public void setAlt_address(String alt_address) {
        this.alt_address = alt_address;
    }

    /**
     * Gets city name.
     *
     * @return the city name
     */
    public String getCity_name() {
        return city_name;
    }

    /**
     * Sets city name.
     *
     * @param city_name the city name
     */
    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    /**
     * Gets zip.
     *
     * @return the zip
     */
    public String getZip() {
        return zip;
    }

    /**
     * Sets zip.
     *
     * @param zip the zip
     */
    public void setZip(String zip) {
        this.zip = zip;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer_view_main)) return false;
        Customer_view_main that = (Customer_view_main) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getAddress(), that.getAddress()) &&
                Objects.equals(getAlt_address(), that.getAlt_address()) &&
                Objects.equals(getCity_name(), that.getCity_name()) &&
                Objects.equals(getZip(), that.getZip()) &&
                Objects.equals(getCountry_name(), that.getCountry_name()) &&
                Objects.equals(getPhone(), that.getPhone());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getAddress(), getAlt_address(),
                getCity_name(), getZip(), getCountry_name(), getPhone());
    }


    private void execute_sql_stmt() {
        try {
            this.rs = dbc.get_mysql_resultSet(sql_stm);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

