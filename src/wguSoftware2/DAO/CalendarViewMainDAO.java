package wguSoftware2.DAO;

import wguSoftware2.models.Active_User;
import wguSoftware2.models.Appoinment_view_main;
import wguSoftware2.models.Customer_view_main;
import wguSoftware2.utils.Database_v3;

import java.sql.*;
import java.time.ZonedDateTime;
import java.util.ArrayList;

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

    public CalendarViewMainDAO(Database_v3 curr_db,Active_User au) {

        this.curr_db = curr_db;
        this.active_user = au;
    }


    public Appoinment_view_main create(Appoinment_view_main apv, Active_User active_user, Customer_view_main selectedCVM) throws SQLException, ClassNotFoundException {

        this.active_user = active_user;
        this.customer_view_main = selectedCVM;

        String title = apv.getTitle();
        String description = apv.getDescription();
        String location = apv.getLocation();
        String contact = apv.getCustomerName();
        String apt_type = apv.getAppointment_type();
        ZonedDateTime start_time_znd = apv.getStart_date_time_zdt();
        ZonedDateTime end_time_znd = apv.getEnd_date_time_zdt();

        Integer customerID = customer_view_main.getId();
        Integer userID = active_user.getActive_user_id();
        String url = "customer link";
        ZonedDateTime createDate = ZonedDateTime.now();
        String createdBy = active_user.getActive_user_name();
        ZonedDateTime lastUpdate = createDate;
        String  lastUpdateBy = createdBy;

        String sql_stmt = "INSERT INTO appointment " +
                "(customerId, userId, title, " +
                "description, location, contact, type, url, " +
                "start, end, createDate, createdBy, " +
                "lastUpdateBy) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";

        this.curr_db.dbConnect();
        Connection con = this.curr_db.getCon();
        PreparedStatement ps = con.prepareStatement(sql_stmt, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1,customerID);
        ps.setInt(2,userID);
        ps.setString(3,title);
        ps.setString(4,description);
        ps.setString(5,location);
        ps.setString(6,contact);
        ps.setString(7,apt_type);
        ps.setString(8,url);
        ps.setTimestamp(9,Timestamp.valueOf(start_time_znd.toLocalDateTime()));
        ps.setTimestamp(10,Timestamp.valueOf(end_time_znd.toLocalDateTime()));
        ps.setTimestamp(11,Timestamp.valueOf(createDate.toLocalDateTime()));
        ps.setString(12,active_user.getActive_user_name());
        ps.setString(13,active_user.getActive_user_name());



        System.out.println(ps.toString());
        rs = curr_db.dbExecuteUpdate(ps);
        Integer apt_id = null;
        if (rs.next()) {
            apt_id = rs.getInt("GENERATED_KEY");
        }

        apv.setId(apt_id);


        return apv;
    }

    public void delete(Appoinment_view_main selectedItem) throws SQLException, ClassNotFoundException {

        String sql_stmt = "DELETE FROM appointment WHERE appointmentId = ?";
        this.curr_db.dbConnect();
        Connection con = this.curr_db.getCon();
        PreparedStatement ps = con.prepareStatement(sql_stmt, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1,selectedItem.getId());
        rs = curr_db.dbExecuteUpdate(ps);


    }

    public ArrayList<String> getAppointmentTypeAndContact(Integer apt_id) throws SQLException, ClassNotFoundException {
        ArrayList<String> r_val = new ArrayList<>();
        Connection con;
        PreparedStatement ps;
        ResultSet rs;
        String sql_update_view = "SELECT appointment.type, appointment.contact from appointment where appointmentId = ?";

        this.curr_db.dbConnect();
        con = this.curr_db.getCon();
        ps = con.prepareStatement(sql_update_view, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, apt_id);

        rs = ps.executeQuery();


        if (rs.next()){
            r_val.add(rs.getString(1));
            r_val.add(rs.getString(2));
        }


        return r_val;
    }


    public Appoinment_view_main update(Appoinment_view_main apv, Active_User active_user, Customer_view_main selectedCVM) throws SQLException, ClassNotFoundException {

        this.active_user = active_user;
        this.customer_view_main = selectedCVM;

        String title = apv.getTitle();
        String description = apv.getDescription();
        String location = apv.getLocation();
        String contact = apv.getCustomerName();
        String apt_type = apv.getAppointment_type();
        ZonedDateTime start_time_znd = apv.getStart_date_time_zdt();
        ZonedDateTime end_time_znd = apv.getEnd_date_time_zdt();

        Integer customerID = active_user.getActive_user_id();
        Integer userID = customer_view_main.getId();
        String url = "customer link";
        ZonedDateTime createDate = ZonedDateTime.now();
        String createdBy = active_user.getActive_user_name();
        ZonedDateTime lastUpdate = createDate;
        String  lastUpdateBy = createdBy;

        String sql_stmt = "INSERT INTO appointment " +
                "(customerId, userId, title, " +
                "description, location, contact, type, url, " +
                "start, end, createDate, createdBy, " +
                "lastUpdateBy) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";

        this.curr_db.dbConnect();
        Connection con = this.curr_db.getCon();
        PreparedStatement ps = con.prepareStatement(sql_stmt, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1,customerID);
        ps.setInt(2,userID);
        ps.setString(3,title);
        ps.setString(4,description);
        ps.setString(5,location);
        ps.setString(6,contact);
        ps.setString(7,apt_type);
        ps.setString(8,url);
        ps.setTimestamp(9,Timestamp.valueOf(start_time_znd.toLocalDateTime()));
        ps.setTimestamp(10,Timestamp.valueOf(end_time_znd.toLocalDateTime()));
        ps.setTimestamp(11,Timestamp.valueOf(createDate.toLocalDateTime()));
        ps.setString(12,active_user.getActive_user_name());
        ps.setString(13,active_user.getActive_user_name());



        rs = curr_db.dbExecuteUpdate(ps);
        Integer apt_id = null;
        if (rs.next()) {
            apt_id = rs.getInt("GENERATED_KEY");
        }

        apv.setId(apt_id);


        return apv;
    }

    public Appoinment_view_main update(Appoinment_view_main apv, Active_User active_user) throws SQLException, ClassNotFoundException {

        this.active_user = active_user;

        String title = apv.getTitle();
        String description = apv.getDescription();
        String location = apv.getLocation();
        String contact = apv.getCustomerName();
        String apt_type = apv.getAppointment_type();
        Timestamp start_time_znd = apv.getStart_ts_ldt();
        Timestamp end_time_znd = apv.getEnd_ts_ldt();

        String url = "customer link";

        Timestamp lastUpdate =  Timestamp.valueOf(ZonedDateTime.now().toLocalDateTime());
        String  lastUpdateBy = active_user.getActive_user_name();


        String sql_stmt = "UPDATE appointment SET " +
                "userId = ?, title = ?, description = ?, location = ?," +
                " type = ?, start = ?, end = ?, lastUpdate = ?," +
                " lastUpdateBy = ? WHERE appointmentId = ?";

        this.curr_db.dbConnect();
        Connection con = this.curr_db.getCon();
        PreparedStatement ps = con.prepareStatement(sql_stmt, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1,active_user.getActive_user_id());
        ps.setString(2,title);
        ps.setString(3,description);
        ps.setString(4,location);
        ps.setString(5,apt_type);
        ps.setTimestamp(6,end_time_znd);
        ps.setTimestamp(7,start_time_znd);
        ps.setTimestamp(8,lastUpdate);
        ps.setString(9,active_user.getActive_user_name());
        ps.setInt(10,apv.getId());

        ps.execute();

        return apv;

    }


}
