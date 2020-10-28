package be.switchfully.item.business.repository;

import be.switchfully.item.business.entity.Item;
import lombok.Data;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
@Data
public class ItemRepository {

    private Map<String, Item> items;

    public ItemRepository() {
        this.items = new HashMap<>();
    }
}
