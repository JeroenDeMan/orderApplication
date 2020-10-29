package be.switchfully.order.service.mapper;

import be.switchfully.order.business.entity.Order;
import be.switchfully.order.service.dto.OrderDTO;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    public Order toEntity (OrderDTO orderDTO) {
        return new Order(orderDTO.getCustomerId());
    }

    public OrderDTO toDTO (Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setCustomerId(order.getCostumerId());


        return orderDTO;
    }
}
