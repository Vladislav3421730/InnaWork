package com.example.warehouse.service;

import com.example.warehouse.entity.Order;
import com.example.warehouse.entity.OrderItem;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.time.format.DateTimeFormatter;

@Service
public class PdfGeneratorService {

    public byte[] generateOrderPdf(Order order) {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Document document = new Document();
            PdfWriter.getInstance(document, out);
            document.open();

            Font titleFont = new Font(Font.HELVETICA, 16, Font.BOLD);
            Font normalFont = new Font(Font.HELVETICA, 12);

            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

            document.add(new Paragraph("Детали заказа", titleFont));
            document.add(new Paragraph(" "));
            document.add(new Paragraph("ID заказа: " + order.getId(), normalFont));
            document.add(new Paragraph("Дата заказа: " + order.getOrderDate().format(dateTimeFormatter), normalFont));
            document.add(new Paragraph("Покупатель: " + order.getCustomer().getName(), normalFont));
            document.add(new Paragraph("Email: " + order.getCustomer().getEmail(), normalFont));
            document.add(new Paragraph(" "));

            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(100);
            table.addCell("Название");
            table.addCell("Кол-во");
            table.addCell("Цена за ед.");
            table.addCell("Сумма");

            for (OrderItem item : order.getOrderItems()) {
                table.addCell(item.getProduct().getName());
                table.addCell(String.valueOf(item.getQuantity()));
                table.addCell(String.format("%.2f", item.getProduct().getPrice()));
                table.addCell(String.format("%.2f", item.getProduct().getPrice() * item.getQuantity()));
            }

            document.add(table);
            document.add(new Paragraph(" "));
            document.add(new Paragraph("Итоговая сумма: " + String.format("%.2f", order.getPrice()), titleFont));

            document.close();
            return out.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при генерации PDF", e);
        }
    }
}
