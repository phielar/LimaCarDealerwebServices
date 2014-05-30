/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package philaman.cput.cardealer.presentation.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import philaman.cput.cardealer.domain.SalesGrade;
import philaman.cput.cardealer.service.CommissionService;

/**
 *
 * @author phila
 */
@Controller
@RequestMapping(value = "api/commission")
public class CommissionRestController {

    @Autowired
    private CommissionService service;

    @RequestMapping(value = "create", method = RequestMethod.POST)
    @ResponseBody
    public String create(@RequestBody SalesGrade grade) {
        service.persist(grade);
        return "Commission rate created";
    }

    @RequestMapping(value = "update", method = RequestMethod.PUT)
    @ResponseBody
    public String update(@RequestBody SalesGrade grade) {
        service.merge(grade);
        return "Commission rate updated";
    }

    @RequestMapping(value = "commission/{id}", method = RequestMethod.GET)
    @ResponseBody
    public SalesGrade update(@PathVariable Long id) {
        return service.find(id);
    }

    @RequestMapping(value = "delete}", method = RequestMethod.DELETE)
    @ResponseBody
    public String remove(@RequestBody SalesGrade grade) {
        service.remove(grade);
        return "Deleted";
    }

    @RequestMapping(value = "commission/{grade}&&{price}", method = RequestMethod.GET)
    @ResponseBody
    public double update(@PathVariable String grade, @PathVariable double price) {
        return service.getSaleCommission(grade, price);
    }

    @RequestMapping(value = "commission/{grade}", method = RequestMethod.GET)
    @ResponseBody
    public double update(@PathVariable String grade) {
        return service.getSalesCommissionRate(grade);
    }

}
