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
import philaman.cput.cardealer.domain.Part;
import philaman.cput.cardealer.domain.VehicleService;
import philaman.cput.cardealer.repository.PartRepository;
import philaman.cput.cardealer.repository.ServiceRepository;
import philaman.cput.cardealer.service.ServiceTotalPriceService;
import philaman.cput.cardealer.test.AppConfigTest;

/**
 *
 * @author phila
 */
public class ServiceTotalPriceServiceTest {

    private static ApplicationContext ctx;
    private ServiceTotalPriceService priceService;
    private ServiceRepository repo;

    public ServiceTotalPriceServiceTest() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    @Test
    public void serviceTotalPriceTest() {
        repo = ctx.getBean(ServiceRepository.class);
        priceService = ctx.getBean(ServiceTotalPriceService.class);
        List<Part> parts = new ArrayList<>();
        parts.add(new Part.Builder(75.50).name("Clutch").build());
        parts.add(new Part.Builder(35.50).name("Rings").build());
        parts.add(new Part.Builder(20.50).name("Fender").build());
        parts.add(new Part.Builder(60.50).name("Clutch").build());
        PartRepository re = ctx.getBean(PartRepository.class);

        VehicleService service = new VehicleService.Builder("14-May-2014").duration(5).hourRate(150).parts(parts).build();
        repo.save(service);
        Long id = service.getId();
        re.save(parts);
        double servicePrice = priceService.getServicePrice(id);
        Assert.assertEquals(servicePrice, 942.0, 0000000.1);
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        ctx = new AnnotationConfigApplicationContext(AppConfigTest.class);
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
        repo = ctx.getBean(ServiceRepository.class);
        repo.deleteAll();
    }
}
