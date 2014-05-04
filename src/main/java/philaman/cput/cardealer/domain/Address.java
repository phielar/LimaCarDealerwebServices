/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package philaman.cput.cardealer.domain;

import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 *
 * @author phila
 */
@Embeddable
public class Address implements Serializable {

    private String houseNumber;
    private String street;
    private String suburb;
    private String city;
    private String province;
    private String postalCode;

    private Address() {
    }

    private Address(Builder build) {
        this.houseNumber = build.houseNumber;
        this.street = build.street;
        this.suburb = build.suburb;
        this.city = build.city;
        this.province = build.province;
        this.postalCode = build.postalCode;
    }

    public static class Builder {

        private String houseNumber;
        private String street;
        private String suburb;
        private String city;
        private String province;
        private String postalCode;

        public Builder(String houseNumber) {
            this.houseNumber = houseNumber;
        }

        public Builder street(String value) {
            street = value;
            return this;
        }

        public Builder suburb(String value) {
            suburb = value;
            return this;
        }

        public Builder city(String value) {
            city = value;
            return this;
        }

        public Builder province(String value) {
            province = value;
            return this;
        }

        public Builder postalCode(String value) {
            postalCode = value;
            return this;
        }

        public Address build() {
            return new Address(this);
        }

        public Builder Address(Address address) {
            this.houseNumber = address.getHouseNumber();
            this.street = address.getStreet();
            this.suburb = address.getSuburb();
            this.city = address.getCity();
            this.province = address.getProvince();
            this.postalCode = address.getPostalCode();
            return this;
        }

    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public String getStreet() {
        return street;
    }

    public String getSuburb() {
        return suburb;
    }

    public String getCity() {
        return city;
    }

    public String getProvince() {
        return province;
    }

    public String getPostalCode() {
        return postalCode;
    }
}

