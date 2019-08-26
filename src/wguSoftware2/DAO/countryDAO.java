package wguSoftware2.DAO;

import wguSoftware2.models.Country;
import wguSoftware2.utils.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZonedDateTime;

public class countryDAO {

    private String sql_stm = null;
    private ResultSet rs = null;
    private Database dbc = null;
    private Country country = null;

    public countryDAO(Database dbc) {
        this.dbc = dbc;
    }


    public Country create_country(Country c) throws SQLException {

        String country_str = c.getCountry_str();
        String create_date = c.getConverter().ldt_to_mysql_dt_str(ZonedDateTime.now());
        String created_by = c.getCreate_by();
        String lastupdate = create_date;
        String lastupate_by = created_by;
        String sql_str = "INSERT INTO country (country, createDate, createdBy, lastUpdate, lastUpdateBy) " +
                "VALUES ('" + country_str + "', '" + create_date + "', '" + created_by + "'," +
                " '" + lastupdate + "', '" + lastupate_by + "')";

        try {
            dbc.send_mysql_command(sql_str);

            try {
                ResultSet country_rs_pk = this.dbc.get_mysql_resultSet("SELECT LAST_INSERT_ID();");
                while (country_rs_pk.next())
                    c.setCountry_id(country_rs_pk.getInt(1));
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return c;
    }


    public Country getCountry_by_ID(String id) throws SQLException {

        try {
            this.sql_stm = "SELECT * from country where countryId = " + id + ";";
            this.rs = dbc.get_mysql_resultSet(sql_stm);
            return getCountry(this.rs);
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }


    }

    private Country getCountry(ResultSet rs) throws SQLException {
        this.country = new Country();

        while (this.rs.next()) {

            country.setCountry_id(rs.getInt("countryID"));
            country.setCountry_str(rs.getString("country"));
            country.setCreate_date_time(
                    ZonedDateTime.from(rs.getTimestamp("createDate").toLocalDateTime()));
            country.setCreate_by(rs.getString("createBy"));
            country.setLast_update_time(
                    ZonedDateTime.from(rs.getTimestamp("lastUpdate").toLocalDateTime()));
            country.setLast_update_by(rs.getString("lastUpdateBy"));


        }

        return country;
    }

    private Country updateCountry (Country c) throws SQLException {

        String country_name = c.getCountry_str();
        String current_user = c.getActive_user();
        String country_id = String.valueOf(c.getCountry_id());
        this.sql_stm = "update country c\n" +
                "set country = '" + country_name + "', lastUpdate = now(), lastUpdateBy = '" + current_user + "'\n" +
                "where countryId = '" + country_id + "';";

        try {
            this.rs = dbc.get_mysql_resultSet(sql_stm);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return c;
    }

}
