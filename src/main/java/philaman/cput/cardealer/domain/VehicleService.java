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
import javax.persistence.OneToOne;

/**
 *
 * @author phila
 */
@Entity
public class VehicleService implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @Column(nullable = false)
    private String sDate;
    private String report;
    private double duration;
    @Column(nullable = false)
    private double hourRate;
    @OneToOne
    private Mechanic mechanics;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "serrvice_id")
    private List<Part> parts;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "serrvice_id")
    private List<ServiceBooking> serviceBooking;

    private VehicleService() {
    }

    private VehicleService(Builder build) {
        this.id = build.id;
        this.name = build.name;
        this.sDate = build.sDate;
        this.report = build.report;
        this.hourRate = build.hourRate;
        this.parts = build.parts;
        this.mechanics = build.mechanics;
        this.serviceBooking = build.serviceBooking;
        this.duration = build.duration;
    }

    public static class Builder {

        private Long id;
        private String name;
        private String sDate;
        private String report;
        private double hourRate;
        private double duration;
        private Mechanic mechanics;
        private List<Part> parts;
        private List<ServiceBooking> serviceBooking;

        public Builder(String sDate) {
            this.sDate = sDate;
        }

        public Builder id(Long value) {
            id = value;
            return this;
        }

        public Builder parts(List<Part> value) {
            parts = value;
            return this;
        }

        public Builder mechanics(Mechanic value) {
            mechanics = value;
            return this;
        }

        public Builder name(String value) {
            name = value;
            return this;
        }

        public Builder duration(double value) {
            duration = value;
            return this;
        }

        public Builder report(String value) {
            report = value;
            return this;
        }

        public Builder hourRate(double value) {
            hourRate = value;
            return this;
        }

        public Builder serviceBooking(List<ServiceBooking> value) {
            serviceBooking = value;
            return this;
        }

        public Builder service(VehicleService service) {

            this.id = service.getId();
            this.name = service.getName();
            this.sDate = service.getSDate();
            this.report = service.getReport();
            this.hourRate = service.getHourRate();
            this.mechanics = service.getMechanics();
            this.parts = service.getParts();
            this.serviceBooking = service.getServiceBooking();
            this.duration = service.getDuration();
            return this;
        }

        public VehicleService build() {
            return new VehicleService(this);
        }
    }

    public Mechanic getMechanics() {
        return mechanics;
    }

    public double getDuration() {
        return duration;
    }

    public List<Part> getParts() {
        return parts;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSDate() {
        return sDate;
    }

    public double getHourRate() {
        return hourRate;
    }

    public List<ServiceBooking> getServiceBooking() {
        return serviceBooking;
    }

    public String getReport() {
        return report;
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
        if (!(object instanceof VehicleService)) {
            return false;
        }
        VehicleService other = (VehicleService) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "philaman.cput.cardealer.domain.Service[ id=" + id + " ]";
    }

}
