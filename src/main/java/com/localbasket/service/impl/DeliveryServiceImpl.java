package com.localbasket.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.localbasket.dto.DeliveryRequestDTO;
import com.localbasket.dto.DeliveryResponseDTO;
import com.localbasket.entity.Delivery;
import com.localbasket.entity.DeliveryPartner;
import com.localbasket.entity.Order;
import com.localbasket.enums.DeliveryStatus;
import com.localbasket.repository.DeliveryPartnerRepository;
import com.localbasket.repository.DeliveryRepository;
import com.localbasket.repository.OrderRepository;
import com.localbasket.service.DeliveryService;

@Service
public class DeliveryServiceImpl implements DeliveryService {

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private DeliveryPartnerRepository deliveryPartnerRepository;
    @Override
    public DeliveryResponseDTO assignDelivery(DeliveryRequestDTO request) {

        // Debug
        System.out.println("Order ID: " + request.getOrderId());
        System.out.println("Partner ID: " + request.getDeliveryPartnerId());

        Order order = orderRepository.findById(request.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found"));

        DeliveryPartner partner = deliveryPartnerRepository
                .findById(request.getDeliveryPartnerId())
                .orElseThrow(() -> new RuntimeException("Delivery Partner not found"));

        // Debug
        System.out.println("Order Found: " + order.getId());
        System.out.println("Partner Found: " + partner.getId());

        if (deliveryRepository.findByOrderId(order.getId()).isPresent()) {
            throw new RuntimeException("Delivery already assigned for this order");
        }

        Delivery delivery = new Delivery();

        delivery.setOrder(order);
        delivery.setDeliveryPartner(partner);
        delivery.setStatus(DeliveryStatus.ASSIGNED);
        delivery.setAssignedAt(LocalDateTime.now());

        // Debug
        System.out.println("Saving delivery...");

        Delivery saved = deliveryRepository.save(delivery);

        System.out.println("Delivery Saved Successfully");

        return mapToDTO(saved);
    }


    @Override
    public DeliveryResponseDTO getDeliveryById(Long id) {

        Delivery delivery = deliveryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Delivery not found"));

        return mapToDTO(delivery);
    }

    @Override
    public DeliveryResponseDTO getDeliveryByOrderId(Long orderId) {

        Delivery delivery = deliveryRepository.findByOrderId(orderId)
                .orElseThrow(() -> new RuntimeException("Delivery not found"));

        return mapToDTO(delivery);
    }

    @Override
    public List<DeliveryResponseDTO> getAllDeliveries() {

        return deliveryRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<DeliveryResponseDTO> getDeliveriesByPartner(Long deliveryPartnerId) {

        return deliveryRepository.findByDeliveryPartnerId(deliveryPartnerId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

   
    @Override
    public DeliveryResponseDTO updateLocation(
            Long id,
            Double latitude,
            Double longitude) {

        Delivery delivery = deliveryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Delivery not found"));

        delivery.setCurrentLatitude(latitude);
        delivery.setCurrentLongitude(longitude);

        Delivery updated = deliveryRepository.save(delivery);

        return mapToDTO(updated);
    }
    @Override
    public DeliveryResponseDTO updateDeliveryStatus(Long id, String status) {


        Delivery delivery =
                deliveryRepository.findById(id)
                .orElseThrow(() ->
                new RuntimeException("Delivery not found"));


        DeliveryStatus deliveryStatus =
                DeliveryStatus.valueOf(
                        status.toUpperCase()
                );


        delivery.setStatus(deliveryStatus);



        if(deliveryStatus == DeliveryStatus.PICKED_UP) {

            delivery.setPickedUpAt(
                    LocalDateTime.now()
            );


            // Update Order Status
            delivery.getOrder()
            .setStatus("OUT_FOR_DELIVERY");

        }



        if(deliveryStatus == DeliveryStatus.DELIVERED) {

            delivery.setDeliveredAt(
                    LocalDateTime.now()
            );


            // Update Order Status
            delivery.getOrder()
            .setStatus("DELIVERED");

        }



        // save order changes
        orderRepository.save(
                delivery.getOrder()
        );


        Delivery updated =
                deliveryRepository.save(delivery);


        return mapToDTO(updated);

    }
    @Override
    public void deleteDelivery(Long id) {

        Delivery delivery = deliveryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Delivery not found"));

        deliveryRepository.delete(delivery);
    }

    private DeliveryResponseDTO mapToDTO(Delivery delivery) {

        DeliveryResponseDTO dto = new DeliveryResponseDTO();

        dto.setId(delivery.getId());
        dto.setOrderId(delivery.getOrder().getId());

        if (delivery.getDeliveryPartner() != null) {
            dto.setDeliveryPartnerId(delivery.getDeliveryPartner().getId());
        }

        dto.setStatus(delivery.getStatus());
        dto.setAssignedAt(delivery.getAssignedAt());
        dto.setPickedUpAt(delivery.getPickedUpAt());
        dto.setDeliveredAt(delivery.getDeliveredAt());
        dto.setCurrentLatitude(delivery.getCurrentLatitude());
        dto.setCurrentLongitude(delivery.getCurrentLongitude());

        return dto;
    }
}