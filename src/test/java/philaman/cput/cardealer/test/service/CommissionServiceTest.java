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
import philaman.cput.cardealer.domain.SalesGrade;
import philaman.cput.cardealer.repository.SalesGradeRepository;
import philaman.cput.cardealer.service.CommissionService;

/**
 *
 * @author phila
 */
public class CommissionServiceTest {

    private static ApplicationContext ctx;
    private CommissionService service;
    private SalesGradeRepository repo;

    public CommissionServiceTest() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    @Test
    public void CommissionAmountTest() {
        repo = ctx.getBean(SalesGradeRepository.class);
        service = ctx.getBean(CommissionService.class);

        SalesGrade grade = new SalesGrade.Builder("A").rate(10.65).build();
        SalesGrade grade1 = new SalesGrade.Builder("B").rate(5.65).build();
        SalesGrade grade2 = new SalesGrade.Builder("C").rate(5.0).build();
        SalesGrade grade3 = new SalesGrade.Builder("D").rate(3.0).build();
        SalesGrade grade4 = new SalesGrade.Builder("E").rate(2.60).build();

        repo.save(grade);
        repo.save(grade1);
        repo.save(grade2);
        repo.save(grade3);
        repo.save(grade4);

        double commAmount = service.getSaleCommission("C", 250000.0);
        Assert.assertEquals(commAmount, 12500.0, 0000.1);

    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        ctx = new AnnotationConfigApplicationContext(AppConfig.class);
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
        repo = ctx.getBean(SalesGradeRepository.class);
        repo.deleteAll();
    }
}
