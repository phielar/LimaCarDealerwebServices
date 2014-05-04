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
public class Manufacturer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @Embedded
    private Contact contact;
    @Embedded
    private Address address;
//   / @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "manufacturerID")
//    List<Model> carModelsManufactured;

    private Manufacturer() {
    }

    private Manufacturer(Builder build) {
        this.id = build.id;
        this.name = build.name;
        this.contact = build.contact;
        this.address = build.address;
//        this.carModelsManufactured = build.carModelsManufactured;
    }

    public static class Builder {

        private Long id;
        private String name;
        private Contact contact;
        private Address address;
//        List<Model> carModelsManufactured;

        public Builder(Address address) {
            this.address = address;
        }

        public Builder id(Long value) {
            id = value;
            return this;
        }

        public Builder name(String value) {
            name = value;
            return this;
        }

        public Builder contact(Contact value) {
            contact = value;
            return this;
        }

//        public Builder carModelsManufactured(List<Model> value) {
//            carModelsManufactured = value;
//            return this;
//        }
        public Manufacturer build() {
            return new Manufacturer(this);
        }

        public Builder manufacture(Manufacturer manufacture) {
            this.id = manufacture.getId();
            this.name = manufacture.getName();
            this.contact = manufacture.getContact();
            this.address = manufacture.getAddress();
//            this.carModelsManufactured = manufacture.getCarModelsManufactured();
            return this;
        }
    }

    public Address getAddress() {
        return address;
    }

    public Contact getContact() {
        return contact;
    }

//    public List<Model> getCarModelsManufactured() {
//        return carModelsManufactured;
//    }
    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
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
        if (!(object instanceof Manufacturer)) {
            return false;
        }
        Manufacturer other = (Manufacturer) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "philaman.cput.cardealer.domain.Manufacturer[ id=" + id + " ]";
    }

}
