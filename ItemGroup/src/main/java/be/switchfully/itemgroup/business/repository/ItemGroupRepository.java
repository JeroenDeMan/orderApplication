package be.switchfully.itemgroup.business.repository;

import be.switchfully.itemgroup.business.entity.ItemGroup;
import lombok.Data;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
@Data
public class ItemGroupRepository {

    private Map<String, ItemGroup> itemGroups;

    public ItemGroupRepository() {
        this.itemGroups = new HashMap<>();
    }
}
