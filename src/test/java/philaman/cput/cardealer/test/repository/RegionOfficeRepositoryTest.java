/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package philaman.cput.cardealer.test.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import philaman.cput.cardealer.app.config.AppConfig;
import philaman.cput.cardealer.domain.Address;
import philaman.cput.cardealer.domain.Branch;
import philaman.cput.cardealer.domain.Contact;
import philaman.cput.cardealer.domain.RegionOffice;
import philaman.cput.cardealer.repository.RegionOfficeRepository;

/**
 *
 * @author phila
 */
public class RegionOfficeRepositoryTest {

    private static ApplicationContext ctx;
    private RegionOfficeRepository repo;
    private Long id;

    public RegionOfficeRepositoryTest() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void createRegionOffice() {
        repo = ctx.getBean(RegionOfficeRepository.class);
        //region Branches Details
//        Address address = new Address.Builder("l68 n3").street("septa").suburb("epping industry1")
//                .city("Cape Town").province("Western Cape").postalCode("5465").build();
//
//        Contact cs = new Contact.Builder("0215646").phone("021546865").email("info@LimaDealer.com").build();
//
//        List<Branch> branch = new ArrayList<>();
//        branch.add(new Branch.Builder("Cape Town").address(address).contact(cs).branchYearToDateSales(20).build());
//        branch.add(new Branch.Builder("Port arlfred").address(address).contact(cs).branchYearToDateSales(20).build());
//        //region office info
//        Address regionAddress = new Address.Builder("452").street("Evenue Road").city("PE").province("EC")
//                .postalCode("7525").build();
//        Contact contact = new Contact.Builder("0215648").phone("021 564 6835").email("vwInfo@volkswagen.com").build();

        RegionOffice office = new RegionOffice.Builder("EC8085").officeName("VW_Southern Africa").build();
        repo.save(office);
        id = office.getId();
        Assert.assertNotNull(office);
    }

    @Test(dependsOnMethods = "createRegionOffice")
    public void readRegionOffice() {
        repo = ctx.getBean(RegionOfficeRepository.class);
        RegionOffice office = repo.findOne(id);
        Assert.assertEquals(office.getOfficeName(), "VW_Southern Africa");
    }

    @Test(dependsOnMethods = "readRegionOffice")
    public void updateRegionOffice() {
        repo = ctx.getBean(RegionOfficeRepository.class);
        RegionOffice office = repo.findOne(id);
        //updating 
        RegionOffice updateOffice = new RegionOffice.Builder("EC8085").regionOffice(office).officeName("Southern Afriva VW")
                .build();
        repo.save(updateOffice);
        Assert.assertEquals(updateOffice.getOfficeName(), "Southern Afriva VW");
    }

    @Test(dependsOnMethods = "updateRegionOffice")
    public void deleteRegionOffice() {
        repo = ctx.getBean(RegionOfficeRepository.class);
        RegionOffice office = repo.findOne(id);
        repo.delete(office);

        RegionOffice deleteOffice = repo.findOne(id);
        Assert.assertNull(deleteOffice);
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        ctx = new AnnotationConfigApplicationContext(AppConfig.class);
    }

    @AfterClass
    public void tearDownClass() throws Exception {
        repo=ctx.getBean(RegionOfficeRepository.class);
        repo.deleteAll();
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }
}
