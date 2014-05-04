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
public class Part implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String name;
    private String description;
    @Column(precision = 2)
    private double purchasePrice;
    @Column(precision = 2)
    private double retailPrice;

    private Part() {
    }

    private Part(Builder build) {
        this.id = build.id;
        this.name = build.name;
        this.description = build.description;
        this.purchasePrice = build.purchasePrice;
        this.retailPrice = build.retailPrice;
    }

    public static class Builder {

        private Long id;
        private String name;
        private String description;
        private double purchasePrice;
        private double retailPrice;

        public Builder(double purchasePrice) {
            this.purchasePrice = purchasePrice;
        }

        public Builder name(String value) {
            name = value;
            return this;
        }

        public Builder id(Long value) {
            id = value;
            return this;
        }

        public Builder description(String value) {
            description = value;
            return this;
        }

        public Builder retailPrice(double value) {
            retailPrice = value;
            return this;
        }

        public Builder part(Part part) {
            this.id = part.getId();
            this.name = part.getName();
            this.description = part.getDescription();
            this.purchasePrice = part.getPurchasePrice();
            this.retailPrice = part.getRetailPrice();
            return this;
        }

        public Part build() {
            return new Part(this);
        }
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public double getRetailPrice() {
        return retailPrice;
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
        if (!(object instanceof Part)) {
            return false;
        }
        Part other = (Part) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "philaman.cput.cardealer.domain.Part[ id=" + id + " ]";
    }

}
