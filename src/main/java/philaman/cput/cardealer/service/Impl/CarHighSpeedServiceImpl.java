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
import philaman.cput.cardealer.service.CarHighSpeedService;

/**
 *
 * @author phila
 */
@Service
public class CarHighSpeedServiceImpl implements CarHighSpeedService {

    @Autowired
    private ModelRepository repository;

    @Override
    public List<Model> getVehicleSpeedInRange(int min, int max) {
        List<Model> cars = new ArrayList<>();
        List<Model> allModels = repository.findAll();
        for (Model model : allModels) {
            if (model.getTopSpeeed() >= min && model.getTopSpeeed() <= max) {
                cars.add(model);
            }
        }
        return cars;
    }
}
