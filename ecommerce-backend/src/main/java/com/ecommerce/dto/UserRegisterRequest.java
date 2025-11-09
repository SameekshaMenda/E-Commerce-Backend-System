package com.ecommerce.dto;
import com.ecommerce.entity.Role;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRegisterRequest {
    private String name;
    private String email;
    private String password;
    private Role role;
}

