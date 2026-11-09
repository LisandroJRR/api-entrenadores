package com.upiiz.entrenadores.dto;

import com.upiiz.entrenadores.model.Rol;
import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String password;
    private Rol rol;
}