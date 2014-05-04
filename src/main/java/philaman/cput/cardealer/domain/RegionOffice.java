/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package philaman.cput.cardealer.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

/**
 *
 * @author phila
 */
@Entity
public class RegionOffice implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String officeName;
    @Column(unique = true)
    private String regionCode;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "RegionOnfficeID")
    List<Branch> branch;

    private RegionOffice() {
    }

    public RegionOffice(Builder build) {
        this.id = build.id;
        this.officeName = build.officeName;
        this.branch = build.branch;
    }

    public static class Builder {

        private Long id;
        private String regionCode;
        private String officeName;
        List<Branch> branch;

        public Builder(String regionCode) {
            this.regionCode = regionCode;
        }

        public Builder officeName(String officeName) {
            this.officeName = officeName;
            return this;
        }

        public Builder id(Long value) {
            id = value;
            return this;
        }

        public Builder branch(List<Branch> value) {
            branch = value;
            return this;
        }

        public Builder regionOffice(RegionOffice regionOffice) {
            this.id = regionOffice.getId();
            this.regionCode = regionOffice.getRegionCode();
            this.officeName = regionOffice.getOfficeName();
            this.branch = regionOffice.getBranch();
            return this;
        }

        public RegionOffice build() {
            return new RegionOffice(this);
        }
    }

    public Long getId() {
        return id;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public String getOfficeName() {
        return officeName;
    }

    public List<Branch> getBranch() {
        return branch;
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
        if (!(object instanceof RegionOffice)) {
            return false;
        }
        RegionOffice other = (RegionOffice) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "philaman.cput.cardealer.domain.RegionOffice[ id=" + id + " ]";
    }

}
