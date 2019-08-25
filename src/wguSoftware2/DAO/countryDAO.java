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

    public Country searchCountry(String id) throws SQLException {

        try {
            this.sql_stm = "SELECT * from country where countryId = " + id + ";";
            this.rs =   dbc.get_mysql_resultSet(sql_stm);
            return getCountry(this.rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    private Country getCountry(ResultSet rs) throws SQLException {
        this.country = new Country();

        while(this.rs.next()){

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

}
