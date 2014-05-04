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
public class Colour implements Serializable {

    private String colSection;
    private String color;

    private Colour() {
    }

    private Colour(Builder build) {
        this.colSection = build.colSection;
        this.color = build.color;
    }

    public static class Builder {

        private String colSection;
        private String color;

        public Builder(String colSection) {
            this.colSection = colSection;
        }

        public Builder color(String value) {
            color = value;
            return this;
        }

        public Builder color(Colour colour) {
            this.colSection = colour.getColSection();
            this.color = colour.getColor();
            return this;
        }

        public Colour build() {
            return new Colour(this);
        }
    }

    public String getColSection() {
        return colSection;
    }

    public String getColor() {
        return color;
    }

}
