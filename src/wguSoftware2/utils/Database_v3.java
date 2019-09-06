package wguSoftware2.utils;
import com.sun.rowset.CachedRowSetImpl;
import java.sql.*;
public class Database_v3 {


    private String JDBC_DRIVER = null;
    private String DB_URL_PREFIX = null;
    private String DB_IP = null;
    private String DB_NAME = null;
    private String DBUSER = null;
    private String DBPASS = null;
    private String DB_URL = null;
    private Connection con = null;

    public Database_v3(String DB_IP, String DB_NAME, String DBUSER, String DBPASS) {
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

    //DB Execute Query Operation
    public  ResultSet dbExecuteQuery(String queryStmt) throws SQLException, ClassNotFoundException {
        //Declare statement, resultSet and CachedResultSet as null
        Statement stmt = null;
        ResultSet resultSet = null;
        CachedRowSetImpl crs = null;
        try {
            //Connect to DB (Establish Oracle Connection)
            this.dbConnect();
            System.out.println("Select statement: " + queryStmt + "\n");

            //Create statement
            stmt = con.createStatement();

            //Execute select (query) operation
            resultSet = stmt.executeQuery(queryStmt);

            //CachedRowSet Implementation
            //In order to prevent "java.sql.SQLRecoverableException: Closed Connection: next" error
            //We are using CachedRowSet
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
            if (stmt != null) {
                //Close Statement
                stmt.close();
            }
            //Close connection
            dbDisconnect();
        }
        //Return CachedRowSet
        return crs;
    }

    //DB Execute Update (For Update/Insert/Delete) Operation
    public  void dbExecuteUpdate(String sqlStmt) throws SQLException, ClassNotFoundException {
        //Declare statement as null
        Statement stmt = null;
        try {
            //Connect to DB (Establish Oracle Connection)
            dbConnect();
            //Create Statement
            stmt = this.con.createStatement();
            //Run executeUpdate operation with given sql statement
            stmt.executeUpdate(sqlStmt);
        } catch (SQLException e) {
            System.out.println("Problem occurred at executeUpdate operation : " + e);
            throw e;
        } finally {
            if (stmt != null) {
                //Close statement
                stmt.close();
            }
            //Close connection
            dbDisconnect();
        }
    }
}