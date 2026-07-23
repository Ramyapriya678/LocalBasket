package com.localbasket.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.localbasket.dto.DeliveryPartnerResponseDTO;
import com.localbasket.entity.DeliveryPartner;
import com.localbasket.repository.DeliveryPartnerRepository;
import com.localbasket.service.DeliveryPartnerService;

@Service
public class DeliveryPartnerServiceImpl implements DeliveryPartnerService {

    @Autowired
    private DeliveryPartnerRepository deliveryPartnerRepository;

    @Override
    public List<DeliveryPartnerResponseDTO> getAllDeliveryPartners() {

        return deliveryPartnerRepository.findAll()
                .stream()
                .map(dp -> new DeliveryPartnerResponseDTO(
                        dp.getId(),
                        dp.getUser().getFirstName() + " " + dp.getUser().getLastName(),
                        dp.getUser().getEmail(),
                        dp.getVehicleNumber(),
                        dp.getIsAvailable()
                ))
                .toList();
    }

    @Override
    public DeliveryPartner getDeliveryPartnerById(Long id) {

        return deliveryPartnerRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Delivery Partner not found with id : " + id
                        ));
    }
}