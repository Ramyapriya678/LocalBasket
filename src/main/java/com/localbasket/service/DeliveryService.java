package com.localbasket.service;

import java.util.List;

import com.localbasket.dto.DeliveryRequestDTO;
import com.localbasket.dto.DeliveryResponseDTO;

public interface DeliveryService {

    // Assign a delivery partner to an order
    DeliveryResponseDTO assignDelivery(DeliveryRequestDTO request);

    // Get delivery by Delivery ID
    DeliveryResponseDTO getDeliveryById(Long id);

    // Get delivery by Order ID
    DeliveryResponseDTO getDeliveryByOrderId(Long orderId);

    // Get all deliveries
    List<DeliveryResponseDTO> getAllDeliveries();

    // Get all deliveries of a delivery partner
    List<DeliveryResponseDTO> getDeliveriesByPartner(Long deliveryPartnerId);

    // Update delivery status
    DeliveryResponseDTO updateDeliveryStatus(Long id, String status);

    // Update current delivery location
    DeliveryResponseDTO updateLocation(
            Long id,
            Double latitude,
            Double longitude);

    // Delete delivery
    void deleteDelivery(Long id);
}