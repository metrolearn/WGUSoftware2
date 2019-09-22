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
