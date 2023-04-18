package com.example.calendaapp.interfaces;

import com.example.calendaapp.dto.EmpleadoDto;
import com.example.calendaapp.model.Empleado;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CRUDInterface {

    @GET("/consultarAll") // working
    Call<List<Empleado>> getAll();

    @GET("/consultar/{id}")  // working
    Call<Empleado> getOne(@Path("id") int id);

    @POST("/guardar") // working
    Call<Empleado> create(@Body EmpleadoDto dto);

    @PUT("/actualizar/{id}") // on it
    Call<Empleado> edit(@Path("id") int id, @Body EmpleadoDto dto);

    @DELETE("/user/{id}") // working, set  Jsonreader.setLenient = true
    Call<Empleado> delete(@Path("id") int id);
}
