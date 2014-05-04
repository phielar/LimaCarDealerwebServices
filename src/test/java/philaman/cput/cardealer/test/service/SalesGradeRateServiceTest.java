/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package philaman.cput.cardealer.test.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import philaman.cput.cardealer.app.config.AppConfig;
import philaman.cput.cardealer.domain.SalesGrade;
import philaman.cput.cardealer.repository.SalesGradeRepository;
import philaman.cput.cardealer.service.SalesGradeRateService;

/**
 *
 * @author phila
 */
public class SalesGradeRateServiceTest {
    private static ApplicationContext ctx;
    private SalesGradeRateService service;
    private SalesGradeRepository repo;
   
    public SalesGradeRateServiceTest() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
     @Test
     public void rate() {
         repo=ctx.getBean(SalesGradeRepository.class);
         service=ctx.getBean(SalesGradeRateService.class);
         SalesGrade grade=new SalesGrade.Builder("A").rate(18.5).build();
         SalesGrade grade1=new SalesGrade.Builder("B").rate(16.5).build();
         SalesGrade grade2=new SalesGrade.Builder("C").rate(14.5).build();
         SalesGrade grade3=new SalesGrade.Builder("D").rate(12.5).build();
         
         repo.save(grade);
         repo.save(grade1);
         repo.save(grade2);
         repo.save(grade3);
         
         double rate=service.getSalesPersonGradeRate("B");
         Assert.assertEquals(rate, 16.5, 0000.1);
     }

    @BeforeClass
    public static void setUpClass() throws Exception {
        ctx=new AnnotationConfigApplicationContext(AppConfig.class);
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @AfterMethod
    public void tearDownMethod() throws Exception{
        repo=ctx.getBean(SalesGradeRepository.class);
        repo.deleteAll();
    }
}
