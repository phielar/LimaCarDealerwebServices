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
import philaman.cput.cardealer.domain.ServiceBooking;
import philaman.cput.cardealer.repository.ServiceBookingRepository;

/**
 *
 * @author phila
 */
public class ServiceBookingRepositoryTest {

    private static ApplicationContext ctx;
    private Long id;
    private ServiceBookingRepository repo;

    public ServiceBookingRepositoryTest() {
    }

    @Test
    public void createServiceBooking() {
        repo = ctx.getBean(ServiceBookingRepository.class);
        ServiceBooking booking = new ServiceBooking.Builder("13-Mar-2014").serviceType("Normal")
                .description("Term Service, checking of the care to see if its still in a good condition for long distances")
                .build();
        repo.save(booking);
        id = booking.getId();
        Assert.assertNotNull(booking);
    }

    @Test(dependsOnMethods = "createServiceBooking")
    public void readServiceBooking() {
        repo = ctx.getBean(ServiceBookingRepository.class);
        ServiceBooking booking = repo.findOne(id);
        Assert.assertEquals(booking.getServiceType(), "Normal");
    }

    @Test(dependsOnMethods = "readServiceBooking")
    public void updateServiceBooking() {
        //searching
        repo = ctx.getBean(ServiceBookingRepository.class);
        ServiceBooking booking = repo.findOne(id);
        //updating
        ServiceBooking updateBooking = new ServiceBooking.Builder("13-Mar-2014")
                .serviceBooking(booking).serviceType("Repair").build();
        repo.save(updateBooking);

        Assert.assertEquals(updateBooking.getServiceType(), "Repair");
    }

    @Test(dependsOnMethods = "updateServiceBooking")
    public void deleteServiceBooking() {
        repo = ctx.getBean(ServiceBookingRepository.class);
        ServiceBooking booking = repo.findOne(id);
        repo.delete(booking);

        ServiceBooking deleteBooking = repo.findOne(id);
        Assert.assertNull(deleteBooking);
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        ctx = new AnnotationConfigApplicationContext(AppConfig.class);
    }

    @AfterClass
    public void tearDownClass() throws Exception {
        repo=ctx.getBean(ServiceBookingRepository.class);
        repo.deleteAll();
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
       
    }
}
