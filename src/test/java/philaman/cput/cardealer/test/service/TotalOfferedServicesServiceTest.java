/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package philaman.cput.cardealer.test.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import philaman.cput.cardealer.app.config.AppConfig;
import philaman.cput.cardealer.domain.OfferedService;
import philaman.cput.cardealer.domain.ServiceBooking;
import philaman.cput.cardealer.repository.OfferedServiceRepository;
import philaman.cput.cardealer.service.TotalOfferedServicesService;

/**
 *
 * @author phila
 */
public class TotalOfferedServicesServiceTest {

    private static ApplicationContext ctx;
    private OfferedServiceRepository repo;
    private TotalOfferedServicesService service;

    public TotalOfferedServicesServiceTest() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void getOfferedServicesTest() {
        repo = ctx.getBean(OfferedServiceRepository.class);
        service = ctx.getBean(TotalOfferedServicesService.class);

        OfferedService offeredService = new OfferedService.Builder("Accident & repairs").rate(350).build();
        OfferedService offeredService1 = new OfferedService.Builder("Tyres & Wheels").rate(150).build();
        OfferedService offeredService2 = new OfferedService.Builder("Windscreen & Glasses").rate(100).build();
        OfferedService offeredService3 = new OfferedService.Builder("Servicing").rate(400).build();

        repo.save(offeredService);
        repo.save(offeredService1);
        repo.save(offeredService2);
        repo.save(offeredService3);
        
        List<OfferedService> allService = service.getAllOfferedServices();
        Assert.assertEquals(allService.size(), 4);
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        ctx = new AnnotationConfigApplicationContext(AppConfig.class);
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
        /*repo = ctx.getBean(OfferedServiceRepository.class);
         repo.deleteAll();*/
    }
}
