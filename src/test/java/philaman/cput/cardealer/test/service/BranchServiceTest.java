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
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import philaman.cput.cardealer.domain.Branch;
import philaman.cput.cardealer.domain.Contact;
import philaman.cput.cardealer.domain.Model;
import philaman.cput.cardealer.repository.BranchRepository;
import philaman.cput.cardealer.repository.ModelRepository;
import philaman.cput.cardealer.service.BranchService;
import philaman.cput.cardealer.test.AppConfigTest;

/**
 *
 * @author phila
 */
public class BranchServiceTest {

    private static ApplicationContext ctx;
    private ModelRepository modelRepository;
    private BranchRepository repo;
    private BranchService service;

    public BranchServiceTest() {
    }

    @Test
    public void testBranch() {
        modelRepository = ctx.getBean(ModelRepository.class);
        repo = ctx.getBean(BranchRepository.class);

        List<Model> models = new ArrayList<>();
        List<Model> models1 = new ArrayList<>();
        List<Model> models2 = new ArrayList<>();
        models.add(new Model.Builder("3-series").make("BMW").bodyType("Sedan")
                .modelDescr("open sunroof").price(250000).build());
        models.add(new Model.Builder("Mazda 5").make("Mazda").bodyType("hatch").price(180000)
                .topSpeeed(280).build());
        models.add(new Model.Builder("Tazz").make("Toyota").bodyType("Sedan").price(120000)
                .topSpeeed(180).build());
        models1.add(new Model.Builder("Golf 3").make("VW").bodyType("hatch").price(190000)
                .topSpeeed(240).build());
        models2.add(new Model.Builder("Mazda 5").make("Mazda").bodyType("hatch").price(180000)
                .topSpeeed(160).build());

        Contact c = new Contact.Builder("215487").email("pineInfo@zmotos.com").build();
        Contact c1 = new Contact.Builder("0215487").email("capeInfo@zmotos.com").build();
        Contact c2 = new Contact.Builder("4215487").email("GateInfo@zmotos.com").build();
        Contact c3 = new Contact.Builder("2015487").email("BelvilleInfo@zmotos.com").build();

        Branch branch = new Branch.Builder("Pinelands").contact(c).branchYearToDateSales(52)
                .models(models).build();
        Branch branch1 = new Branch.Builder("Cape town").contact(c1).branchYearToDateSales(42)
                .models(models1).build();
        Branch branch2 = new Branch.Builder("Gatesville").contact(c2).branchYearToDateSales(50)
                .models(models2).build();
        Branch branch3 = new Branch.Builder("Belville").contact(c3).branchYearToDateSales(32)
                .models(models2).build();

        modelRepository.save(models);
        modelRepository.save(models1);
        modelRepository.save(models2);

        repo.save(branch);
        repo.save(branch1);
        repo.save(branch2);
        repo.save(branch3);

        Assert.assertNotNull(branch);
        Assert.assertNotNull(branch3);

    }

    @Test(dependsOnMethods = "testBranch")
    public void topSalesTest() {
        service = ctx.getBean(BranchService.class);
        Branch ms = service.getTopBranchSales();
        Assert.assertEquals(ms.getBranchname(), "Pinelands");
    }

    @Test(dependsOnMethods = "testBranch")
    public void modelsInbranchSearch() {
        service = ctx.getBean(BranchService.class);
        List<Branch> branchs = service.getTotalBranches("Mazda 5");
        Assert.assertEquals(branchs.size(), 2);
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        ctx = new AnnotationConfigApplicationContext(AppConfigTest.class);
    }

    @AfterClass
    public void tearDownClass() throws Exception {
        modelRepository = ctx.getBean(ModelRepository.class);
        repo = ctx.getBean(BranchRepository.class);

        modelRepository.deleteAll();
        repo.deleteAll();
    }

}
