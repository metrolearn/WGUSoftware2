package wguSoftware2.models;

import wguSoftware2.utils.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

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


    public Customer_view_main(Database dbc) {
        this.dbc = dbc;
        this.country = new Country();
    }

    public Customer_view_main(Integer id, String name, String address, String alt_address, String city_name,
                              String zip, String country, String phone) throws SQLException {
        this.id = id;
        this.name = name;
        this.address = address;
        this.alt_address = alt_address;
        this.city_name = city_name;
        this.zip = zip;
        this.country_name = country;
        this.phone = phone;



        this.sql_stm = "select * from country where country = '" + country_name + "'";
        execute_sql_stmt();

        if (!this.rs.next()){
            // no country found.
            System.out.println("No country found, we need to add this one.");
        }else {
            System.out.println("Country found, we need to add this one.");
            while (rs.next()){
                this.country.setCountry_id(rs.getInt("countryId"));
            }
        }

        String city_id = "4";
        String city_name_ = "New Estel";
        this.sql_stm = "select * from city \n" +
                "    inner join country c on city.countryId = c.countryId\n" +
                "where cityId = " + city_id + " \n" +
                "  and city = '" + city_name_ + "'";





    }

    public Customer_view_main(Integer customer_id, String customer_name, String address, String phone) {

        this.id = customer_id;
        this.name = customer_name;
        this.address = address;
        this.phone = phone;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAlt_address() {
        return alt_address;
    }

    public void setAlt_address(String alt_address) {
        this.alt_address = alt_address;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    public String getPhone() {
        return phone;
    }

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

