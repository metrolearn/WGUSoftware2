package wguSoftware2.models;

import java.time.Instant;
import java.util.Date;
import java.util.Objects;

/**
 * The type Appointment.
 */
public class Appointment {

  private Integer appointment_id;
  private Integer customer_id;
  private Integer user_id;
  private String title;
  private String description;
  private String location;
  private String contact;
  private String type;
  private String url;
  private Date start_date_time;
  private Date end_date_time;
  private Date create_date_time;
  private String last_update_time;
  private String last_update_by;

  /**
   * Instantiates a new Appointment.
   *
   * @param title           the title
   * @param description     the description
   * @param location        the location
   * @param start_date_time the start date time
   * @param end_date_time   the end date time
   */
  public Appointment(String title, String description, String location,
      Date start_date_time, Date end_date_time) {
    this.title = title;
    this.description = description;
    this.location = location;
    this.start_date_time = start_date_time;
    this.end_date_time = end_date_time;
    this.create_date_time = Date.from(Instant.now());

  }

  /**
   * Gets appointment id.
   *
   * @return the appointment id
   */
  public Integer getAppointment_id() {
    return appointment_id;
  }

  /**
   * Sets appointment id.
   *
   * @param appointment_id the appointment id
   */
  public void setAppointment_id(Integer appointment_id) {
    this.appointment_id = appointment_id;
  }

  /**
   * Gets customer id.
   *
   * @return the customer id
   */
  public Integer getCustomer_id() {
    return customer_id;
  }

  /**
   * Sets customer id.
   *
   * @param customer_id the customer id
   */
  public void setCustomer_id(Integer customer_id) {
    this.customer_id = customer_id;
  }

  /**
   * Gets user id.
   *
   * @return the user id
   */
  public Integer getUser_id() {
    return user_id;
  }

  /**
   * Sets user id.
   *
   * @param user_id the user id
   */
  public void setUser_id(Integer user_id) {
    this.user_id = user_id;
  }

  /**
   * Gets title.
   *
   * @return the title
   */
  public String getTitle() {
    return title;
  }

  /**
   * Sets title.
   *
   * @param title the title
   */
  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * Gets description.
   *
   * @return the description
   */
  public String getDescription() {
    return description;
  }

  /**
   * Sets description.
   *
   * @param description the description
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * Gets location.
   *
   * @return the location
   */
  public String getLocation() {
    return location;
  }

  /**
   * Sets location.
   *
   * @param location the location
   */
  public void setLocation(String location) {
    this.location = location;
  }

  /**
   * Gets contact.
   *
   * @return the contact
   */
  public String getContact() {
    return contact;
  }

  /**
   * Sets contact.
   *
   * @param contact the contact
   */
  public void setContact(String contact) {
    this.contact = contact;
  }

  /**
   * Gets type.
   *
   * @return the type
   */
  public String getType() {
    return type;
  }

  /**
   * Sets type.
   *
   * @param type the type
   */
  public void setType(String type) {
    this.type = type;
  }

  /**
   * Gets url.
   *
   * @return the url
   */
  public String getUrl() {
    return url;
  }

  /**
   * Sets url.
   *
   * @param url the url
   */
  public void setUrl(String url) {
    this.url = url;
  }

  /**
   * Gets start date time.
   *
   * @return the start date time
   */
  public Date getStart_date_time() {
    return start_date_time;
  }

  /**
   * Sets start date time.
   *
   * @param start_date_time the start date time
   */
  public void setStart_date_time(Date start_date_time) {
    this.start_date_time = start_date_time;
  }

  /**
   * Gets end date time.
   *
   * @return the end date time
   */
  public Date getEnd_date_time() {
    return end_date_time;
  }

  /**
   * Sets end date time.
   *
   * @param end_date_time the end date time
   */
  public void setEnd_date_time(Date end_date_time) {
    this.end_date_time = end_date_time;
  }

  /**
   * Gets create date time.
   *
   * @return the create date time
   */
  public Date getCreate_date_time() {
    return create_date_time;
  }

  /**
   * Sets create date time.
   *
   * @param create_date_time the create date time
   */
  public void setCreate_date_time(Date create_date_time) {
    this.create_date_time = create_date_time;
  }

  /**
   * Gets last update time.
   *
   * @return the last update time
   */
  public String getLast_update_time() {
    return last_update_time;
  }

  /**
   * Sets last update time.
   *
   * @param last_update_time the last update time
   */
  public void setLast_update_time(String last_update_time) {
    this.last_update_time = last_update_time;
  }

  /**
   * Gets last update by.
   *
   * @return the last update by
   */
  public String getLast_update_by() {
    return last_update_by;
  }

  /**
   * Sets last update by.
   *
   * @param last_update_by the last update by
   */
  public void setLast_update_by(String last_update_by) {
    this.last_update_by = last_update_by;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Appointment)) {
      return false;
    }
    Appointment that = (Appointment) o;
    return Objects.equals(getAppointment_id(), that.getAppointment_id()) &&
        Objects.equals(getCustomer_id(), that.getCustomer_id()) &&
        Objects.equals(getUser_id(), that.getUser_id()) &&
        Objects.equals(getTitle(), that.getTitle()) &&
        Objects.equals(getDescription(), that.getDescription()) &&
        Objects.equals(getLocation(), that.getLocation()) &&
        Objects.equals(getContact(), that.getContact()) &&
        Objects.equals(getType(), that.getType()) &&
        Objects.equals(getUrl(), that.getUrl()) &&
        Objects.equals(getStart_date_time(), that.getStart_date_time()) &&
        Objects.equals(getEnd_date_time(), that.getEnd_date_time()) &&
        Objects.equals(getCreate_date_time(), that.getCreate_date_time()) &&
        Objects.equals(getLast_update_time(), that.getLast_update_time()) &&
        Objects.equals(getLast_update_by(), that.getLast_update_by());
  }

  @Override
  public int hashCode() {
    return Objects
        .hash(getAppointment_id(), getCustomer_id(), getUser_id(), getTitle(), getDescription(),
            getLocation(), getContact(), getType(), getUrl(), getStart_date_time(),
            getEnd_date_time(), getCreate_date_time(), getLast_update_time(), getLast_update_by());
  }

  /**
   * Create appointment db entry boolean.
   *
   * @param sql_statement the sql statement
   * @return the boolean
   */
  public Boolean create_appointment_db_entry(String sql_statement){
    Boolean r_val = Boolean.FALSE;
    String sql = "INSERT INTO appointment "
        + "(appointmentId, customerId, userId, title, description, location, contact, type, url,"
        + " start, end, createDate, createdBy, lastUpdate, lastUpdateBy) "
        + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    return r_val;
  }

  /**
   * Read appointment db entry boolean.
   *
   * @param sql_statement the sql statement
   * @return the boolean
   */
  public Boolean read_appointment_db_entry(String sql_statement){
    Boolean r_val = Boolean.FALSE;
    String sql = "SELECT appointmentId, customerId, userId, title, description, location, contact,"
        + " type, url, start, end, createDate, createdBy, lastUpdate, lastUpdateBy"
        + " FROM appointment WHERE appointmentId = ? ";

    return r_val;
  }

  /**
   * Update appointment db entry boolean.
   *
   * @param sql_statement the sql statement
   * @return the boolean
   */
  public Boolean update_appointment_db_entry(String sql_statement){
    Boolean r_val = Boolean.FALSE;
    String sql = "UPDATE appointment SET  customerId = ?, userId = ?, title = ?, description = ?,"
        + " location = ?, contact = ?, type = ?, url = ?, start = ?, end = ?, createDate = ?,"
        + " createdBy = ?, lastUpdate = ?, lastUpdateBy = ? WHERE appointmentId = ?";

    return r_val;
  }

  /**
   * Delete appointment db entry boolean.
   *
   * @param sql_statement the sql statement
   * @return the boolean
   */
  public Boolean delete_appointment_db_entry(String sql_statement){
    Boolean r_val = Boolean.FALSE;
    String sql = "DELETE FROM appointment WHERE appointmentId = ?";

    return r_val;
  }
}