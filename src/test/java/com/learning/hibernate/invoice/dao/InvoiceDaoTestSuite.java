package com.learning.hibernate.invoice.dao;

import com.learning.hibernate.invoice.Invoice;
import com.learning.hibernate.invoice.Item;
import com.learning.hibernate.invoice.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
public class InvoiceDaoTestSuite {

    @Autowired
    private InvoiceDao invoiceDao;

    @Test
    void testInvoiceDaoSave() {
        // Given
        Product car = new Product("Car");
        Product smartphone = new Product("Smartphone");
        Product laptop = new Product("Laptop");
        Product shoes = new Product("Shoes");
        Product table = new Product("Table");

        Item item = new Item(new BigDecimal(25000), 25, new BigDecimal(50000));
        Item item1 = new Item(new BigDecimal(500), 10, new BigDecimal(1300));
        Item item2 = new Item(new BigDecimal(2500), 25, new BigDecimal(3000));
        Item item3 = new Item(new BigDecimal(50), 100, new BigDecimal(100));
        Item item4 = new Item(new BigDecimal(500), 3, new BigDecimal(650));

        item.setProduct(car);
        item1.setProduct(smartphone);
        item2.setProduct(laptop);
        item3.setProduct(shoes);
        item4.setProduct(table);

        Invoice invoice = new Invoice("20342134353");
        invoice.getItems().add(item);
        invoice.getItems().add(item1);
        Invoice invoice1 = new Invoice("11454244234");
        invoice1.getItems().add(item2);
        invoice1.getItems().add(item3);
        Invoice invoice2 = new Invoice("12345235432");
        invoice2.getItems().add(item4);

        item.setInvoice(invoice);
        item1.setInvoice(invoice);
        item2.setInvoice(invoice1);
        item3.setInvoice(invoice1);
        item4.setInvoice(invoice2);
        // When
        invoiceDao.save(invoice);
        invoiceDao.save(invoice1);
        invoiceDao.save(invoice2);
        int invoiceId = invoice.getId();
        int invoiceId2 = invoice1.getId();
        int invoiceId3 = invoice2.getId();
        // Then
        assertNotEquals(0, invoiceId);
        assertNotEquals(0, invoiceId2);
        assertNotEquals(0, invoiceId3);
        // CleanUp
        invoiceDao.deleteById(invoiceId);
        invoiceDao.deleteById(invoiceId2);
        invoiceDao.deleteById(invoiceId3);

    }
}
