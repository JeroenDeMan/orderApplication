package be.switchfully.item.service;

import be.switchfully.item.business.entity.Item;
import be.switchfully.item.business.repository.ItemRepository;
import be.switchfully.item.exceptions.NotAuthorizedException;
import be.switchfully.item.service.dto.AdminDTO;
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
        itemService.setAdmin(new AdminDTO());
        Assertions.assertEquals(itemDTO.getName(), itemService.addNewItem(itemDTO).getName());
    }

    @Test
    public void whenAdminIsNotLoggedIn_AddingANewItemThrowsNotAuthorizeException() {
        Assertions.assertThrows(NotAuthorizedException.class, () -> itemService.addNewItem(itemDTO));
    }

    @Test
    public void retrievingAItemById_ReturnsExpectedItem() {
        itemService.setAdmin(new AdminDTO());
        ItemDTO expectedResult = itemService.addNewItem(itemDTO);
        Assertions.assertEquals(expectedResult.getName(), itemService.getItemById(expectedResult.getId()).getName());
    }

    @Test
    public void updateAItem_changesTheItemIDataBase() {
        itemService.setAdmin(new AdminDTO());
        ItemDTO item = itemService.addNewItem(itemDTO);

        itemDTO.setName("new name");
        itemDTO.setDescription("changed it");
        itemDTO.setPrice(2.5);
        itemDTO.setAmount(15);

        Assertions.assertEquals("new name", itemService.updateItem(item.getId(), itemDTO).getName());
    }


}