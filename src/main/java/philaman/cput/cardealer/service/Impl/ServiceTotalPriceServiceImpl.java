/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package philaman.cput.cardealer.service.Impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import philaman.cput.cardealer.domain.Part;
import philaman.cput.cardealer.domain.VehicleService;
import philaman.cput.cardealer.repository.PartRepository;
import philaman.cput.cardealer.repository.ServiceRepository;
import philaman.cput.cardealer.service.ServiceTotalPriceService;

/**
 *
 * @author phila
 */
@Service
public class ServiceTotalPriceServiceImpl implements ServiceTotalPriceService {

    @Autowired
    private ServiceRepository repository;
    
    @Override
    public double getServicePrice(Long id) {
        double price =-999;
        VehicleService service = repository.findOne(id);
        List<Part> parts = service.getParts();
        price = service.getDuration() * service.getHourRate();
        for (Part part : parts) {
            price += part.getPurchasePrice();
        }
        return price;
    }

}
