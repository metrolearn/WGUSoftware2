package wguSoftware2.models;

import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import wguSoftware2.utils.Database_v3;

import java.sql.*;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.TimeZone;
import java.util.function.BooleanSupplier;

/**
 * The type Appoinment view main.
 */
public class Appoinment_view_main {

    private String title = null;
    private String description = null;
    private String location = null;
    private String customerName = null ;
    private String appointment_type = null;
    private String contact = null;
    private MyDateTime start_date_time = null;
    private MyDateTime end_date_time = null;
    private MyDateTime create_date = null;
    private MyDateTime last_update = null;

    private String dateViewStr  = null;
    private String startTimeViewStr = null;
    private String  endTimeViewStr = null;
    private Boolean startPm = null;
    private Boolean endPm = null;



    private ZoneId zoneID = null;
    private Integer customerID = null;
    private Integer userID = null;
    private String url = null;
    private String createdBy = null;
    private String  lastUpdateBy = null;
    private Integer id = null;
    private Hyperlink hl =null;
    private Active_User active_user = null;

//    public Appoinment_view_main(Active_User ac, String title, String description, String location, String contact,
//
//                                String appointment_type, ZonedDateTime start_date_time, ZonedDateTime end_date_time) {
//        this.title = title;
//        this.description = description;
//        this.location = location;
//        this.customerName = contact;
//        this.appointment_type = appointment_type;
//        this.url = "www.link.com";
//    }
//
//    public Appoinment_view_main(Active_User ac, Integer appointmentId, String contact, String description, String title, String location, Timestamp start, Timestamp end, String s, String apt_type) {
//
//        this.id = appointmentId;
//        this.description = description;
//        this.zoneID = ac.getTz().toZoneId();
//        this.title = title;
//        this.location = location;
//        this.appointment_type = apt_type;
//        create_hyperlink();
//
//    }

    /**
     * Instantiates a new Appoinment view main.
     *
     * @param ac            the ac
     * @param customer_id   the customer id
     * @param appointmentId the appointment id
     * @param customerName  the customer name
     * @param description   the description
     * @param title         the title
     * @param location      the location
     * @param start         the start
     * @param end           the end
     * @param url           the url
     * @param apt_type      the apt type
     */
    public Appoinment_view_main(Active_User ac, Integer customer_id, Integer appointmentId, String customerName, String description, String title, String location,
                                Timestamp start, Timestamp end, String url, String apt_type)
    {

        this.start_date_time = new MyDateTime(start,ac);
        this.end_date_time = new MyDateTime(end,ac);
        this.dateViewStr =start_date_time.getSimpleDateLocalStr();
        this.startTimeViewStr =start_date_time.getSimpleTimeLocal();
        this.endTimeViewStr =end_date_time.getSimpleTimeLocal();

        if(start_date_time.getSimpleAmPm().toUpperCase().equals("PM")){
            this.startPm = true;
        }else{
            this.startPm = false;
        }

        if(end_date_time.getSimpleAmPm().toUpperCase().equals("PM")){
            this.endPm = true;
        }else{
            this.endPm = false;
        }




        this.customerID = customer_id;
        this.id = appointmentId;
        this.customerName = customerName;
        this.description = description;
        this.zoneID = ac.getTz().toZoneId();
        this.url = url;
        this.title = title;
        this.location = location;
        this.appointment_type = apt_type;
        create_hyperlink();

    }

    /**
     * Create hyperlink.
     */
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


    /**
     * Gets start date time.
     *
     * @return the start date time
     */
    public MyDateTime getStart_date_time() {
        return start_date_time;
    }

    /**
     * Gets end date time.
     *
     * @return the end date time
     */
    public MyDateTime getEnd_date_time() {
        return end_date_time;
    }

    /**
     * Gets create date.
     *
     * @return the create date
     */
    public MyDateTime getCreate_date() {
        return create_date;
    }

    /**
     * Gets last update.
     *
     * @return the last update
     */
    public MyDateTime getLast_update() {
        return last_update;
    }

    /**
     * Gets created by.
     *
     * @return the created by
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Gets hl.
     *
     * @return the hl
     */
    public Hyperlink getHl() {
        return hl;
    }

    /**
     * Sets hl.
     *
     * @param hl the hl
     */
    public void setHl(Hyperlink hl) {
        this.hl = hl;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets customer id.
     *
     * @return the customer id
     */
    public Integer getCustomerID() {
        return customerID;
    }

    /**
     * Sets customer id.
     *
     * @param customerID the customer id
     */
    public void setCustomerID(Integer customerID) {
        this.customerID = customerID;
    }

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public Integer getUserID() {
        return userID;
    }

    /**
     * Sets user id.
     *
     * @param userID the user id
     */
    public void setUserID(Integer userID) {
        this.userID = userID;
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
     * Gets last update by.
     *
     * @return the last update by
     */
    public String getLastUpdateBy() {
        return lastUpdateBy;
    }

    /**
     * Sets last update by.
     *
     * @param lastUpdateBy the last update by
     */
    public void setLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
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
     * Gets customer name.
     *
     * @return the customer name
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * Sets customer name.
     *
     * @param customerName the customer name
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * Gets appointment type.
     *
     * @return the appointment type
     */
    public String getAppointment_type() {
        return appointment_type.toString();
    }

    /**
     * Sets appointment type.
     *
     * @param appointment_type the appointment type
     */
    public void setAppointment_type(String appointment_type) {
        this.appointment_type = appointment_type;
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
     * Gets zone id.
     *
     * @return the zone id
     */
    public ZoneId getZoneID() {
        return zoneID;
    }

    /**
     * Sets zone id.
     *
     * @param zoneID the zone id
     */
    public void setZoneID(ZoneId zoneID) {
        this.zoneID = zoneID;
    }

    /**
     * Gets active user.
     *
     * @return the active user
     */
    public Active_User getActive_user() {
        return active_user;
    }

    /**
     * Sets active user.
     *
     * @param active_user the active user
     */
    public void setActive_user(Active_User active_user) {
        this.active_user = active_user;
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
     * Sets menu zone date time by zone.
     *
     * @param menu_tz the menu tz
     */
    public void setMenuZoneDateTimeByZone(TimeZone menu_tz) {

        ZonedDateTime menuZonedDateTime_start = this.getStart_date_time().getMenuZonedDateTime();//.end_date_time_zdt.toInstant().atZone(tz.toZoneId());
        ZonedDateTime menuZonedDateTime_end = this.getEnd_date_time().getMenuZonedDateTime();
        menuZonedDateTime_start.toInstant().atZone(menu_tz.toZoneId());


    }

    /**
     * Sets date view string.
     *
     * @param str the str
     */
    public void setDateViewString(String str) {
        this.dateViewStr = str;
    }

    /**
     * Sets start date time.
     *
     * @param start_date_time the start date time
     */
    public void setStart_date_time(MyDateTime start_date_time) {
        this.start_date_time = start_date_time;
    }

    /**
     * Sets end date time.
     *
     * @param end_date_time the end date time
     */
    public void setEnd_date_time(MyDateTime end_date_time) {
        this.end_date_time = end_date_time;
    }

    /**
     * Sets create date.
     *
     * @param create_date the create date
     */
    public void setCreate_date(MyDateTime create_date) {
        this.create_date = create_date;
    }

    /**
     * Sets last update.
     *
     * @param last_update the last update
     */
    public void setLast_update(MyDateTime last_update) {
        this.last_update = last_update;
    }

    /**
     * Gets date view str.
     *
     * @return the date view str
     */
    public String getDateViewStr() {
        return dateViewStr;
    }

    /**
     * Sets date view str.
     *
     * @param dateViewStr the date view str
     */
    public void setDateViewStr(String dateViewStr) {
        this.dateViewStr = dateViewStr;
    }

    /**
     * Gets start time view str.
     *
     * @return the start time view str
     */
    public String getStartTimeViewStr() {
        return startTimeViewStr;
    }

    /**
     * Sets start time view str.
     *
     * @param startTimeViewStr the start time view str
     */
    public void setStartTimeViewStr(String startTimeViewStr) {
        this.startTimeViewStr = startTimeViewStr;
    }

    /**
     * Sets start time view str plus dst.
     *
     * @param startTimeViewStr the start time view str
     */
    public void setStartTimeViewStrPlusDST(String startTimeViewStr) {
        this.startTimeViewStr = startTimeViewStr;
    }

    /**
     * Sets start time view str minus dst.
     *
     * @param startTimeViewStr the start time view str
     */
    public void setStartTimeViewStrMinusDST(String startTimeViewStr) {
        this.startTimeViewStr = startTimeViewStr;
    }

    /**
     * Gets end time view str.
     *
     * @return the end time view str
     */
    public String getEndTimeViewStr() {
        return endTimeViewStr;
    }

    /**
     * Sets end time view str.
     *
     * @param endTimeViewStr the end time view str
     */
    public void setEndTimeViewStr(String endTimeViewStr) {
        this.endTimeViewStr = endTimeViewStr;
    }

    /**
     * Gets start pm.
     *
     * @return the start pm
     */
    public Boolean getStartPm() {
        return startPm;
    }

    /**
     * Sets start pm.
     *
     * @param startPm the start pm
     */
    public void setStartPm(Boolean startPm) {
        this.startPm = startPm;
    }

    /**
     * Gets end pm.
     *
     * @return the end pm
     */
    public Boolean getEndPm() {
        return endPm;
    }

    /**
     * Sets end pm.
     *
     * @param endPm the end pm
     */
    public void setEndPm(Boolean endPm) {
        this.endPm = endPm;
    }

    /**
     * Sets created by.
     *
     * @param createdBy the created by
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public String toString() {
        return "Appoinment_view_main{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", customerName='" + customerName + '\'' +
                ", appointment_type='" + appointment_type + '\'' +
                ", contact='" + contact + '\'' +
                ", start_date_time=" + start_date_time +
                ", end_date_time=" + end_date_time +
                ", create_date=" + create_date +
                ", last_update=" + last_update +
                ", dateViewStr='" + dateViewStr + '\'' +
                ", startTimeViewStr='" + startTimeViewStr + '\'' +
                ", endTimeViewStr='" + endTimeViewStr + '\'' +
                ", startPm=" + startPm +
                ", endPm=" + endPm +
                ", zoneID=" + zoneID +
                ", customerID=" + customerID +
                ", userID=" + userID +
                ", createdBy='" + createdBy + '\'' +
                ", lastUpdateBy='" + lastUpdateBy + '\'' +
                ", id=" + id +
                ", hl=" + hl +
                ", active_user=" + active_user +
                '}';
    }
}
