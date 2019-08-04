package wguSoftware2.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

/**
 * The type Database.
 */
public class Database {

  private String database_driver = "com.mysql.jdbc.Driver";
  private String database_url = "jdbc:mysql://";
  private String database_name;
  private String username;
  private String password;
  private String max_pool = "300"; // set your own limit
  private Connection connection;
  private Properties properties;

  /**
   * Instantiates a new Database.
   *
   * @param database_ip the database ip
   * @param database_name the database name
   * @param username the username
   * @param password the password
   */
  public Database(String database_ip, String database_name, String username,
      String password) {
    this.database_url += database_ip;
    this.database_name = database_name;
    this.username = username;
    this.password = password;

    this.properties = new Properties();
    this.properties.setProperty("user", this.username);
    this.properties.setProperty("password", this.password);
    this.properties.setProperty("MaxPooledStatements", this.max_pool);

  }

  /**
   * Create mysql db connection connection.
   *
   * @return the connection
   */
  public Connection create_mysql_db_connection() {
    if (connection == null) {
      try {
        Class.forName(this.database_driver).newInstance();
        connection = DriverManager.getConnection(this.database_url, this.properties);
      } catch (ClassNotFoundException | SQLException | IllegalAccessException | InstantiationException exception) {
        exception.printStackTrace();
      }
    }
    return connection;
  }

  /**
   * Disconnect from mysql db.
   */
  public void disconnect_from_mysql_db() {
    if (connection != null) {
      try {
        connection.close();
        connection = null;
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }
public void send_mysql_command(String sql) throws SQLException {
  Connection c = this.connection;
  try {
    PreparedStatement statement = c.prepareStatement(sql);
  } finally {
    c.close();
    this.disconnect_from_mysql_db();
  }

}

public boolean seed_user_names_into_db(){


  String csvFile = "src/wguSoftware2/seed_data/username_list.csv";
  BufferedReader br = null;
  String line = "";
  String cvsSplitBy = ",";

  try {

    br = new BufferedReader(new FileReader(csvFile));
    while ((line = br.readLine()) != null) {

      // use comma as separator
      String[] country = line.split(cvsSplitBy);

      for (String s: country
      ) {
        System.out.print(s);
      }


    }

  } catch (IOException e) {
    e.printStackTrace();
  } finally {
    if (br != null) {
      try {
        br.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }






    return true;
}





}