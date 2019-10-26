package wguSoftware2.models;

import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import wguSoftware2.utils.Database_v3;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Appoinment_view_main {

    private String title;
    private String description;
    private String location;
    private String customerName;
    private String appointment_type;
    private String contact;


    private ZonedDateTime start_date_time_zdt_utc = null;
    private ZonedDateTime end_date_time_zdt_utc;
    private ZonedDateTime start_zdt_ldt = null;
    private ZonedDateTime end_zdt_ldt= null;
    private Timestamp start_ts_utc = null;
    private Timestamp end_ts_utc = null;
    private Timestamp start_ts_local = null;
    private Timestamp end_ts_local = null;

    private ZonedDateTime createDate = null;
    private ZonedDateTime lastUpdate = null;
    private String dateViewString = null;
    private String timeViewStringStart = null;
    private String timeViewStringEnd = null;
    private String start_day_of_week = null;
    private String start_month = null;
    private String standard_date = null;

    private ZoneId zoneID = null;
    private Integer customerID = null;
    private Integer userID = null;
    private String url = null;
    private String createdBy = null;
    private String  lastUpdateBy = null;
    private Integer id = null;
    private Hyperlink hl =null;
    private Active_User active_user = null;

    public Appoinment_view_main(Active_User ac, String title, String description, String location, String contact,

                                String appointment_type, ZonedDateTime start_date_time, ZonedDateTime end_date_time) {
        this.title = title;
        this.description = description;
        this.location = location;
        this.customerName = contact;
        this.appointment_type = appointment_type;
        this.start_date_time_zdt_utc = start_date_time;
        this.end_date_time_zdt_utc = end_date_time;
        this.url = "www.link.com";
        Timestamp start = Timestamp.from(start_date_time.toInstant());
        Timestamp end = Timestamp.from(start_date_time.toInstant());
        date_time_view_convert(start,end,ac.getTz().toZoneId());

    }

    private void date_time_view_convert(Timestamp start, Timestamp end, ZoneId zid) {
        DateTimeFormatter date_formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        ZoneId zoneID = zid;
        this.dateViewString = ZonedDateTime.ofInstant(start.toInstant(), zoneID).format(date_formatter);

        DateTimeFormatter time_formatter = DateTimeFormatter.ofPattern("HH:mm");
        this.timeViewStringStart = ZonedDateTime.ofInstant(start.toInstant(), zoneID).format(time_formatter);
        this.timeViewStringEnd =ZonedDateTime.ofInstant(end.toInstant(), zoneID).format(time_formatter);
    }

    public Appoinment_view_main(Active_User ac, Integer customer_id, Integer appointmentId, String customerName, String description, String title, String location,
                                Timestamp start, Timestamp end, String url, String apt_type)
    {
        this.customerID = customer_id;
        this.id = appointmentId;
        this.customerName = customerName;
        this.description = description;
        this.zoneID = ac.getTz().toZoneId();
        this.start_date_time_zdt_utc = ZonedDateTime.ofInstant(start.toInstant(), zoneID);
        this.end_date_time_zdt_utc = ZonedDateTime.ofInstant(end.toInstant(), zoneID);

        DateTimeFormatter day_of_week_formatter = DateTimeFormatter.ofPattern("EEEE");
        this.start_day_of_week = start_date_time_zdt_utc.format(day_of_week_formatter);
        DateTimeFormatter month_formatter = DateTimeFormatter.ofPattern("MMMM");
        this.start_month = start_date_time_zdt_utc.format(month_formatter);
        DateTimeFormatter std_date = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        this.standard_date = start_date_time_zdt_utc.format(std_date);
//        this.end_ts = end;
//        this.end_ldt = end_ts.toLocalDateTime();
//        this.end_ts_ldt = Timestamp.valueOf(this.end_ts.toLocalDateTime());
        this.url = url;
        this.title = title;
        this.location = location;
        this.appointment_type = apt_type;
        create_hyperlink();
        date_time_view_convert(start,end,zoneID);

    }

    public void create_hyperlink() {
        String s2 = "Detail view customer ID: ";
        Hyperlink h = new Hyperlink();
        h.setTooltip(new Tooltip("Double click to open detail view."));
        h.setText(s2 + customerID.toString());
        this.hl = h;
        this.hl.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.isPrimaryButtonDown() && event.getClickCount() == 1) {
                    String[] arrOfStr = hl.getText().split(": ");





                    try {
                        Database_v3 db = new Database_v3(
                                "52.206.157.109",
                                "U05mJi",
                                "U05mJi",
                                "53688547099"
                        );
                        db.dbConnect();
                        Connection con = db.getCon();
                        String sql_stm = "select * from customer inner join address a on customer.addressId = a.addressId" +
                                " inner join city c on a.cityId = c.cityId" +
                                " inner join country c2 on c.countryId = c2.countryId " +
                                "where customerId = ?;";

                        PreparedStatement ps = con.prepareStatement(sql_stm, Statement.RETURN_GENERATED_KEYS);
                        ps.setInt(1, Integer.parseInt(arrOfStr[1]));
                        ResultSet rs = ps.executeQuery();
                        while (rs.next()) {
                            String name = rs.getString("customerName");
                            String address = rs.getString("address");
                            String address2 = rs.getString("address2");
                            String city = rs.getString("city");
                            String postalCode = rs.getString("postalCode");
                            String country = rs.getString("country");
                            String phone = rs.getString("phone");


                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setHeaderText("Customer Detail");
                            alert.setTitle("Customer Detail");
                            alert.setContentText("" +
                                    "Customer name: "+name+"\n" +
                                    "Customer address: "+address+"\n"+
                                    "Customer address2: "+address2+"\n"+
                                    "Customer city: "+city+"\n"+
                                    "Customer zip: "+postalCode+"\n"+
                                    "Customer country: "+country+"\n"+
                                    "Customer phone: "+phone+"\n");


                            alert.showAndWait();


                            try {

                            } catch (Exception e) {
                                System.out.println(e);
                            }


                        }
                    }
                     catch (SQLException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }




                }
            }

        });
    }

    public Appoinment_view_main(Active_User ac, Integer appointmentId, String contact, String description, String title, String location, Timestamp start, Timestamp end, String s, String apt_type) {

        this.id = appointmentId;
        this.description = description;
        this.zoneID = ac.getTz().toZoneId();
        this.start_date_time_zdt_utc = ZonedDateTime.ofInstant(start.toInstant(), zoneID);

        DateTimeFormatter day_of_week_formatter = DateTimeFormatter.ofPattern("EEEE");
        this.start_day_of_week = start_date_time_zdt_utc.format(day_of_week_formatter);
        DateTimeFormatter month_formatter = DateTimeFormatter.ofPattern("MMMM");
        this.start_month = start_date_time_zdt_utc.format(month_formatter);
        DateTimeFormatter std_date = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        this.standard_date = start_date_time_zdt_utc.format(std_date);

        this.end_date_time_zdt_utc = ZonedDateTime.ofInstant(end.toInstant(), zoneID);
        this.title = title;
        this.location = location;
        this.appointment_type = apt_type;
        create_hyperlink();
        date_time_view_convert(start,end,zoneID);

    }

    public Hyperlink getHl() {
        return hl;
    }

    public void setHl(Hyperlink hl) {
        this.hl = hl;
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

    public ZonedDateTime getStart_date_time_zdt_utc() {
        return start_date_time_zdt_utc;
    }

    public void setStart_date_time_zdt_utc(ZonedDateTime start_date_time_zdt_utc) {
        this.start_date_time_zdt_utc = start_date_time_zdt_utc;
    }

    public ZonedDateTime getEnd_date_time_zdt_utc() {
        return end_date_time_zdt_utc;
    }

    public void setEnd_date_time_zdt_utc(ZonedDateTime end_date_time_zdt_utc) {
        this.end_date_time_zdt_utc = end_date_time_zdt_utc;
    }

    public String getContact() {
        return contact;
    }

    public ZonedDateTime getStart_zdt_ldt() {
        return start_zdt_ldt;
    }

    public void setStart_zdt_ldt(ZonedDateTime start_zdt_ldt) {
        this.start_zdt_ldt = start_zdt_ldt;
    }

    public ZonedDateTime getEnd_zdt_ldt() {
        return end_zdt_ldt;
    }

    public void setEnd_zdt_ldt(ZonedDateTime end_zdt_ldt) {
        this.end_zdt_ldt = end_zdt_ldt;
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



    public ZoneId getZoneID() {
        return zoneID;
    }

    public void setZoneID(ZoneId zoneID) {
        this.zoneID = zoneID;
    }

    public Active_User getActive_user() {
        return active_user;
    }

    public void setActive_user(Active_User active_user) {
        this.active_user = active_user;
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

    public void ajustTimebySeconds(long diff_in_seconds) {

        start_date_time_zdt_utc = this.start_date_time_zdt_utc.plusSeconds(diff_in_seconds);
        end_date_time_zdt_utc = this.end_date_time_zdt_utc.plusSeconds(diff_in_seconds);
//        start_ldt = this.start_ldt.plusSeconds(diff_in_seconds);
//        end_ldt= this.start_ldt.plusSeconds(diff_in_seconds);
        updateTimes();

    }

    public void updateTimes() {


        start_zdt_ldt = start_date_time_zdt_utc.withZoneSameInstant(active_user.getTz().toZoneId());
        end_zdt_ldt = end_date_time_zdt_utc.withZoneSameInstant(active_user.getTz().toZoneId());
        DateTimeFormatter day_of_week_formatter = DateTimeFormatter.ofPattern("EEEE");
        this.start_day_of_week = start_date_time_zdt_utc.format(day_of_week_formatter);
        DateTimeFormatter month_formatter = DateTimeFormatter.ofPattern("MMMM");
        this.start_month = start_date_time_zdt_utc.format(month_formatter);
        DateTimeFormatter std_date = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        this.standard_date = start_date_time_zdt_utc.format(std_date);
        Timestamp start = Timestamp.from(start_date_time_zdt_utc.toInstant());
        Timestamp end = Timestamp.from(end_date_time_zdt_utc.toInstant());
        date_time_view_convert(start,end, start_date_time_zdt_utc.getZone());


    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    // called from main init

}
