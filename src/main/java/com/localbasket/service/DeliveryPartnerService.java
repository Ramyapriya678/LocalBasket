package com.localbasket.service;


import java.util.List;

import com.localbasket.dto.DeliveryPartnerResponseDTO;
import com.localbasket.entity.DeliveryPartner;


public interface DeliveryPartnerService {


    List<DeliveryPartnerResponseDTO> getAllDeliveryPartners();


    DeliveryPartner getDeliveryPartnerById(Long id);

}