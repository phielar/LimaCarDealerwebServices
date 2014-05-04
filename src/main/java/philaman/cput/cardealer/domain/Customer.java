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
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstname;
    private String lastname;
    private double balance;
    @Embedded
    private Address address;
    @Embedded
    private Contact contact;
    @Embedded
    private Employment employment;
    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "customer_id")
    private List<Dependent> dependent;

    private Customer() {
    }

    private Customer(Builder build) {
        this.id = build.id;
        this.firstname = build.firstname;
        this.lastname = build.lastname;
        this.balance = build.balance;
        this.contact = build.contact;
        this.employment = build.employment;
        this.dependent = build.dependent;
    }

    public static class Builder {

        private Long id;
        private String firstname;
        private String lastname;
        private double balance;
        private Address address;
        private Contact contact;
        private Employment employment;
        private List<Dependent> dependent;

        public Builder(String lastname) {
            this.lastname = lastname;
        }

        public Builder id(Long value) {
            id = value;
            return this;
        }

        public Builder address(Address value) {
            address = value;
            return this;
        }

        public Builder firstname(String value) {
            firstname = value;
            return this;
        }

        public Builder balance(double value) {
            balance = value;
            return this;
        }

        public Builder contact(Contact value) {
            contact = value;
            return this;
        }

        public Builder employment(Employment value) {
            employment = value;
            return this;
        }

        public Builder dependent(List<Dependent> value) {
            dependent = value;
            return this;
        }

        public Builder customer(Customer customer) {
            this.id = customer.getId();
            this.firstname = customer.getFirstname();
            this.lastname = customer.getLastname();
            this.balance = customer.getBalance();
            this.address = customer.getAddress();
            this.contact = customer.getContact();
            this.employment = customer.getEmployment();
            this.dependent = customer.getDependent();
            return this;
        }

        public Customer build() {
            return new Customer(this);
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

    public double getBalance() {
        return balance;
    }

    public Contact getContact() {
        return contact;
    }

    public Employment getEmployment() {
        return employment;
    }

    public List<Dependent> getDependent() {
        return dependent;
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
        if (!(object instanceof Customer)) {
            return false;
        }
        Customer other = (Customer) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "philaman.cput.cardealer.domain.Customer[ id=" + id + " ]";
    }

}
