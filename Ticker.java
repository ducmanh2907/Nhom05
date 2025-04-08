package com.example.esport.model;

public class Ticker {
    private Long productId;
    private String productName;
    private String beginAt;
    private String customerName;
    private String customerEmail;
    private Integer seat;  // Seat là Integer

    // Constructor
    public Ticker(Long productId, String productName, String beginAt,
                  String customerName, String customerEmail, Integer seat) {
        this.productId = productId;
        this.productName = productName;
        this.beginAt = beginAt;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.seat = seat;
    }

    // Getter và Setter cho tất cả các trường
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getBeginAt() {
        return beginAt;
    }

    public void setBeginAt(String beginAt) {
        this.beginAt = beginAt;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public Integer getSeat() {
        return seat;
    }

    public void setSeat(Integer seat) {
        this.seat = seat;
    }
}
