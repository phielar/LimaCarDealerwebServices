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
import philaman.cput.cardealer.domain.Manufacturer;
import philaman.cput.cardealer.repository.ManufacturerRepository;
import philaman.cput.cardealer.service.ManufacturerSearchService;

/**
 *
 * @author phila
 */
@Service
public class ManufacturerSearchServiceImpl implements ManufacturerSearchService {

    @Autowired
    private ManufacturerRepository repository;

    @Override
    public List<Manufacturer> getManufacturerInCity(String city) {
        List<Manufacturer> manufacturers = new ArrayList<>();
        List<Manufacturer> allManufacturers = repository.findAll();
        for (Manufacturer manufacturer : allManufacturers) {
            if (manufacturer.getAddress().getCity().equals(city)) {
                manufacturers.add(manufacturer);
            }
        }
        return manufacturers;
    }

}
