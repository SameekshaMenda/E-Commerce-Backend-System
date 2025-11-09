package com.ecommerce.service;

import com.ecommerce.dto.CartItemRequest;
import com.ecommerce.entity.*;
import com.ecommerce.exception.NotFoundException;
import com.ecommerce.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepo;
    private final ProductRepository productRepo;
    private final UserRepository userRepo;

    private Cart getCartForUser(Long userId) {
        User u = userRepo.findById(userId)
                .orElseThrow(() -> new NotFoundException("User"));
        return cartRepo.findByUser(u)
                .orElseGet(() -> cartRepo.save(Cart.builder()
                        .user(u)
                        .totalPrice(0.0)
                        .build()));
    }

    @Transactional
    public Cart add(Long userId, Long productId, CartItemRequest req) {
        Cart cart = getCartForUser(userId);
        Product p = productRepo.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product"));

        CartItem item = cart.getItems().stream()
                .filter(i -> i.getProduct().getId().equals(productId))
                .findFirst()
                .orElseGet(() -> {
                    CartItem ci = CartItem.builder()
                            .cart(cart)
                            .product(p)
                            .quantity(0)
                            .priceAtAdd(p.getPrice().doubleValue())
                            .build();
                    cart.getItems().add(ci);
                    return ci;
                });

        item.setQuantity(item.getQuantity() + req.getQuantity());

        recalcTotal(cart);

        return cartRepo.save(cart);
    }

    @Transactional
    public Cart update(Long userId, Long productId, CartItemRequest req) {
        Cart cart = getCartForUser(userId);
        CartItem item = cart.getItems().stream()
                .filter(i -> i.getProduct().getId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Item not in cart"));

        item.setQuantity(req.getQuantity());
        if (item.getQuantity() <= 0)
            cart.getItems().remove(item);


        recalcTotal(cart);

        return cartRepo.save(cart);
    }

    @Transactional
    public Cart remove(Long userId, Long productId) {
        Cart cart = getCartForUser(userId);
        if (cart.getItems() != null) {
            cart.getItems().removeIf(i -> i.getProduct().getId().equals(productId));
        }
        cart.setTotalPrice(
            cart.getItems() == null ? 0.0 :
            cart.getItems().stream().mapToDouble(i -> i.getQuantity() * i.getProduct().getPrice()).sum()
        );
        return cartRepo.save(cart);
    }


    public Cart view(Long userId) {
        return getCartForUser(userId);
    }

    private void recalcTotal(Cart cart) {
        double total = cart.getItems().stream()
                .mapToDouble(i -> i.getQuantity() * i.getPriceAtAdd())
                .sum();
        cart.setTotalPrice(total);
    }
}
