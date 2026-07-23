package com.localbasket.dto;


public class DeliveryPartnerResponseDTO {


    private Long id;

    private String name;

    private String email;

    private String vehicleNumber;

    private Boolean available;



    public DeliveryPartnerResponseDTO(
            Long id,
            String name,
            String email,
            String vehicleNumber,
            Boolean available) {

        this.id = id;
        this.name = name;
        this.email = email;
        this.vehicleNumber = vehicleNumber;
        this.available = available;

    }



    public Long getId() {
        return id;
    }


    public String getName() {
        return name;
    }


    public String getEmail() {
        return email;
    }


    public String getVehicleNumber() {
        return vehicleNumber;
    }


    public Boolean getAvailable() {
        return available;
    }

}