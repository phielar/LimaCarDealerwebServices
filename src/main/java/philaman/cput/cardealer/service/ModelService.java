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
public interface ModelService extends Services<Model, Long>{

    public List<Model> getVehicleSpeedInRange(int min, int max);
    public List<Model> getVehicleOfBodyType(String value);
    public List<Model> getVehicleInPriceRange(double min, double max);
    public List<Model> getMakeModels(String make);
    public List<Model> getNumberOfVehiclesInBrand(String name);
    public List<Model> isSaleVehicles(boolean b);
    public List<Model> getVehiclesManufacturedbeforeYear(int year);
}
