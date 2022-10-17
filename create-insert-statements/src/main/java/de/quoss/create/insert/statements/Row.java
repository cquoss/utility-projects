package de.quoss.create.insert.statements;

import java.util.Date;
import java.util.Objects;

public class Row {
    
    private String surname;
    
    private String givenName;
    
    private Date dateOfBirth;

    private String postalCode;

    public Row(final String surname, final String givenName, final Date dateOfBirth, final String postalCode) {
        this.surname = surname;
        this.givenName = givenName;
        this.dateOfBirth = dateOfBirth;
        this.postalCode = postalCode;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Row row = (Row) o;
        return Objects.equals(surname, row.surname) && Objects.equals(givenName, row.givenName) && Objects.equals(dateOfBirth, row.dateOfBirth) && Objects.equals(postalCode, row.postalCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(surname, givenName, dateOfBirth, postalCode);
    }

    @Override
    public String toString() {
        return "Row ["
                + "surname=" + surname
                + ",givenName=" + givenName
                + ",dateOfBirth=" + dateOfBirth
                + ",postalCode=" + postalCode
                + "]";
    }
}
