package wguSoftware2.DAO;

import wguSoftware2.models.Customer_view_main;
import wguSoftware2.utils.Database_v3;

import java.sql.ResultSet;

public class CalendarViewMainDAO {

    private ResultSet rs = null;
    private Database_v3 curr_db = null;
    private Customer_view_main cvm = null;
    private String active_user_name = null;

    private Integer country_id;
    private Integer city_id;
    private Integer address_id = null;
    private Integer customer_id = null;
    private boolean activate_customer = Boolean.parseBoolean(null);



}
