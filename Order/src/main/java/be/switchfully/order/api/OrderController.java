package be.switchfully.order.api;

import be.switchfully.order.service.OrderService;

import be.switchfully.order.service.dto.OrderDTO;
import be.switchfully.order.service.dto.OrderDTOCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping (path = "/api/orders")
public class OrderController {

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<OrderDTO> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping(path = "/myorder/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.FOUND)
    public OrderDTOCustomer getOrdersForSpecificCustomer(@PathVariable String id) {
        return orderService.getOrdersForSpecificCustomer(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDTO creatOrder(@RequestBody OrderDTO orderDTO) {
        return orderService.createOrder(orderDTO);
    }

    @PostMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void addGroupItems(@PathVariable String id, @RequestBody List<String> itemGroupKeysDTO) {
        orderService.addGroupItems(id, itemGroupKeysDTO);
    }
}
