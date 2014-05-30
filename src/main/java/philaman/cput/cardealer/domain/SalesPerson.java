/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package philaman.cput.cardealer.domain;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author phila
 */
@Entity
public class SalesPerson implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Embedded
    private Commission comm;
    private String firstname;
    private String lastname;
    private double basicSalary;
    private double salary;
    private int age;
    @OneToOne
    private SalesGrade grade;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Department department;
    private String hireDate;

    private SalesPerson() {
    }

    private SalesPerson(Builder build) {
        this.id = build.id;
        this.firstname = build.firstname;
        this.lastname = build.lastname;
        this.age = build.age;
        this.hireDate = build.hireDate;
        this.basicSalary = build.basicSalary;
        this.salary = build.salary;
        this.comm = build.comm;
        this.grade = build.grade;
        this.department = build.department;
    }

    public static class Builder {

        private Long id;
        private Commission comm;
        private String firstname;
        private String lastname;
        private double basicSalary;
        private Department department;
        private double salary;
        private int age;
        private String hireDate;
        private SalesGrade grade;

        public Builder(String firstname) {
            this.firstname = firstname;
        }

        public Builder department(Department value) {
            department = value;
            return this;
        }

        public Builder lastname(String value) {
            lastname = value;
            return this;
        }

        public Builder age(int value) {
            age = value;
            return this;
        }

        public Builder hireDate(String value) {
            hireDate = value;
            return this;
        }

        public Builder salary(double value) {
            salary = value;
            return this;
        }

        public Builder basicSalary(double value) {
            basicSalary = value;
            return this;
        }

        public Builder comm(Commission value) {
            comm = value;
            return this;
        }

        public Builder grade(SalesGrade value) {
            grade = value;
            return this;
        }

        public Builder salesPerson(SalesPerson salesPerson) {
            this.comm = salesPerson.getComm();
            this.id = salesPerson.getId();
            this.firstname = salesPerson.getFirstname();
            this.lastname = salesPerson.getLastname();
            this.age = salesPerson.getAge();
            this.hireDate = salesPerson.getHireDate();
            this.salary = salesPerson.getSalary();
            this.grade = salesPerson.getGrade();
            this.department = salesPerson.getDepartment();
            return this;
        }

        public SalesPerson build() {
            return new SalesPerson(this);
        }
    }

    public int getAge() {
        return age;
    }

    public Department getDepartment() {
        return department;
    }

    public String getHireDate() {
        return hireDate;
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

    public double getBasicSalary() {
        return basicSalary;
    }

    public double getSalary() {
        return salary;
    }

    public Commission getComm() {
        return comm;
    }

    public SalesGrade getGrade() {
        return grade;
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
        if (!(object instanceof SalesPerson)) {
            return false;
        }
        SalesPerson other = (SalesPerson) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "philaman.cput.cardealer.domain.SalesPerson[ id=" + id + " ]";
    }

}
