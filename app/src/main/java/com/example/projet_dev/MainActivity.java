package com.example.projet_dev;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private TextView textViewError;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button buttonLogin;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DBHelper(this);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        textViewError = findViewById(R.id.textViewError);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();

                if (validateCredentials(email, password)) {
                    // Les identifiants sont valides, redirigez l'utilisateur vers la prochaine activité
                    Intent intent = new Intent(MainActivity.this, NextActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    // Afficher un message d'erreur si les identifiants ne correspondent pas
                    textViewError.setVisibility(View.VISIBLE);
                }
            }
        });
        TextView buttonSignUp = findViewById(R.id.buttonSignUp);
        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ouvrir l'activité d'inscription
                Intent intent = new Intent(MainActivity.this, InscriptionActivity.class);
                startActivity(intent);
            }
        });

    }

    private boolean validateCredentials(String email, String password) {
        // Vérifiez si les identifiants existent dans la table Utilisateur
        return dbHelper.checkUser(email, password);
    }
}

