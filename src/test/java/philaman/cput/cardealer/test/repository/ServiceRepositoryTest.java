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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import philaman.cput.cardealer.app.config.AppConfig;
import philaman.cput.cardealer.domain.Mechanic;
import philaman.cput.cardealer.domain.VehicleService;
import philaman.cput.cardealer.domain.ServiceBooking;
import philaman.cput.cardealer.repository.MechanicRepository;
import philaman.cput.cardealer.repository.ServiceRepository;

/**
 *
 * @author phila
 */
public class ServiceRepositoryTest {

    private static ApplicationContext ctx;
    private ServiceRepository repo;
    private MechanicRepository repoM;
    private Long id;

    public ServiceRepositoryTest() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void createService() {
        repo = ctx.getBean(ServiceRepository.class);

        Mechanic mechanics = new Mechanic.Builder("Electricity Installation").firstname("Phila").lastname("Manyika")
                .ratings("Professional").build();

        repoM = ctx.getBean(MechanicRepository.class);
        repoM.save(mechanics);

        List<ServiceBooking> booking = new ArrayList<>();
        booking.add(new ServiceBooking.Builder("13-Mar-2014").serviceType("Normal Service")
                .description("Term Servicce, checking of the care to see if its still in a good condition for long distances")
                .build());
        booking.add(new ServiceBooking.Builder("14-Mar-2014").serviceType("Repairs Service")
                .description("Engine Stopped").build());
        VehicleService service = new VehicleService.Builder("15-Mar-2014").name("Repairs").serviceBooking(booking)
                .report("The to was leaking and has been fixed.").mechanics(mechanics).hourRate(250).build();

        repo.save(service);

        id = service.getId();
        Assert.assertNotNull(service);
    }

    @Test(dependsOnMethods = "createService")
    public void readService() {
        repo = ctx.getBean(ServiceRepository.class);
        VehicleService service = repo.findOne(id);
        Assert.assertEquals(service.getName(), "Repairs");
    }

    @Test(dependsOnMethods = "readService")
    public void updateService() {
        repo = ctx.getBean(ServiceRepository.class);
        VehicleService service = repo.findOne(id);

        VehicleService updateService = new VehicleService.Builder("16-Apr-2014").service(service).name("Engine top, JV-Joints Repair")
                .build();
        repo.save(updateService);
        Assert.assertEquals(updateService.getName(), "Engine top, JV-Joints Repair");
    }

    @Test(dependsOnMethods = "updateService")
    public void deleteService() {
        repo = ctx.getBean(ServiceRepository.class);
        VehicleService service = repo.findOne(id);
        repo.delete(service);

        VehicleService deleteService = repo.findOne(id);
        Assert.assertNull(deleteService);
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        ctx = new AnnotationConfigApplicationContext(AppConfig.class);
    }

}
