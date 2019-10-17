package wguSoftware2.models;

import java.util.Calendar;
import java.util.Objects;
import java.util.TimeZone;

public class Active_User {

    private Integer active_user_id;
    private String active_user_name;
    private TimeZone tz = null;
    private String current_ip = null;
    private String current_location = null;
    private GeoIP gp = null;

    public Active_User(Integer active_user_id, String active_user_name) {
        this.active_user_id = active_user_id;
        this.active_user_name = active_user_name;
        this.tz = Calendar.getInstance().getTimeZone();

    }

    public Integer getActive_user_id() {
        return active_user_id;
    }

    public String getActive_user_name() {
        return active_user_name;
    }

    public GeoIP getGp() {
        return gp;
    }

    public void setGp(GeoIP gp) {
        this.gp = gp;
    }

    public String getCurrent_ip() {
        return current_ip;
    }

    public void setCurrent_ip(String current_ip) {
        this.current_ip = current_ip;
    }

    public String getCurrent_location() {
        return current_location;
    }

    public void setCurrent_location(String current_location) {
        this.current_location = current_location +", Time Zone: "+this.tz.getDisplayName();
    }

    public void setActive_user_id(Integer active_user_id) {
        this.active_user_id = active_user_id;
    }

    public void setActive_user_name(String active_user_name) {
        this.active_user_name = active_user_name;
    }

    public TimeZone getTz() {
        return tz;
    }

    public void setTz(TimeZone tz) {
        this.tz = tz;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Active_User)) return false;
        Active_User that = (Active_User) o;
        return Objects.equals(getActive_user_id(), that.getActive_user_id()) &&
                Objects.equals(getActive_user_name(), that.getActive_user_name());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getActive_user_id(), getActive_user_name());
    }

    @Override
    public String toString() {
        return "Active_User{" +
                "active_user_id='" + active_user_id + '\'' +
                ", active_user_name='" + active_user_name + '\'' +
                '}';
    }


}
