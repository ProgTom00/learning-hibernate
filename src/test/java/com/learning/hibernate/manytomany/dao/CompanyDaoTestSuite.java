package com.learning.hibernate.manytomany.dao;

import com.learning.hibernate.manytomany.Company;
import com.learning.hibernate.manytomany.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
class CompanyDaoTestSuite {
    @Autowired
    private CompanyDao companyDao;

    @Autowired
    private EmployeeDao employeeDao;

    @Test
    void testSaveManyToMany() {
//Given
        Employee johnSmith = new Employee("John", "Smith");
        Employee stephanieClarckson = new Employee("Stephanie", "Clarckson");
        Employee lindaKovalsky = new Employee("Linda", "Kovalsky");
        Company softwareMachine = new Company("Software Machine");
        Company dataMasters = new Company("Data Maesters");
        Company greyMatter = new Company("Grey Matter");
        softwareMachine.getEmployees().add(johnSmith);
        dataMasters.getEmployees().add(stephanieClarckson);
        dataMasters.getEmployees().add(lindaKovalsky);
        greyMatter.getEmployees().add(johnSmith);
        greyMatter.getEmployees().add(lindaKovalsky);
        johnSmith.getCompanies().add(softwareMachine);
        johnSmith.getCompanies().add(greyMatter);
        stephanieClarckson.getCompanies().add(dataMasters);
        lindaKovalsky.getCompanies().add(dataMasters);
        lindaKovalsky.getCompanies().add(greyMatter);
//When
        companyDao.save(softwareMachine);
        int softwareMachineId = softwareMachine.getId();
        companyDao.save(dataMasters);
        int dataMaestersId = dataMasters.getId();
        companyDao.save(greyMatter);
        int greyMatterId = greyMatter.getId();
//Then
        assertNotEquals(0, softwareMachineId);
        assertNotEquals(0, dataMaestersId);
        assertNotEquals(0, greyMatterId);
//CleanUp
        try {
            companyDao.deleteById(softwareMachineId);
            companyDao.deleteById(dataMaestersId);
            companyDao.deleteById(greyMatterId);
        } catch (Exception e) {
            System.out.println();

        }
    }

    @Test
    void testEmployeesNamedQueries() {
        // Given
        Employee johnSmith = new Employee("John", "Smith");
        Employee thomasKovalsky = new Employee("Thomas", "Kovalsky");
        Employee stephanieClarckson = new Employee("Stephanie", "Clarckson");
        Employee lindaKovalsky = new Employee("Linda", "Kovalsky");
        Employee johnKovalsky = new Employee("John", "Kovalsky");

        // When
        int johnId = johnSmith.getId();
        int stephanieId = stephanieClarckson.getId();
        int lindaId = lindaKovalsky.getId();
        int johnKovalskyId = johnKovalsky.getId();
        int thomasKovalskyId = thomasKovalsky.getId();

        employeeDao.save(johnSmith);
        employeeDao.save(stephanieClarckson);
        employeeDao.save(lindaKovalsky);
        employeeDao.save(johnKovalsky);
        employeeDao.save(thomasKovalsky);

        List<Employee> employeeList = employeeDao.retrieveEmployeeWithLastname("Kovalsky");
        // Then
        assertEquals(3, employeeList.size());
        try {
            // CleanUp
            employeeDao.deleteById(johnId);
            employeeDao.deleteById(stephanieId);
            employeeDao.deleteById(lindaId);
            employeeDao.deleteById(johnKovalskyId);
            employeeDao.deleteById(thomasKovalskyId);
        } catch (Exception e) {
            System.out.println();
        }
    }

    @Test
    void testCompaniesNamedQueries() {
        // Given
        Company softwareMachine = new Company("Software Machine");
        Company dataMasters = new Company("Data Maesters");
        Company greyMatter = new Company("Grey Matter");
        Company greyHole = new Company("Grey Hole");
        Company dataProducers = new Company("Data Producers");

        // When
        companyDao.save(softwareMachine);
        companyDao.save(dataMasters);
        companyDao.save(greyMatter);
        companyDao.save(greyHole);
        companyDao.save(dataProducers);

        int softwareMachineId = softwareMachine.getId();
        int dataMastersId = dataMasters.getId();
        int getMatterId = greyMatter.getId();
        int greyHoleId = greyHole.getId();
        int dataProducersId = dataProducers.getId();

        List<Company> companyList = companyDao.retrieveCompaniesWithThreeLetters("GRE");
        List<Company> softCompany = companyDao.retrieveCompaniesWithThreeLetters("SOF");
        List<Company> dataCompany = companyDao.retrieveCompaniesWithThreeLetters("DAT");
        // Then
        assertEquals(2, companyList.size());
        assertNotEquals(2, softCompany.size());
        assertEquals(2, dataCompany.size());
        // CleanUp
        try {
            companyDao.deleteById(softwareMachineId);
            companyDao.deleteById(dataMastersId);
            companyDao.deleteById(getMatterId);
            companyDao.deleteById(greyHoleId);
            companyDao.deleteById(dataProducersId);
        } catch (Exception e) {
            System.out.println();
        }
    }
}
