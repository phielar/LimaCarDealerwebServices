/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package philaman.cput.cardealer.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Embedded;
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
public class Branch implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String branchname;
    private int branchYearToDateSales;
    @Embedded
    private Contact contact;
    @Embedded
    private Address address;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "Branch_id")
    private List<VehicleService> services;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "Branch_id")
    private List<Model> models;

    private Branch(Builder build) {
        this.id = build.id;
        this.branchname = build.branchname;
        this.branchYearToDateSales = build.branchYearToDateSales;
        this.contact = build.contact;
        this.address = build.address;
        this.models = build.models;
        this.services = build.services;
    }

    private Branch() {
    }

    public static class Builder {

        private Long id;
        private String branchname;
        private int branchYearToDateSales;
        private Contact contact;
        private Address address;
        private List<VehicleService> services;
        private List<Model> models;

        public Builder(String branchname) {
            this.branchname = branchname;
        }

        public Builder id(Long value) {
            id = value;
            return this;
        }

        public Builder address(Address value) {
            address = value;
            return this;
        }

        public Builder services(List<VehicleService> value) {
            services = value;
            return this;
        }

        public Builder models(List<Model> value) {
            models = value;
            return this;
        }

        public Builder branchYearToDateSales(int value) {
            branchYearToDateSales = value;
            return this;
        }

        public Builder contact(Contact value) {
            contact = value;
            return this;
        }

        public Branch build() {
            return new Branch(this);
        }

        public Builder branch(Branch branch) {
            this.id = branch.getId();
            this.branchname = branch.getBranchname();
            this.branchYearToDateSales = branch.getBranchYearToDateSales();
            this.contact = branch.getContact();
            this.address = branch.getAddress();
            this.models = branch.getModels();
            this.services = branch.getServices();
            return this;
        }

    }

    public Long getId() {
        return id;
    }

    public Address getAddress() {
        return address;
    }

    public Contact getContact() {
        return contact;
    }

    public String getBranchname() {
        return branchname;
    }

    public int getBranchYearToDateSales() {
        return branchYearToDateSales;
    }

    public List<VehicleService> getServices() {
        return services;
    }

    public List<Model> getModels() {
        return models;
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
        if (!(object instanceof Branch)) {
            return false;
        }
        Branch other = (Branch) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "philaman.cput.cardealer.domain.Branch[ id=" + id + " ]";
    }

}
