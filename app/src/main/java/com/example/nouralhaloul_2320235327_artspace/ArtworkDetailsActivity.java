package com.example.nouralhaloul_2320235327_artspace;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ArtworkDetailsActivity extends AppCompatActivity {

    TextView textDetails;
    Button buttonBack, buttonVisitWebsite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artwork_details);

        getWindow().setStatusBarColor(Color.parseColor("#FFF5EC"));
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        );
        getWindow().setNavigationBarColor(Color.WHITE);

        textDetails = findViewById(R.id.textDetails);
        buttonBack = findViewById(R.id.buttonBack);
        buttonVisitWebsite = findViewById(R.id.buttonVisitWebsite);

        Intent intent = getIntent();

        String title = intent.getStringExtra("title");
        String artist = intent.getStringExtra("artist");
        String year = intent.getStringExtra("year");

        textDetails.setText(
                "Title: " + title
                        + "\n\nArtist: " + artist
                        + "\n\nYear: " + year
        );

        buttonBack.setOnClickListener(view -> {
            finish();
        });

        buttonVisitWebsite.setOnClickListener(view -> {

            Uri webpage = Uri.parse(
                    "https://science.nasa.gov/mission/webb/"
            );

            Intent webIntent = new Intent(
                    Intent.ACTION_VIEW,
                    webpage
            );

            try {
                startActivity(webIntent);
            } catch (ActivityNotFoundException e) {
                Toast.makeText(
                        ArtworkDetailsActivity.this,
                        "No browser application found",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
    }
}
