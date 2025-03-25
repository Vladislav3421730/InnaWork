package com.example.warehouse.controller;

import com.example.warehouse.entity.Customer;
import com.example.warehouse.entity.Order;
import com.example.warehouse.service.CustomerService;
import com.example.warehouse.service.OrderService;
import com.example.warehouse.utils.AnalyticsOrderByMonthUtils;
import com.example.warehouse.utils.QuickChartGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/analytics")
public class AnalyticsController {

    private final OrderService orderService;
    private final CustomerService customerService;

    @GetMapping("/all")
    public String getAnalyticsPage(Model model) {
        List<Order> orders = orderService.findAll();
        getGraphics(orders, model);
        return "analytics";
    }

    @GetMapping("/{customerId}")
    public String getAnalyticsPageForEachCustomer(@PathVariable Long customerId, Model model) {
        Customer customer = customerService.findById(customerId);
        getGraphics(customer.getOrders(), model);
        return "analytics";
    }

    private void getGraphics(List<Order> orders, Model model) {

        Map<String, Double> sumSalesByMonth = AnalyticsOrderByMonthUtils.getSumSalesByMonth(orders);
        String sumUrl = QuickChartGenerator.generateChartUrl(sumSalesByMonth);

        Map<String, Double> countSalesByMonth = AnalyticsOrderByMonthUtils.getCountSalesByMonth(orders);
        String countUrl = QuickChartGenerator.generateChartUrl(countSalesByMonth);

        Map<String, Double> averageCheckByMonth = AnalyticsOrderByMonthUtils.getAverageCheckByMonth(orders);
        String averageUrl = QuickChartGenerator.generateChartUrl(averageCheckByMonth);

        model.addAttribute("sumUrl", sumUrl);
        model.addAttribute("countUrl", countUrl);
        model.addAttribute("averageUrl", averageUrl);
    }

}
