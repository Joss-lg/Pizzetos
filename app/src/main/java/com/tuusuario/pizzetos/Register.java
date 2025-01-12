package com.tuusuario.pizzetos;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {
    EditText nombre, correo, contrasena;
    Button btnRegistro;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register); // Asegúrate de usar el nombre correcto del archivo XML

        this.setTitle("Registro");

        // Inicializar FirebaseAuth
        mAuth = FirebaseAuth.getInstance();

        // Asignar elementos del diseño a las variables
        nombre = findViewById(R.id.nombre);
        correo = findViewById(R.id.correo);
        contrasena = findViewById(R.id.contrasena);
        btnRegistro = findViewById(R.id.btn_registro);

        // Configurar el botón de registro
        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombreUser = nombre.getText().toString().trim();
                String emailUser = correo.getText().toString().trim();
                String passUser = contrasena.getText().toString().trim();

                if (nombreUser.isEmpty() || emailUser.isEmpty() || passUser.isEmpty()) {
                    Toast.makeText(Register.this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
                } else if (passUser.length() < 6) {
                    Toast.makeText(Register.this, "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show();
                } else {
                    registrarUsuario(emailUser, passUser);
                }
            }
        });
    }

    private void registrarUsuario(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Register.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Register.this, LoginActivity.class)); // Redirigir al Login
                            finish();
                        } else {
                            Log.e("RegisterActivity", "Error: " + task.getException());
                            Toast.makeText(Register.this, "Error al registrarse", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
