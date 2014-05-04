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
import philaman.cput.cardealer.service.VehiclePriceRangeService;

/**
 *
 * @author phila
 */
@Service
public class VehiclePriceRangeServiceImpl implements VehiclePriceRangeService {

    @Autowired
    private ModelRepository modelRepository;

    @Override
    public List<Model> getVehicleInPriceRange(double min, double max) {
        List<Model> inRangeCars = new ArrayList<>();
        List<Model> allModels = modelRepository.findAll();
        for (Model model : allModels) {
            if ((model.getPrice() >= min) && (model.getPrice() <= max)) {
                inRangeCars.add(model);
            }
        }
        return inRangeCars;
    }

}
