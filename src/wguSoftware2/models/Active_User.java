package wguSoftware2.models;

import java.util.Objects;

public class Active_User {

    private Integer active_user_id;
    private String active_user_name;

    public Active_User(Integer active_user_id, String active_user_name) {
        this.active_user_id = active_user_id;
        this.active_user_name = active_user_name;
    }

    public Integer getActive_user_id() {
        return active_user_id;
    }

    public String getActive_user_name() {
        return active_user_name;
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
