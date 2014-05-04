/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package philaman.cput.cardealer.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author phila
 */
@Entity
public class Brand implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String brandName;
    private String country;
    private String dateEstablised;

    private Brand() {
    }

    private Brand(Builder build) {
        this.id = build.id;
        this.brandName = build.brandName;
        this.country = build.country;
        this.dateEstablised = build.dateEstablised;
    }

    public static class Builder {

        private Long id;
        private String brandName;
        private String country;
        private String dateEstablised;

        public Builder(String brandName) {
            this.brandName = brandName;
        }

        public Builder id(Long value) {
            id = value;
            return this;
        }

        public Builder country(String value) {
            country = value;
            return this;
        }

        public Builder dateEstablised(String value) {
            dateEstablised = value;
            return this;
        }

        public Brand build() {
            return new Brand(this);
        }

        public Builder brand(Brand brand) {
            this.id = brand.getId();
            this.brandName = brand.getBrandName();
            this.country = brand.getCountry();
            this.dateEstablised = brand.getDateEstablised();
            return this;
        }

    }

    public Long getId() {
        return id;
    }

    public String getBrandName() {
        return brandName;
    }

    public String getCountry() {
        return country;
    }

    public String getDateEstablised() {
        return dateEstablised;
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
        if (!(object instanceof Brand)) {
            return false;
        }
        Brand other = (Brand) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "philaman.cput.cardealer.domain.Brand[ id=" + id + " ]";
    }

}
