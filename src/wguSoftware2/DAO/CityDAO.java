package wguSoftware2.DAO;

import wguSoftware2.models.City;
import wguSoftware2.utils.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZonedDateTime;

public class CityDAO  extends CountryDAO {

    private String sql_stm = null;
    private ResultSet rs = null;
    private Database dbc = null;
    private City city = null;


    public CityDAO(Database dbc) {
        super(dbc);
        this.dbc = dbc;
    }




    public City create_City(City c) throws SQLException{


        String city_name = c.getCity_str();
        Integer country_id = c.getCountry_id();
        String create_date = String.valueOf(ZonedDateTime.now());
        String created_by = c.getCreated_by();
        ZonedDateTime lastupdate = ZonedDateTime.parse(create_date);
        String lastupate_by = created_by;


        this.sql_stm = "INSERT into city (city, countryId, createDate, createdBy, lastUpdate, lastUpdateBy)\n" +
                "VALUES ('" + city_name + "','" + country_id + "','" + create_date + "','" + created_by + "','lastupdate','lastupdateBY')"


        execute_sql_stmt();


        try {
            ResultSet country_rs_pk = this.dbc.get_mysql_resultSet("SELECT LAST_INSERT_ID();");
            while (country_rs_pk.next())
                c.setCountry_id(country_rs_pk.getInt(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return c;

    }

    private void execute_sql_stmt() {
        try {
            this.rs = dbc.get_mysql_resultSet(sql_stm);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
