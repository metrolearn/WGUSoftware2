package wguSoftware2.models;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Appoinment_view_main {

    private String title;
    private String description;
    private String location;
    private String customerName;
    private String appointment_type;
    private ZonedDateTime start_date_time_zdt;
    private ZonedDateTime end_date_time_zdt;
    private LocalDateTime start_ldt = null;
    private LocalDateTime end_ldt= null;
    private ZoneId zoneID = null;
    private Integer customerID = null;
    private Integer userID = null;
    private String url = null;
    private ZonedDateTime createDate = null;
    private String createdBy = null;
    private ZonedDateTime lastUpdate = null;
    private String  lastUpdateBy = null;
    private Integer id = null;
    private Timestamp start_ts_ldt;
    private Timestamp end_ts_ldt;
    private Timestamp start_ts;
    private Timestamp end_ts;
    private String dateViewString = null;
    private String timeViewStringStart = null;
    private String timeViewStringEnd = null;
    private String start_day_of_week = null;
    private String start_month = null;
    private String standard_date = null;
    public Appoinment_view_main(String title, String description, String location, String contact,

                                String appointment_type, ZonedDateTime start_date_time, ZonedDateTime end_date_time) {
        this.title = title;
        this.description = description;
        this.location = location;
        this.customerName = contact;
        this.appointment_type = appointment_type;
        this.start_date_time_zdt = start_date_time;
        this.end_date_time_zdt = end_date_time;
        this.url = "www.link.com";
        Timestamp start = Timestamp.from(start_date_time.toInstant());
        Timestamp end = Timestamp.from(start_date_time.toInstant());
        date_time_view_convert(start,end);


    }

    // called from main init

    public Appoinment_view_main(Integer appointmentId, String customerName, String description, String title, String location,
                                Timestamp start, Timestamp end, String url, String apt_type)
    {
        this.id = appointmentId;
        this.customerName = customerName;
        this.description = description;
        this.zoneID = ZoneId.of("UTC");
        this.start_date_time_zdt = ZonedDateTime.ofInstant(start.toInstant(), zoneID);
        this.start_ts = start;
        this.start_ldt = start_ts.toLocalDateTime();
        this.start_ts_ldt = Timestamp.valueOf(this.start_ts.toLocalDateTime());
        DateTimeFormatter day_of_week_formatter = DateTimeFormatter.ofPattern("EEEE");
        this.start_day_of_week = start_date_time_zdt.format(day_of_week_formatter);
        DateTimeFormatter month_formatter = DateTimeFormatter.ofPattern("MMMM");
        this.start_month = start_date_time_zdt.format(month_formatter);
        DateTimeFormatter std_date = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        this.standard_date = start_date_time_zdt.format(std_date);
        this.end_ts = end;
        this.end_ldt = end_ts.toLocalDateTime();
        this.end_ts_ldt = Timestamp.valueOf(this.end_ts.toLocalDateTime());
        this.end_date_time_zdt = ZonedDateTime.ofInstant(end.toInstant(), zoneID);
        this.url = url;
        this.title = title;
        this.location = location;
        this.appointment_type = apt_type;







        date_time_view_convert(start,end);

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

    public ZonedDateTime getStart_date_time_zdt() {
        return start_date_time_zdt;
    }

    public void setStart_date_time_zdt(ZonedDateTime start_date_time_zdt) {
        this.start_date_time_zdt = start_date_time_zdt;
    }

    public ZonedDateTime getEnd_date_time_zdt() {
        return end_date_time_zdt;
    }

    public void setEnd_date_time_zdt(ZonedDateTime end_date_time_zdt) {
        this.end_date_time_zdt = end_date_time_zdt;
    }

    public Timestamp getStart_ts_ldt() {
        return start_ts_ldt;
    }

    public void setStart_ts_ldt(Timestamp start_ts_ldt) {
        this.start_ts_ldt = start_ts_ldt;
    }

    public Timestamp getEnd_ts_ldt() {
        return end_ts_ldt;
    }

    public void setEnd_ts_ldt(Timestamp end_ts_ldt) {
        this.end_ts_ldt = end_ts_ldt;
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

    public LocalDateTime getStart_ldt() {
        return start_ldt;
    }

    public void setStart_ldt(LocalDateTime start_ldt) {
        this.start_ldt = start_ldt;
    }

    public LocalDateTime getEnd_ldt() {
        return end_ldt;
    }

    public void setEnd_ldt(LocalDateTime end_ldt) {
        this.end_ldt = end_ldt;
    }

    public ZoneId getZoneID() {
        return zoneID;
    }

    public void setZoneID(ZoneId zoneID) {
        this.zoneID = zoneID;
    }

    public Timestamp getStart_ts() {
        return start_ts;
    }

    public void setStart_ts(Timestamp start_ts) {
        this.start_ts = start_ts;
    }

    public Timestamp getEnd_ts() {
        return end_ts;
    }

    public void setEnd_ts(Timestamp end_ts) {
        this.end_ts = end_ts;
    }

    public String getStart_day_of_week() {
        return start_day_of_week;
    }

    public void setStart_day_of_week(String start_day_of_week) {
        this.start_day_of_week = start_day_of_week;
    }

    public String getStart_month() {
        return start_month;
    }

    public void setStart_month(String start_month) {
        this.start_month = start_month;
    }

    public String getStandard_date() {
        return standard_date;
    }

    public void setStandard_date(String standard_date) {
        this.standard_date = standard_date;
    }

}
