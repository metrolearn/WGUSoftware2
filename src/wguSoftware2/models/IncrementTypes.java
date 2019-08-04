package wguSoftware2.models;

import java.util.Objects;

/**
 * The type Increment types. This class is intended to to abstract the following mysql db table.
 * table incrementtypes ( incrementTypeId int not null primary key, incrementTypeDescription
 * varchar(45) not null );
 */
public class IncrementTypes {

  private Integer increment_type_id;
  private String  increment_type_description;

  /**
   * Instantiates a new Increment types.
   */
  public IncrementTypes() {
  }

  /**
   * Gets increment type id.
   *
   * @return the increment type id
   */
  public Integer getIncrement_type_id() {
    return increment_type_id;
  }

  /**
   * Sets increment type id.
   *
   * @param increment_type_id the increment type id
   */
  public void setIncrement_type_id(Integer increment_type_id) {
    this.increment_type_id = increment_type_id;
  }

  /**
   * Gets increment type description.
   *
   * @return the increment type description
   */
  public String getIncrement_type_description() {
    return increment_type_description;
  }

  /**
   * Sets increment type description.
   *
   * @param increment_type_description the increment type description
   */
  public void setIncrement_type_description(String increment_type_description) {
    this.increment_type_description = increment_type_description;
  }

  @Override
  public String toString() {
    return "IncrementTypes{" +
        "increment_type_id=" + increment_type_id +
        ", increment_type_description='" + increment_type_description + '\'' +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof IncrementTypes)) {
      return false;
    }
    IncrementTypes that = (IncrementTypes) o;
    return Objects.equals(getIncrement_type_id(), that.getIncrement_type_id()) &&
        Objects
            .equals(getIncrement_type_description(), that.getIncrement_type_description());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getIncrement_type_id(), getIncrement_type_description());
  }

  /**
   * Create increment types db entry boolean.
   *
   * @param sql_statement the sql statement
   * @return the boolean
   */
  public Boolean create_increment_types_db_entry(String sql_statement){
    Boolean r_val = Boolean.FALSE;
    String sql = "INSERT INTO incrementtypes (incrementTypeId, incrementTypeDescription) VALUES "
        + "(?,?)";
    return r_val;
  }

  /**
   * Read increment types db entry boolean.
   *
   * @param sql_statement the sql statement
   * @return the boolean
   */
  public Boolean read_increment_types_db_entry(String sql_statement){
    Boolean r_val = Boolean.FALSE;
    String sql = "SELECT incrementTypeId, incrementTypeDescription FROM incrementtypes WHERE "
        + "incrementTypeId = ?";
    return r_val;
  }

  /**
   * Update increment types db entry boolean.
   *
   * @param sql_statement the sql statement
   * @return the boolean
   */
  public Boolean update_increment_types_db_entry(String sql_statement){
    Boolean r_val = Boolean.FALSE;
    String sql = "UPDATE incrementtypes SET  incrementTypeDescription = ? WHERE "
        + "incrementTypeId = ?";
    return r_val;
  }

  /**
   * Delete increment types db entry boolean.
   *
   * @param sql_statement the sql statement
   * @return the boolean
   */
  public Boolean delete_increment_types_db_entry(String sql_statement){
    Boolean r_val = Boolean.FALSE;
    String sql = "DELETE FROM incrementtypes WHERE incrementTypeId = ?";
    return r_val;
  }



}
