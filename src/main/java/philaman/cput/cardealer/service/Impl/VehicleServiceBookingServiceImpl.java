/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package philaman.cput.cardealer.service.Impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import philaman.cput.cardealer.domain.ServiceBooking;
import philaman.cput.cardealer.repository.ServiceBookingRepository;
import philaman.cput.cardealer.service.VehicleServiceBookingService;

/**
 *
 * @author phila
 */
@Service
public class VehicleServiceBookingServiceImpl implements VehicleServiceBookingService {

    @Autowired
    private ServiceBookingRepository bookingRepository;

    @Override
    public List<ServiceBooking> getBooking(){
        return bookingRepository.findAll();
    }
}
