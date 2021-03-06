package be.switchfully.customer.service;

import be.switchfully.customer.business.entity.Customer;
import be.switchfully.customer.business.repository.CustomerRepository;
import be.switchfully.customer.exceptions.NotAuthorizedException;
import be.switchfully.customer.service.dto.AdminDTO;
import be.switchfully.customer.service.dto.CustomerDTO;
import be.switchfully.customer.service.mapper.CustomerMapper;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Data
public class CustomerService {
    public static final String ADMIN_URL = "http://localhost:7080/api/admins/";
    private AdminDTO admin;
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    private boolean isAdminLoggedIn() {
        return admin != null;

    }

    public String logIn(String id) {
        RestTemplate rtAdmin = new RestTemplate();
        AdminDTO adminDTO = rtAdmin.getForObject(ADMIN_URL + id, AdminDTO.class);
        if (adminDTO == null) return "login failed";
        admin = adminDTO;
        return "logged in ";
    }

    public CustomerDTO registerCustomer(CustomerDTO customerDTO) {
        Customer customer = customerMapper.toEntity(customerDTO);
        customerRepository.getCustomers().put(customer.getId(), customer);

        return customerMapper.toDTO(customer);
    }

    public CustomerDTO getCustomerById(String id) {
        if (!isAdminLoggedIn()) throw new NotAuthorizedException("get customer by id");
        return customerMapper.toDTO(customerRepository.getCustomers().get(id));
    }

    public List<CustomerDTO> getAllCustomers() {
        if (!isAdminLoggedIn()) throw new NotAuthorizedException("get all customers");
        return customerRepository.getCustomers()
                .values()
                .stream()
                .map(customerMapper::toDTO)
                .collect(Collectors.toList());
    }
}
