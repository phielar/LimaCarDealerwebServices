/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package philaman.cput.cardealer.service.Impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import philaman.cput.cardealer.domain.SalesGrade;
import philaman.cput.cardealer.repository.SalesGradeRepository;
import philaman.cput.cardealer.service.CommissionService;

/**
 *
 * @author phila
 */
@Service
public class CommissionServiceImpl implements CommissionService{

    @Autowired
    private SalesGradeRepository repository;

    @Override
    public double getSaleCommission(String grade, double price) {
        List<SalesGrade> allgredes = repository.findAll();
        double commission = -999;
        for (SalesGrade salesGrade : allgredes) {
            if (salesGrade.getGrade().equalsIgnoreCase(grade)) {
                commission = price * (salesGrade.getRate() / 100);
            }
        }
        return commission;
    }
    
    @Override
    public double getSalesCommissionRate(String grade) {
        double rate = -9999;
        List<SalesGrade> allGrades = repository.findAll();
        for (SalesGrade salesGrade1 : allGrades) {
            if (salesGrade1.getGrade().equals(grade)) {
                rate = salesGrade1.getRate();
            }
        }
        return rate;
    }  
    
    @Override
    public SalesGrade find(Long id) {
     return repository.findOne(id);
    }

    @Override
    public SalesGrade persist(SalesGrade entity) {
      return repository.save(entity);
    }

    @Override
    public SalesGrade merge(SalesGrade entity) {
       if(entity!=null)
           repository.save(entity);
       return null;
    }

    @Override
    public void remove(SalesGrade entity) {
        repository.delete(entity);
    }

    @Override
    public List<SalesGrade> findAll() {
     return repository.findAll();
    }
    
    
}
