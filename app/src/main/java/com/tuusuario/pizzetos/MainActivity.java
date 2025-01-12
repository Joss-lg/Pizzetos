package com.tuusuario.pizzetos;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private Button btn_login;
    private EditText correo, contrasena;
    private TextView tvRegister;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login); // Asegúrate de que el archivo XML sea `activity_login.xml`
        this.setTitle("Login");

        // Inicializar Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Asignar los elementos del diseño
        correo = findViewById(R.id.correo);
        contrasena = findViewById(R.id.contrasena);
        btn_login = findViewById(R.id.btn_login);
        tvRegister = findViewById(R.id.tv_User_reg_log);

        // Listener para el botón de iniciar sesión
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailUser = correo.getText().toString().trim();
                String passUser = contrasena.getText().toString().trim();

                if (emailUser.isEmpty() || passUser.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Ingresar los datos", Toast.LENGTH_SHORT).show();
                } else {
                    loginUser(emailUser, passUser);
                }
            }
        });

        // Listener para el texto de registro
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Register.class)); // Cambia si tienes otra clase de registro
            }
        });
    }

    private void loginUser(String emailUser, String passUser) {
        mAuth.signInWithEmailAndPassword(emailUser, passUser)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d("MainActivity", "Inicio de sesión exitoso");
                            FirebaseUser user = mAuth.getCurrentUser();
                            if (user != null) {
                                Log.d("MainActivity", "Usuario autenticado: " + user.getEmail());
                            }
                            startActivity(new Intent(MainActivity.this, Home.class)); // Cambia si tienes otra actividad para la pantalla principal
                            finish();
                            Toast.makeText(MainActivity.this, "Bienvenido", Toast.LENGTH_SHORT).show();
                        } else {
                            Log.e("MainActivity", "Error en el inicio de sesión: " + task.getException());
                            Toast.makeText(MainActivity.this, "Error en el inicio de sesión", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("MainActivity", "Error al iniciar sesión: " + e.getMessage());
                        Toast.makeText(MainActivity.this, "Error al iniciar sesión: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            Log.d("MainActivity", "Usuario autenticado: " + user.getEmail());
            startActivity(new Intent(MainActivity.this, Home.class)); // Cambia si tienes otra actividad para la pantalla principal
            finish();
        } else {
            Log.d("MainActivity", "No hay un usuario autenticado actualmente.");
        }
    }
}
