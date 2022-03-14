package com.learning.hibernate.invoice.dao;

import com.learning.hibernate.invoice.Invoice;
import org.springframework.data.repository.CrudRepository;

public interface InvoiceDao extends CrudRepository<Invoice, Integer> {

}
