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
import philaman.cput.cardealer.service.ModelService;

/**
 *
 * @author phila
 */
@Service
public class ModelServiceImpl implements ModelService {

    @Autowired
    private ModelRepository repository;
  
    
    @Override
    public List<Model> isSaleVehicles(boolean isOnSale) {

        List<Model> onSaleModels = new ArrayList<>();
        List<Model> allModels = repository.findAll();
        for (Model model : allModels) {
            if (model.isOnSale() == isOnSale) {
                onSaleModels.add(model);
            }
        }
        return onSaleModels;
    }
    @Override
    public List<Model> getNumberOfVehiclesInBrand(String name) {
        List<Model> models = repository.findAll();
        List<Model> inBrandModels = new ArrayList<>();
        for (Model model : models) {
            if (model.getMake().equalsIgnoreCase(name)) {
                inBrandModels.add(model);
            }
        }
        return inBrandModels;
    }
    
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

    @Override
    public List<Model> getVehicleOfBodyType(String value) {
       
        List<Model> models = new ArrayList<>();        
        List<Model> allVehicles = repository.findAll();
        
        for (Model model : allVehicles) {
            if (!model.getBodyType().equalsIgnoreCase(value)) {
            } else {
                models.add(model);
            }
        }
        return models;
    }

     @Override
    public List<Model> getVehicleInPriceRange(double min, double max) {
        List<Model> inRangeCars = new ArrayList<>();
        List<Model> allModels = repository.findAll();
        for (Model model : allModels) {
            if ((model.getPrice() >= min) && (model.getPrice() <= max)) {
                inRangeCars.add(model);
            }
        }
        return inRangeCars;
    }

    @Override
    public List<Model> getMakeModels(String make) {
        List<Model> sameMakeModels = new ArrayList<>();
        List<Model> allModels = repository.findAll();
        for (Model model : allModels) {
            if (model.getMake().equalsIgnoreCase(make)) {
                sameMakeModels.add(model);
            }
        }
        return sameMakeModels;
    }

    @Override
    public Model find(Long id) {
        return repository.findOne(id);
    }

    @Override
    public Model persist(Model entity) {
        return repository.save(entity);
    }

    @Override
    public Model merge(Model entity) {
        if(entity.getId()!=null)
            repository.save(entity);
        return null;
    }

    @Override
    public void remove(Model entity) {
        repository.delete(entity);
    }

    @Override
    public List<Model> findAll() {
       return repository.findAll();
    }

    @Override
    public List<Model> getVehiclesManufacturedbeforeYear(int year) {
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
