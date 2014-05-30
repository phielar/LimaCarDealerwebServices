/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package philaman.cput.cardealer.test.service;

import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import philaman.cput.cardealer.domain.Address;
import philaman.cput.cardealer.domain.Contact;
import philaman.cput.cardealer.domain.Manufacturer;
import philaman.cput.cardealer.repository.ManufacturerRepository;
import philaman.cput.cardealer.service.ManufacturerListService;
import philaman.cput.cardealer.test.AppConfigTest;

/**
 *
 * @author phila
 */
public class ManufacturerListServiceTest {

    private static ApplicationContext ctx;
    private ManufacturerRepository repo;
    private ManufacturerListService service;

    public ManufacturerListServiceTest() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    @Test
    public void ManufacturerListTest() {
        repo = ctx.getBean(ManufacturerRepository.class);
        repo.deleteAll();
        repo = ctx.getBean(ManufacturerRepository.class);
        service = ctx.getBean(ManufacturerListService.class);
        Address a = new Address.Builder("123 SteveHouse").build();
        Contact c = new Contact.Builder("215487").email("pineInfo@zmotos.com").build();
        Contact c1 = new Contact.Builder("0215487").email("capeInfo@zmotos.com").build();
        Contact c2 = new Contact.Builder("4215487").email("GateInfo@zmotos.com").build();

        Manufacturer m = new Manufacturer.Builder(a).contact(c).name("Sams VW Motor").build();
        Manufacturer m1 = new Manufacturer.Builder(a).contact(c1).name("Toyota Motors").build();
        Manufacturer m2 = new Manufacturer.Builder(a).contact(c2).name("Green BMW").build();

        repo.save(m);
        repo.save(m1);
        repo.save(m2);

        List<Manufacturer> allSuppliers = service.getManufacturers();
        Assert.assertEquals(allSuppliers.size(), 3);

    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        ctx = new AnnotationConfigApplicationContext(AppConfigTest.class);
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
        repo = ctx.getBean(ManufacturerRepository.class);
        repo.deleteAll();
    }
}
