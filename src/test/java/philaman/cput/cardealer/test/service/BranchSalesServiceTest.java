/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package philaman.cput.cardealer.test.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import philaman.cput.cardealer.app.config.AppConfig;
import philaman.cput.cardealer.domain.Branch;
import philaman.cput.cardealer.domain.Contact;
import philaman.cput.cardealer.repository.BranchRepository;
import philaman.cput.cardealer.service.BranchSalesService;

/**
 *
 * @author phila
 */
public class BranchSalesServiceTest {

    private static ApplicationContext ctx;
    private BranchRepository repo;
    private BranchSalesService service;

    public BranchSalesServiceTest() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    @Test
    public void getCarsInBranch() {
        repo = ctx.getBean(BranchRepository.class);
        service = ctx.getBean(BranchSalesService.class);

        Contact c = new Contact.Builder("215487").email("pineInfo@zmotos.com").build();
        Contact c1 = new Contact.Builder("0215487").email("capeInfo@zmotos.com").build();
        Contact c2 = new Contact.Builder("4215487").email("GateInfo@zmotos.com").build();

        Branch branch = new Branch.Builder("Pinelangs").contact(c).branchYearToDateSales(52).build();
        Branch branch1 = new Branch.Builder("Cape town").contact(c1).branchYearToDateSales(42).build();
        Branch branch2 = new Branch.Builder("Gatesville").contact(c2).branchYearToDateSales(50).build();

        repo.save(branch);
        repo.save(branch1);
        repo.save(branch2);

        Branch ms = service.getTopBranchSales();
        Assert.assertEquals(ms.getBranchname(), "Pinelangs");

    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        ctx = new AnnotationConfigApplicationContext(AppConfig.class);
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
        repo = ctx.getBean(BranchRepository.class);
        repo.deleteAll();
    }
}
