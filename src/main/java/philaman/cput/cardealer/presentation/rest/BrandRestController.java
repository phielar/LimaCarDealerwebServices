/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package philaman.cput.cardealer.presentation.rest;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import philaman.cput.cardealer.domain.Brand;
import philaman.cput.cardealer.service.BrandService;

/**
 *
 * @author phila
 */
@Controller
@RequestMapping("api/brand")
public class BrandRestController {
    @Autowired
     BrandService brandService;
    
    @RequestMapping(value = "create", method = RequestMethod.POST)
    @ResponseBody  
    public String createBrand(@RequestBody Brand branch){
        brandService.persist(branch);        
        return "Brand Added";
    }
    
    @RequestMapping(value ="update", method = RequestMethod.PUT)
    @ResponseBody
    public String updateBrandInfo(@RequestBody Brand brand){
        brandService.merge(brand);
        return "update was successful.";
    }
    
    @RequestMapping(value = "brand", method = RequestMethod.GET)
    @ResponseBody
    public List<Brand> getBrands(){
        System.out.println("Brand list");
       return brandService.getBrand();
    }
    
    @RequestMapping(value = "name/{name}", method = RequestMethod.GET)
    @ResponseBody
    public Brand getBrandByName(@PathVariable String value){
        Brand brand=brandService.getBrandByName(value);
        if(brand!=null)
            return brand;
        return null;               
    }
    @RequestMapping(value ="id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Brand getBrandById(@PathVariable Long id)  {
        return brandService.find(id);
    } 
    
}
