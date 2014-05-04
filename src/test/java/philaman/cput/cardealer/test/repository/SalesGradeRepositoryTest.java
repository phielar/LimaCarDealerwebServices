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
import philaman.cput.cardealer.domain.SalesGrade;
import philaman.cput.cardealer.repository.SalesGradeRepository;

/**
 *
 * @author phila
 */
public class SalesGradeRepositoryTest {

    private static ApplicationContext ctx;
    private Long id;
    private SalesGradeRepository repo;

    public SalesGradeRepositoryTest() {
    }

    @Test
    public void createSalesGrade() {
        repo = ctx.getBean(SalesGradeRepository.class);
        SalesGrade grade = new SalesGrade.Builder("A").rate(15).build();
        repo.save(grade);
        id = grade.getId();
        Assert.assertNotNull(grade);
    }

    @Test(dependsOnMethods = "createSalesGrade")
    public void readSalesGrade() {
        repo = ctx.getBean(SalesGradeRepository.class);
        SalesGrade grade = repo.findOne(id);
        Assert.assertEquals(grade.getGrade(), "A");
    }

    @Test(dependsOnMethods = "readSalesGrade")
    public void updateSalesGrade() {
        repo = ctx.getBean(SalesGradeRepository.class);
        SalesGrade grade = repo.findOne(id);
        //update
        SalesGrade updateGrade = new SalesGrade.Builder("A").salesPerson(grade)
                .rate(17).build();
        repo.save(updateGrade);
        Assert.assertEquals(updateGrade.getRate(), 17.0, 0000.1);
    }

    @Test(dependsOnMethods = "updateSalesGrade")
    public void deleteSalesGrade() {
        repo = ctx.getBean(SalesGradeRepository.class);
        SalesGrade grade = repo.findOne(id);
        repo.delete(grade);

        SalesGrade deleteGrade = repo.findOne(id);
        Assert.assertNull(deleteGrade);
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        ctx = new AnnotationConfigApplicationContext(AppConfig.class);
    }

    @AfterClass
    public void tearDownClass() throws Exception {
        repo=ctx.getBean(SalesGradeRepository.class);
        repo.deleteAll();
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }
}
