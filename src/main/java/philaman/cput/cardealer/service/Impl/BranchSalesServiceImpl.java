/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package philaman.cput.cardealer.service.Impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import philaman.cput.cardealer.domain.Branch;
import philaman.cput.cardealer.repository.BranchRepository;
import philaman.cput.cardealer.service.BranchSalesService;

/**
 *
 * @author phila
 */
@Service
public class BranchSalesServiceImpl implements BranchSalesService {

    @Autowired
    private BranchRepository repository;

    @Override
    public Branch getTopBranchSales() {
        List<Branch> allBranchs = repository.findAll();
        Branch highSales = allBranchs.get(0);
        for (int i = 1; i < allBranchs.size(); i++) {
            if (allBranchs.get(i).getBranchYearToDateSales() > highSales.getBranchYearToDateSales()) {
                highSales = allBranchs.get(i);
            }
        }
        return highSales;
    }
}
