package be.switchfully.order.service.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class OrderDTO {

    private String id;
    private String customerId;
    private String customerName;
    private List<ItemGroupDTO> itemGroups;
    private double totalPrice;

    public OrderDTO() {
        itemGroups = new ArrayList<>();
    }
}
