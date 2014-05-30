/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package philaman.cput.cardealer.test.repository;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import philaman.cput.cardealer.app.config.AppConfig;
import philaman.cput.cardealer.domain.Address;
import philaman.cput.cardealer.domain.Contact;
import philaman.cput.cardealer.domain.Mechanic;
import philaman.cput.cardealer.repository.MechanicRepository;

/**
 *
 * @author phila
 */
public class MechanicRepositoryTest {

    private static ApplicationContext ctx;
    private Long id;
    private MechanicRepository repo;

    public MechanicRepositoryTest() {
    }

    @Test
    public void createMechanic() {
        repo = ctx.getBean(MechanicRepository.class);
        Address address = new Address.Builder("S74").street("Siyota Street").suburb("Khayelitsha")
                .city("Cape Town").province("Western Cape").postalCode("7088").build();
        Contact contact = new Contact.Builder("0215645").cell("0739452541").phone("0215462145")
                .email("ManPhi@Liam.com").build();

        Mechanic mechanic = new Mechanic.Builder("Electricity Installation")
                .firstname("Phila").lastname("Manyika")
                .ratings("Professional").address(address)
                .contact(contact).build();
        repo.save(mechanic);
        id = mechanic.getId();
        Assert.assertNotNull(mechanic);
    }

    @Test(dependsOnMethods = "createMechanic")
    public void readMechanic() {
        repo = ctx.getBean(MechanicRepository.class);
        Mechanic mechanic = repo.findOne(id);
        Assert.assertEquals("Electricity Installation", mechanic.getSpeciality());
    }

    @Test(dependsOnMethods = "readMechanic")
    public void updateMechanic() {
        repo = ctx.getBean(MechanicRepository.class);
        Mechanic mechanic = repo.findOne(id);

        Mechanic updateMechanic = new Mechanic.Builder("Electricity Installation")
                .mechanic(mechanic).ratings("Expect").build();
        repo.save(updateMechanic);
        Assert.assertEquals("Expect", updateMechanic.getRatings());
    }

    @Test(dependsOnMethods = "updateMechanic")
    public void deleteMechanic() {
        repo = ctx.getBean(MechanicRepository.class);
        Mechanic mechanic = repo.findOne(id);
        repo.delete(mechanic);

        Mechanic deleteMechanic = repo.findOne(id);
        Assert.assertNull(deleteMechanic);
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        ctx = new AnnotationConfigApplicationContext(AppConfig.class);
    }

}
