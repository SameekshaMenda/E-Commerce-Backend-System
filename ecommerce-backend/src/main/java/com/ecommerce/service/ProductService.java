package com.ecommerce.service;

import com.ecommerce.dto.ProductDTO; 
import com.ecommerce.entity.Product; 
import com.ecommerce.exception.NotFoundException;
import com.ecommerce.repository.ProductRepository; 
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*; 


import org.springframework.stereotype.Service;
@Service @RequiredArgsConstructor
public class ProductService {
  private final ProductRepository repo;

  public Product create(ProductDTO d){ return repo.save(toEntity(d)); }
  
  
  public Product update(Long id, ProductDTO d){
    Product p = get(id);
    
    p.setName(d.getName()); p.setDescription(d.getDescription()); p.setPrice(d.getPrice());
    
    p.setStock(d.getStock()); p.setCategory(d.getCategory()); p.setImageUrl(d.getImageUrl()); p.setRating(d.getRating());
    return repo.save(p);
  }
  public void delete(Long id){ repo.delete(get(id)); }
  public Product get(Long id){ return repo.findById(id).orElseThrow(() -> new NotFoundException("Product")); }

  public Page<Product> list(int page, int size, String category, Double minPrice, Double maxPrice){
    Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
    Page<Product> all = repo.findAll(pageable);
    return all.map(p -> p); 
  }

  private Product toEntity(ProductDTO d){
	  
    return Product.builder().name(d.getName()).description(d.getDescription())
      .price(d.getPrice()).stock(d.getStock()).category(d.getCategory())
      .imageUrl(d.getImageUrl()).rating(d.getRating()).build();
  }
}
