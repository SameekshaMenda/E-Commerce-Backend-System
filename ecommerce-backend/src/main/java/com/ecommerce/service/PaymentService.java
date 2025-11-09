package com.ecommerce.service;

import com.ecommerce.entity.PaymentStatus; 
import org.springframework.stereotype.Service;
import java.util.concurrent.ThreadLocalRandom;


@Service
public class PaymentService {
  public PaymentStatus simulate(String mode, double amount){

    return ThreadLocalRandom.current().nextDouble() < 0.85 ? PaymentStatus.SUCCESS : PaymentStatus.FAILED;
  }
}
