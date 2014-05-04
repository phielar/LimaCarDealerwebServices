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
import philaman.cput.cardealer.app.config.AppConfig;
import philaman.cput.cardealer.domain.Model;
import philaman.cput.cardealer.repository.ModelRepository;
import philaman.cput.cardealer.service.TotalOnSaleVehicleService;

/**
 *
 * @author phila
 */
public class TotalOnSaleVehicleServiceTest {

    private static ApplicationContext ctx;
    private ModelRepository repo;
    private TotalOnSaleVehicleService service;

    public TotalOnSaleVehicleServiceTest() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    @Test
    public void onSaleVehiclesTest() {
        repo = ctx.getBean(ModelRepository.class);
        service = ctx.getBean(TotalOnSaleVehicleService.class);

        Model model = new Model.Builder("GTI 7").releaseYear(2013).make("VW").onSale(false).build();
        Model model1 = new Model.Builder("Mazda 4").releaseYear(2011).make("Mazda").onSale(true).build();
        Model model2 = new Model.Builder("GTI 5").releaseYear(2010).make("VW").onSale(false).build();
        Model model3 = new Model.Builder("Golf 3").releaseYear(2005).make("VW").onSale(true).build();
        Model model4 = new Model.Builder("Tida").releaseYear(2013).make("Nissan").onSale(false).build();

        repo.save(model);
        repo.save(model1);
        repo.save(model2);
        repo.save(model3);
        repo.save(model4);

        List<Model> onSaleModel = service.isSaleVehicles(true);
        Assert.assertEquals(onSaleModel.size(), 2);
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        ctx = new AnnotationConfigApplicationContext(AppConfig.class);
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
        repo = ctx.getBean(ModelRepository.class);
        repo.deleteAll();
    }
}
