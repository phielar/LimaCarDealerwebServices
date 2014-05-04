/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package philaman.cput.cardealer.service.Impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import philaman.cput.cardealer.domain.Model;
import philaman.cput.cardealer.repository.ModelRepository;
import philaman.cput.cardealer.service.TotalVehicleBodyTypeService;

/**
 *
 * @author phila
 */
@Service
public class TotalVehicleBodyTypeServiceImpl implements TotalVehicleBodyTypeService {

    @Autowired
    private ModelRepository modelRepository;

    @Override
    public List<Model> getVehicleOfBodyType(String value) {
       
        List<Model> models = new ArrayList<>();        
        List<Model> allVehicles = modelRepository.findAll();
        
        for (Model model : allVehicles) {
            if (!model.getBodyType().equalsIgnoreCase(value)) {
            } else {
                models.add(model);
            }
        }
        return models;
    }

}
