package com.localbasket.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.localbasket.dto.OrderDeliveryRequestDTO;
import com.localbasket.dto.OrderDeliveryResponseDTO;
import com.localbasket.entity.DeliveryPartner;
import com.localbasket.entity.Order;
import com.localbasket.entity.OrderDelivery;
import com.localbasket.enums.DeliveryStatus;
import com.localbasket.repository.DeliveryPartnerRepository;
import com.localbasket.repository.OrderDeliveryRepository;
import com.localbasket.repository.OrderRepository;
import com.localbasket.service.OrderDeliveryService;

@Service
public class OrderDeliveryServiceImpl implements OrderDeliveryService {

    @Autowired
    private OrderDeliveryRepository orderDeliveryRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private DeliveryPartnerRepository deliveryPartnerRepository;

    @Override
    public OrderDeliveryResponseDTO assignDeliveryPartner(OrderDeliveryRequestDTO request) {

        Order order = orderRepository.findById(request.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found"));

        DeliveryPartner partner = deliveryPartnerRepository.findById(request.getDeliveryPartnerId())
                .orElseThrow(() -> new RuntimeException("Delivery Partner not found"));

        if (orderDeliveryRepository.findByOrderId(order.getId()).isPresent()) {
            throw new RuntimeException("Delivery already assigned for this order");
        }

        OrderDelivery delivery = new OrderDelivery();
        delivery.setOrder(order);
        delivery.setDeliveryPartner(partner);
        delivery.setDeliveryStatus(DeliveryStatus.ASSIGNED);
        delivery.setAssignedAt(LocalDateTime.now());

        OrderDelivery saved = orderDeliveryRepository.save(delivery);

        return mapToDTO(saved);
    }

    @Override
    public OrderDeliveryResponseDTO getDeliveryById(Long id) {

        OrderDelivery delivery = orderDeliveryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Delivery not found"));

        return mapToDTO(delivery);
    }

    @Override
    public OrderDeliveryResponseDTO getDeliveryByOrderId(Long orderId) {

        OrderDelivery delivery = orderDeliveryRepository.findByOrderId(orderId)
                .orElseThrow(() -> new RuntimeException("Delivery not found"));

        return mapToDTO(delivery);
    }

    @Override
    public List<OrderDeliveryResponseDTO> getAllDeliveries() {

        return orderDeliveryRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDeliveryResponseDTO updateDeliveryStatus(Long id, String status) {

        OrderDelivery delivery = orderDeliveryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Delivery not found"));

        DeliveryStatus deliveryStatus = DeliveryStatus.valueOf(status.toUpperCase());

        delivery.setDeliveryStatus(deliveryStatus);

        if (deliveryStatus == DeliveryStatus.PICKED_UP) {
            delivery.setPickedUpAt(LocalDateTime.now());
        }

        if (deliveryStatus == DeliveryStatus.DELIVERED) {
            delivery.setDeliveredAt(LocalDateTime.now());
        }

        OrderDelivery updated = orderDeliveryRepository.save(delivery);

        return mapToDTO(updated);
    }

    @Override
    public void deleteDelivery(Long id) {

        OrderDelivery delivery = orderDeliveryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Delivery not found"));

        orderDeliveryRepository.delete(delivery);
    }

    private OrderDeliveryResponseDTO mapToDTO(OrderDelivery delivery) {

        OrderDeliveryResponseDTO dto = new OrderDeliveryResponseDTO();

        dto.setId(delivery.getId());
        dto.setOrderId(delivery.getOrder().getId());
        dto.setDeliveryPartnerId(delivery.getDeliveryPartner().getId());
        dto.setDeliveryStatus(delivery.getDeliveryStatus());
        dto.setAssignedAt(delivery.getAssignedAt());
        dto.setPickedUpAt(delivery.getPickedUpAt());
        dto.setDeliveredAt(delivery.getDeliveredAt());

        return dto;
    }
}