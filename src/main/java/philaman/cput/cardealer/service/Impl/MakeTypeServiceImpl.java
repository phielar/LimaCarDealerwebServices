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
import philaman.cput.cardealer.service.MakeTypeService;

/**
 *
 * @author phila
 */
@Service
public class MakeTypeServiceImpl implements MakeTypeService {

    @Autowired
    private ModelRepository modelRepository;

    @Override
    public List<Model> getMakeModels(String make) {
        List<Model> sameMakeModels = new ArrayList<>();
        List<Model> allModels = modelRepository.findAll();
        for (Model model : allModels) {
            if (model.getMake().equalsIgnoreCase(make)) {
                sameMakeModels.add(model);
            }
        }
        return sameMakeModels;
    }

}
