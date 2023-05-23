package com.auctix.auctx.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class UsersDto {

    private Long id;

    private String email;

    private String username;

    private String password;

    private String role;
}
