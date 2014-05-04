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
import philaman.cput.cardealer.service.VehicleBeforeDateService;

/**
 *
 * @author phila
 */
@Service
public class VehicleBeforeDateServiceImpl implements VehicleBeforeDateService {

    @Autowired
    private ModelRepository repository;

    @Override
    public List<Model> getVehiclesbeforeDate(int year) {
        List<Model> models = new ArrayList<>();
        List<Model> AllModels = repository.findAll();
        for (Model model : AllModels) {
            if (model.getReleaseYear() < year) {
                models.add(model);
            }
        }
        return models;
    }

}
