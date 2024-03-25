package com.example.projet_dev;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Interface4Activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interface4);

        // Récupération de la valeur de numberOfAds de l'intention
        int numberOfAds = getIntent().getIntExtra("numberOfAds", 0);

        // Construction de la phrase avec la valeur de numberOfAds
        String phrase = "Il y a actuellement " + numberOfAds + " annonce(s) sur Agadir";

        // Affichage de la phrase dans un TextView
        TextView phraseTextView;
        phraseTextView = findViewById(R.id.textViewPhrase);
        phraseTextView.setText(phrase);
    }

}
