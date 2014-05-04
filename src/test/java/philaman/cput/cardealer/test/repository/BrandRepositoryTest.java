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
import org.testng.annotations.Test;
import philaman.cput.cardealer.app.config.AppConfig;
import philaman.cput.cardealer.domain.Brand;
import philaman.cput.cardealer.repository.BrandRepository;

/**
 *
 * @author phila
 */
public class BrandRepositoryTest {

    private static ApplicationContext ctx;
    private Long id;
    private BrandRepository repo;

    public BrandRepositoryTest() {
    }

    @Test
    public void createBrand() {
        repo = ctx.getBean(BrandRepository.class);
        Brand brand = new Brand.Builder("Nissan").country("Germany").dateEstablised("1945")
                .build();
        repo.save(brand);
        id = brand.getId();
        Assert.assertNotNull(brand);
    }

    @Test(dependsOnMethods = "createBrand")
    public void readBrand() {
        repo = ctx.getBean(BrandRepository.class);
        Brand brand = repo.findOne(id);
        Assert.assertEquals(brand.getBrandName(), "Nissan");
    }

    @Test(dependsOnMethods = "readBrand")
    public void updateBrand() {
        repo = ctx.getBean(BrandRepository.class);
        Brand brand = repo.findOne(id);
        Brand updatebrand = new Brand.Builder("Nisssan")
                .brand(brand).country("Japan").build();

        repo.save(updatebrand);
        Assert.assertEquals(updatebrand.getCountry(), "Japan");
    }

    @Test(dependsOnMethods = "updateBrand")
    public void deleteBrand() {
        //Search and delete
        repo = ctx.getBean(BrandRepository.class);
        Brand brand = repo.findOne(id);
        repo.delete(brand);

        //checks if brand has been deleted
        Brand deletedbrand = repo.findOne(id);
        Assert.assertNull(deletedbrand);
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        ctx = new AnnotationConfigApplicationContext(AppConfig.class);
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }

    @AfterClass
    public void tearDownClass() throws Exception {
        repo = ctx.getBean(BrandRepository.class);
        repo.deleteAll();
    }
}
