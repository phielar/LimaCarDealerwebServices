/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package philaman.cput.cardealer.test.restapi;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.testng.Assert;
import org.testng.annotations.Test;
import philaman.cput.cardealer.domain.Branch;
import philaman.cput.cardealer.domain.Contact;
import philaman.cput.cardealer.domain.Model;

/**
 *
 * @author phila
 */
public class BranchControllerTest {

    private final RestTemplate restTemplate = new RestTemplate();
    private final static String URL = "";

    public BranchControllerTest() {
    }

    @Test
    public void create() {
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
        List<Branch> branchs=new ArrayList<>();
        branchs.add(new Branch.Builder("Pinelands").contact(c).branchYearToDateSales(52).models(models).build());
        branchs.add(new Branch.Builder("Cape town").contact(c1).branchYearToDateSales(42).models(models1).build());
        branchs.add(new Branch.Builder("Gatesville").contact(c2).branchYearToDateSales(50).models(models2).build());
        branchs.add(new Branch.Builder("Belville").contact(c3).branchYearToDateSales(32).models(models2).build());
       
        
        HttpEntity<Branch[]> requestEntity = new HttpEntity(branchs, getContentType());
        ResponseEntity<String> responseEntity = restTemplate.exchange(URL + "api/branch/create", HttpMethod.POST, requestEntity, String.class);

        System.out.println("responce body" + responseEntity.getBody());
        System.out.println("responce code" + responseEntity.getStatusCode());
        System.out.println("responce header" + responseEntity.getHeaders());
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

    @Test(dependsOnMethods = "create")
    public void update() {
        HttpEntity<?> requestEntity = getHttpEntity();
        HttpEntity<Branch> responseEntity = restTemplate.exchange(URL + "api/branch/update", HttpMethod.GET, requestEntity, Branch.class);
        Branch branch = responseEntity.getBody();
        Branch updateBranch = new Branch.Builder("Pinelands").branch(branch).branchYearToDateSales(60).build();

        ResponseEntity<String> responseEntity1 = restTemplate.exchange(URL + "api/branch/update", HttpMethod.POST, requestEntity, String.class);
        Assert.assertEquals(responseEntity1.getStatusCode(), HttpStatus.OK);
    }
    @Test(dependsOnMethods = "update")
    public void getBranch(){
        
    }

    private HttpEntity<?> getHttpEntity() {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(new MediaType("application", "json"));
        HttpEntity<?> requestEntity = new HttpEntity<>(requestHeaders);
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        return requestEntity;
    }

    private HttpHeaders getContentType() {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(new MediaType("application", "json"));
        return requestHeaders;
    }
}
