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
import philaman.cput.cardealer.service.MakeTypeService;

/**
 *
 * @author phila
 */
public class MakeTypeServiceTest {

    private static ApplicationContext ctx;
    private ModelRepository repo;
    private MakeTypeService service;

    public MakeTypeServiceTest() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void findMake() {
        repo = ctx.getBean(ModelRepository.class);
        service = ctx.getBean(MakeTypeService.class);

        Model model = new Model.Builder("3-series").make("BMW").bodyType("Sedan")
                .modelDescr("open sunroof").build();
        Model model1 = new Model.Builder("Mazda 5").make("Mazda").bodyType("hatch").build();
        Model model2 = new Model.Builder("Mazda 6").make("Mazda").bodyType("hatch").build();
        Model model3 = new Model.Builder("Tazz").make("Toyota").bodyType("Sedan").build();
        Model model4 = new Model.Builder("323").make("Mazda").bodyType("hatch").build();

        repo.save(model);
        repo.save(model1);
        repo.save(model2);
        repo.save(model3);
        repo.save(model4);

        List<Model> mazdaCars = service.getMakeModels("Mazda");
        Assert.assertEquals(3, mazdaCars.size());
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
