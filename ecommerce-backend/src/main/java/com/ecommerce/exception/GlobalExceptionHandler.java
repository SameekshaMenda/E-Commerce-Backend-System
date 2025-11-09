package com.ecommerce.exception;

import org.springframework.http.*; 
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.util.*;


@RestControllerAdvice
public class GlobalExceptionHandler {
	
	
  @ExceptionHandler(NotFoundException.class) public ResponseEntity<?> nf(NotFoundException e){ return ResponseEntity.status(404).body(Map.of("error",e.getMessage())); }
  @ExceptionHandler(BadRequestException.class) public ResponseEntity<?> br(BadRequestException e){ return ResponseEntity.badRequest().body(Map.of("error",e.getMessage())); }
  @ExceptionHandler(MethodArgumentNotValidException.class)
  
  
  public ResponseEntity<?> mv(MethodArgumentNotValidException e)
  {
    var errs = e.getBindingResult().getFieldErrors().stream().collect(java.util.stream.Collectors.toMap(f->f.getField(), f->f.getDefaultMessage(), (a,b)->a));
    return ResponseEntity.badRequest().body(Map.of("errors", errs));
  }
  @ExceptionHandler(IllegalArgumentException.class) public ResponseEntity<?> iae(IllegalArgumentException e){ return ResponseEntity.badRequest().body(Map.of("error",e.getMessage())); }
}
