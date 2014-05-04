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
import philaman.cput.cardealer.domain.Address;
import philaman.cput.cardealer.domain.Branch;
import philaman.cput.cardealer.domain.Contact;
import philaman.cput.cardealer.repository.BranchRepository;

/**
 *
 * @author phila
 */
public class BranchRepositoryTest {

    private static ApplicationContext ctx;
    private BranchRepository repo;
    private Long id;

    public BranchRepositoryTest() {
    }

    @Test
    public void createBranch() {
        repo = ctx.getBean(BranchRepository.class);

        Contact cs = new Contact.Builder("0215646").phone("021546865").email("info@LimaDealer.com").build();

        Address address = new Address.Builder("l68 n3").street("septa").suburb("epping industry1")
                .city("Cape Town").province("Western Cape").postalCode("5465").build();

        Branch branch = new Branch.Builder("Cape Town").contact(cs).address(address).branchYearToDateSales(20).build();
        repo.save(branch);
        id = branch.getId();
        Assert.assertNotNull(branch);
    }

    @Test(dependsOnMethods = "createBranch")
    public void readBranch() {
        repo = ctx.getBean(BranchRepository.class);
        Branch branch = repo.findOne(id);
        Assert.assertEquals("Cape Town", branch.getBranchname());
    }

    @Test(dependsOnMethods = "readBranch")
    public void updateBranch() {
        repo = ctx.getBean(BranchRepository.class);
        Branch branch = repo.findOne(id);

        Branch updateBranch = new Branch.Builder("Cape Flats").branch(branch).branchYearToDateSales(25)
                .build();
        repo.save(updateBranch);
        Assert.assertEquals(25, updateBranch.getBranchYearToDateSales());
    }

    @Test(dependsOnMethods = "updateBranch")
    public void deleteBranch() {
        repo = ctx.getBean(BranchRepository.class);
        Branch branch = repo.findOne(id);
        repo.delete(branch);

        Branch deletedBranch = repo.findOne(id);
        Assert.assertNull(deletedBranch);
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        ctx = new AnnotationConfigApplicationContext(AppConfig.class);
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }
    @AfterClass
    public void tearDownClass() throws Exception{
        repo=ctx.getBean(BranchRepository.class);
        repo.deleteAll();
    }
}
