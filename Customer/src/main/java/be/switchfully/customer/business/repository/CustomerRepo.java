package be.switchfully.customer.business.repository;

import be.switchfully.customer.business.entity.Customer;
import lombok.Data;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
@Data
public class CustomerRepo {

    private Map<String, Customer> customers;

    public CustomerRepo() {
        this.customers = new HashMap<>();
    }
}
