package be.switchfully.itemgroup.service;

import be.switchfully.itemgroup.business.entity.ItemGroup;
import be.switchfully.itemgroup.business.repository.ItemGroupRepository;
import be.switchfully.itemgroup.service.dto.ItemDTO;
import be.switchfully.itemgroup.service.dto.ItemGroupDTO;
import be.switchfully.itemgroup.service.mapper.ItemGroupMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ItemGroupService {
    public static final String ITEM_URL = "http://localhost:9090/api/items/";

    private ItemGroupRepository itemGroupRepository;
    private ItemGroupMapper itemGroupMapper;

    @Autowired
    public ItemGroupService(ItemGroupRepository itemGroupRepository, ItemGroupMapper itemGroupMapper) {
        this.itemGroupRepository = itemGroupRepository;
        this.itemGroupMapper = itemGroupMapper;
    }

    public ItemGroupDTO retrieveItem(ItemGroupDTO itemGroupDTO) {
        RestTemplate rtItem = new RestTemplate();
        ItemDTO itemDTO = rtItem.getForObject(ITEM_URL + itemGroupDTO.getItemId(), ItemDTO.class);
        itemGroupDTO.setItemName(itemDTO.getName());
        itemGroupDTO.setItemPrice(itemDTO.getPrice());
        itemGroupDTO.setItemStockAmount(itemDTO.getAmount());
        return itemGroupDTO;
    }

    private ItemGroup setShippingDate (int stockAmount, ItemGroup itemGroup) {
        if ((stockAmount - itemGroup.getAmount()) > 0) itemGroup.itemInStockSetShipping();
        else itemGroup.itemOutOfStockSetShipping();

        return itemGroup;
    }

    public ItemGroupDTO addItemGroup(ItemGroupDTO itemGroupDTO) {
        ItemGroupDTO itemGroupResult = retrieveItem(itemGroupDTO);
        ItemGroup itemGroup = setShippingDate(itemGroupResult.getItemStockAmount(), itemGroupMapper.toEntity(itemGroupResult));

        itemGroupRepository.getItemGroups().put(itemGroup.getGroupId(), itemGroup);

        return retrieveItem(itemGroupMapper.toDTO(itemGroup));

    }
}
