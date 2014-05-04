/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package philaman.cput.cardealer.test.service;

import java.util.List;
import junit.framework.Assert;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import philaman.cput.cardealer.app.config.AppConfig;
import philaman.cput.cardealer.domain.Mechanic;
import philaman.cput.cardealer.repository.MechanicRepository;
import philaman.cput.cardealer.service.SpecialistMechanicService;

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
        Mechanic m = new Mechanic.Builder("Engine").ratings("intermediate").build();
        Mechanic m1 = new Mechanic.Builder("Tyres & Windscreen").ratings("intermediate").build();
        Mechanic m2 = new Mechanic.Builder("windscreen & Glass").ratings("intermediate").build();
        Mechanic m3 = new Mechanic.Builder("Engine").ratings("Professional").build();
        Mechanic m4 = new Mechanic.Builder("Engine").ratings("intermediate").build();

        repo.save(m);
        /*repo.save(m1);
        repo.save(m2);
        repo.save(m3);
        repo.save(m4);*/

        List<Mechanic> specialisMec = service.getSpecialityMechanic("Engine", "intermediate");
        Assert.assertEquals(2, specialisMec.size());

    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        ctx = new AnnotationConfigApplicationContext(AppConfig.class);
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
        repo = ctx.getBean(MechanicRepository.class);
        repo.deleteAll();
    }
}
