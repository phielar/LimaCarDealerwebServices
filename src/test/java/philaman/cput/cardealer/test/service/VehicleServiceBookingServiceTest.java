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
import philaman.cput.cardealer.domain.ServiceBooking;
import philaman.cput.cardealer.repository.ServiceBookingRepository;
import philaman.cput.cardealer.service.VehicleServiceBookingService;
import philaman.cput.cardealer.test.AppConfigTest;

/**
 *
 * @author phila
 */
public class VehicleServiceBookingServiceTest {

    private static ApplicationContext ctx;
    private ServiceBookingRepository repo;
    private VehicleServiceBookingService service;

    public VehicleServiceBookingServiceTest() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void totalBookings() {
        repo = ctx.getBean(ServiceBookingRepository.class);
        service = ctx.getBean(VehicleServiceBookingService.class);
        ServiceBooking booking = new ServiceBooking.Builder("10-May-2014").serviceType("Repair & Accident")
                .description("bent bumper").build();
        ServiceBooking booking1 = new ServiceBooking.Builder("12-May-2014").serviceType("Repair & Accident")
                .description("Engine failer").build();
        ServiceBooking booking2 = new ServiceBooking.Builder("18-May-2014").serviceType("Tyres & Wheels")
                .description("flat, air coming out").build();
        ServiceBooking booking3 = new ServiceBooking.Builder("13-May-2014").serviceType("Windscreens & Glass")
                .description("cracked rear window").build();
        ServiceBooking booking4 = new ServiceBooking.Builder("10-May-2014").serviceType("Servicing")
                .description("Car check-up").build();
        ServiceBooking booking5 = new ServiceBooking.Builder("12-May-2014").serviceType("Repair & Accident")
                .description("Exost pipe noisy").build();

        repo.save(booking);
        repo.save(booking1);
        repo.save(booking2);
        repo.save(booking3);
        repo.save(booking4);
        repo.save(booking5);

        List<ServiceBooking> Allbookings = service.getBooking();
        Assert.assertEquals(Allbookings.size(), 6);
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        ctx = new AnnotationConfigApplicationContext(AppConfigTest.class);
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
        repo = ctx.getBean(ServiceBookingRepository.class);
        repo.deleteAll();
    }
}
