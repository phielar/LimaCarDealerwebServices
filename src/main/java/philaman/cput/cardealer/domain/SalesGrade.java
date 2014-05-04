/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package philaman.cput.cardealer.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author phila
 */
@Entity
public class SalesGrade implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String grade;
    @Column(nullable = false)
    private double rate;

    private SalesGrade() {
    }

    public SalesGrade(Builder build) {
        this.id = build.id;
        this.grade = build.grade;
        this.rate = build.rate;
    }

    public static class Builder {

        private String grade;
        private double rate;
        private Long id;

        public Builder(String grade) {
            this.grade = grade;
        }

        public Builder rate(double value) {
            rate = value;
            return this;
        }

        public Builder id(Long value) {
            id = value;
            return this;
        }

        public Builder salesPerson(SalesGrade salesGrade) {
            this.id = salesGrade.getId();
            this.grade = salesGrade.getGrade();
            this.rate = salesGrade.getRate();
            return this;
        }

        public SalesGrade build() {
            return new SalesGrade(this);
        }

    }

    public Long getId() {
        return id;
    }

    public String getGrade() {
        return grade;
    }

    public double getRate() {
        return rate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SalesGrade)) {
            return false;
        }
        SalesGrade other = (SalesGrade) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "philaman.cput.cardealer.domain.SalesGrade[ id=" + id + " ]";
    }

}
