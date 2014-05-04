/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package philaman.cput.cardealer.service.Impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import philaman.cput.cardealer.domain.OfferedService;
import philaman.cput.cardealer.repository.OfferedServiceRepository;
import philaman.cput.cardealer.service.ServicePriceService;

/**
 *
 * @author phila
 */
@Service
public class ServicePriceServiceImpl implements ServicePriceService {

    @Autowired
    private OfferedServiceRepository repository;

    @Override
    public double getServiceCost(String service) {
        List<OfferedService> offered = repository.findAll();
        double price = -999;
        for (OfferedService offeredService : offered) {
            if (offeredService.getService().equalsIgnoreCase(service)) {
                price = offeredService.getDurationhr() * offeredService.getRate();
            }
        }
        return price;
    }

}
