package be.switchfully.order.service;

import be.switchfully.order.business.entity.Order;
import be.switchfully.order.business.repository.OrderRepository;
import be.switchfully.order.service.dto.ItemGroupDTO;
import be.switchfully.order.service.dto.OrderDTO;
import be.switchfully.order.service.mapper.OrderMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Or;
import org.springframework.util.Assert;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {
    OrderService orderService;
    ItemGroupDTO itemGroupDTO1;
    ItemGroupDTO itemGroupDTO2;
    OrderDTO orderDTO;
    Order order;
    OrderRepository orderRepository;

    @BeforeEach
    public void setUp() {
        orderRepository = new OrderRepository();
        orderService = new OrderService(orderRepository, new OrderMapper());

        order = new Order("2e6dd26b-6119-4ad1-8d14-362721a33da7");
        order.getItemGroups().add("92876fad-c922-43d5-a504-1ab513e2d101");

        orderRepository.getOrders().put(order.getId(), order);

        orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setCustomerId(order.getCostumerId());

        itemGroupDTO1 = new ItemGroupDTO();
        itemGroupDTO1.setGroupId("5555");
        itemGroupDTO1.setShippingDate("12/11/2020");
        itemGroupDTO1.setAmount(5);
        itemGroupDTO1.setGroupPrice(12.5);
        itemGroupDTO1.setItemName("Item 1");

        itemGroupDTO2 = new ItemGroupDTO();
        itemGroupDTO2.setGroupId("4444");
        itemGroupDTO2.setShippingDate("12/11/2020");
        itemGroupDTO2.setAmount(2);
        itemGroupDTO2.setGroupPrice(10);
        itemGroupDTO2.setItemName("Item 2");

        orderDTO.getItemGroups().add(itemGroupDTO1);
        orderDTO.getItemGroups().add(itemGroupDTO2);
    }


    @Test
    public void whenCalculatingPrice_thePriceIsCalculateForTheEntireOrder() {
        double expectedResult = 22.5;

        Assertions.assertEquals(expectedResult, orderService.calculatePrice(orderDTO).getTotalPrice());

    }

}