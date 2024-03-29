package com.example.calendaapp.dto;

import com.example.calendaapp.model.Empleado;

public class EmpleadoDto {

    private String user;

    private String password;
    private String email;

    public EmpleadoDto() {
    }

    public EmpleadoDto(String user, String password, String email) {

        this.user = user;
        this.password = password;
        this.email = email;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
