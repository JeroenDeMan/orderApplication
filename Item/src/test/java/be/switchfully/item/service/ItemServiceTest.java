package be.switchfully.item.service;

import be.switchfully.item.business.entity.Item;
import be.switchfully.item.business.repository.ItemRepository;
import be.switchfully.item.service.dto.ItemDTO;
import be.switchfully.item.service.mapper.ItemMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemServiceTest {
    private Item item;
    private ItemDTO itemDTO;
    private ItemService itemService;

    @BeforeEach
    public void setUp() {
        item = new Item("charger", "I don't know what it does", 5.0, 10);
        itemDTO = new ItemDTO();
        itemDTO.setName(item.getName());
        itemDTO.setDescription(item.getDescription());
        itemDTO.setPrice(item.getPrice());
        itemDTO.setAmount(item.getAmount());

        itemService = new ItemService(new ItemRepository(), new ItemMapper());
    }

    @Test
    public void giveANewItem_isSaveAndReturnsSameItemInfo(){
        Assertions.assertEquals(itemDTO.getName(), itemService.addNewItem(itemDTO).getName());
    }

}