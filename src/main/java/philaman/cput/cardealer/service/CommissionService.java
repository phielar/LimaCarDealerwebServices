/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package philaman.cput.cardealer.service;

import philaman.cput.cardealer.domain.SalesGrade;

/**
 *
 * @author phila
 */
public interface CommissionService extends Services<SalesGrade, Long>{
    
    public double getSalesCommissionRate(String grade);
    public double getSaleCommission(String grade, double price);
}
