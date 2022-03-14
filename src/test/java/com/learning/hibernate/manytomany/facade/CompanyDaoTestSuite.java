package com.learning.hibernate.manytomany.facade;

import com.learning.hibernate.manytomany.Company;
import com.learning.hibernate.manytomany.dao.CompanyDao;
import com.learning.hibernate.manytomany.dao.EmployeeDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CompanyDaoTestSuite {

    @Autowired
    private CompanyDao companyDao;
    @Autowired
    private SearchFacade searchFacade;

    @Test
    void findCompaniesByFragment() {
    // Given
        Company softwareMachine = new Company("Software Machine");
        Company dataMasters = new Company("Data Maesters");
        Company greyMatter = new Company("Grey Matter");
        Company greyHole = new Company("Grey Hole");
        Company dataProducers = new Company("Data Producers");
        // When
        companyDao.save(softwareMachine);
        companyDao.save(dataMasters);
        companyDao.save(greyHole);
        companyDao.save(greyMatter);
        companyDao.save(dataProducers);

        int softwareMachineId = softwareMachine.getId();
        int dataMastersId = dataMasters.getId();
        int greyMatterId = greyMatter.getId();
        int greyHoleId = greyHole.getId();
        int dataProducersId = dataProducers.getId();

        List<Company> companyList = searchFacade.findCompaniesByFragment("Grey");
        // Then
        assertEquals(2, companyList.size());
        try {
            // CleanUp
            companyDao.deleteById(softwareMachineId);
            companyDao.deleteById(dataMastersId);
            companyDao.deleteById(greyHoleId);
            companyDao.deleteById(greyMatterId);
            companyDao.deleteById(dataProducersId);
        } catch (Exception e) {

        }

    }
}
