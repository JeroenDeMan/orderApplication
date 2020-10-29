package be.switchfully.admin.business.entity;

import lombok.Data;

import java.util.Objects;
import java.util.UUID;

@Data
public class Admin {

    private final String id;
    private String name;

    public Admin(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Admin admin = (Admin) o;
        return Objects.equals(id, admin.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
