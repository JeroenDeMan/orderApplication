package be.switchfully.itemgroup.service;

import be.switchfully.itemgroup.business.entity.ItemGroup;
import be.switchfully.itemgroup.business.repository.ItemGroupRepository;
import be.switchfully.itemgroup.exceptions.ItemGroupNotFoundException;
import be.switchfully.itemgroup.service.dto.ItemGroupDTO;
import be.switchfully.itemgroup.service.mapper.ItemGroupMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ItemGroupServiceTest {
    ItemGroup itemGroup;
    ItemGroupDTO itemGroupDTO;
    ItemGroupService itemGroupService;
    ItemGroupRepository repository;

    @BeforeEach
    public void setUp() {
        itemGroup = new ItemGroup("0001", 5);
        itemGroupDTO = new ItemGroupDTO();
        itemGroupDTO.setItemStockAmount(10);
        itemGroupDTO.setGroupId("0001");
        itemGroupDTO.setAmount(5);
        itemGroupDTO.setItemName("TestItem");
        itemGroupDTO.setItemPrice(2.5);

        repository = new ItemGroupRepository();

        itemGroupService = new ItemGroupService(repository, new ItemGroupMapper());
    }

    @Test
    public void givenAItemGroupAndPositiveStock_ShippingDateIsNextDay() {
        LocalDate expectedDate = LocalDate.now().plusDays(1L);
        ItemGroup result = itemGroupService.setShippingDate(7, itemGroup);

        assertEquals(expectedDate, result.getShippingDate());
    }

    @Test
    public void givenAItemGroupAndNegativeStock_ShippingDateIsIn7Day() {
        LocalDate expectedDate = LocalDate.now().plusDays(7L);
        ItemGroup result = itemGroupService.setShippingDate(0, itemGroup);

        assertEquals(expectedDate, result.getShippingDate());
    }

    @Test
    public void calculatePriceGiveItemPriceAndAmount_ReturnsExpectPrice() {
        double expectedResult = 12.5;
        itemGroupService.calculateGroupPrice(itemGroup, itemGroupDTO);

        assertEquals(expectedResult, itemGroup.getGroupPrice());
    }

    @Test
    public void givenWrongItemGroupId_NotFoundExceptionIsThrown() {
        assertThrows(ItemGroupNotFoundException.class, () -> itemGroupService.getItemGroupById("1234"));
    }

}