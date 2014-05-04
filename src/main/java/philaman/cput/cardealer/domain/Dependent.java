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
public class Dependent implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String name;
    private int age;
    @Column(precision = 0)
    private float allowanceAmount;

    private Dependent() {
    }

    public Dependent(Builder build) {
        this.id = build.id;
        this.name = build.name;
        this.age = build.age;
        this.allowanceAmount = build.allowanceAmount;
    }

    public static class Builder {

        private Long id;
        private String name;
        private int age;
        private float allowanceAmount;

        public Builder(float allowanceAmount) {
            this.allowanceAmount = allowanceAmount;
        }

        public Builder id(Long value) {
            id = value;
            return this;
        }

        public Builder age(int value) {
            age = value;
            return this;
        }

        public Builder name(String value) {
            name = value;
            return this;
        }

        public Builder dependent(Dependent dependent) {
            this.id = dependent.getId();
            this.name = dependent.getName();
            this.age = dependent.getAge();
            this.allowanceAmount = dependent.getAllowanceAmount();
            return this;
        }

        public Dependent build() {
            return new Dependent(this);
        }
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public float getAllowanceAmount() {
        return allowanceAmount;
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
        if (!(object instanceof Dependent)) {
            return false;
        }
        Dependent other = (Dependent) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "philaman.cput.cardealer.domain.Dependent[ id=" + id + " ]";
    }

}
