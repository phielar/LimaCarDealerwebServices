/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package philaman.cput.cardealer.test.service;

import junit.framework.Assert;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import philaman.cput.cardealer.app.config.AppConfig;
import philaman.cput.cardealer.domain.OfferedService;
import philaman.cput.cardealer.repository.OfferedServiceRepository;
import philaman.cput.cardealer.service.ServicePriceService;

/**
 *
 * @author phila
 */
public class ServicePriceServiceTest {

    private static ApplicationContext ctx;
    private OfferedServiceRepository repo;
    private ServicePriceService servicePriceService;

    public ServicePriceServiceTest() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    @Test
    public void servicePriceTest() {
        repo = ctx.getBean(OfferedServiceRepository.class);
        servicePriceService = ctx.getBean(ServicePriceService.class);

        OfferedService offeredService = new OfferedService.Builder("Accident & repairs").durationhr(5).rate(350).build();
        OfferedService offeredService1 = new OfferedService.Builder("Tyres & Wheels").durationhr(3).rate(150).build();
        OfferedService offeredService2 = new OfferedService.Builder("Windscreen & Glasses").durationhr(2).rate(100).build();
        OfferedService offeredService3 = new OfferedService.Builder("Servicing").durationhr(2).rate(400).build();

        repo.save(offeredService);
        repo.save(offeredService1);
        repo.save(offeredService2);
        repo.save(offeredService3);
        double servicePrice = servicePriceService.getServiceCost("Tyres & Wheels");
        Assert.assertEquals(servicePrice, 450.0, 0000.1);

    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        ctx = new AnnotationConfigApplicationContext(AppConfig.class);
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
        repo = ctx.getBean(OfferedServiceRepository.class);
        repo.deleteAll();
    }
}
