package com.learning.hibernate.manytomany;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NamedNativeQueries({
        @NamedNativeQuery(
                name = "Company.retrieveCompaniesWithThreeLetters",
                query = "SELECT * FROM COMPANIES WHERE SUBSTRING(COMPANY_NAME,1,3) = :INPUT",
                resultClass = Company.class
        ),
        @NamedNativeQuery(
                name = "Company.retrieveCompaniesWithTheFragment",
                query = "SELECT * FROM COMPANIES WHERE COMPANY_NAME LIKE :INPUT",
                resultClass = Company.class
        )
})
@Entity
@Table(name = "COMPANIES")
public class Company {

    private int id;
    private String name;
    private List<Employee> employees = new ArrayList<>();

    public Company() {
    }

    public Company(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "COMPANY_ID", nullable = false, unique = true)
    public int getId() {
        return id;
    }

    @NotNull
    @Column(name = "COMPANY_NAME")
    public String getName() {
        return name;
    }

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "companies")
    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    private void setId(int id) {
        this.id = id;
    }

    private void setName(String name) {
        this.name = name;
    }
}