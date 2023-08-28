package com.colegioabc.interfaces;

import com.colegioabc.model.Estudiante;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface EstudianteApi {
    @POST("api/estudiantes")
    Call<Estudiante> registrarDatos(@Body Estudiante e);


}
