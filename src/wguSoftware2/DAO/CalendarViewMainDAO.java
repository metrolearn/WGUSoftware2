package wguSoftware2.DAO;

import wguSoftware2.models.Active_User;
import wguSoftware2.models.Appoinment_view_main;
import wguSoftware2.models.Customer_view_main;
import wguSoftware2.utils.Database_v3;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.ArrayList;

import static java.time.ZoneOffset.UTC;

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


    public Appoinment_view_main create(Appoinment_view_main avm, Active_User active_user, Customer_view_main selectedCVM) throws SQLException, ClassNotFoundException {

        this.active_user = active_user;
        this.customer_view_main = selectedCVM;

        String title = avm.getTitle();
        String description = avm.getDescription();
        String location = avm.getLocation();
        String contact = avm.getCustomerName();
        String apt_type = avm.getAppointment_type();
        Timestamp start_time_ts = avm.getStart_date_time().getUTCTimeStamp();
        Timestamp end_time_ts = avm.getEnd_date_time().getUTCTimeStamp();

        Integer customerID = customer_view_main.getId();
        Integer userID = active_user.getActive_user_id();


        String url = "customer link";
        Timestamp create_date_ts = Timestamp.valueOf(ZonedDateTime.now().toLocalDateTime());
        String createdBy = active_user.getActive_user_name();

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
        ps.setTimestamp(9,start_time_ts);
        ps.setTimestamp(10,end_time_ts);
        ps.setTimestamp(11,create_date_ts);
        ps.setString(12,createdBy);
        ps.setString(13,active_user.getActive_user_name());



        System.out.println(ps.toString());
        rs = curr_db.dbExecuteUpdate(ps);
        Integer apt_id = null;
        if (rs.next()) {
            apt_id = rs.getInt("GENERATED_KEY");
        }

        avm.setId(apt_id);


        return avm;
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


    public Appoinment_view_main update(Appoinment_view_main avm, Active_User active_user, Customer_view_main selectedCVM) throws SQLException, ClassNotFoundException {

        this.active_user = active_user;
        this.customer_view_main = selectedCVM;

        String title = avm.getTitle();
        String description = avm.getDescription();
        String location = avm.getLocation();
        String contact = avm.getCustomerName();
        String apt_type = avm.getAppointment_type();
        Timestamp start_time_ts_utc = avm.getStart_date_time().getUTCTimeStamp();
        Timestamp end_time_ts_utc = avm.getEnd_date_time().getUTCTimeStamp();
        Timestamp create_date_ts_utc = avm.getStart_date_time().getNowUTCTS();
        Timestamp last_update = create_date_ts_utc;

        Integer customerID = active_user.getActive_user_id();
        Integer userID = customer_view_main.getId();
        String url = "customer link";
        String active_user_name = active_user.getActive_user_name();
        String createdBy = active_user_name;
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
        ps.setTimestamp(9,start_time_ts_utc);
        ps.setTimestamp(10,end_time_ts_utc);
        ps.setTimestamp(11,create_date_ts_utc);
        ps.setString(12, active_user_name);
        ps.setString(13, active_user_name);



        rs = curr_db.dbExecuteUpdate(ps);
        Integer apt_id = null;
        if (rs.next()) {
            apt_id = rs.getInt("GENERATED_KEY");
        }

        avm.setId(apt_id);


        return avm;
    }

    public Appoinment_view_main update(Appoinment_view_main avm, Active_User active_user) throws SQLException, ClassNotFoundException {

        this.active_user = active_user;

        String title = avm.getTitle();
        String description = avm.getDescription();
        String location = avm.getLocation();
        String contact = avm.getCustomerName();
        String apt_type = avm.getAppointment_type();

        Timestamp start_time_ts_utc = avm.getStart_date_time().getUTCTimeStamp();
        Timestamp end_time_ts_utc = avm.getEnd_date_time().getUTCTimeStamp();
        Timestamp update_time_ts_utc = avm.getStart_date_time().getNowUTCTS();

        String active_user_name = active_user.getActive_user_name();
        String  lastUpdateBy = active_user_name;


        String sql_stmt = "" +
                "UPDATE appointment " +
                "SET title        = ?," +
                "    description  = ?," +
                "    location     = ?," +
                "    contact      = ?," +
                "    type         = ?," +
                "    start        = ?," +
                "    end          = ?," +
                "    lastUpdate   = ?," +
                "    lastUpdateBy = ?" +
                "WHERE appointmentId = ?";

        this.curr_db.dbConnect();
        Connection con = this.curr_db.getCon();
        PreparedStatement ps = con.prepareStatement(sql_stmt, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1,title);
        ps.setString(2,description);
        ps.setString(3,location);
        ps.setString(4,contact);
        ps.setString(5,apt_type);
        ps.setTimestamp(6,start_time_ts_utc);
        ps.setTimestamp(7,end_time_ts_utc);
        ps.setTimestamp(8,update_time_ts_utc);
        ps.setString(9, active_user_name);
        ps.setInt(10,avm.getId());

        ps.execute();

        return avm;

    }

    private Timestamp convertZTDtoTSUTC(ZonedDateTime zdt) {
        ZonedDateTime zdt_utc = ZonedDateTime.of(zdt.toLocalDateTime(), UTC);
        Timestamp ts_utc = Timestamp.valueOf(zdt_utc.toLocalDateTime());
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return Timestamp.valueOf(dateFormat.format(ts_utc));

    }

    public Integer getNextAppointmentID() throws SQLException, ClassNotFoundException {
        this.curr_db.dbConnect();
        Connection con = this.curr_db.getCon();

        String sqlStmt = "SELECT AUTO_INCREMENT FROM information_schema.tables" +
                " WHERE table_name = 'appointment' AND " +
                "table_schema = DATABASE( );";

        PreparedStatement ps = con.prepareStatement(sqlStmt, Statement.RETURN_GENERATED_KEYS);


        rs = curr_db.dbExecuteQuery(ps);
        Integer next_id = null;
        while (rs.next()){
           next_id = rs.getInt(1);
        }
        return next_id;




    }


}
