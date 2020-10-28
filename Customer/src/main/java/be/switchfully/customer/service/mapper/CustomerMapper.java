package be.switchfully.customer.service.mapper;

import be.switchfully.customer.business.entity.Address;
import be.switchfully.customer.business.entity.Customer;
import be.switchfully.customer.service.dto.CustomerDTO;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public Customer toEntity(CustomerDTO customerDTO) {
        return new Customer(
                customerDTO.getFirstName(),
                customerDTO.getLastName(),
                customerDTO.getMailAddress(),
                customerDTO.getPhoneNumber(),
                new Address(customerDTO.getStreet(),
                        customerDTO.getNumber(),
                        customerDTO.getCity(),
                        customerDTO.getPostalCode())
        );


    }

    public CustomerDTO toDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(customer.getId());
        customerDTO.setFirstName(customer.getFirstName());
        customerDTO.setLastName(customer.getLastName());
        customerDTO.setMailAddress(customer.getMailAddress());
        customerDTO.setPhoneNumber(customer.getPhoneNumber());
        customerDTO.setStreet(customer.getAddress().getStreet());
        customerDTO.setNumber(customer.getAddress().getNumber());
        customerDTO.setCity(customer.getAddress().getCity());
        customerDTO.setPostalCode(customer.getAddress().getPostalCode());

        return customerDTO;
    }
}
