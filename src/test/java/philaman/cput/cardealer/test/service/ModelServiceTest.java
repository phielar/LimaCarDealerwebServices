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
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import philaman.cput.cardealer.domain.Model;
import philaman.cput.cardealer.repository.ModelRepository;
import philaman.cput.cardealer.service.ModelService;
import philaman.cput.cardealer.test.AppConfigTest;

/**
 *
 * @author phila
 */
public class ModelServiceTest {
    private static ApplicationContext ctx;
    private ModelRepository repo;
    private ModelService service;
    public ModelServiceTest() {
    }

    @Test
    public void testModels(){
        repo=ctx.getBean(ModelRepository.class);
        repo.deleteAll();
        repo=ctx.getBean(ModelRepository.class);
        
        Model model = new Model.Builder("3-series").make("BMW").bodyType("Sedan")
                .modelDescr("open sunroof").price(250000).onSale(true).releaseYear(2012).build();
        Model model1 = new Model.Builder("Mazda 5").make("Mazda").bodyType("hatch").price(180000)
                .topSpeeed(280).onSale(true).releaseYear(2011).build();
        Model model2 = new Model.Builder("Tazz").make("Toyota").bodyType("Sedan").price(120000)
                .topSpeeed(180).onSale(true).releaseYear(2008).build();
        Model model3 = new Model.Builder("Golf 3").make("VW").bodyType("hatch").price(190000)
                .topSpeeed(240).onSale(false).releaseYear(2006).build();
        Model model4 = new Model.Builder("323").make("Mazda").bodyType("hatch").price(80000)
                .topSpeeed(160).onSale(true).releaseYear(2000).build();
 
        repo.save(model);
        repo.save(model1);
        repo.save(model2);
        repo.save(model3);
        repo.save(model4);
    }
    @Test(dependsOnMethods = "testModels", enabled = true)
    public void priceInRangeTest(){
        service=ctx.getBean(ModelService.class);
        List<Model> carsInRange = service.getVehicleInPriceRange(90000.0, 200000.0);
        Assert.assertEquals(3, carsInRange.size());
    }
        
    @Test (dependsOnMethods = "testModels", enabled = true)
    public void makeSearchTest(){
        service=ctx.getBean(ModelService.class);
        List<Model> mazdaCars = service.getMakeModels("Mazda");
        Assert.assertEquals(2, mazdaCars.size());
    }
    
    @Test(dependsOnMethods = "testModels", enabled = true)
    public void speedRangeTest(){
        service=ctx.getBean(ModelService.class);
        List<Model> models = service.getVehicleSpeedInRange(180, 300);
        Assert.assertEquals(3, models.size());
    }
    
    @Test(dependsOnMethods = "testModels", enabled = true)
    public void modelBodyTypeSearchTest(){
        service=ctx.getBean(ModelService.class);
        List<Model> models = service.getVehicleOfBodyType("Sedan");
        Assert.assertEquals(models.size(), 2);
    }
    @Test(dependsOnMethods = "testModels", enabled = true)
    public void totalVehicesOfBrandTest(){
        
        service=ctx.getBean(ModelService.class);
        List<Model> total=service.getNumberOfVehiclesInBrand("Mazda");
        Assert.assertEquals(2, total.size());
    }
    @Test(dependsOnMethods = "testModels", enabled = true)
    public void reducedPriceTest(){
        service=ctx.getBean(ModelService.class);
        List<Model> onSaleModel = service.isSaleVehicles(true);
        Assert.assertEquals(onSaleModel.size(), 3);
    }
    @Test(dependsOnMethods = "testModels")
    public void releaseYearTest(){
        service=ctx.getBean(ModelService.class);
        List<Model> models = service.getVehiclesManufacturedbeforeYear(2011);

        Assert.assertEquals(3, models.size());
    }
    
    @BeforeClass
    public static void setUpClass() throws Exception {
        ctx=new AnnotationConfigApplicationContext(AppConfigTest.class);
    }

    @AfterClass
    public  void tearDownClass() throws Exception {
        repo=ctx.getBean(ModelRepository.class);
        repo.deleteAll();
    }
    
    
}
