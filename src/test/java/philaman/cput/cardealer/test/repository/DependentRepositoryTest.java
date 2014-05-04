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
import philaman.cput.cardealer.domain.Dependent;
import philaman.cput.cardealer.repository.DependentRepository;

/**
 *
 * @author phila
 */
public class DependentRepositoryTest {

    private static ApplicationContext ctx;
    private Long id;
    private DependentRepository repo;

    public DependentRepositoryTest() {
    }

    @Test
    public void createDependent() {
        repo = ctx.getBean(DependentRepository.class);
        Dependent dependent = new Dependent.Builder(500).name("Sime Monali").age(15).build();
        repo.save(dependent);
        id = dependent.getId();
        Assert.assertNotNull(dependent);
    }

    @Test(dependsOnMethods = "createDependent")
    public void readDependent() {
        repo = ctx.getBean(DependentRepository.class);
        Dependent dependent = repo.findOne(id);
        Assert.assertEquals(dependent.getAllowanceAmount(), 500.0, 0000.1);
    }

    @Test(dependsOnMethods = "readDependent")
    public void updateDependent() {
        repo = ctx.getBean(DependentRepository.class);
        Dependent dependent = repo.findOne(id);
        Dependent updateDependent = new Dependent.Builder(500).dependent(dependent)
                .name("Sine Monile").build();
        repo.save(updateDependent);
        Assert.assertEquals(updateDependent.getName(), "Sine Monile");
    }

    @Test(dependsOnMethods = "updateDependent")
    public void deleteDependent() {
        repo = ctx.getBean(DependentRepository.class);
        Dependent dependent = repo.findOne(id);
        repo.delete(dependent);

        Dependent deleteDependent = repo.findOne(id);
        Assert.assertNull(deleteDependent);
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        ctx = new AnnotationConfigApplicationContext(AppConfig.class);
    }

    @AfterClass
    public void tearDownClass() throws Exception {
        repo=ctx.getBean(DependentRepository.class);
        repo.deleteAll();
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }
}
