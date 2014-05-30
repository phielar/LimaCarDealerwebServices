/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package philaman.cput.cardealer.service;

import java.util.List;
import philaman.cput.cardealer.domain.Branch;

/**
 *
 * @author phila
 */
public interface BranchService extends Services<Branch, Long>{
    
    public Branch getTopBranchSales();
    public List<Branch> getTotalBranches(String name);
}
