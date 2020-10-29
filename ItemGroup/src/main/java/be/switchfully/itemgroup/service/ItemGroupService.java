package be.switchfully.itemgroup.service;

import be.switchfully.itemgroup.business.entity.ItemGroup;
import be.switchfully.itemgroup.business.repository.ItemGroupRepository;
import be.switchfully.itemgroup.exceptions.ItemGroupNotFoundException;
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

    public ItemGroup setShippingDate (int stockAmount, ItemGroup itemGroup) {
        if ((stockAmount - itemGroup.getAmount()) > 0) itemGroup.itemInStockSetShipping();
        else itemGroup.itemOutOfStockSetShipping();

        return itemGroup;
    }

    public void calculateGroupPrice(ItemGroup itemGroup, ItemGroupDTO itemGroupDTO) {
        itemGroup.setGroupPrice(itemGroupDTO.getItemPrice() * itemGroup.getAmount());

    }

    public ItemGroupDTO addItemGroup(ItemGroupDTO itemGroupDTO) {
        ItemGroupDTO itemGroupResult = retrieveItem(itemGroupDTO);
        ItemGroup itemGroup = setShippingDate(itemGroupResult.getItemStockAmount(), itemGroupMapper.toEntity(itemGroupResult));
        calculateGroupPrice(itemGroup, itemGroupDTO);

        itemGroupRepository.getItemGroups().put(itemGroup.getGroupId(), itemGroup);

        return retrieveItem(itemGroupMapper.toDTO(itemGroup));

    }

    public ItemGroupDTO getItemGroupById(String id) {
        if(!itemGroupRepository.getItemGroups().containsKey(id)) throw new ItemGroupNotFoundException(id);
        ItemGroupDTO result = itemGroupMapper.toDTO(itemGroupRepository.getItemGroups().get(id));
        return retrieveItem(result);
    }
}
