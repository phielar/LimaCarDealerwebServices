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
import philaman.cput.cardealer.domain.OfferedService;
import philaman.cput.cardealer.repository.OfferedServiceRepository;
import philaman.cput.cardealer.service.TotalOfferedServicesService;

/**
 *
 * @author phila
 */
@Service
public class TotalOfferedServicesServiceImpl implements TotalOfferedServicesService {

    @Autowired
    private OfferedServiceRepository repository;

    @Override
    public List<OfferedService> getAllOfferedServices() {
        return repository.findAll();
    }

}
