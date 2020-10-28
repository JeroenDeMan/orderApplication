package be.switchfully.order.service.dto;

import lombok.Data;

@Data
public class ItemGroupDTO {

    private String groupId;
    private String shippingDate;
    private int amount;
    private double groupPrice;
    private String itemName;

}
