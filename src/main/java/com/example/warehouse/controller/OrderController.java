package com.example.warehouse.controller;

import com.example.warehouse.entity.Order;
import com.example.warehouse.service.CustomerService;
import com.example.warehouse.service.OrderService;
import com.example.warehouse.service.PdfGeneratorService;
import com.example.warehouse.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final ProductService productService;
    private final CustomerService customerService;
    private final PdfGeneratorService pdfGeneratorService;

    @GetMapping
    public String getOrderPage(Model model) {
        model.addAttribute("orders",orderService.findAll());
        return "orders";
    }

    @GetMapping("/add")
    public String getAddOrderPage(Model model) {
        model.addAttribute("products", productService.findAll());
        model.addAttribute("customers", customerService.findAll());
        return "addOrder";
    }

    @GetMapping("/{orderId}")
    public String getOrder(@PathVariable Long orderId, Model model) {
        model.addAttribute("order", orderService.findById(orderId));
        return "order";
    }

    @GetMapping("/download/{orderId}")
    public ResponseEntity<byte[]> addOrder(@PathVariable Long orderId) {

        Order order = orderService.findById(orderId);

        byte[] pdfBytes = pdfGeneratorService.generateOrderPdf(order);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDisposition(ContentDisposition
                .builder("attachment")
                .filename("order_" + order.getId() + ".pdf")
                .build());

        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }


    @PostMapping("/add")
    public String addOrder(
            @RequestParam Long customerId,
            @RequestParam List<Long> productIds,
            @RequestParam List<Integer> quantities,
            Principal principal,
            Model model) {
        model.addAttribute("products", productService.findAll());
        model.addAttribute("customers", customerService.findAll());
        orderService.save(principal, customerId, productIds, quantities);
        return "redirect:/orders";
    }

    @PostMapping("/update/status/{orderId}")
    public String updateAddress(@PathVariable Long orderId, @RequestParam String status) {
        orderService.update(orderId,status);
        return "redirect:/orders/"+orderId;
    }


}
