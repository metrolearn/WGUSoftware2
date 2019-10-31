package wguSoftware2.utils;

import wguSoftware2.models.MyDateTime;

import java.sql.*;

/**
 * The type Utils.
 */
public class Utils {

    /**
     * The constant OPEN_HOURS.
     */
    public static final int OPEN_HOURS = 9;
    /**
     * The constant CLOSE_HOURS.
     */
    public static final int CLOSE_HOURS = 17;
    private DatabaseMain db = null;
    private Connection con = null;

    /**
     * Instantiates a new Utils.
     *
     * @throws SQLException           the sql exception
     * @throws ClassNotFoundException the class not found exception
     */
    public Utils() throws SQLException, ClassNotFoundException {
         db = new DatabaseMain(
                "52.206.157.109",
                "U05mJi",
                "U05mJi",
                "53688547099"
        );

    }

    /**
     * Time check error.
     *
     * @param start the start
     * @param end   the end
     * @throws Error the error
     */
    public void timeCheckError(MyDateTime start, MyDateTime end) throws Error {
        if (start.getLocal24hour() < OPEN_HOURS) {

            throw new Error("Start of appointment cannot be before open hours.");
        }
         if(end.getLocal24hour() > CLOSE_HOURS){

             throw new Error("End of appointment cannot be after close hours.");

         }
    }

    /**
     * Appointment overlap check.
     *
     * @param myStartDateTime the my start date time
     * @param myEndDateTime   the my end date time
     * @throws SQLException             the sql exception
     * @throws ClassNotFoundException   the class not found exception
     * @throws IllegalArgumentException the illegal argument exception
     */
    public void appointmentOverlapCheck(MyDateTime myStartDateTime, MyDateTime myEndDateTime) throws SQLException, ClassNotFoundException, IllegalArgumentException{


        String startTimeTS = myStartDateTime.getSimpleDateMySqlUTCTs();
        startTimeTS = "'"+startTimeTS+"'";
        String endTimeTS = myEndDateTime.getSimpleDateMySqlUTCTs();
        endTimeTS = "'"+endTimeTS+"'";


        String sql_str = "SELECT appointmentId from appointment WHERE" +
                " " + startTimeTS + " BETWEEN appointment.start and appointment.end " +
                "OR " +
                endTimeTS + " BETWEEN appointment.start and appointment.end;";

        System.out.println(sql_str);

        db.dbConnect();
        Connection con = db.getCon();
        PreparedStatement ps = con.prepareStatement(sql_str, Statement.RETURN_GENERATED_KEYS);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            throw new IllegalArgumentException(
                    "Schedule conflict: You cannot schedule appointments that overlap.");
        }


    }
}