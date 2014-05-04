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
import philaman.cput.cardealer.service.SalesGradeRateService;

/**
 *
 * @author phila
 */
@Service
public class SalesGradeRateServiceImpl implements SalesGradeRateService {

    @Autowired
    private SalesGradeRepository gradeRepository;

    @Override
    public double getSalesPersonGradeRate(String grade) {
        double rate = -9999;
        List<SalesGrade> allGrades = gradeRepository.findAll();
        for (SalesGrade salesGrade1 : allGrades) {
            if (salesGrade1.getGrade().equals(grade)) {
                rate = salesGrade1.getRate();
            }
        }
        return rate;
    }

}
