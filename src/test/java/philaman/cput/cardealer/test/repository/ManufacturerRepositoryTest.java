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
import philaman.cput.cardealer.domain.Address;
import philaman.cput.cardealer.domain.Manufacturer;
import philaman.cput.cardealer.repository.ManufacturerRepository;

/**
 *
 * @author phila
 */
public class ManufacturerRepositoryTest {

    private static ApplicationContext ctx;
    private ManufacturerRepository repo;

    private Long id;

    public ManufacturerRepositoryTest() {
    }

    @Test
    public void createManufacturer() {
        repo = ctx.getBean(ManufacturerRepository.class);

        Address address = new Address.Builder("452").street("Evenue Road").city("PE").province("EC")
                .postalCode("7525").build();

        Manufacturer manufacturer = new Manufacturer.Builder(address).name("VW Manufacturers")
                .build();

        repo.save(manufacturer);
        id = manufacturer.getId();
        Assert.assertNotNull(manufacturer);
    }

    @Test(dependsOnMethods = "createManufacturer")
    public void readManufacturer() {
        repo = ctx.getBean(ManufacturerRepository.class);
        Manufacturer manufacturer = repo.findOne(id);
        Assert.assertEquals("VW Manufacturers", manufacturer.getName());
    }

    @Test(dependsOnMethods = "readManufacturer")
    public void updateManufacturer() {
        repo = ctx.getBean(ManufacturerRepository.class);
        Manufacturer manufacturer = repo.findOne(id);

        Manufacturer updateManufacturer = new Manufacturer.Builder(null).manufacture(manufacturer)
                .name("VW SA Brothers").build();
        repo.save(updateManufacturer);
        Assert.assertEquals("VW SA Brothers", updateManufacturer.getName());
    }

    //@Test(dependsOnMethods = "updateManufacturer")
    public void deleteManufacturer() {
        repo = ctx.getBean(ManufacturerRepository.class);
        Manufacturer manufacturer = repo.findOne(id);
        repo.delete(manufacturer);

        Manufacturer updateManufacturer = repo.findOne(id);
        Assert.assertNull(updateManufacturer);
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        ctx = new AnnotationConfigApplicationContext(AppConfig.class);
    }

    @AfterClass
    public void tearDownClass() throws Exception {
        /*repo = ctx.getBean(ManufacturerRepository.class);
        repo.deleteAll();*/
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }
}
