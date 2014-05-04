/*
 * To change this template, choose Tools | Templates
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
public class Commission implements Serializable {

    private double carPrice;
    private double saleCommission;

    private Commission() {
    }

    public Commission(Builder Build) {
        this.carPrice = Build.carPrice;
        this.saleCommission = Build.saleCommission;
    }

    public static class Builder {

        private double carPrice;
        private double saleCommission;

        public Builder(double carPrice) {
            this.carPrice = carPrice;
        }

        public Builder SaleCommission(double value) {
            saleCommission = value;
            return this;
        }

        public Builder commission(Commission com) {
            this.carPrice = com.getCarPrice();
            this.saleCommission = com.getSaleCommission();
            return this;
        }

        public Commission build() {
            return new Commission(this);
        }

    }

    public double getCarPrice() {
        return carPrice;
    }

    public double getSaleCommission() {
        return saleCommission;
    }
}
