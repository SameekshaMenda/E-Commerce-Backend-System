package com.ecommerce.service;

import com.ecommerce.entity.*;
import com.ecommerce.exception.NotFoundException;
import com.ecommerce.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepo;
    private final CartRepository cartRepo;
    private final UserRepository userRepo;
    private final ProductRepository productRepo;
    private final OrderItemRepository orderItemRepo;

    @Transactional
    public Order checkout(Long userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));
        Cart cart = cartRepo.findByUser(user)
                .orElseThrow(() -> new NotFoundException("Cart not found"));

        if (cart.getItems() == null || cart.getItems().isEmpty()) {
            throw new IllegalStateException("Cart is empty! Add products before checkout.");
        }

        Order order = Order.builder()
                .user(user)
                .orderDate(Instant.now())
                .paymentStatus(PaymentStatus.SUCCESS)
                .orderStatus(OrderStatus.PLACED)
                .totalAmount(0.0)
                .build();

        double total = 0.0;

        for (CartItem ci : cart.getItems()) {
            Product product = ci.getProduct();
            if (product == null) throw new NotFoundException("Product missing in cart item!");

            double lineTotal = product.getPrice() * ci.getQuantity();
            total += lineTotal;


            if (product.getStock() < ci.getQuantity()) {
                throw new IllegalStateException("Not enough stock for " + product.getName());
            }
            product.setStock(product.getStock() - ci.getQuantity());
            productRepo.save(product);

            OrderItem oi = OrderItem.builder()
                    .order(order)
                    .product(product)
                    .quantity(ci.getQuantity())
                    .price(lineTotal)
                    .build();

            orderItemRepo.save(oi);
        }

        order.setTotalAmount(total);
        orderRepo.save(order);

        cart.getItems().clear();
        cart.setTotalPrice(0.0);
        cartRepo.save(cart);

        return order;
    }

    public List<Order> listForUser(Long userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));
        return orderRepo.findByUser(user);
    }

    public Order get(Long orderId) {
        return orderRepo.findById(orderId)
                .orElseThrow(() -> new NotFoundException("Order not found"));
    }

    @Transactional
    public Order updateStatus(Long orderId, OrderStatus status) {
        Order order = orderRepo.findById(orderId)
                .orElseThrow(() -> new NotFoundException("Order not found"));
        order.setOrderStatus(status);
        return orderRepo.save(order);
    }
}
