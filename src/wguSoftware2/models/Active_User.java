package wguSoftware2.models;

import java.sql.Time;
import java.util.Calendar;
import java.util.Objects;
import java.util.TimeZone;

/**
 * The type Active user.
 */
public class Active_User {

    private Integer active_user_id;
    private String active_user_name;
    private TimeZone tz = null;
    private TimeZone menu_tz = null;
    private String current_ip = null;
    private String current_location = null;
    private GeoIP gp = null;

    /**
     * Instantiates a new Active user.
     *
     * @param active_user_id   the active user id
     * @param active_user_name the active user name
     */
    public Active_User(Integer active_user_id, String active_user_name) {
        this.active_user_id = active_user_id;
        this.active_user_name = active_user_name;
        this.tz = Calendar.getInstance().getTimeZone();

    }

    /**
     * Gets menu tz.
     *
     * @return the menu tz
     */
    public TimeZone getMenu_tz() {
        return menu_tz;
    }

    /**
     * Sets menu tz.
     *
     * @param menu_tz the menu tz
     */
    public void setMenu_tz(TimeZone menu_tz) {
        this.menu_tz = menu_tz;
    }

    /**
     * Gets active user id.
     *
     * @return the active user id
     */
    public Integer getActive_user_id() {
        return active_user_id;
    }

    /**
     * Gets active user name.
     *
     * @return the active user name
     */
    public String getActive_user_name() {
        return active_user_name;
    }

    /**
     * Gets gp.
     *
     * @return the gp
     */
    public GeoIP getGp() {
        return gp;
    }

    /**
     * Sets geo ip.
     *
     * @param gp the gp
     */
    public void setGeoIp(GeoIP gp) {
        this.gp = gp;
    }

    /**
     * Gets current ip.
     *
     * @return the current ip
     */
    public String getCurrent_ip() {
        return current_ip;
    }

    /**
     * Sets current ip.
     *
     * @param current_ip the current ip
     */
    public void setCurrent_ip(String current_ip) {
        this.current_ip = current_ip;
    }

    /**
     * Gets current location.
     *
     * @return the current location
     */
    public String getCurrent_location() {
        return current_location;
    }

    /**
     * Sets current location.
     *
     * @param current_location the current location
     */
    public void setCurrent_location(String current_location) {
        this.current_location = current_location +", Time Zone: "+this.tz.getDisplayName();
    }

    /**
     * Sets active user id.
     *
     * @param active_user_id the active user id
     */
    public void setActive_user_id(Integer active_user_id) {
        this.active_user_id = active_user_id;
    }

    /**
     * Sets active user name.
     *
     * @param active_user_name the active user name
     */
    public void setActive_user_name(String active_user_name) {
        this.active_user_name = active_user_name;
    }

    /**
     * Gets tz.
     *
     * @return the tz
     */
    public TimeZone getTz() {
        return tz;
    }

    /**
     * Sets tz.
     *
     * @param tz the tz
     */
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
                "active_user_id=" + active_user_id +
                ", active_user_name='" + active_user_name + '\'' +
                ", tz=" + tz +
                ", menu_tz=" + menu_tz +
                ", current_ip='" + current_ip + '\'' +
                ", current_location='" + current_location + '\'' +
                ", gp=" + gp +
                '}';
    }
}
