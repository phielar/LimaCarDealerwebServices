package philaman.cput.cardealer.service.Impl;


import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import philaman.cput.cardealer.domain.ServiceBooking;
import philaman.cput.cardealer.repository.ServiceBookingRepository;
import philaman.cput.cardealer.service.ServiceTypeBookingService;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author phila
 */
@Service
public class ServiceTypeBookingServiceImpl implements ServiceTypeBookingService {

    @Autowired
    public ServiceBookingRepository bookingRepository;

    @Override
    public List<ServiceBooking> getServiceBookings(String date, String ServiceType) {
        List<ServiceBooking> bookings = new ArrayList<>();
        List<ServiceBooking> AllBookings = bookingRepository.findAll();
        for (ServiceBooking serviceBooking : AllBookings) {
            if (serviceBooking.getServiceType().equalsIgnoreCase(ServiceType)
                    && serviceBooking.getDate().equalsIgnoreCase(date)) {
                bookings.add(serviceBooking);
            }
        }
        return bookings;
    }

}
