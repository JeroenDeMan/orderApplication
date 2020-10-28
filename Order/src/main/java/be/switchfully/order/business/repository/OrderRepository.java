package be.switchfully.order.business.repository;

import be.switchfully.order.business.entity.Order;
import lombok.Data;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
@Data
public class OrderRepository {

    private Map<String, Order> orders;

    public OrderRepository() {
        this.orders = new HashMap<>();
    }
}
