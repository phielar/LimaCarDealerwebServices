/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package philaman.cput.cardealer.test.repository;

import java.util.ArrayList;
import java.util.List;
import junit.framework.Assert;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import philaman.cput.cardealer.app.config.AppConfig;
import philaman.cput.cardealer.domain.OfferedService;
import philaman.cput.cardealer.domain.ServiceBooking;
import philaman.cput.cardealer.repository.OfferedServiceRepository;
import philaman.cput.cardealer.repository.ServiceBookingRepository;

/**
 *
 * @author phila
 */
public class OfferedServiceRepositoryTest {

    private static ApplicationContext ctx;
    private OfferedServiceRepository repo;
    private Long id;
    ServiceBookingRepository brepo;

    public OfferedServiceRepositoryTest() {
    }

    @Test
    public void createOfferedService() {
        repo = ctx.getBean(OfferedServiceRepository.class);
        OfferedService offeredService = new OfferedService.Builder("Clutch Change")
                .rate(230).durationhr(0.5).build();
        repo.save(offeredService);
        id = offeredService.getId();
        Assert.assertNotNull(offeredService);
    }

    @Test(dependsOnMethods = "createOfferedService")
    public void readOfferedService() {
        repo = ctx.getBean(OfferedServiceRepository.class);
        OfferedService offeredService = repo.findOne(id);
        Assert.assertEquals(0.5, offeredService.getDurationhr(), 0000.1);
    }

    @Test(dependsOnMethods = "readOfferedService")
    public void updateOfferedService() {
        repo = ctx.getBean(OfferedServiceRepository.class);
        OfferedService offeredService = repo.findOne(id);
        List<ServiceBooking> booking = new ArrayList<>();
        booking.add(new ServiceBooking.Builder("06-May-2014")
                .serviceType("Installation").description("Brake pads installation").build());
        booking.add(new ServiceBooking.Builder("06-May-2014")
                .serviceType("Installation").description("Side-view mirror installation").build());
        brepo = ctx.getBean(ServiceBookingRepository.class);
        brepo.save(booking);

        OfferedService updateOfferedService = new OfferedService.Builder("Clutch Change")
                .offeredServices(offeredService)
                .rate(350).serviceBooking(null).build();
        Assert.assertEquals(350, updateOfferedService.getRate(), 0000.1);
    }

    @Test(dependsOnMethods = "updateOfferedService")
    public void deleteOfferedService() {
        repo = ctx.getBean(OfferedServiceRepository.class);
        OfferedService offeredService = repo.findOne(id);
        repo.delete(offeredService);

        OfferedService deleteService = repo.findOne(id);
        Assert.assertNull(deleteService);
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        ctx = new AnnotationConfigApplicationContext(AppConfig.class);
    }

    @AfterClass
    public void tearDownClass() throws Exception {
        repo = ctx.getBean(OfferedServiceRepository.class);
        repo.deleteAll();
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }
}
