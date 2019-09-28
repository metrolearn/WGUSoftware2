package wguSoftware2.DAO;

import sun.management.MappedMXBeanType;
import wguSoftware2.models.Appoinment_view_main;
import wguSoftware2.models.Customer_view_main;
import wguSoftware2.utils.Database_v3;

import java.sql.ResultSet;
import java.time.ZonedDateTime;

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


    public Appoinment_view_main create(Appoinment_view_main apv) {


        String title = apv.getTitle();
        String description = apv.getDescription();
        String location = apv.getLocation();
        String contact = apv.getContact();
        String apt_type = apv.getAppointment_type();
        ZonedDateTime start_time_znd = apv.getStart_date_time();
        ZonedDateTime end_time_znd = apv.getEnd_date_time();

        Integer customerID = null;
        Integer userID = null;
        String url = null;
        ZonedDateTime createDate = null;
        String createdBy = null;
        ZonedDateTime lastUpdate = null;
        String  lastUpdateBy = null;






        return apv;
    }
}
