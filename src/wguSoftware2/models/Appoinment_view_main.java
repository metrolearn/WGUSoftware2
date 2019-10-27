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

    public Appoinment_view_main(Active_User ac, String title, String description, String location, String contact,

                                String appointment_type, ZonedDateTime start_date_time, ZonedDateTime end_date_time) {
        this.title = title;
        this.description = description;
        this.location = location;
        this.customerName = contact;
        this.appointment_type = appointment_type;
        this.url = "www.link.com";
    }

    public Appoinment_view_main(Active_User ac, Integer appointmentId, String contact, String description, String title, String location, Timestamp start, Timestamp end, String s, String apt_type) {

        this.id = appointmentId;
        this.description = description;
        this.zoneID = ac.getTz().toZoneId();
        this.title = title;
        this.location = location;
        this.appointment_type = apt_type;
        create_hyperlink();

    }

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


    public MyDateTime getStart_date_time() {
        return start_date_time;
    }

    public MyDateTime getEnd_date_time() {
        return end_date_time;
    }

    public MyDateTime getCreate_date() {
        return create_date;
    }

    public MyDateTime getLast_update() {
        return last_update;
    }

    public String getCreatedBy() {
        return createdBy;
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

    public String getContact() {
        return contact;
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


    public void ajustTimebySeconds(long diff_in_seconds) {


    }



    public void setContact(String contact) {
        this.contact = contact;
    }


    public void setMenuZoneDateTimeByZone(TimeZone menu_tz) {

        ZonedDateTime menuZonedDateTime_start = this.getStart_date_time().getMenuZonedDateTime();//.end_date_time_zdt.toInstant().atZone(tz.toZoneId());
        ZonedDateTime menuZonedDateTime_end = this.getEnd_date_time().getMenuZonedDateTime();
        menuZonedDateTime_start.toInstant().atZone(menu_tz.toZoneId());


    }

    public void setDateViewString(String str) {
        this.dateViewStr = str;
    }

    public void setStart_date_time(MyDateTime start_date_time) {
        this.start_date_time = start_date_time;
    }

    public void setEnd_date_time(MyDateTime end_date_time) {
        this.end_date_time = end_date_time;
    }

    public void setCreate_date(MyDateTime create_date) {
        this.create_date = create_date;
    }

    public void setLast_update(MyDateTime last_update) {
        this.last_update = last_update;
    }

    public String getDateViewStr() {
        return dateViewStr;
    }

    public void setDateViewStr(String dateViewStr) {
        this.dateViewStr = dateViewStr;
    }

    public String getStartTimeViewStr() {
        return startTimeViewStr;
    }

    public void setStartTimeViewStr(String startTimeViewStr) {
        this.startTimeViewStr = startTimeViewStr;
    }

    public String getEndTimeViewStr() {
        return endTimeViewStr;
    }

    public void setEndTimeViewStr(String endTimeViewStr) {
        this.endTimeViewStr = endTimeViewStr;
    }

    public Boolean getStartPm() {
        return startPm;
    }

    public void setStartPm(Boolean startPm) {
        this.startPm = startPm;
    }

    public Boolean getEndPm() {
        return endPm;
    }

    public void setEndPm(Boolean endPm) {
        this.endPm = endPm;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}
