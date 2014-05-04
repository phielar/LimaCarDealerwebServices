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
import philaman.cput.cardealer.app.config.AppConfig;
import philaman.cput.cardealer.domain.Address;
import philaman.cput.cardealer.domain.Contact;
import philaman.cput.cardealer.domain.Manufacturer;
import philaman.cput.cardealer.repository.ManufacturerRepository;
import philaman.cput.cardealer.service.ManufacturerSearchService;

/**
 *
 * @author phila
 */
public class ManufacturerSearchServiceTest {

    private static ApplicationContext ctx;
    private ManufacturerRepository repo;
    private ManufacturerSearchService service;

    public ManufacturerSearchServiceTest() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    @Test
    public void findManufacturer() {
        repo = ctx.getBean(ManufacturerRepository.class);
        service = ctx.getBean(ManufacturerSearchService.class);
        Address a = new Address.Builder("123 SteveHouse").city("Cape town").build();
        Address a1 = new Address.Builder("12 Epping").city("Cape town").build();
        Address a2 = new Address.Builder("684x werehouse").city("Midrand").build();

        Contact c = new Contact.Builder("215487").email("pineInfo@zmotos.com").build();
        Contact c1 = new Contact.Builder("0215487").email("capeInfo@zmotos.com").build();
        Contact c2 = new Contact.Builder("4215487").email("GateInfo@zmotos.com").build();

        Manufacturer m = new Manufacturer.Builder(a).contact(c).name("Sams VW Motor").build();
        Manufacturer m1 = new Manufacturer.Builder(a1).contact(c1).name("Toyota Motors").build();
        Manufacturer m2 = new Manufacturer.Builder(a2).contact(c2).name("Green BMW").build();

        repo.save(m);
        repo.save(m1);
        repo.save(m2);

        List<Manufacturer> closeManufacturer = service.getManufacturerInCity("Cape town");
        Assert.assertEquals(closeManufacturer.size(), 2);
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        ctx = new AnnotationConfigApplicationContext(AppConfig.class);
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
        repo = ctx.getBean(ManufacturerRepository.class);
        repo.deleteAll();

    }
}
