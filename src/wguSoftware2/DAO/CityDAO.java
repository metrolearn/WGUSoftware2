package wguSoftware2.DAO;

import wguSoftware2.models.City;
import wguSoftware2.utils.Database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CityDAO extends CountryDAO {
    public CityDAO(Database dbc) {
        super(dbc);
    }

    private String sql_stm = null;
    private ResultSet rs = null;
    private Database dbc = null;
    private City city = null;


    public City create_City(City c) throws SQLException{



        return c;
    }
}
