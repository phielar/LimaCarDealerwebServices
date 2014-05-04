/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package philaman.cput.cardealer.test.service;

import java.util.List;
import junit.framework.Assert;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import philaman.cput.cardealer.app.config.AppConfig;
import philaman.cput.cardealer.domain.Model;
import philaman.cput.cardealer.repository.ModelRepository;
import philaman.cput.cardealer.service.VehicleBeforeDateService;

/**
 *
 * @author phila
 */
public class VehicleBeforeDateServiceTest {

    private static ApplicationContext ctx;
    private ModelRepository repo;
    private VehicleBeforeDateService service;

    public VehicleBeforeDateServiceTest() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    @Test
    public void getVehicleReleaseDate() {
        repo = ctx.getBean(ModelRepository.class);
        service = ctx.getBean(VehicleBeforeDateService.class);

        Model model = new Model.Builder("3-series").make("BMW").bodyType("Sedan")
                .modelDescr("open sunroof").releaseYear(2012).build();
        Model model1 = new Model.Builder("Mazda 5").make("Mazda").releaseYear(2011).bodyType("hatch").build();
        Model model2 = new Model.Builder("Tazz").make("Toyota").bodyType("Sedan").releaseYear(2010).build();
        Model model3 = new Model.Builder("Golf 3").make("VW").bodyType("hatch").releaseYear(2008).build();

        repo.save(model);
        repo.save(model1);
        repo.save(model2);
        repo.save(model3);

        List<Model> models = service.getVehiclesbeforeDate(2011);

        Assert.assertEquals(2, models.size());
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
