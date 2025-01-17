package com.tuusuario.pizzetos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private EditText correo, contrasena;
    private Button btn_login, btn_admin;
    private TextView tvRegister;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Login");

        // Inicializar Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Asignar los elementos del diseño
        correo = findViewById(R.id.correo);
        contrasena = findViewById(R.id.contrasena);
        btn_login = findViewById(R.id.btn_login);
        btn_admin = findViewById(R.id.btn_admin);
        tvRegister = findViewById(R.id.tv_User_reg_log);

        // Verificar si se reciben datos del registro
        Intent intent = getIntent();
        if (intent != null) {
            String nombre = intent.getStringExtra("nombre");
            if (nombre != null) {
                Toast.makeText(this, "¡Registro exitoso! Bienvenido, " + nombre, Toast.LENGTH_SHORT).show();
            }
        }

        // Listener para el botón de iniciar sesión
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailUser = correo.getText().toString().trim();
                String passUser = contrasena.getText().toString().trim();

                if (emailUser.isEmpty() || passUser.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Por favor, ingresa tus datos", Toast.LENGTH_SHORT).show();
                } else {
                    loginUser(emailUser, passUser);
                }
            }
        });

        // Listener para el botón de login del administrador
        btn_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, admin.class);
                startActivity(intent);
            }
        });

        // Listener para el texto de registro
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Register.class));
            }
        });
    }

    private void loginUser(String emailUser, String passUser) {
        mAuth.signInWithEmailAndPassword(emailUser, passUser)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            if (user != null) {
                                Toast.makeText(MainActivity.this, "Bienvenido", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(MainActivity.this, Home.class));
                                finish();
                            }
                        } else {
                            Toast.makeText(MainActivity.this, "Error en el inicio de sesión", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        // Verificar si hay un usuario autenticado
        if (user != null) {
            // Usuario ya autenticado, redirigir a Home
            Intent intent = new Intent(MainActivity.this, Home.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
    }
}
