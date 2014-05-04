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
import philaman.cput.cardealer.service.TotalBranchService;

/**
 *
 * @author phila
 */
@Service
public class TotalBranchServiceImpl implements TotalBranchService{
    @Autowired
    private BranchRepository repository;

    @Override
    public List<Branch> getTotalBranches() {
       return repository.findAll();
    }
    
    
    
            
}
