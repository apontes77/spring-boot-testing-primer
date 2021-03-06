package com.alexandrepontes.app.springboottestingprimer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;


    @GetMapping
    public ResponseEntity<List<Customer>> getCustomers() {
        final List<Customer> customers = customerService.returnsCustomerFromDatabase();

        return ResponseEntity.ok().body(customers);
    }

}
