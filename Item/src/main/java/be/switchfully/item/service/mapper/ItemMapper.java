package be.switchfully.item.service.mapper;

import be.switchfully.item.business.entity.Item;
import be.switchfully.item.service.dto.ItemDTO;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper {

    public Item toEntity(ItemDTO itemDTO) {
        return new Item(
                itemDTO.getName(),
                itemDTO.getDescription(),
                itemDTO.getPrice(),
                itemDTO.getAmount()
        );
    }

    public ItemDTO toDto(Item item) {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setId(item.getId());
        itemDTO.setName(item.getName());
        itemDTO.setDescription(item.getDescription());
        itemDTO.setPrice(item.getPrice());
        itemDTO.setAmount(item.getAmount());

        return itemDTO;
    }
}
