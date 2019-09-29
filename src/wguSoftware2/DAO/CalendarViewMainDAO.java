package wguSoftware2.DAO;

import sun.management.MappedMXBeanType;
import wguSoftware2.models.Active_User;
import wguSoftware2.models.Appoinment_view_main;
import wguSoftware2.models.Customer_view_main;
import wguSoftware2.utils.Database_v3;

import java.sql.*;
import java.time.ZonedDateTime;

public class CalendarViewMainDAO {

    private ResultSet rs = null;
    private Database_v3 curr_db = null;
    private Customer_view_main cvm = null;
    private Active_User active_user = null;
    private  Customer_view_main customer_view_main = null;

    private Integer country_id;
    private Integer city_id;
    private Integer address_id = null;
    private Integer customer_id = null;
    private boolean activate_customer = Boolean.parseBoolean(null);

    public CalendarViewMainDAO(Database_v3 curr_db) {

        this.curr_db = curr_db;
    }


    public Appoinment_view_main create(Appoinment_view_main apv, Active_User active_user, Customer_view_main selectedCVM) throws SQLException, ClassNotFoundException {

        this.active_user = active_user;
        this.customer_view_main = selectedCVM;

        String title = apv.getTitle();
        String description = apv.getDescription();
        String location = apv.getLocation();
        String contact = apv.getContact();
        String apt_type = apv.getAppointment_type();
        ZonedDateTime start_time_znd = apv.getStart_date_time();
        ZonedDateTime end_time_znd = apv.getEnd_date_time();

        Integer customerID = active_user.getActive_user_id();
        Integer userID = customer_view_main.getId();
        String url = "www.link.com";
        ZonedDateTime createDate = ZonedDateTime.now();
        String createdBy = active_user.getActive_user_name();
        ZonedDateTime lastUpdate = createDate;
        String  lastUpdateBy = createdBy;

        String sql_stmt = "INSERT INTO appointment " +
                "(customerId, userId, title, " +
                "description, location, contact, type, url, " +
                "start, end, createDate, createdBy, " +
                "lastUpdateBy) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        this.curr_db.dbConnect();
        Connection con = this.curr_db.getCon();
        PreparedStatement ps = con.prepareStatement(sql_stmt, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1,customerID);
        ps.setString(2,title);
        ps.setString(3,description);
        ps.setString(4,location);
        ps.setString(5,contact);
        ps.setString(6,apt_type);
        ps.setString(7,url);
        ps.setTimestamp(8,Timestamp.valueOf(start_time_znd.toLocalDateTime()));
        ps.setTimestamp(9,Timestamp.valueOf(end_time_znd.toLocalDateTime()));
        ps.setTimestamp(10,Timestamp.valueOf(createDate.toLocalDateTime()));
        ps.setString(11,active_user.getActive_user_name());
        ps.setString(12,active_user.getActive_user_name());



        rs = curr_db.dbExecuteUpdate(ps);


        rs = curr_db.dbExecuteUpdate(ps);
        Integer apt_id = null;
        if (rs.next()) {
            apt_id = rs.getInt("GENERATED_KEY");
        }

        apv.setAptID(apt_id);


        return apv;
    }
}
