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
import philaman.cput.cardealer.domain.Brand;
import philaman.cput.cardealer.repository.BrandRepository;
import philaman.cput.cardealer.service.BrandListService;

/**
 *
 * @author phila
 */
public class BrandListServiceTest {

    private static ApplicationContext ctx;
    private BrandRepository repo;
    private BrandListService service;

    public BrandListServiceTest() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    @Test
    public void totalBrands() {
        repo = ctx.getBean(BrandRepository.class);
        service = ctx.getBean(BrandListService.class);

        Brand brand = new Brand.Builder("BMW").country("Germamy").build();
        Brand brand1 = new Brand.Builder("CHEVROLET").country("Ameriac").build();
        Brand brand2 = new Brand.Builder("VOLKWAGEN").country("Germany").build();
        Brand brand3 = new Brand.Builder("Audi").country("Germamy").build();
        Brand brand4 = new Brand.Builder("Toyota").country("Japan").build();

        repo.save(brand);
        repo.save(brand1);
        repo.save(brand2);
        repo.save(brand3);
        repo.save(brand4);

        List<Brand> allBrands = service.getBrandList();
        Assert.assertEquals(5, allBrands.size());

    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        ctx = new AnnotationConfigApplicationContext(AppConfig.class);
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
        repo = ctx.getBean(BrandRepository.class);
        repo.deleteAll();
    }
}
