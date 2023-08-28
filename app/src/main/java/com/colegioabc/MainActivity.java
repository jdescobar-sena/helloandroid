package com.colegioabc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.colegioabc.interfaces.EstudianteApi;
import com.colegioabc.model.Estudiante;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    EditText nmrCodigo, txtNombre, txtApellido, nmrEdad, txtContrasena;
    Button btnRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nmrCodigo = findViewById(R.id.nmrCodigo);
        txtNombre = findViewById(R.id.txtNombre);
        txtApellido = findViewById(R.id.txtApellido);
        nmrEdad = findViewById(R.id.nmrEdad);
        txtContrasena = findViewById(R.id.txtContrasena);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrarEstudiante();
            }
        });
    }

    public void registrarEstudiante() {
        // en la interface se llama a registrarDatos pero acá se llama registrarEstudiante, cuidado si hay error...
        int codigo = Integer.parseInt(nmrCodigo.getText().toString());
        String nombre = txtNombre.getText().toString();
        String apellido = txtApellido.getText().toString();
        int edad = Integer.parseInt(nmrEdad.getText().toString());
        String contrasena = txtContrasena.getText().toString();

        Estudiante e = new Estudiante( codigo, nombre, apellido, edad, contrasena );

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://10.0.2.2:8080/").addConverterFactory(GsonConverterFactory.create()).build();

        EstudianteApi estudianteApi = retrofit.create(EstudianteApi.class);
        Call<Estudiante> call = estudianteApi.registrarDatos(e);

        call.enqueue(new Callback<Estudiante>() {
            @Override
            public void onResponse(Call<Estudiante> call, Response<Estudiante> response) {
                Toast.makeText(MainActivity.this, "Registrado", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Estudiante> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });

    }
}