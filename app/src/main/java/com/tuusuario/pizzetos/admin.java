package com.tuusuario.pizzetos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class admin extends AppCompatActivity {

    private EditText adminEmail, adminPassword;
    private Button adminLoginButton;

    // Credenciales del administrador
    private final String ADMIN_EMAIL = "admin@pizzetos.mx";
    private final String ADMIN_PASSWORD = "pizzetos2020";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        setTitle("Admin Login");

        // Enlazar elementos del diseño
        adminEmail = findViewById(R.id.adminEmail);
        adminPassword = findViewById(R.id.adminPassword);
        adminLoginButton = findViewById(R.id.adminLoginButton);

        // Listener para el botón de inicio de sesión
        adminLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailInput = adminEmail.getText().toString().trim();
                String passwordInput = adminPassword.getText().toString().trim();

                if (emailInput.isEmpty() || passwordInput.isEmpty()) {
                    Toast.makeText(admin.this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
                } else if (emailInput.equals(ADMIN_EMAIL) && passwordInput.equals(ADMIN_PASSWORD)) {
                    // Credenciales correctas
                    Toast.makeText(admin.this, "Bienvenido Administrador", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(admin.this, menuadmin.class));
                    finish();
                } else {
                    // Credenciales incorrectas
                    Toast.makeText(admin.this, "Correo o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
