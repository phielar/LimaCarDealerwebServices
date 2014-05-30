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
import philaman.cput.cardealer.domain.Branch;
import philaman.cput.cardealer.domain.Model;
import philaman.cput.cardealer.repository.BranchRepository;
import philaman.cput.cardealer.service.BranchService;

/**
 *
 * @author phila
 */
@Service
public class BranchServiceImpl implements BranchService {

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

    @Override
    public List<Branch> getTotalBranches(String name) {
        List<Branch> branchs = new ArrayList<>();
        List<Model> model = new ArrayList<>();
        List<Branch> allBranchs = repository.findAll();
        for (Branch branch : allBranchs) {
            model = branch.getModels();
            for (Model mod : model) {
                if (mod.getModelName().equalsIgnoreCase(name)) {
                    branchs.add(branch);
                }
            }
        }
        return branchs;
    }

    @Override
    public Branch find(Long id) {
        return repository.findOne(id);
    }

    @Override
    public Branch persist(Branch entity) {
        return repository.save(entity);
    }

    @Override
    public Branch merge(Branch entity) {
        if(entity!=null)
            repository.save(entity);
        return null;
    }

    @Override
    public void remove(Branch entity) {
        repository.delete(entity);
    }

    @Override
    public List<Branch> findAll() {
        return repository.findAll();
    }

}
