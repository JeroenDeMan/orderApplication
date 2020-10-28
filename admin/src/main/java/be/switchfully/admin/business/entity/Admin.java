package be.switchfully.admin.business.entity;

import lombok.Data;

import java.util.UUID;

@Data
public class Admin {

    private final String id;
    private String name;

    public Admin(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
    }
}
