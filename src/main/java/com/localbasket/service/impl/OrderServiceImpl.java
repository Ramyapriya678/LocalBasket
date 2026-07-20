package com.localbasket.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.localbasket.entity.Address;
import com.localbasket.entity.Cart;
import com.localbasket.entity.CartItem;
import com.localbasket.entity.Order;
import com.localbasket.entity.OrderItem;
import com.localbasket.entity.Store;
import com.localbasket.entity.StoreProduct;
import com.localbasket.entity.User;

import com.localbasket.repository.AddressRepository;
import com.localbasket.repository.CartRepository;
import com.localbasket.repository.OrderRepository;
import com.localbasket.repository.StoreProductRepository;
import com.localbasket.repository.UserRepository;

import com.localbasket.service.OrderService;


@Service
public class OrderServiceImpl implements OrderService {


    @Autowired
    private OrderRepository orderRepository;


    @Autowired
    private StoreProductRepository storeProductRepository;


    @Autowired
    private UserRepository userRepository;


    @Autowired
    private AddressRepository addressRepository;


    @Autowired
    private CartRepository cartRepository;



    




    // Cart -> Order
    @Override
    public Order placeOrder(Long userId, Long addressId) {


        User user =
                userRepository.findById(userId)
                .orElseThrow(() ->
                new RuntimeException("User not found"));



        Address address =
                addressRepository.findById(addressId)
                .orElseThrow(() ->
                new RuntimeException("Address not found"));



        Cart cart = cartRepository.findByUserId(userId);

        if (cart == null) {
            throw new RuntimeException("Cart not found");
        }



        if(cart.getCartItems().isEmpty()) {

            throw new RuntimeException("Cart is empty");
        }



        Store store =
                cart.getCartItems()
                .get(0)
                .getStoreProduct()
                .getStore();



        Order order = new Order();


        order.setUser(user);

        order.setAddress(address);

        order.setStore(store);

        order.setStatus("PLACED");

        order.setOrderDate(LocalDateTime.now());



        double totalAmount = 0;



        List<OrderItem> orderItems =
                new ArrayList<>();



        for(CartItem cartItem : cart.getCartItems()) {


            OrderItem orderItem =
                    new OrderItem();


            orderItem.setOrder(order);


            orderItem.setStoreProduct(
                    cartItem.getStoreProduct()
            );


            orderItem.setQuantity(
                    cartItem.getQuantity()
            );


            orderItem.setPrice(
                    cartItem.getPrice().doubleValue()
            );


            orderItem.setSubtotal(
                    cartItem.getSubtotal()
            );



            totalAmount +=
                    cartItem.getSubtotal()
                    .doubleValue();



            orderItems.add(orderItem);
        }



        order.setOrderItems(orderItems);


        order.setTotalAmount(totalAmount);



        Order savedOrder =
                orderRepository.save(order);



        // Clear cart after successful order

        cart.getCartItems().clear();

        cart.setTotalAmount(BigDecimal.ZERO);


        cartRepository.save(cart);



        return savedOrder;
    }




    @Override
    public Order getOrderById(Long id) {

        return orderRepository.findById(id)
                .orElseThrow(() ->
                new RuntimeException("Order not found"));
    }




    @Override
    public List<Order> getAllOrders() {

        return orderRepository.findAll();
    }




    @Override
    public List<Order> getOrdersByUser(Long userId) {

        return orderRepository.findByUserId(userId);
    }




    @Override
    public Order updateOrderStatus(Long orderId, String status) {


        Order order =
                getOrderById(orderId);


        order.setStatus(status);


        return orderRepository.save(order);
    }




    @Override
    public void deleteOrder(Long id) {

        orderRepository.deleteById(id);
    }

}