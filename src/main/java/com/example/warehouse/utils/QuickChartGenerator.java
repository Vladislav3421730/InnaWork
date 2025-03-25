package com.example.warehouse.utils;

import lombok.extern.slf4j.Slf4j;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Slf4j
public class QuickChartGenerator {

    public static String generateChartUrl(Map<String, Double> salesByMonth) {
        StringBuilder data = new StringBuilder();
        StringBuilder labels = new StringBuilder();

        for (Map.Entry<String, Double> entry : salesByMonth.entrySet()) {
            labels.append("\"").append(entry.getKey()).append("\",");
            data.append(entry.getValue()).append(",");
        }

        if (labels.length() > 0) labels.setLength(labels.length() - 1);
        if (data.length() > 0) data.setLength(data.length() - 1);
        String chartData = String.format(
                "{\n" +
                        "  \"type\": \"bar\",\n" +
                        "  \"data\": {\n" +
                        "    \"labels\": [%s],\n" +
                        "    \"datasets\": [{\n" +
                        "      \"label\": \"Sales by Month\",\n" +
                        "      \"data\": [%s],\n" +
                        "      \"backgroundColor\": \"rgba(54, 162, 235, 0.2)\",\n" +
                        "      \"borderColor\": \"rgba(54, 162, 235, 1)\",\n" +
                        "      \"borderWidth\": 1\n" +
                        "    }]\n" +
                        "  }\n" +
                        "}", labels.toString(), data.toString());

        try {
            String encodedData = URLEncoder.encode(chartData, StandardCharsets.UTF_8);
            return "https://quickchart.io/chart?c=" + encodedData;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
