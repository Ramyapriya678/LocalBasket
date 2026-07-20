package com.localbasket.entity;

import java.math.BigDecimal;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotBlank(message = "Address line 1 is required")
    @Column(name = "address_line1", nullable = false)
    private String addressLine1;


    @Column(name = "address_line2")
    private String addressLine2;


    @NotBlank(message = "City is required")
    @Column(nullable = false)
    private String city;


    @NotBlank(message = "State is required")
    @Column(nullable = false)
    private String state;


    @NotBlank(message = "Pincode is required")
    @Pattern(
            regexp = "^[0-9]{6}$",
            message = "Pincode must contain exactly 6 digits"
    )
    @Column(nullable = false)
    private String pincode;


    private String country = "India";


    private BigDecimal latitude;


    private BigDecimal longitude;



    public Address() {
    }



    public Address(Long id, String addressLine1, String addressLine2,
                   String city, String state, String pincode,
                   String country, BigDecimal latitude,
                   BigDecimal longitude) {

        this.id = id;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.state = state;
        this.pincode = pincode;
        this.country = country;
        this.latitude = latitude;
        this.longitude = longitude;
    }



    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getAddressLine1() {
        return addressLine1;
    }


    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }


    public String getAddressLine2() {
        return addressLine2;
    }


    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }


    public String getCity() {
        return city;
    }


    public void setCity(String city) {
        this.city = city;
    }


    public String getState() {
        return state;
    }


    public void setState(String state) {
        this.state = state;
    }


    public String getPincode() {
        return pincode;
    }


    public void setPincode(String pincode) {
        this.pincode = pincode;
    }


    public String getCountry() {
        return country;
    }


    public void setCountry(String country) {
        this.country = country;
    }


    public BigDecimal getLatitude() {
        return latitude;
    }


    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }


    public BigDecimal getLongitude() {
        return longitude;
    }


    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }
}