package wguSoftware2.models;

import java.time.ZonedDateTime;
import java.util.Objects;

public class Appoinment_view_main {

    private String title;
    private String description;
    private String location;
    private String contact;
    private String appointment_type;
    private ZonedDateTime start_date_time;
    private ZonedDateTime end_date_time;
    private Integer customerID = null;
    private Integer userID = null;
    private String url = null;
    private ZonedDateTime createDate = null;
    private String createdBy = null;
    private ZonedDateTime lastUpdate = null;
    private String  lastUpdateBy = null;
    private Integer aptID = null;
    public Appoinment_view_main(String title, String description, String location, String contact,

                                String appointment_type, ZonedDateTime start_date_time, ZonedDateTime end_date_time) {
        this.title = title;
        this.description = description;
        this.location = location;
        this.contact = contact;
        this.appointment_type = appointment_type;
        this.start_date_time = start_date_time;
        this.end_date_time = end_date_time;

    }

    public Integer getAptID() {
        return aptID;
    }

    public void setAptID(Integer aptID) {
        this.aptID = aptID;
    }

    public Integer getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Integer customerID) {
        this.customerID = customerID;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public ZonedDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(ZonedDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getLastUpdateBy() {
        return lastUpdateBy;
    }

    public void setLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAppointment_type() {
        return appointment_type.toString();
    }

    public void setAppointment_type(String appointment_type) {
        this.appointment_type = appointment_type;
    }

    public ZonedDateTime getStart_date_time() {
        return start_date_time;
    }

    public void setStart_date_time(ZonedDateTime start_date_time) {
        this.start_date_time = start_date_time;
    }

    public ZonedDateTime getEnd_date_time() {
        return end_date_time;
    }

    public void setEnd_date_time(ZonedDateTime end_date_time) {
        this.end_date_time = end_date_time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Appoinment_view_main)) return false;
        Appoinment_view_main that = (Appoinment_view_main) o;
        return Objects.equals(title, that.title) &&
                Objects.equals(description, that.description) &&
                Objects.equals(location, that.location) &&
                Objects.equals(contact, that.contact) &&
                Objects.equals(appointment_type, that.appointment_type) &&
                Objects.equals(start_date_time, that.start_date_time) &&
                Objects.equals(end_date_time, that.end_date_time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, location, contact, appointment_type, start_date_time, end_date_time);
    }

    @Override
    public String toString() {
        return "Appoinment_view_main{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", contact='" + contact + '\'' +
                ", appointment_type='" + appointment_type + '\'' +
                ", start_date_time=" + start_date_time +
                ", end_date_time=" + end_date_time +
                '}';
    }
}
