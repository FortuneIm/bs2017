package com.example.yiseo.bs2017;

import android.graphics.Bitmap;

public class Book {
    private String title;
    private String link;
    private String imag;
    private String author;
    private String price;
    private String discount;
    private String publisher;
    private String pubdate;
    private String isbn;
    private String description;

    Book() {

    }
    Book(Book book) {
        this.author = book.getAuthor();
        this.title = book.getTitle();
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getLink() {
        return link;
    }
    public void setLink(String link) {
        this.link = link;
    }
    public String getImag() {
        return imag;
    }
    public void setImag(String imag) {
        this.imag = imag;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }
    public String getDiscount() {
        return discount;
    }
    public void setDiscount(String discount) {
        this.discount = discount;
    }
    public String getPublisher() {
        return publisher;
    }
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
    public String getPubdate() {
        return pubdate;
    }
    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }
    public String getIsbn() {
        return isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    @Override
    public String toString() {
        return "Book [title=" + title + ", link=" + link + ", imag=" + imag + ", author=" + author + ", price=" + price
                + ", discount=" + discount + ", publisher=" + publisher + ", pubdate=" + pubdate + ", isbn=" + isbn
                + ", description=" + description + "]";
    }

}