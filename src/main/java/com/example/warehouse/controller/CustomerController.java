package com.example.warehouse.controller;

import com.example.warehouse.entity.Customer;
import com.example.warehouse.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/counterparties")
@RequiredArgsConstructor
@Slf4j
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public String getCustomerPage(@RequestParam(required = false, defaultValue = "") String search, Model model) {
        List<Customer> customers = customerService.findAll(search);
        model.addAttribute("customers",customers);
        model.addAttribute("search", search);
        return "counterparties";
    }

    @GetMapping("/add")
    public String getAddCustomerPage(Model model) {
        model.addAttribute("customer", new Customer());
        model.addAttribute("edit", false);
        return "addCounterparty";
    }

    @PostMapping("/add")
    public String addCustomer(@Valid Customer customer, BindingResult result) {
        if (result.hasErrors()) {
            return "addCounterparty";
        }
        customerService.save(customer);
        return "redirect:/counterparties";
    }

    @GetMapping("/edit/{customerId}")
    public String getEditPage(@PathVariable Long customerId, Model model) {
        Customer customer = customerService.findById(customerId);
        model.addAttribute("customer", customer);
        model.addAttribute("edit", true);
        return "addCounterparty";
    }
}

