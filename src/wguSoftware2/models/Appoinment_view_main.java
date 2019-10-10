package wguSoftware2.models;

import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Appoinment_view_main {

    private String title;
    private String description;
    private String location;
    private String customerName;
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
    private Integer id = null;
    private ZonedDateTime start;
    private ZonedDateTime end;
    private String dateViewString = null;
    private String timeViewStringStart = null;
    private String timeViewStringEnd = null;
    public Appoinment_view_main(String title, String description, String location, String contact,

                                String appointment_type, ZonedDateTime start_date_time, ZonedDateTime end_date_time) {
        this.title = title;
        this.description = description;
        this.location = location;
        this.customerName = contact;
        this.appointment_type = appointment_type;
        this.start_date_time = start_date_time;
        this.end_date_time = end_date_time;
        this.url = "www.link.com";
        Timestamp start = Timestamp.from(start_date_time.toInstant());
        Timestamp end = Timestamp.from(start_date_time.toInstant());
        date_time_view_convert(start,end);


    }

    public Appoinment_view_main(Integer appointmentId, String customerName, String description, String title, String location,
                                Timestamp start, Timestamp end, String url)
    {
        this.id = appointmentId;
        this.customerName = customerName;
        this.description = description;
        this.start = ZonedDateTime.ofInstant(start.toInstant(), ZoneId.of("UTC"));
        this.end = ZonedDateTime.ofInstant(end.toInstant(), ZoneId.of("UTC"));
        this.url = url;
        this.title = title;
        this.location = location;
        date_time_view_convert(start,end);

    }

    public Appoinment_view_main(String title, String location, String contact, String apt_type, String description, Timestamp start_date, Timestamp end_date) {




    }

    private void date_time_view_convert(Timestamp start, Timestamp end) {
        DateTimeFormatter date_formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        this.dateViewString = ZonedDateTime.ofInstant(start.toInstant(), ZoneId.of("UTC")).format(date_formatter);

        DateTimeFormatter time_formatter = DateTimeFormatter.ofPattern("HH:mm");
        this.timeViewStringStart = ZonedDateTime.ofInstant(start.toInstant(), ZoneId.of("UTC")).format(time_formatter);
        this.timeViewStringEnd =ZonedDateTime.ofInstant(end.toInstant(), ZoneId.of("UTC")).format(time_formatter);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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
                Objects.equals(customerName, that.customerName) &&
                Objects.equals(appointment_type, that.appointment_type) &&
                Objects.equals(start_date_time, that.start_date_time) &&
                Objects.equals(end_date_time, that.end_date_time);
    }

    public ZonedDateTime getStart() {
        return start;
    }

    public void setStart(ZonedDateTime start) {
        this.start = start;
    }

    public ZonedDateTime getEnd() {
        return end;
    }

    public void setEnd(ZonedDateTime end) {
        this.end = end;
    }

    public String getDateViewString() {
        return dateViewString;
    }

    public void setDateViewString(String dateViewString) {
        this.dateViewString = dateViewString;
    }

    public String getTimeViewStringStart() {
        return timeViewStringStart;
    }

    public void setTimeViewStringStart(String timeViewStringStart) {
        this.timeViewStringStart = timeViewStringStart;
    }

    public String getTimeViewStringEnd() {
        return timeViewStringEnd;
    }

    public void setTimeViewStringEnd(String timeViewStringEnd) {
        this.timeViewStringEnd = timeViewStringEnd;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, location, customerName, appointment_type, start_date_time, end_date_time);
    }

    @Override
    public String toString() {
        return "Appoinment_view_main{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", contact='" + customerName + '\'' +
                ", appointment_type='" + appointment_type + '\'' +
                ", start_date_time=" + start_date_time +
                ", end_date_time=" + end_date_time +
                ", customerID=" + customerID +
                ", userID=" + userID +
                ", url='" + url + '\'' +
                ", createDate=" + createDate +
                ", createdBy='" + createdBy + '\'' +
                ", lastUpdate=" + lastUpdate +
                ", lastUpdateBy='" + lastUpdateBy + '\'' +
                ", aptID=" + id +
                ", start=" + start +
                ", end=" + end +
                ", dateViewString='" + dateViewString + '\'' +
                '}';
    }
}
