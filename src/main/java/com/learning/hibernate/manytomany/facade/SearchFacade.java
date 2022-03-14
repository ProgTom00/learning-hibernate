package com.learning.hibernate.manytomany.facade;

import com.learning.hibernate.manytomany.Company;
import com.learning.hibernate.manytomany.Employee;
import com.learning.hibernate.manytomany.dao.CompanyDao;
import com.learning.hibernate.manytomany.dao.EmployeeDao;
import org.hibernate.internal.build.AllowPrintStacktrace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchFacade {
    private final EmployeeDao employee;
    private final CompanyDao company;

    @Autowired
    public SearchFacade(EmployeeDao employee, CompanyDao company) {
        this.employee = employee;
        this.company = company;
    }

    public List<Company> findCompaniesByFragment(String input) {
        String fragment = "%" + input + "%";
        return company.retrieveCompaniesWithTheFragment(fragment);
    }

    public List<Employee> findEmployeesByFragment(String input) {
        String fragment = "%" + input + "%";
        return employee.retrieveEmployeeWithTheFragment(fragment);
    }
}
