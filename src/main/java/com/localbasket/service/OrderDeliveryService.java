package com.localbasket.service;

import java.util.List;

import com.localbasket.dto.OrderDeliveryRequestDTO;
import com.localbasket.dto.OrderDeliveryResponseDTO;

public interface OrderDeliveryService {

    OrderDeliveryResponseDTO assignDeliveryPartner(OrderDeliveryRequestDTO request);

    OrderDeliveryResponseDTO getDeliveryById(Long id);

    OrderDeliveryResponseDTO getDeliveryByOrderId(Long orderId);

    List<OrderDeliveryResponseDTO> getAllDeliveries();

    OrderDeliveryResponseDTO updateDeliveryStatus(Long id, String status);

    void deleteDelivery(Long id);
}