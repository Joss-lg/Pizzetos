package com.tuusuario.pizzetos;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Calendar;

public class Register extends AppCompatActivity {

    EditText nombre, correo, contrasena, telefono;
    Button btnRegistro, btnFechaNacimiento;
    RadioGroup sexoGroup;
    RadioButton rbMasculino, rbFemenino;
    FirebaseAuth mAuth;
    String fechaNacimientoSeleccionada = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        this.setTitle("Registro");

        // Inicializar FirebaseAuth
        mAuth = FirebaseAuth.getInstance();

        // Asignar elementos del diseño a las variables
        nombre = findViewById(R.id.nombre);
        correo = findViewById(R.id.correo);
        contrasena = findViewById(R.id.contrasena);
        telefono = findViewById(R.id.telefono);
        btnRegistro = findViewById(R.id.btn_registro);
        btnFechaNacimiento = findViewById(R.id.btn_fecha_nacimiento);
        sexoGroup = findViewById(R.id.sexo);
        rbMasculino = findViewById(R.id.rb_masculino);
        rbFemenino = findViewById(R.id.rb_femenino);

        // Configurar botón para seleccionar fecha de nacimiento
        btnFechaNacimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        Register.this,
                        (view, year1, month1, dayOfMonth) -> {
                            fechaNacimientoSeleccionada = dayOfMonth + "/" + (month1 + 1) + "/" + year1;
                            btnFechaNacimiento.setText(fechaNacimientoSeleccionada);
                        },
                        year, month, day);

                datePickerDialog.show();
            }
        });

        // Configurar botón de registro
        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombreUser = nombre.getText().toString().trim();
                String emailUser = correo.getText().toString().trim();
                String passUser = contrasena.getText().toString().trim();
                String telefonoUser = telefono.getText().toString().trim();

                int sexoSeleccionadoId = sexoGroup.getCheckedRadioButtonId();
                String sexo = (sexoSeleccionadoId == R.id.rb_masculino) ? "Masculino" : "Femenino";

                if (nombreUser.isEmpty() || emailUser.isEmpty() || passUser.isEmpty() || telefonoUser.isEmpty() || fechaNacimientoSeleccionada.isEmpty()) {
                    Toast.makeText(Register.this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
                } else if (passUser.length() < 6) {
                    Toast.makeText(Register.this, "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show();
                } else {
                    registrarUsuario(emailUser, passUser, nombreUser, telefonoUser, fechaNacimientoSeleccionada, sexo);
                }
            }
        });
    }

    private void registrarUsuario(String email, String password, String nombre, String telefono, String fecha, String sexo) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Register.this, "Registro exitoso", Toast.LENGTH_SHORT).show();

                            // Redirigir al MainActivity
                            Intent intent = new Intent(Register.this, MainActivity.class);
                            intent.putExtra("nombre", nombre);
                            intent.putExtra("telefono", telefono);
                            intent.putExtra("fechaNacimiento", fecha);
                            intent.putExtra("sexo", sexo);
                            startActivity(intent);
                            finish();
                        } else {
                            Log.e("RegisterActivity", "Error: " + task.getException());
                            Toast.makeText(Register.this, "Error al registrarse", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
