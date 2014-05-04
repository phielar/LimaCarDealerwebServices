/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package philaman.cput.cardealer.domain;

import java.io.Serializable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author phila
 */
@Entity
public class Mechanic implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstname;
    private String lastname;
    private String speciality;
    private String ratings;
    @Embedded
    private Contact contact;
    @Embedded
    private Address address;

    private Mechanic() {
    }

    private Mechanic(Builder build) {
        this.id = build.id;
        this.firstname = build.firstname;
        this.lastname = build.lastname;
        this.speciality = build.speciality;
        this.ratings = build.ratings;
        this.contact = build.contact;
        this.address = build.address;
    }

    public static class Builder {

        private Long id;
        private String firstname;
        private String lastname;
        private String speciality;
        private String ratings;
        private Contact contact;
        private Address address;

        public Builder(String speciality) {
            this.speciality = speciality;
        }

        public Builder firstname(String value) {
            firstname = value;
            return this;
        }

        public Builder lastname(String value) {
            lastname = value;
            return this;
        }

        public Builder address(Address value) {
            address = value;
            return this;
        }

        public Builder contact(Contact value) {
            contact = value;
            return this;
        }

        public Builder id(Long value) {
            id = value;
            return this;
        }

        public Builder ratings(String value) {
            ratings = value;
            return this;
        }

        public Builder mechanic(Mechanic mechanic) {
            this.id = mechanic.getId();
            this.firstname = mechanic.getFirstname();
            this.lastname = mechanic.getLastname();
            this.address = mechanic.getAddress();
            this.contact = mechanic.getContact();
            this.speciality = mechanic.getSpeciality();
            this.ratings = mechanic.getRatings();
            return this;
        }

        public Mechanic build() {
            return new Mechanic(this);
        }

    }

    public Long getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getSpeciality() {
        return speciality;
    }

    public String getRatings() {
        return ratings;
    }

    public Contact getContact() {
        return contact;
    }

    public Address getAddress() {
        return address;
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
        if (!(object instanceof Mechanic)) {
            return false;
        }
        Mechanic other = (Mechanic) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "philaman.cput.cardealer.domain.Mechani[ id=" + id + " ]";
    }

}
