/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package philaman.cput.cardealer.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author phila
 */
@Embeddable
public class Contact implements Serializable {

    private String phone;
    private String cell;
    @Column(unique = true)
    private String email;
    private String fax;

    private Contact() {
    }

    public Contact(Builder build) {
        this.phone = build.phone;
        this.cell = build.cell;
        this.email = build.email;
        this.fax = build.fax;
    }

    public static class Builder {

        private String phone;
        private String cell;
        private String email;
        private String fax;

        public Builder(String fax) {
            this.fax = fax;
        }

        public Builder phone(String value) {
            phone = value;
            return this;
        }

        public Builder cell(String value) {
            cell = value;
            return this;
        }

        public Builder email(String value) {
            email = value;
            return this;
        }

        public Builder(Contact contact) {
            this.phone = contact.getPhone();
            this.cell = contact.getCell();
            this.email = contact.getEmail();
            this.fax = contact.getAddress();
        }

        public Contact build() {
            return new Contact(this);
        }
    }

    public String getPhone() {
        return phone;
    }

    public String getCell() {
        return cell;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return fax;
    }

}
