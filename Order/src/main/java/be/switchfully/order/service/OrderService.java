package be.switchfully.order.service;

import be.switchfully.order.business.entity.Order;
import be.switchfully.order.business.repository.OrderRepository;
import be.switchfully.order.service.dto.CustomerDTO;
import be.switchfully.order.service.dto.ItemGroupDTO;
import be.switchfully.order.service.dto.OrderDTO;
import be.switchfully.order.service.dto.OrderDTOCustomer;
import be.switchfully.order.service.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    public static final String ITEM_GROUP_URL = "http://localhost:8070/api/itemGroups/";
    public static final String COSTUMER_URL = "http://localhost:8080/api/customers/";

    private OrderRepository orderRepository;
    private OrderMapper orderMapper;

    @Autowired
    public OrderService(OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    private OrderDTO addCustomerName (OrderDTO orderDTO) {
        RestTemplate rtCustomer = new RestTemplate();
        CustomerDTO customerDTO = rtCustomer.getForObject(COSTUMER_URL + orderDTO.getCustomerId(), CustomerDTO.class);
       orderDTO.setCustomerName (customerDTO.getFirstName() + " " + customerDTO.getLastName());
        return orderDTO;
    }

    private OrderDTO addItemGroupInformation(OrderDTO orderDTO) {
        RestTemplate rtItemGroup = new RestTemplate();
        Order order = orderRepository.getOrders().get(orderDTO.getId());

        if(!order.getItemGroups().isEmpty()) {
            for (String itemGroupId: order.getItemGroups()) {
                orderDTO.getItemGroups().add(rtItemGroup.getForObject(ITEM_GROUP_URL + itemGroupId, ItemGroupDTO.class));
            }
        }

        return orderDTO;
    }

    public OrderDTO calculatePrice(OrderDTO orderDTO) {
        orderDTO.setTotalPrice(orderDTO.getItemGroups().stream().mapToDouble(ItemGroupDTO::getGroupPrice).sum());
        orderRepository.getOrders().get(orderDTO.getId()).calculatePrice(orderDTO.getTotalPrice());
        return orderDTO;
    }

    private OrderDTO addPriceItemGroupsAndCustomer(OrderDTO orderDTO){
        addCustomerName(orderDTO);
        addItemGroupInformation(orderDTO);
        calculatePrice(orderDTO);
        return orderDTO;
    }

    public List<OrderDTO> getAllOrders() {
        return orderRepository.getOrders()
                .values()
                .stream()
                .map(order -> orderMapper.toDTO(order))
                .map(this::addPriceItemGroupsAndCustomer)
                .collect(Collectors.toList());
    }

    public OrderDTO createOrder (OrderDTO orderDTO) {
        Order order = orderMapper.toEntity(orderDTO);
        orderRepository.getOrders().put(order.getId(), order);

        OrderDTO orderDTOResult = orderMapper.toDTO(order);

        return addPriceItemGroupsAndCustomer(orderDTOResult);
    }

    public void addGroupItems (String id, List<String> itemGroupKeys) {
        orderRepository.getOrders().get(id).getItemGroups().addAll(itemGroupKeys);
    }

    public OrderDTOCustomer getOrdersForSpecificCustomer(String id){
        OrderDTOCustomer result = new OrderDTOCustomer();

        List<OrderDTO> orders = orderRepository.getOrders().values()
                .stream()
                .filter(order -> order.getCostumerId().equals(id))
                .map(order -> orderMapper.toDTO(order))
                .map(this::addPriceItemGroupsAndCustomer)
                .collect(Collectors.toList());

        //result.setCustomerName(addCustomerName(id));
        result.getOrderList().addAll(orders);
        result.setTotalPrice(orders.stream().mapToDouble(OrderDTO::getTotalPrice ).sum());

        return result;

    }
}
