package com.example.projet_dev;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projet_dev.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class InscriptionActivity extends AppCompatActivity {

    private EditText editTextEmail, editTextPassword;
    private Button buttonCreateAccount;
    private Spinner spinnerCity; // Ajout du Spinner
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        // Initialisation des vues et de la base de données
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonCreateAccount = findViewById(R.id.buttonCreateAccount);
        spinnerCity = findViewById(R.id.spinnerCity); // Initialisation du Spinner
        dbHelper = new DBHelper(this);

        // Ajout des éléments au Spinner
        List<String> cities = new ArrayList<>();
        cities.add("Agadir");
        cities.add("Settat");
        cities.add("Marrakech");
        // Ajoutez autant d'éléments que vous le souhaitez à cette liste

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, cities);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCity.setAdapter(adapter);

        // Gestionnaire de clic sur le bouton "CREER UN COMPTE!"
        buttonCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Récupérer les valeurs de l'e-mail, du mot de passe et de la ville
                String email = editTextEmail.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();
                //String selectedCity = spinnerCity.getSelectedItem().toString();

                // Vérifier si les champs sont vides
                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(InscriptionActivity.this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                } else {
                    // Ouvrir la base de données en mode écriture
                    SQLiteDatabase db = dbHelper.getWritableDatabase();

                    // Créer un objet ContentValues pour stocker les valeurs
                    ContentValues values = new ContentValues();
                    values.put(DBHelper.COLUMN_EMAIL, email);
                    values.put(DBHelper.COLUMN_PASSWORD, password);

                    // Insérer les valeurs dans la table Utilisateur
                    long id = db.insert(DBHelper.TABLE_UTILISATEUR, null, values);

                    // Fermer la base de données
                    db.close();

                    // Vérifier si l'insertion a réussi
                    if (id != -1) {
                        Toast.makeText(InscriptionActivity.this, "Compte créé avec succès", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(InscriptionActivity.this, "Erreur lors de la création du compte", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
