package be.switchfully.itemgroup.service.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ItemGroupDTO {

    private String groupId;
    private String shippingDate;
    private int amount;
    private String itemId;
    private String itemName;
    private double itemPrice;
    private int itemStockAmount;
}
