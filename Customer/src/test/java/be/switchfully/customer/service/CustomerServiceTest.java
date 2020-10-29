package be.switchfully.customer.service;

import be.switchfully.customer.business.entity.Address;
import be.switchfully.customer.business.entity.Customer;
import be.switchfully.customer.business.repository.CustomerRepository;
import be.switchfully.customer.exceptions.NotAuthorizedException;
import be.switchfully.customer.service.dto.AdminDTO;
import be.switchfully.customer.service.dto.CustomerDTO;
import be.switchfully.customer.service.mapper.CustomerMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Not;
import org.springframework.util.Assert;

class CustomerServiceTest {
    private Address address;
    private Customer customer;
    private CustomerDTO customerDTO;
    private CustomerService customerService;

    @BeforeEach
    public void setUp() {
        address = new Address("Some Street", "22", "Lokeren", 9160);
        customer = new Customer("Jeroen", "De Man", "myMail@gmail.com", "0477778181", address);
        customerDTO = new CustomerDTO();
        customerDTO.setFirstName(customer.getFirstName());
        customerDTO.setLastName(customer.getLastName());
        customerDTO.setMailAddress(customer.getMailAddress());
        customerDTO.setPhoneNumber(customer.getPhoneNumber());
        customerDTO.setStreet(customer.getAddress().getStreet());
        customerDTO.setNumber(customer.getAddress().getNumber());
        customerDTO.setCity(customer.getAddress().getCity());
        customerDTO.setPostalCode(customer.getAddress().getPostalCode());

        customerService = new CustomerService(new CustomerRepository(), new CustomerMapper());
    }

    @Test
    public void giveCustomerDTO_savesCustomer() {

        Assertions.assertEquals(customerDTO.getFirstName(), customerService.registerCustomer(customerDTO).getFirstName());
    }

    @Test
    public void ifAdminIsNotLoggedIn_GettingAllCustomersThrowsNotAuthorizedException() {
        Assertions.assertThrows(NotAuthorizedException.class, () -> customerService.getAllCustomers());
    }

    @Test
    public void ifAdminIsNotLoggedIn_gettingCustomerByIdThrowsNotAuthorizedException() {
        Assertions.assertThrows(NotAuthorizedException.class, () -> customerService.getCustomerById("1234"));
    }

    @Test
    public void AddingCustomer_increaseSizeOfCustomerRepository() {
        CustomerDTO result = customerService.registerCustomer(customerDTO);
        customerService.setAdmin(new AdminDTO());

        Assertions.assertEquals(result.getId(), customerService.getCustomerById(result.getId()).getId());
        Assertions.assertEquals(1, customerService.getAllCustomers().size());
    }

}