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
public class Department implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String name;
    private String regionCode;

    private Department() {
    }

    private Department(Builder build) {
        this.id = build.id;
        this.name = build.name;
        this.regionCode = build.regionCode;
    }

    public static class Builder {

        private Long id;
        private String name;
        private String regionCode;

        public Builder(String regionCode) {
            this.regionCode = regionCode;
        }

        public Builder name(String value) {
            this.name = value;
            return this;
        }

        public Builder id(Long value) {
            id = value;
            return this;
        }

        public Builder department(Department department) {
            this.id = department.getId();
            this.name = department.getName();
            this.regionCode = department.getRegionCode();
            return this;
        }

        public Department build() {
            return new Department(this);
        }
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRegionCode() {
        return regionCode;
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
        if (!(object instanceof Department)) {
            return false;
        }
        Department other = (Department) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "philaman.cput.cardealer.domain.Department[ id=" + id + " ]";
    }

}
