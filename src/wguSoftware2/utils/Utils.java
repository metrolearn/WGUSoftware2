package wguSoftware2.utils;

import com.mysql.jdbc.MultiHostMySQLConnection;
import javafx.scene.control.Alert;
import wguSoftware2.models.MyDateTime;

import java.sql.*;

public class Utils {

    public static final int OPEN_HOURS = 9;
    public static final int CLOSE_HOURS = 17;
    private Database_v3 db = null;
    private Connection con = null;

    public Utils() throws SQLException, ClassNotFoundException {
         db = new Database_v3(
                "52.206.157.109",
                "U05mJi",
                "U05mJi",
                "53688547099"
        );

    }

    public void timeCheckError(MyDateTime start, MyDateTime end) throws Error {
        if (start.getLocal24hour() < OPEN_HOURS) {

            throw new Error("Start of appointment cannot be before open hours.");
        }
         if(end.getLocal24hour() > CLOSE_HOURS){

             throw new Error("End of appointment cannot be after close hours.");

         }
    }

    public void appointmentOverlapCheck(MyDateTime myStartDateTime, MyDateTime myEndDateTime) throws SQLException, ClassNotFoundException, IllegalArgumentException{
        String startTS = null;
        String endTs = null;

        String utcStartTimeStamp = myStartDateTime.getSimpleDateMySqlUTCTs();
        String utcEndTimeStamp = myEndDateTime.getSimpleDateMySqlUTCTs();

        String sql_stm;
        sql_stm = "SELECT* FROM appointment WHERE " + startTS + " BETWEEN appointment.start AND appointment.end OR " + endTs + " BETWEEN appointment.start AND appointment.end;";



        db.dbConnect();
        Connection con = db.getCon();
        PreparedStatement ps = con.prepareStatement(sql_stm, Statement.RETURN_GENERATED_KEYS);

        ResultSet rs = ps.executeQuery();

        if (!rs.next()) {
            throw new IllegalArgumentException(
                    "Schedule conflict: You cannot schedule appointments that overlap.");
        }


    }
}