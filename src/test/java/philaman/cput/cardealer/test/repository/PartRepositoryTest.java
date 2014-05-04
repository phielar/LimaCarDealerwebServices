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
import philaman.cput.cardealer.domain.Part;
import philaman.cput.cardealer.repository.PartRepository;

/**
 *
 * @author phila
 */
public class PartRepositoryTest {

    private static ApplicationContext ctx;
    private Long id;
    private PartRepository repo;

    public PartRepositoryTest() {
    }

    @Test
    public void createPart() {
        repo = ctx.getBean(PartRepository.class);
        Part part = new Part.Builder(450).name("Clutch-Plate").description("inside a gearbox main power source of a vehicle")
                .retailPrice(460).build();
        repo.save(part);
        id = part.getId();
        Assert.assertNotNull(part);
    }

    @Test(dependsOnMethods = "createPart")
    public void readPart() {
        repo = ctx.getBean(PartRepository.class);
        Part part = repo.findOne(id);
        Assert.assertEquals("Clutch-Plate", part.getName());
    }

    @Test(dependsOnMethods = "readPart")
    public void updatePart() {
        repo = ctx.getBean(PartRepository.class);
        Part part = repo.findOne(id);

        Part updatePart = new Part.Builder(450).part(part).retailPrice(480).build();
        repo.save(updatePart);
        Assert.assertEquals( updatePart.getRetailPrice(), 480.0);
    }

    @Test(dependsOnMethods = "updatePart")
    public void deletePart() {
        repo = ctx.getBean(PartRepository.class);
        Part part = repo.findOne(id);
        repo.delete(part);

        Part deletePart = repo.findOne(id);
        Assert.assertNull(deletePart);
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        ctx = new AnnotationConfigApplicationContext(AppConfig.class);
    }

    @AfterClass
    public void tearDownClass() throws Exception {
        repo=ctx.getBean(PartRepository.class);
        repo.deleteAll();
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }
}
