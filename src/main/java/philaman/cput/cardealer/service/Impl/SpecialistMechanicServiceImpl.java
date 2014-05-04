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
import philaman.cput.cardealer.domain.Mechanic;
import philaman.cput.cardealer.repository.MechanicRepository;
import philaman.cput.cardealer.service.SpecialistMechanicService;

/**
 *
 * @author phila
 */
@Service
public class SpecialistMechanicServiceImpl implements SpecialistMechanicService {

    @Autowired
    private MechanicRepository mechanicRepository;

    @Override
    public List<Mechanic> getSpecialityMechanic(String speciality, String rating) {
        List<Mechanic> mechanics = new ArrayList<>();
        List<Mechanic> allMechanics = mechanicRepository.findAll();
        for (Mechanic mechanic : allMechanics) {
            if (mechanic.getRatings().equalsIgnoreCase(rating) && mechanic.getSpeciality().equalsIgnoreCase(speciality)) {
                mechanics.add(mechanic);
            }
        }
        return mechanics;
    }

}
