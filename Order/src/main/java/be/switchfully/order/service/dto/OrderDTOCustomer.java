package be.switchfully.order.service.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class OrderDTOCustomer {

    private String customerId;
    private String customerName;
    private List<OrderDTO> orderList;
    private double totalPrice;

    public OrderDTOCustomer() {
        orderList = new ArrayList<>();
    }
}
