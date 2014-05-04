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
public class Employment implements Serializable {

    private String employeeNumber;
    private String employer;
    private String position;
    private String EmploymentPeriod;
    private double salary;

    private Employment() {
    }

    private Employment(Builder build) {
        this.employer = build.employer;
        this.position = build.position;
        this.EmploymentPeriod = build.EmploymentPeriod;
        this.salary = build.salary;
    }

    public static class Builder {

        private String employeeNumber;
        private String employer;
        private String position;
        private String EmploymentPeriod;
        private double salary;

        public Builder(double salary) {
            this.salary = salary;
        }

        public Builder employer(String value) {
            employer = value;
            return this;
        }

        public Builder position(String value) {
            position = value;
            return this;
        }

        public Builder employeeNumber(String value) {
            employeeNumber = value;
            return this;
        }

        public Builder employmentPeriod(String value) {
            EmploymentPeriod = value;
            return this;
        }

        public Builder employmentInformation(Employment employmentInformation) {
            this.employer = employmentInformation.getEmployer();
            this.position = employmentInformation.getPosition();
            this.EmploymentPeriod = employmentInformation.getEmploymentPeriod();
            this.salary = employmentInformation.getSalary();
            this.employeeNumber = employmentInformation.getEmployeeNumber();
            return this;
        }

        public Employment build() {
            return new Employment(this);
        }
    }

    public String getEmployer() {
        return employer;
    }

    public String getPosition() {
        return position;
    }

    public String getEmploymentPeriod() {
        return EmploymentPeriod;
    }

    public double getSalary() {
        return salary;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

}
