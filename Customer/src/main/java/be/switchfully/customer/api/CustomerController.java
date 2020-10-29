package be.switchfully.customer.api;

import be.switchfully.customer.business.entity.Customer;
import be.switchfully.customer.service.CustomerService;
import be.switchfully.customer.service.dto.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/customers")
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping(path = "/login/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String logIn(@PathVariable String id) {
        return customerService.logIn(id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerDTO> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.FOUND)
    public CustomerDTO getCustomerById(@PathVariable String id) {
        return customerService.getCustomerById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDTO registerCustomer(@RequestBody CustomerDTO customerDTO) {
        return customerService.registerCustomer(customerDTO);
    }

}
