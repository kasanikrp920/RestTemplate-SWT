package com.rest.template.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Borrower {


    private long bookIsbn;

    private String firstName;

    private String lastName;

    private String email;

    private int nunOfDaysLoan;

    private String date ;

    private int isDeleted;

    public Borrower() {
    }

    public Borrower(long bookIsbn, String firstName, String lastName, String email, int nunOfDaysLoan, String date, int isDeleted) {
        this.bookIsbn = bookIsbn;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.nunOfDaysLoan = nunOfDaysLoan;
        this.date = date;
        this.isDeleted = isDeleted;
    }

    public long getBookIsbn() {
        return bookIsbn;
    }

    public void setBookIsbn(long bookIsbn) {
        this.bookIsbn = bookIsbn;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getNunOfDaysLoan() {
        return nunOfDaysLoan;
    }

    public void setNunOfDaysLoan(int nunOfDaysLoan) {
        this.nunOfDaysLoan = nunOfDaysLoan;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        return "Borrower{" +
                "bookIsbn=" + bookIsbn +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", nunOfDaysLoan=" + nunOfDaysLoan +
                ", date='" + date + '\'' +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
