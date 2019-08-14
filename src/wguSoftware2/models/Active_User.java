package wguSoftware2.models;

import java.util.Objects;

public class Active_User {

    String user_name;

    public Active_User(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_name() {
        return user_name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Active_User)) return false;
        Active_User that = (Active_User) o;
        return Objects.equals(getUser_name(), that.getUser_name());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUser_name());
    }

    @Override
    public String toString() {
        return "Active_User{" +
                "user_name='" + user_name + '\'' +
                '}';
    }
}
