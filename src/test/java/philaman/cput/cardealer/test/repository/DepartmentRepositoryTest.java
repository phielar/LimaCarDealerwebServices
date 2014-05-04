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
import org.testng.annotations.Test;
import philaman.cput.cardealer.app.config.AppConfig;
import philaman.cput.cardealer.domain.Department;
import philaman.cput.cardealer.repository.DepartmentRepository;

/**
 *
 * @author phila
 */
public class DepartmentRepositoryTest {

    private static ApplicationContext ctx;
    private DepartmentRepository repo;
    private Long id;

    public DepartmentRepositoryTest() {
    }

    @Test
    public void createDepartment() {
        repo = ctx.getBean(DepartmentRepository.class);
        Department department = new Department.Builder("EC8085").name("ITDeet").build();
        repo.save(department);
        id = department.getId();
        Assert.assertNotNull(department);
    }

    @Test(dependsOnMethods = "createDepartment")
    public void readDepartment() {
        //Search
        repo = ctx.getBean(DepartmentRepository.class);
        Department department = repo.findOne(id);
        Assert.assertEquals(department.getName(), "ITDeet");
    }

    @Test(dependsOnMethods = "readDepartment")
    public void updateDepartment() {
        //Search
        repo = ctx.getBean(DepartmentRepository.class);
        Department department = repo.findOne(id);
        //update
        Department updateDepartment = new Department.Builder("EC8085").department(department).name("ITDeep").build();
        repo.save(updateDepartment);
        Assert.assertEquals(updateDepartment.getName(), "ITDeep");
    }

    @Test(dependsOnMethods = "updateDepartment")
    public void deleteDepartment() {
        repo = ctx.getBean(DepartmentRepository.class);
        Department department = repo.findOne(id);
        repo.delete(department);

        Department deleteDepartment = repo.findOne(id);
        Assert.assertNull(deleteDepartment);
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        ctx = new AnnotationConfigApplicationContext(AppConfig.class);
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }
    @AfterClass
    public void tearDownClass() throws Exception{
        repo=ctx.getBean(DepartmentRepository.class);
        repo.deleteAll();
        
    }
}
