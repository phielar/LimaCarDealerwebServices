/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package philaman.cput.cardealer.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
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
public class OfferedService implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String service;
    private double rate;
    private String durationhr;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "OfferedServices_id")
    private List<ServiceBooking> serviceBooking;

    private OfferedService() {
    }

    private OfferedService(Builder build) {
        this.id = build.id;
        this.service = build.service;
        this.rate = build.rate;
        this.durationhr = build.durationhr;
        this.serviceBooking = build.serviceBooking;
    }

    public static class Builder {

        private Long id;
        private String service;
        private double rate;
        private String durationhr;
        private List<ServiceBooking> serviceBooking;

        public Builder(String service) {
            this.service = service;
        }

        public Builder id(Long value) {
            id = value;
            return this;
        }

        public Builder serviceBooking(List<ServiceBooking> value) {
            serviceBooking = value;
            return this;
        }

        public Builder rate(double value) {
            rate = value;
            return this;
        }

        public Builder durationhr(String value) {
            durationhr = value;
            return this;
        }

        public Builder offeredServices(OfferedService os) {
            this.id = os.getId();
            this.service = os.getService();
            this.rate = os.getRate();
            this.durationhr = os.getDurationhr();
            this.serviceBooking = os.getServiceBooking();
            return this;
        }

        public OfferedService build() {
            return new OfferedService(this);
        }

    }

    public Long getId() {
        return id;
    }

    public List<ServiceBooking> getServiceBooking() {
        return serviceBooking;
    }

    public String getService() {
        return service;
    }

    public double getRate() {
        return rate;
    }

    public String getDurationhr() {
        return durationhr;
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
        if (!(object instanceof OfferedService)) {
            return false;
        }
        OfferedService other = (OfferedService) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "philaman.cput.cardealer.domain.OfferedService[ id=" + id + " ]";
    }

}
