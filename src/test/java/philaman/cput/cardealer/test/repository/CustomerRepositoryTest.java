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
import org.testng.annotations.Test;
import philaman.cput.cardealer.app.config.AppConfig;
import philaman.cput.cardealer.domain.Address;
import philaman.cput.cardealer.domain.Contact;
import philaman.cput.cardealer.domain.Customer;
import philaman.cput.cardealer.domain.Dependent;
import philaman.cput.cardealer.domain.Employment;
import philaman.cput.cardealer.repository.CustomerRepository;
import philaman.cput.cardealer.repository.DependentRepository;

/**
 *
 * @author phila
 */
public class CustomerRepositoryTest {

    private static ApplicationContext ctx;
    private Long id;
    private static CustomerRepository repo;

    public CustomerRepositoryTest() {
    }

    @Test
    public void createCustomer() {
        repo = ctx.getBean(CustomerRepository.class);
        Address address = new Address.Builder("19").street("Zone 2").suburb("Langa")
                .city("Cape Town").province("WP").postalCode("7455").build();
        Contact contact = new Contact.Builder("0215465").cell("0823546854").phone("0215464587")
                .email("nako@zmail.com").build();
        //creating a dependent 
        DependentRepository dp = ctx.getBean(DependentRepository.class);
        List<Dependent> dependent = new ArrayList<>();
        dependent.add(new Dependent.Builder(200).age(14).name("Luphiwo Mane").build());
        dependent.add(new Dependent.Builder(250).age(16).name("Simie Mane").build());
        dp.save(dependent);
        
        Employment employment = new Employment.Builder(38000).employeeNumber("2156210168")
                .employer("Engene").employmentPeriod("3 years").build();

        Customer customer = new Customer.Builder("Manyika").firstname("philasande").balance(00.00)
                .contact(contact).address(address).dependent(dependent).employment(employment).build();

        repo.save(customer);
        id = customer.getId();
        Assert.assertNotNull(customer);
    }

    @Test(dependsOnMethods = "createCustomer")
    public void readCustomer() {
        repo = ctx.getBean(CustomerRepository.class);
        Customer customer = repo.findOne(id);
        Assert.assertEquals("nako@zmail.com", customer.getContact().getEmail());
    }

    @Test(dependsOnMethods = "readCustomer")
    public void updateCustomer() {
        repo = ctx.getBean(CustomerRepository.class);
        Customer customer = repo.findOne(id);

        Customer updatecustomer = new Customer.Builder("Manyika").customer(customer).balance(50000)
                .build();

        repo.save(updatecustomer);
        Assert.assertEquals(updatecustomer.getBalance(), 50000.0, 0000.1);
    }

    @Test(dependsOnMethods = "updateCustomer")
    public void deleteCustomer() {
        repo = ctx.getBean(CustomerRepository.class);
        Customer customer = repo.findOne(id);
        repo.delete(customer);

        Customer deleteCustomer = repo.findOne(id);
        Assert.assertNull(deleteCustomer);
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
        repo=ctx.getBean(CustomerRepository.class);
        repo.deleteAll();
    }
}
