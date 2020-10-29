package be.switchfully.order.service;

import be.switchfully.order.business.entity.Order;
import be.switchfully.order.business.repository.OrderRepository;
import be.switchfully.order.service.dto.OrderDTO;
import be.switchfully.order.service.mapper.OrderMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Or;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {
    OrderService orderService;
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

    }


    @Test
    public void whenGivenAItemGroupId_informationOfItemGroupIsGettingReceived(){
        //Write better testings.
//        System.out.println(orderService.addItemGroup(orderDTO));
//        System.out.println(orderService.getAllOrders());
    }

}