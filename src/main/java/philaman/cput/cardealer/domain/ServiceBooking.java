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
public class ServiceBooking implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String sdate;
    private String serviceType;
    private String description;

    private ServiceBooking() {
    }

    private ServiceBooking(Builder build) {
        this.id = build.id;
        this.sdate = build.date;
        this.serviceType = build.serviceType;
        this.description = build.description;
    }

    public static class Builder {

        private Long id;
        private String date;
        private String serviceType;
        private String description;

        public Builder(String date) {
            this.date = date;
        }

        public Builder id(Long value) {
            this.id = value;
            return this;
        }

        public Builder serviceType(String value) {
            this.serviceType = value;
            return this;
        }

        public Builder description(String value) {
            this.description = value;
            return this;
        }

        public Builder serviceBooking(ServiceBooking serviceBooking) {

            this.id = serviceBooking.getId();
            this.date = serviceBooking.getDate();
            this.description = serviceBooking.getDescription();
            this.serviceType = serviceBooking.getServiceType();
            return this;
        }

        public ServiceBooking build() {
            return new ServiceBooking(this);
        }

    }

    public Long getId() {
        return id;
    }

    public String getDate() {
        return sdate;
    }

    public String getServiceType() {
        return serviceType;
    }

    public String getDescription() {
        return description;
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
        if (!(object instanceof ServiceBooking)) {
            return false;
        }
        ServiceBooking other = (ServiceBooking) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "philaman.cput.cardealer.domain.ServiceBooking[ id=" + id + " ]";
    }

}
