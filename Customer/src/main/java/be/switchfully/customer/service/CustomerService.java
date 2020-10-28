package be.switchfully.customer.service;

import be.switchfully.customer.business.entity.Customer;
import be.switchfully.customer.business.repository.CustomerRepo;
import be.switchfully.customer.service.dto.CustomerDTO;
import be.switchfully.customer.service.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private CustomerRepo customerRepo;
    private CustomerMapper customerMapper;

    @Autowired
    public CustomerService(CustomerRepo customerRepo, CustomerMapper customerMapper) {
        this.customerRepo = customerRepo;
        this.customerMapper = customerMapper;
    }

    public CustomerDTO registerCustomer(CustomerDTO customerDTO) {
        Customer customer = customerMapper.toEntity(customerDTO);
        customerRepo.getCustomers().put(customer.getId(), customer);

        return customerMapper.toDTO(customer);
    }
}
