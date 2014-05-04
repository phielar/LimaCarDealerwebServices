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
import philaman.cput.cardealer.domain.Colour;
import philaman.cput.cardealer.domain.Model;
import philaman.cput.cardealer.repository.ModelRepository;

/**
 *
 * @author phila
 */
public class ModelRepositoryTest {

    private static ApplicationContext ctx;
    private ModelRepository repo;
    private Long id;

    public ModelRepositoryTest() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void createModel() {
        repo = ctx.getBean(ModelRepository.class);
        String features = "GTI Sports suspension, Anti-lock Braking System (ABS),Space saver spare wheel for temporary use, radial tire (5-hole)";
        Colour color = new Colour.Builder("body").color("white").build();
        Model model = new Model.Builder("GTI 7").modelDescr("Multicollision brake system fastest GTI")
                .newFeatures(features).topSpeeed("300").colour(color).releaseYear("2013")
                .make("VW").bodyType("Sedan").build();
        repo.save(model);
        id = model.getId();
        Assert.assertNotNull(model);
    }

    @Test(dependsOnMethods = "createModel")
    public void readModel() {
        repo = ctx.getBean(ModelRepository.class);
        Model model = repo.findOne(id);
        Assert.assertEquals("300", model.getTopSpeeed());;
    }

    @Test(dependsOnMethods = "readModel")
    public void updateModel() {
        repo = ctx.getBean(ModelRepository.class);
        Model model = repo.findOne(id);

        Model updatModel = new Model.Builder("GTI 7").model(model).topSpeeed("320").build();
        repo.save(updatModel);
        Assert.assertEquals("320", updatModel.getTopSpeeed());
    }

    //@Test(dependsOnMethods = "updateModel")
    public void deleteModel() {
        repo = ctx.getBean(ModelRepository.class);
        Model model = repo.findOne(id);
        repo.delete(model);

        Model deleteModel = repo.findOne(id);
        Assert.assertNull(deleteModel);
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        ctx = new AnnotationConfigApplicationContext(AppConfig.class);
    }

    @AfterClass
    public void tearDownClass() throws Exception {
         repo = ctx.getBean(ModelRepository.class);
         repo.deleteAll();
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }
}
