package com.example.cookie.model;

import lombok.*;

import java.util.UUID;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class UserDto {
    private String id; // cookie token
    private String name;
    private String password;
}