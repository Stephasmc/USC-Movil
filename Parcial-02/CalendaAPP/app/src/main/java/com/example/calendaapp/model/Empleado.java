package com.example.calendaapp.model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Empleado implements Serializable {

    private int id;
    private String user;
    private String password;
    private String email;

    public Empleado() {
    }

    public Empleado (int id, String user, String password, String email) {
        this.id = id;
        this.user  = user;
        this.password  = password;
        this.email  = email;
    }

    public int getId() {
      //  System.out.println("This is mt id : " + id);
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return user;
    }

    public void setName(String name) {
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



    @Override
    public String toString() {
        return  "{" +
               "id=" + id +
              ", name='"+user+'\''+
            ", password='"+password+'\''+
                ", email='"+email+'\''+
                "}";
    }
}
