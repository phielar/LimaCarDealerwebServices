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
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import philaman.cput.cardealer.domain.Brand;
import philaman.cput.cardealer.domain.Model;
import philaman.cput.cardealer.repository.BrandRepository;
import philaman.cput.cardealer.repository.ModelRepository;
import philaman.cput.cardealer.service.BrandService;
import philaman.cput.cardealer.test.AppConfigTest;

/**
 *
 * @author phila
 */
public class BrandServiceTest {

    private BrandService service;
    private BrandRepository brandRepository;
    private static ApplicationContext ctx;

    public BrandServiceTest() {
    }

    @Test
    public void testBrand() {
        brandRepository = ctx.getBean(BrandRepository.class);
        Brand brand = new Brand.Builder("BMW").country("Germamy").build();
        Brand brand1 = new Brand.Builder("CHEVROLET").country("America").build();
        Brand brand2 = new Brand.Builder("VOLKWAGEN").country("Germany").build();
        Brand brand3 = new Brand.Builder("Mazda").country("South Africa").build();
        Brand brand4 = new Brand.Builder("Toyota").country("Japan").build();

        brandRepository.save(brand);
        brandRepository.save(brand1);
        brandRepository.save(brand2);
        brandRepository.save(brand3);
        brandRepository.save(brand4);

    }

    @Test(dependsOnMethods = "testBrand")
    public void getBrandTest() {
        service = ctx.getBean(BrandService.class);
        Brand brand = service.getBrandByName("CHEVROLET");
        Assert.assertEquals("America", brand.getCountry());

    }

    @Test(dependsOnMethods = "testBrand")
    public void getAllBrandsTest() {
        service = ctx.getBean(BrandService.class);
        List<Brand> brand = service.getBrand();
        Assert.assertEquals(6, brand.size());

    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        ctx = new AnnotationConfigApplicationContext(AppConfigTest.class);
    }

    @AfterClass
    public void tearDownClass() throws Exception {
        brandRepository = ctx.getBean(BrandRepository.class);

        brandRepository.deleteAll();

    }

}
