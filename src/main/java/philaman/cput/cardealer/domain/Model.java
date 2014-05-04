/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package philaman.cput.cardealer.domain;

import java.io.Serializable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author phila
 */
@Entity
public class Model implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Embedded
    private Colour colour;
    private String modelName;
    private String modelDescr;
    private String newFeatures;
    private int topSpeeed;
    private int releaseYear;
    private String make;
    private String bodyType;
    private double price;
    private boolean onSale;

    private Model() {
    }

    private Model(Builder build) {
        this.id = build.id;
        this.modelName = build.modelName;
        this.modelDescr = build.modelDescr;
        this.newFeatures = build.newFeatures;
        this.topSpeeed = build.topSpeeed;
        this.releaseYear = build.releaseYear;
        this.make = build.make;
        this.colour = build.colour;
        this.bodyType = build.bodyType;
        this.price = build.price;
        this.onSale = build.onSale;

    }

    public static class Builder {

        private Long id;
        private String modelName;
        private String modelDescr;
        private String newFeatures;
        private int topSpeeed;
        private int releaseYear;
        private String make;
        private Colour colour;
        private String bodyType;
        private double price;
        private boolean onSale;

        public Builder(String modelName) {
            this.modelName = modelName;
        }

        public Builder id(Long value) {
            id = value;
            return this;
        }

        public Builder onSale(boolean value) {
            onSale = value;
            return this;
        }

        public Builder colour(Colour value) {
            colour = value;
            return this;
        }

        public Builder modelDescr(String value) {
            modelDescr = value;
            return this;
        }

        public Builder bodyType(String value) {
            bodyType = value;
            return this;
        }

        public Builder newFeatures(String value) {
            newFeatures = value;
            return this;
        }

        public Builder price(double value) {
            price = value;
            return this;
        }

        public Builder topSpeeed(int value) {
            topSpeeed = value;
            return this;
        }

        public Builder releaseYear(int value) {
            releaseYear = value;
            return this;
        }

        public Builder make(String value) {
            make = value;
            return this;
        }

        public Model build() {
            return new Model(this);
        }

        public Builder model(Model model) {
            this.id = model.getId();
            this.modelName = model.getModelName();
            this.modelDescr = model.getModelDescr();
            this.newFeatures = model.getNewFeatures();
            this.topSpeeed = model.getTopSpeeed();
            this.releaseYear = model.getReleaseYear();
            this.make = model.getMake();
            this.bodyType = model.getBodyType();
            this.price = model.getPrice();
            this.onSale = model.isOnSale();
            return this;
        }
    }

    public Long getId() {
        return id;
    }

    public String getModelName() {
        return modelName;
    }

    public boolean isOnSale() {
        return onSale;
    }

    public double getPrice() {
        return price;
    }

    public String getModelDescr() {
        return modelDescr;
    }

    public String getNewFeatures() {
        return newFeatures;
    }

    public Colour getColour() {
        return colour;
    }

    public int getTopSpeeed() {
        return topSpeeed;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public String getMake() {
        return make;
    }

    public String getBodyType() {
        return bodyType;
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
        if (!(object instanceof Model)) {
            return false;
        }
        Model other = (Model) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "philaman.cput.cardealer.domain.Model[ id=" + id + " ]";
    }

}
