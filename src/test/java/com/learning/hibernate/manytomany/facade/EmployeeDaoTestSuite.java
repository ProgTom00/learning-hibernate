package com.learning.hibernate.manytomany.facade;

import com.learning.hibernate.manytomany.Company;
import com.learning.hibernate.manytomany.Employee;
import com.learning.hibernate.manytomany.dao.CompanyDao;
import com.learning.hibernate.manytomany.dao.EmployeeDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class EmployeeDaoTestSuite {

    @Autowired
    private EmployeeDao employeeDao;
    @Autowired
    private SearchFacade searchFacade;

    @Test
    void findEmployeesByFragment() {
        // Given
        Employee johnSmith = new Employee("John", "Smith");
        Employee thomasKovalsky = new Employee("Thomas", "Kovalsky");
        Employee stephanieClarckson = new Employee("Stephanie", "Clarckson");
        Employee lindaKovalsky = new Employee("Linda", "Kovalsky");
        Employee johnKovalsky = new Employee("John", "Kovalsky");
        // When
        employeeDao.save(johnSmith);
        employeeDao.save(thomasKovalsky);
        employeeDao.save(lindaKovalsky);
        employeeDao.save(johnKovalsky);
        employeeDao.save(stephanieClarckson);

        int johnId = johnSmith.getId();
        int stephanieId = stephanieClarckson.getId();
        int lindaId = lindaKovalsky.getId();
        int johnKovalskyId = johnKovalsky.getId();
        int thomasKovalskyId = thomasKovalsky.getId();

        List<Employee> employeeList = searchFacade.findEmployeesByFragment("Kova");
        // Then
        assertEquals(3,employeeList.size());
        try {
            // CleanUp
            employeeDao.deleteById(johnId);
            employeeDao.deleteById(stephanieId);
            employeeDao.deleteById(lindaId);
            employeeDao.deleteById(johnKovalskyId);
            employeeDao.deleteById(thomasKovalskyId);
        } catch (Exception e) {

        }
    }
}
