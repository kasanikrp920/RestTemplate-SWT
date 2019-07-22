package com.rest.template.model;


public class Book  extends ExceptionFormat{

    private long ISBN;

    private String authorName;

    private String title;

    private String isAcademic;

    private int totalCount;

    private int available;

    private int isDeleted;


    public Book() {
    }

    public Book(long ISBN, String authorName, String title,   String isAcademic, int totalCount, int available,int isDeleted) {
        this.ISBN = ISBN;
        this.authorName = authorName;
        this.title = title;
        this.isAcademic = isAcademic;
        this.totalCount = totalCount;
        this.available = available;
        this.isDeleted=isDeleted;
    }


    public long getISBN() {
        return ISBN;
    }

    public void setISBN(long ISBN) {
        this.ISBN = ISBN;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsAcademic() {
        return isAcademic;
    }

    public void setIsAcademic(String isAcademic) {
        this.isAcademic = isAcademic;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        return "Book{" +
                "ISBN=" + ISBN +
                ", authorName='" + authorName + '\'' +
                ", title='" + title + '\'' +
                ", isAcademic='" + isAcademic + '\'' +
                ", totalCount=" + totalCount +
                ", available=" + available +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
