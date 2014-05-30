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
import philaman.cput.cardealer.domain.Contact;
import philaman.cput.cardealer.domain.Mechanic;
import philaman.cput.cardealer.repository.MechanicRepository;
import philaman.cput.cardealer.service.SpecialistMechanicService;
import philaman.cput.cardealer.test.AppConfigTest;

/**
 *
 * @author phila
 */
public class SpecialistMechanicServiceTest {

    private static ApplicationContext ctx;
    private MechanicRepository repo;
    private SpecialistMechanicService service;

    public SpecialistMechanicServiceTest() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void mechanicTest() {
        repo = ctx.getBean(MechanicRepository.class);
        service = ctx.getBean(SpecialistMechanicService.class);
        Contact c = new Contact.Builder("02168658").email("enLima@mail.com").build();
        Contact c1 = new Contact.Builder("021686581").email("enLima1@mail.com").build();
        Contact c2 = new Contact.Builder("021686582").email("enLima2@mail.com").build();
        Contact c3 = new Contact.Builder("021686583").email("enLima3@mail.com").build();
        Contact c4 = new Contact.Builder("021686584").email("enLima4@mail.com").build();

        Mechanic m = new Mechanic.Builder("Engine").ratings("intermediate").contact(c).build();
        Mechanic m1 = new Mechanic.Builder("Tyres & Windscreen").contact(c1).ratings("intermediate").build();
        Mechanic m2 = new Mechanic.Builder("windscreen & Glass").contact(c2).ratings("intermediate").build();
        Mechanic m3 = new Mechanic.Builder("Engine").contact(c3).ratings("Professional").build();
        Mechanic m4 = new Mechanic.Builder("Engine").contact(c4).ratings("intermediate").build();

        repo.save(m);
        repo.save(m1);
        repo.save(m2);
        repo.save(m3);
        repo.save(m4);

        List<Mechanic> specialisMec = service.getSpecialityMechanic("Engine", "intermediate");
        Assert.assertEquals(2, specialisMec.size());

    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        ctx = new AnnotationConfigApplicationContext(AppConfigTest.class);
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
        repo = ctx.getBean(MechanicRepository.class);
        repo.deleteAll();
    }
}
