package com.alexandrepontes.app.springboottestingprimer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> returnsCustomerFromDatabase() {
        return customerRepository.findAll();
    }

}
