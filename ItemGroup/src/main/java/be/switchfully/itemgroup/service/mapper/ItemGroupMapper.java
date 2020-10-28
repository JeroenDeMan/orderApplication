package be.switchfully.itemgroup.service.mapper;

import be.switchfully.itemgroup.business.entity.ItemGroup;
import be.switchfully.itemgroup.service.dto.ItemGroupDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ItemGroupMapper {

    public ItemGroup toEntity (ItemGroupDTO itemGroupDTO) {
        return new ItemGroup(itemGroupDTO.getItemId() , itemGroupDTO.getAmount());

    }

    public ItemGroupDTO toDTO (ItemGroup itemGroup) {
        ItemGroupDTO itemGroupDTO = new ItemGroupDTO();
        itemGroupDTO.setGroupId(itemGroup.getGroupId());
        itemGroupDTO.setItemId(itemGroup.getItemId());
        itemGroupDTO.setAmount(itemGroup.getAmount());
        itemGroupDTO.setShippingDate(itemGroup.getShippingDate().toString());

        return itemGroupDTO;
    }
}
