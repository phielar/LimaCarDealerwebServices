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
import philaman.cput.cardealer.service.TotalVehicleService;

/**
 *
 * @author phila
 */
public class TotalVehicleServiceTest {

    private static ApplicationContext ctx;
    private ModelRepository repo;
    private TotalVehicleService service;

    public TotalVehicleServiceTest() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void totalVehiclesTest() {
        repo = ctx.getBean(ModelRepository.class);
        service = ctx.getBean(TotalVehicleService.class);

        Model model = new Model.Builder("3-series").make("BMW").bodyType("Sedan")
                .modelDescr("open sunroof").build();
        Model model1 = new Model.Builder("Mazda 5").make("Mazda").bodyType("hatch").build();
        Model model2 = new Model.Builder("Tazz").make("Toyota").bodyType("Sedan").build();
        Model model3 = new Model.Builder("Golf 3").make("VW").bodyType("hatch").build();

        repo.save(model);
        repo.save(model1);
        repo.save(model2);
        repo.save(model3);

        List<Model> models = service.getAllVehicle();
        Assert.assertEquals(4, models.size());
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
