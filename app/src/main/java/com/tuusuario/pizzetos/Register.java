package com.tuusuario.pizzetos;

<<<<<<< HEAD
import android.app.DatePickerDialog;
=======
>>>>>>> parent of 82304ed (Revert "Login Funcionando verificar autenticacion")
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
<<<<<<< HEAD
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
=======
import android.widget.EditText;
>>>>>>> parent of 82304ed (Revert "Login Funcionando verificar autenticacion")
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

<<<<<<< HEAD
import java.util.Calendar;

public class Register extends AppCompatActivity {

    EditText nombre, correo, contrasena, telefono;
    Button btnFechaNacimiento, btnRegistro;
    RadioGroup sexoGroup;
    RadioButton rbMasculino, rbFemenino;
    FirebaseAuth mAuth;

    private String fechaNacimiento; // Variable para almacenar la fecha seleccionada

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register); // Asegúrate de que el layout sea correcto

        setTitle("Registro");
=======
public class Register extends AppCompatActivity {
    EditText nombre, correo, contrasena;
    Button btnRegistro;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register); // Asegúrate de usar el nombre correcto del archivo XML

        this.setTitle("Registro");
>>>>>>> parent of 82304ed (Revert "Login Funcionando verificar autenticacion")

        // Inicializar FirebaseAuth
        mAuth = FirebaseAuth.getInstance();

        // Asignar elementos del diseño a las variables
        nombre = findViewById(R.id.nombre);
        correo = findViewById(R.id.correo);
        contrasena = findViewById(R.id.contrasena);
<<<<<<< HEAD
        telefono = findViewById(R.id.telefono);
        btnFechaNacimiento = findViewById(R.id.btn_fecha_nacimiento);
        btnRegistro = findViewById(R.id.btn_registro);
        sexoGroup = findViewById(R.id.sexo);
        rbMasculino = findViewById(R.id.rb_masculino);
        rbFemenino = findViewById(R.id.rb_femenino);

        // Configurar el botón para seleccionar fecha de nacimiento
        btnFechaNacimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirDatePicker();
            }
        });
=======
        btnRegistro = findViewById(R.id.btn_registro);
>>>>>>> parent of 82304ed (Revert "Login Funcionando verificar autenticacion")

        // Configurar el botón de registro
        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
<<<<<<< HEAD
                registrarUsuario();
=======
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
>>>>>>> parent of 82304ed (Revert "Login Funcionando verificar autenticacion")
            }
        });
    }

<<<<<<< HEAD
    private void abrirDatePicker() {
        // Obtener la fecha actual para el DatePicker
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // Crear y mostrar el DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                fechaNacimiento = dayOfMonth + "/" + (month + 1) + "/" + year;
                btnFechaNacimiento.setText(fechaNacimiento); // Mostrar la fecha seleccionada en el botón
            }
        }, year, month, day);

        datePickerDialog.show();
    }

    private void registrarUsuario() {
        String nombreUser = nombre.getText().toString().trim();
        String emailUser = correo.getText().toString().trim();
        String passUser = contrasena.getText().toString().trim();
        String telefonoUser = telefono.getText().toString().trim();
        int sexoId = sexoGroup.getCheckedRadioButtonId();

        if (nombreUser.isEmpty() || emailUser.isEmpty() || passUser.isEmpty() || telefonoUser.isEmpty() || fechaNacimiento == null || sexoId == -1) {
            Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        if (passUser.length() < 6) {
            Toast.makeText(this, "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show();
            return;
        }

        String sexo = (sexoId == R.id.rb_masculino) ? "Masculino" : "Femenino";

        // Registrar el usuario en Firebase Authentication
        mAuth.createUserWithEmailAndPassword(emailUser, passUser)
=======
    private void registrarUsuario(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
>>>>>>> parent of 82304ed (Revert "Login Funcionando verificar autenticacion")
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
<<<<<<< HEAD
                            Toast.makeText(Register.this, "Registro exitoso. Por favor, inicia sesión.", Toast.LENGTH_LONG).show();
                            // Redirigir al usuario al MainActivity
                            Intent intent = new Intent(Register.this, MainActivity.class);
                            startActivity(intent);
                            finish(); // Finalizar la actividad actual
=======
                            Toast.makeText(Register.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Register.this, LoginActivity.class)); // Redirigir al Login
                            finish();
>>>>>>> parent of 82304ed (Revert "Login Funcionando verificar autenticacion")
                        } else {
                            Log.e("RegisterActivity", "Error: " + task.getException());
                            Toast.makeText(Register.this, "Error al registrarse", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
