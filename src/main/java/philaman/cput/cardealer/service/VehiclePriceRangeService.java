/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package philaman.cput.cardealer.service;

import java.util.List;
import philaman.cput.cardealer.domain.Model;

/**
 *
 * @author phila
 */
public interface VehiclePriceRangeService {

    public List<Model> getVehicleInPriceRange(double min, double max);
}
