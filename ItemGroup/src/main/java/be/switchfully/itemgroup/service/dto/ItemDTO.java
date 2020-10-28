package be.switchfully.itemgroup.service.dto;

import lombok.Data;

@Data
public class ItemDTO {

    private String id;
    private String name;
    private String description;
    private double price;
    private int amount;
}
