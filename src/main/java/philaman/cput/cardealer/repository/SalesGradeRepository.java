/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package philaman.cput.cardealer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import philaman.cput.cardealer.domain.SalesGrade;

/**
 *
 * @author phila
 */
@Repository
public interface SalesGradeRepository extends JpaRepository<SalesGrade, Long> {

}
