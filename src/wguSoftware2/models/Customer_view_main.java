package wguSoftware2.models;

import java.util.Objects;

public class Customer_view_main {

    private Integer id;
    private String name;
    private String address;
    private String phone;

    public Customer_view_main(Integer id, String name, String address, String phone) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer_view_main)) return false;
        Customer_view_main that = (Customer_view_main) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getAddress(), that.getAddress()) &&
                Objects.equals(getPhone(), that.getPhone());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getAddress(), getPhone());
    }

    @Override
    public String toString() {
        return "Customer_view_main{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
