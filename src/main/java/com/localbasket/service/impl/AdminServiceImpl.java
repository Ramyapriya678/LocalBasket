package com.localbasket.service.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.localbasket.dto.DashboardResponseDTO;
import com.localbasket.entity.Order;
import com.localbasket.repository.CategoryRepository;
import com.localbasket.repository.DeliveryRepository;
import com.localbasket.repository.OrderRepository;
import com.localbasket.repository.PaymentRepository;
import com.localbasket.repository.ProductRepository;
import com.localbasket.repository.StoreRepository;
import com.localbasket.repository.UserRepository;
import com.localbasket.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Override
    public DashboardResponseDTO getDashboard() {

        DashboardResponseDTO dto = new DashboardResponseDTO();

        dto.setTotalUsers(userRepository.count());
        dto.setTotalStores(storeRepository.count());
        dto.setTotalProducts(productRepository.count());
        dto.setTotalOrders(orderRepository.count());
        dto.setTotalCategories(categoryRepository.count());
        dto.setTotalPayments(paymentRepository.count());
        dto.setTotalDeliveries(deliveryRepository.count());

        Double totalRevenue = orderRepository.findAll()
                .stream()
                .mapToDouble(Order::getTotalAmount)
                .sum();

        dto.setTotalRevenue(BigDecimal.valueOf(totalRevenue));

        return dto;
    }
}