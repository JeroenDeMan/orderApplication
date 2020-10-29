package be.switchfully.order.business.entity;

import lombok.Data;

import java.util.*;

@Data
public class Order {

    private final String id;
    private final Set<String> itemGroups;
    private final String costumerId;
    private double totalPrice;

    public Order(String costumerId) {
        this.id = UUID.randomUUID().toString();
        this.itemGroups = new HashSet<>();
        this.costumerId = costumerId;
    }

    public void calculatePrice(double totalPrice) {
        setTotalPrice(totalPrice);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
