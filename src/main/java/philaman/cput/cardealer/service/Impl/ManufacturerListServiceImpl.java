/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package philaman.cput.cardealer.service.Impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import philaman.cput.cardealer.domain.Manufacturer;
import philaman.cput.cardealer.repository.ManufacturerRepository;
import philaman.cput.cardealer.service.ManufacturerListService;

/**
 *
 * @author phila
 */
@Service
public class ManufacturerListServiceImpl implements ManufacturerListService {

    @Autowired
    private ManufacturerRepository repository;

    @Override
    public List<Manufacturer> getManufacturers() {
        return repository.findAll();
    }

}
