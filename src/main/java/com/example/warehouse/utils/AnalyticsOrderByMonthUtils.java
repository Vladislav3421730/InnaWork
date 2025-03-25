package com.example.warehouse.utils;

import com.example.warehouse.entity.Order;
import lombok.extern.slf4j.Slf4j;

import java.time.Month;
import java.time.Year;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class AnalyticsOrderByMonthUtils {

    public static Map<String, Double> getSumSalesByMonth(List<Order> orders) {
        Map<String, Double> salesByMonth = orders.stream()
                .filter(x -> x.getOrderDate().getYear() == Year.now().getValue() - 1)
                .collect(Collectors.groupingBy(order -> order.getOrderDate().getMonth().toString(),
                        Collectors.summingDouble(Order::getPrice)));
        List<String> months = Arrays.stream(Month.values()).map(Enum::toString).toList();
        Map<String, Double> sortedSalesByMonth = new LinkedHashMap<>();
        for (String month : months) {
            sortedSalesByMonth.put(month, salesByMonth.getOrDefault(month, 0.0));
        }
        return sortedSalesByMonth;
    }

    public static Map<String, Double> getCountSalesByMonth(List<Order> orders) {
        Map<String, Long> salesByMonth = orders.stream()
                .filter(x -> x.getOrderDate().getYear() == Year.now().getValue() - 1)
                .collect(Collectors.groupingBy(order -> order.getOrderDate().getMonth().toString(),
                        Collectors.counting()));
        List<String> months = Arrays.stream(Month.values()).map(Enum::toString).toList();
        Map<String, Double> sortedSalesByMonth = new LinkedHashMap<>();
        for (String month : months) {
            sortedSalesByMonth.put(month, salesByMonth.getOrDefault(month, 0L).doubleValue());
        }
        return sortedSalesByMonth;
    }

    public static Map<String, Double> getAverageCheckByMonth(List<Order> orders) {
        Map<String, Double> salesByMonth = orders.stream()
                .filter(x -> x.getOrderDate().getYear() == Year.now().getValue() - 1)
                .collect(Collectors.groupingBy(order -> order.getOrderDate().getMonth().toString(),
                        Collectors.averagingDouble(Order::getPrice)));
        List<String> months = Arrays.stream(Month.values()).map(Enum::toString).toList();
        Map<String, Double> sortedSalesByMonth = new LinkedHashMap<>();
        for (String month : months) {
            sortedSalesByMonth.put(month, salesByMonth.getOrDefault(month, 0.0));
        }
        return sortedSalesByMonth;
    }

}
