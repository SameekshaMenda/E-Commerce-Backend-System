package com.ecommerce.dto;


import jakarta.validation.constraints.*; 
import lombok.Data;

@Data public class CartItemRequest { @Min(1) private int quantity; }