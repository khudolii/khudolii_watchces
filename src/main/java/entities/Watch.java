package entities;

import java.util.Objects;

public class Watch {
    private int id;
    private String mark;
    private String type;
    private int vendorId;

    public Watch(int id, String mark, String type) {
        this.id = id;
        this.mark = mark;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getVendorId() {
        return vendorId;
    }

    public void setVendorId(int vendorId) {
        this.vendorId = vendorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Watch watch = (Watch) o;
        return id == watch.id &&
                vendorId == watch.vendorId &&
                Objects.equals(mark, watch.mark) &&
                Objects.equals(type, watch.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, mark, type, vendorId);
    }
}
