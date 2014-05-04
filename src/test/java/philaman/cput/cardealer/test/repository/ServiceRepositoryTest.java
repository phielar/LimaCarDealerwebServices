/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package philaman.cput.cardealer.test.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import philaman.cput.cardealer.app.config.AppConfig;
import philaman.cput.cardealer.domain.Mechanic;
import philaman.cput.cardealer.domain.Service;
import philaman.cput.cardealer.domain.ServiceBooking;
import philaman.cput.cardealer.repository.ServiceRepository;

/**
 *
 * @author phila
 */
public class ServiceRepositoryTest {

    private static ApplicationContext ctx;
    private ServiceRepository repo;
    private Long id;

    public ServiceRepositoryTest() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void createService() {
        repo = ctx.getBean(ServiceRepository.class);

        List<Mechanic> mechanics = new ArrayList<>();
        mechanics.add(new Mechanic.Builder("Electricity Installation").firstname("Phila").lastname("Manyika")
                .ratings("Professional").build());
        
        List<ServiceBooking> booking = new ArrayList<>();
        booking.add(new ServiceBooking.Builder("13-Mar-2014").serviceType("Normal Service")
                .description("Term Service, checking of the care to see if its still in a good condition for long distances")
                .build());
        booking.add(new ServiceBooking.Builder("14-Mar-2014").serviceType("Repairs Service")
                .description("Engine Stopped").build());
        Service service = new Service.Builder("15-Mar-2014").name("Repairs").serviceBooking(booking)
                .report("The to was leaking and has been fixed.").mechanics(mechanics).hourRate(250).build();
        
        repo.save(service);
        id = service.getId();
        Assert.assertNotNull(service);
    }

    @Test(dependsOnMethods = "createService")
    public void readService() {
        repo = ctx.getBean(ServiceRepository.class);
        Service service = repo.findOne(id);
        Assert.assertEquals(service.getName(), "Repairs");
    }

    @Test(dependsOnMethods = "readService")
    public void updateService() {
        repo = ctx.getBean(ServiceRepository.class);
        Service service = repo.findOne(id);

        Service updateService = new Service.Builder("16-Apr-2014").service(service).name("Engine top, JV-Joints Repair")
                .build();
        repo.save(updateService);
        Assert.assertEquals(updateService.getName(), "Engine top, JV-Joints Repair");
    }

    @Test(dependsOnMethods = "updateService")
    public void deleteService() {
        repo = ctx.getBean(ServiceRepository.class);
        Service service = repo.findOne(id);
        repo.delete(service);

        Service deleteService = repo.findOne(id);
        Assert.assertNull(deleteService);
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        ctx = new AnnotationConfigApplicationContext(AppConfig.class);
    }

    @AfterClass
    public void tearDownClass() throws Exception {
        repo = ctx.getBean(ServiceRepository.class);
        repo.deleteAll();
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }
}
