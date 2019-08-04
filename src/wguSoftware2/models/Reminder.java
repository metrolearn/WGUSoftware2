package wguSoftware2.models;

import java.util.Date;
import java.util.Objects;

/**
 * The type Reminder.
 *
 * TABLE `reminder` ( `reminderId` int(10) NOT NULL, `reminderDate` datetime NOT NULL,
 * `snoozeIncrement` int(11) NOT NULL, `snoozeIncrementTypeId` int(11) NOT NULL, `appointmentId`
 * int(10) NOT NULL, `createdBy` varchar(45) NOT NULL, `createdDate` datetime NOT NULL,
 * `remindercol` varchar(45) NOT NULL, PRIMARY KEY (`reminderId`) ) ENGINE=InnoDB DEFAULT
 * CHARSET=latin1
 */
public class Reminder {

  private Integer reminder_id;
  private Date reminder_date_time;
  private Integer snooze_increment;
  private Integer snooze_increment_type_id;
  private Integer appointment_id;
  private String  created_by;
  private Date create_date_time;
  private String reminder_color;

  /**
   * Instantiates a new Reminder.
   */
  public Reminder() {
  }

  /**
   * Gets reminder id.
   *
   * @return the reminder id
   */
  public Integer getReminder_id() {
    return reminder_id;
  }

  /**
   * Sets reminder id.
   *
   * @param reminder_id the reminder id
   */
  public void setReminder_id(Integer reminder_id) {
    this.reminder_id = reminder_id;
  }

  /**
   * Gets reminder date time.
   *
   * @return the reminder date time
   */
  public Date getReminder_date_time() {
    return reminder_date_time;
  }

  /**
   * Sets reminder date time.
   *
   * @param reminder_date_time the reminder date time
   */
  public void setReminder_date_time(Date reminder_date_time) {
    this.reminder_date_time = reminder_date_time;
  }

  /**
   * Gets snooze increment.
   *
   * @return the snooze increment
   */
  public Integer getSnooze_increment() {
    return snooze_increment;
  }

  /**
   * Sets snooze increment.
   *
   * @param snooze_increment the snooze increment
   */
  public void setSnooze_increment(Integer snooze_increment) {
    this.snooze_increment = snooze_increment;
  }

  /**
   * Gets snooze increment type id.
   *
   * @return the snooze increment type id
   */
  public Integer getSnooze_increment_type_id() {
    return snooze_increment_type_id;
  }

  /**
   * Sets snooze increment type id.
   *
   * @param snooze_increment_type_id the snooze increment type id
   */
  public void setSnooze_increment_type_id(Integer snooze_increment_type_id) {
    this.snooze_increment_type_id = snooze_increment_type_id;
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
   * Gets created by.
   *
   * @return the created by
   */
  public String getCreated_by() {
    return created_by;
  }

  /**
   * Sets created by.
   *
   * @param created_by the created by
   */
  public void setCreated_by(String created_by) {
    this.created_by = created_by;
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
   * Gets reminder color.
   *
   * @return the reminder color
   */
  public String getReminder_color() {
    return reminder_color;
  }

  /**
   * Sets reminder color.
   *
   * @param reminder_color the reminder color
   */
  public void setReminder_color(String reminder_color) {
    this.reminder_color = reminder_color;
  }

  @Override
  public String toString() {
    return "Reminder{" +
        "reminder_id=" + reminder_id +
        ", reminder_date_time=" + reminder_date_time +
        ", snooze_increment=" + snooze_increment +
        ", snooze_increment_type_id=" + snooze_increment_type_id +
        ", appointment_id=" + appointment_id +
        ", created_by='" + created_by + '\'' +
        ", create_date_time=" + create_date_time +
        ", reminder_color='" + reminder_color + '\'' +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Reminder)) {
      return false;
    }
    Reminder reminder = (Reminder) o;
    return Objects.equals(getReminder_id(), reminder.getReminder_id()) &&
        Objects.equals(getReminder_date_time(), reminder.getReminder_date_time()) &&
        Objects.equals(getSnooze_increment(), reminder.getSnooze_increment()) &&
        Objects
            .equals(getSnooze_increment_type_id(), reminder.getSnooze_increment_type_id()) &&
        Objects.equals(getAppointment_id(), reminder.getAppointment_id()) &&
        Objects.equals(getCreated_by(), reminder.getCreated_by()) &&
        Objects.equals(getCreate_date_time(), reminder.getCreate_date_time()) &&
        Objects.equals(getReminder_color(), reminder.getReminder_color());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getReminder_id(), getReminder_date_time(), getSnooze_increment(),
        getSnooze_increment_type_id(), getAppointment_id(), getCreated_by(), getCreate_date_time(),
        getReminder_color());
  }

  /**
   * Create reminder db entry boolean.
   *
   * @param sql_statement the sql statement
   * @return the boolean
   */
  public Boolean create_reminder_db_entry(String sql_statement){
    Boolean r_val = Boolean.FALSE;
    String sql = "INSERT INTO reminder (reminderId, reminderDate, snoozeIncrement, "
        + "snoozeIncrementTypeId, appointmentId, createdBy, createdDate, remindercol) "
        + "VALUES (?,?,?,?,?,?,?,?)";
    return r_val;
  }

  /**
   * Read reminder db entry boolean.
   *
   * @param sql_statement the sql statement
   * @return the boolean
   */
  public Boolean read_reminder_db_entry(String sql_statement){
    Boolean r_val = Boolean.FALSE;
    String sql = "SELECT reminderId, reminderDate, snoozeIncrement, snoozeIncrementTypeId, "
        + "appointmentId, createdBy, createdDate, remindercol FROM reminder WHERE reminderId = ?";
    return r_val;
  }

  /**
   * Update reminder db entry boolean.
   *
   * @param sql_statement the sql statement
   * @return the boolean
   */
  public Boolean update_reminder_db_entry(String sql_statement){
    Boolean r_val = Boolean.FALSE;
    String sql = "UPDATE reminder SET  reminderDate = ?, snoozeIncrement = ?, "
        + "snoozeIncrementTypeId = ?, appointmentId = ?, createdBy = ?, createdDate = ?, "
        + "remindercol = ? WHERE reminderId = ?";
    return r_val;
  }

  /**
   * Delete reminder db entry boolean.
   *
   * @param sql_statement the sql statement
   * @return the boolean
   */
  public Boolean delete_reminder_db_entry(String sql_statement){
    Boolean r_val = Boolean.FALSE;
    String sql = "DELETE FROM reminder WHERE reminderId = ?";
    return r_val;
  }


}
