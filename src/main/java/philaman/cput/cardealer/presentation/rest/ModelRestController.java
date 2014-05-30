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
import philaman.cput.cardealer.domain.Model;
import philaman.cput.cardealer.service.ModelService;

/**
 *
 * @author phila
 */
@Controller
@RequestMapping("api/model")
public class ModelRestController {
    @Autowired
    private ModelService service;
    
    @RequestMapping(value = "create", method = RequestMethod.POST)
    @ResponseBody
    public String create(@RequestBody Model model){
        service.persist(model);
        return "Model Added";
    }
    @RequestMapping(value ="update", method = RequestMethod.PUT)
    @ResponseBody
    public String update(@RequestBody Model model){
        service.merge(model);
        return "Model update";
    }
    
    @RequestMapping(value = "model/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Model getModel(@PathVariable Long id){
        return service.find(id);
    }
    
    @RequestMapping(value = "model/{make}", method = RequestMethod.GET)
    @ResponseBody
    public List<Model> getModelByMake(@PathVariable String make){
        return service.getMakeModels(make);
    }
    
    @RequestMapping(value = "model/speed/{min}&&{max}", method = RequestMethod.GET)
    @ResponseBody
    public List<Model> getModelSpeedInRange(@PathVariable int min,@PathVariable int max ){
        return service.getVehicleSpeedInRange(min, max);
    }
    
    @RequestMapping(value ="model/price/{min}&&{max)", method = RequestMethod.GET)
    @ResponseBody
    public List<Model> getModelPriceInRange(@PathVariable double min, @PathVariable double max){
        return service.getVehicleInPriceRange(min, max);
    }
    
    @RequestMapping(value = "model/{body}", method = RequestMethod.GET)
    @ResponseBody
    public List<Model> getModelByBodytype(@PathVariable String body){
        return service.getVehicleOfBodyType(body);
    }
    
    @RequestMapping(value = "model/{brand}", method=RequestMethod.GET)
    @ResponseBody
    public List<Model> getModelsOfBrandType(@PathVariable String brand){
        return service.getNumberOfVehiclesInBrand(brand);
    }
    
    @RequestMapping(value ="model/{sale}", method = RequestMethod.GET)
    @ResponseBody
    public List<Model> getModelPriceReduced(@PathVariable boolean isSale){
        return service.isSaleVehicles(isSale);
    }
    @RequestMapping(value = "model/{year}", method = RequestMethod.GET)
    @ResponseBody
     public List<Model> getModelPriceReduced(@PathVariable int year){
        return service.getVehiclesManufacturedbeforeYear(year);
    }
}
