package com.ecommerce.controller;

import com.ecommerce.dto.CartItemRequest;
import com.ecommerce.entity.Cart; 
import com.ecommerce.service.CartService;

import jakarta.validation.Valid; import lombok.RequiredArgsConstructor; import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/cart") @RequiredArgsConstructor

public class CartController {
	
  private final CartService service;
  
  @PostMapping("/add/{productId}") public Cart add(@RequestParam Long userId, @PathVariable Long productId, @Valid @RequestBody CartItemRequest req){ return service.add(userId, productId, req); }
  @PutMapping("/update/{productId}") public Cart update(@RequestParam Long userId, @PathVariable Long productId, @Valid @RequestBody CartItemRequest req){ return service.update(userId, productId, req); }
  @DeleteMapping("/remove/{productId}") public Cart remove(@RequestParam Long userId, @PathVariable Long productId){ return service.remove(userId, productId); }
  @GetMapping public Cart view(@RequestParam Long userId){ return service.view(userId); 
  
  }
}
