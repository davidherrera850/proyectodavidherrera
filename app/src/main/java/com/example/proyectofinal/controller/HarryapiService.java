package com.example.proyectofinal.controller;

import com.example.proyectofinal.models.Harry;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface HarryapiService {
    //Metodo encargado de buscar un personaje por id
    @GET("personajes/{id}")
    //Metodo encardado de consumir la operacion
    public Call<Harry> find(@Path("id")String id);

}
