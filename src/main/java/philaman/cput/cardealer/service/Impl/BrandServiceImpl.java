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
import philaman.cput.cardealer.domain.Brand;
import philaman.cput.cardealer.domain.Model;
import philaman.cput.cardealer.repository.BrandRepository;
import philaman.cput.cardealer.repository.ModelRepository;
import philaman.cput.cardealer.service.BrandService;

/**
 *
 * @author phila
 */
@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    public BrandRepository brandRepository;
    @Autowired
    public ModelRepository modelRepository;

    @Override
    public Brand find(Long id) {
        return brandRepository.findOne(id);
    }

    @Override
    public Brand persist(Brand entity) {
        return brandRepository.save(entity);
    }

    @Override
    public Brand merge(Brand entity) {

        if (entity.getId() != null) {
            brandRepository.save(entity);
        }
        return null;
    }

    @Override
    public void remove(Brand entity) {
        brandRepository.delete(entity);
    }

    @Override
    public List<Brand> findAll() {
        return brandRepository.findAll();
    }

    @Override
    public Brand getBrandByName(String name) {
        List<Brand> brands = brandRepository.findAll();
        Brand brandByName = null;
        for (Brand brand : brands) {
            if (name.equalsIgnoreCase(brand.getBrandName())) {
                brandByName = brand;
            }
        }
        return brandByName;
    }   

    @Override
    public List<Brand> getBrand() {
       return brandRepository.findAll();
    }

  }
