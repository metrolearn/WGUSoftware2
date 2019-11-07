package wguSoftware2.utils;
import com.sun.rowset.CachedRowSetImpl;
import java.sql.*;

/**
 * The type Database main.
 */
public class DatabaseMain {


    private String JDBC_DRIVER = null;
    private String DB_URL_PREFIX = null;
    private String DB_IP = null;
    private String DB_NAME = null;
    private String DBUSER = null;
    private String DBPASS = null;
    private String DB_URL = null;
    private Connection con = null;

    /**
     * Instantiates a new Database main.
     *
     * @param DB_IP   the db ip
     * @param DB_NAME the db name
     * @param DBUSER  the dbuser
     * @param DBPASS  the dbpass
     */
    public DatabaseMain(String DB_IP, String DB_NAME, String DBUSER, String DBPASS) {
        this.JDBC_DRIVER = "com.mysql.jdbc.Driver";
        this.DB_IP = DB_IP;
        this.DB_NAME = DB_NAME;
        this.DBUSER = DBUSER;
        this.DBPASS = DBPASS;
        this.DB_URL = "jdbc:mysql://" + DB_IP + ":3306/" + DB_NAME;

    }

    //Connection
    //Connection String
    //String connStr = "jdbc:oracle:thin:Username/Password@IP:Port/SID";
    //Username=HR, Password=HR, IP=localhost, IP=1521, SID=xe
    //private static final String connStr = "jdbc:oracle:thin:HR/HR@localhost:1521/xe";
    //Connect to DB

    /**
     * Db connect.
     *
     * @throws SQLException           the sql exception
     * @throws ClassNotFoundException the class not found exception
     */
    public void dbConnect() throws SQLException, ClassNotFoundException {
        //Setting Oracle JDBC Driver
        try {
            Class.forName(this.JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your Oracle JDBC Driver?");
            e.printStackTrace();
            throw e;
        }

        System.out.println("Oracle JDBC Driver Registered!");

        //Establish the Oracle Connection using Connection String
        try {
            this.con = DriverManager.getConnection(DB_URL,this.DBUSER,this.DBPASS);
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console" + e);
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Db disconnect.
     *
     * @throws SQLException the sql exception
     */
//Close Connection
    public  void dbDisconnect() throws SQLException {
        try {
            if (this.con != null && !this.con.isClosed()) {
                this.con.close();
            }
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Db execute query result set.
     *
     * @param ps_Stmt the ps stmt
     * @return the result set
     * @throws SQLException           the sql exception
     * @throws ClassNotFoundException the class not found exception
     */
    public  ResultSet dbExecuteQuery(PreparedStatement ps_Stmt) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = null;
        CachedRowSetImpl crs = null;
        try {
            System.out.println("Select statement: " + ps_Stmt + "\n");


            resultSet = ps_Stmt.executeQuery();
            crs = new CachedRowSetImpl();
            crs.populate(resultSet);
        } catch (SQLException e) {
            System.out.println("Problem occurred at executeQuery operation : " + e);
            throw e;
        } finally {
            if (resultSet != null) {
                //Close resultSet
                resultSet.close();
            }
            dbDisconnect();
        }
        return crs;
    }

    /**
     * Db execute update result set.
     *
     * @param ps_Stmt the ps stmt
     * @return the result set
     * @throws SQLException the sql exception
     */
//DB Execute Update (For Update/Insert/Delete) Operation
    public ResultSet dbExecuteUpdate(PreparedStatement ps_Stmt) throws SQLException {
        ResultSet resultSet = null;
        CachedRowSetImpl crs = null;
        try {
            System.out.println("Select statement: " + ps_Stmt + "\n");


            ps_Stmt.executeUpdate();
            resultSet = ps_Stmt.getGeneratedKeys();
            crs = new CachedRowSetImpl();
            crs.populate(resultSet);
        } catch (SQLException e) {
            System.out.println("Problem occurred at executeQuery operation : " + e);
            throw e;
        } finally {
            if (resultSet != null) {
                //Close resultSet
                resultSet.close();
            }
            dbDisconnect();
        }
        return crs;
    }

    /**
     * Gets con.
     *
     * @return the con
     */
    public Connection getCon() {
        return con;
    }
}