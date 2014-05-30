/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package philaman.cput.cardealer.presentation.rest;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import philaman.cput.cardealer.domain.Branch;
import philaman.cput.cardealer.service.BranchService;

/**
 *
 * @author phila
 */
@Controller
@RequestMapping("api/branch")
public class BranchRestController {

    @Autowired
    private BranchService service;

    @RequestMapping(value = "create", method = RequestMethod.POST)
    @ResponseBody
    public String create(@RequestBody Branch branch) {
        service.persist(branch);
        return "branch created";
    }

    @RequestMapping(value = "update", method = RequestMethod.PUT)
    @ResponseBody
    public String update(@RequestBody Branch branch) {
        service.merge(branch);
        return "Update Complete";
    }
    
    @RequestMapping(value = "branchs", method = RequestMethod.GET)
    @ResponseBody
    public List<Branch> toSalesBranch() {
        return service.findAll();
    }

    @RequestMapping(value = "LeadingBranch", method = RequestMethod.GET)
    @ResponseBody
    public Branch getBranch(@PathVariable Long id) {
        return service.find(id);
    }

    @RequestMapping(value = "LeadingBranch", method = RequestMethod.GET)
    @ResponseBody
    public List<Branch> hasStock(@PathVariable String model) {
        return service.getTotalBranches(model);
    }
    
}
