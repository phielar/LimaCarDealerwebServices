/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package philaman.cput.cardealer.service.Impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import philaman.cput.cardealer.domain.Model;
import philaman.cput.cardealer.repository.ModelRepository;
import philaman.cput.cardealer.service.TotalVehicleService;

/**
 *
 * @author phila
 */
@Service
public class TotalVehicleServiceImpl implements TotalVehicleService {

    @Autowired
    private ModelRepository modelRepository;

    @Override
    public List<Model> getAllVehicle() {
        return modelRepository.findAll();
    }

}
