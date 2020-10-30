package be.switchfully.item.business.entity;

import be.switchfully.item.util.UrgencyIndicator;
import lombok.Data;

import java.util.Objects;
import java.util.UUID;

@Data
public class Item implements Comparable<Item> {

    private final String id;
    private String name;
    private String description;
    private double price;
    private int amount;
    private UrgencyIndicator resupplyEmergency;

    public Item(String name, String description, double price, int amount) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.price = price;
        this.amount = amount;
        determineResupplyEmergency();
    }

    private void determineResupplyEmergency() {
        setResupplyEmergency(UrgencyIndicator.setUrgency(this.amount));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(id, item.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public int compareTo(Item o) {
        return this.getResupplyEmergency().compareTo(o.getResupplyEmergency());
    }
}
