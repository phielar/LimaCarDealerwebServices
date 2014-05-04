/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package philaman.cput.cardealer.test.repository;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import philaman.cput.cardealer.app.config.AppConfig;
import philaman.cput.cardealer.domain.Commission;
import philaman.cput.cardealer.domain.SalesGrade;
import philaman.cput.cardealer.domain.SalesPerson;
import philaman.cput.cardealer.repository.SalesGradeRepository;
import philaman.cput.cardealer.repository.SalesPersonRepository;

/**
 *
 * @author phila
 */
public class SalesPersonRepositoryTest {
    
    private static ApplicationContext ctx;
    private SalesPersonRepository repo;
    private Long id;
    
    public SalesPersonRepositoryTest() {
    }
    
    @Test
    public void createSalesPerson() {
        repo = ctx.getBean(SalesPersonRepository.class);
        Commission commission = new Commission.Builder(250000).SaleCommission(5000).build();
        SalesGrade grade = new SalesGrade.Builder("B").rate(12).build();
        SalesPerson salesPerson = new SalesPerson.Builder("Lima").lastname("Monali").hireDate("12-feb-2014")
                .age(30).basicSalary(5000).comm(null).salary(15000).comm(commission).grade(grade).build();
        SalesGradeRepository repS = ctx.getBean(SalesGradeRepository.class);
        repS.save(grade);
        repo.save(salesPerson);
        id = salesPerson.getId();
        Assert.assertNotNull(salesPerson);
    }
    
    @Test(dependsOnMethods = "createSalesPerson")
    public void readSalesPerson() {
        repo = ctx.getBean(SalesPersonRepository.class);
        SalesPerson person = repo.findOne(id);
        Assert.assertEquals(person.getBasicSalary(), 5000.0, 0000.1);
    }
    
    @Test(dependsOnMethods = "readSalesPerson")
    public void updateSalesPerson() {
        repo = ctx.getBean(SalesPersonRepository.class);
        SalesPerson person = repo.findOne(id);
        
        SalesPerson updatePerson = new SalesPerson.Builder("Lima").salesPerson(person)
                .age(32).build();
        repo.save(updatePerson);
        Assert.assertEquals(updatePerson.getAge(), 32);
    }
    
    @Test(dependsOnMethods = "updateSalesPerson")
    public void deleteSalesPerson() {
        repo = ctx.getBean(SalesPersonRepository.class);
        SalesPerson person = repo.findOne(id);
        repo.delete(person);
        
        SalesPerson deletePerson = repo.findOne(id);
        Assert.assertNull(deletePerson);
    }
    
    @BeforeClass
    public static void setUpClass() throws Exception {
        ctx = new AnnotationConfigApplicationContext(AppConfig.class);
    }
    
    @AfterClass
    public void tearDownClass() throws Exception {
        repo = ctx.getBean(SalesPersonRepository.class);
        repo.deleteAll();
    }
    
    @BeforeMethod
    public void setUpMethod() throws Exception {
    }
    
    @AfterMethod
    public void tearDownMethod() throws Exception {
    }
}
