package com.example.projet_dev;



import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

public class NextActivity extends AppCompatActivity {

    private int numberOfAds = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);

        // Initialisation du TextView pour afficher le nombre d'annonces
        //final TextView adsCountTextView = findViewById(R.id.adsCountTextView);
        //adsCountTextView.setText(String.valueOf(numberOfAds));

        // Récupération du TextView pour le clic
        TextView interface4TextView = findViewById(R.id.textView17);
        interface4TextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigation vers l'interface 4
                navigateToInterface4();
            }
        });
    }

    // Méthode pour naviguer vers l'interface 4
    private void navigateToInterface4() {
        Intent intent = new Intent(this, Interface4Activity.class);
        intent.putExtra("numberOfAds", numberOfAds); // Passer le nombre d'annonces à l'activité suivante
        startActivity(intent);
        incrementAdsCount();
    }


    // Méthode pour incrémenter le nombre d'annonces
    private void incrementAdsCount() {
        numberOfAds++;
        // Mettre à jour le TextView pour afficher le nouveau nombre d'annonces
        TextView adsCountTextView = findViewById(R.id.adsCountTextView);
        adsCountTextView.setText(String.valueOf(numberOfAds));
    }
}