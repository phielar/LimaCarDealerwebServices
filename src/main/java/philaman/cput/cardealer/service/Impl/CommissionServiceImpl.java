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
public class CommissionServiceImpl implements CommissionService {

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

}
