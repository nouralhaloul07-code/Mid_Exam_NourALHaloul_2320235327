package com.example.nouralhaloul_2320235327_artspace;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView textCounter, textTitle, textArtist;
    ImageView imageArtwork;
    Button buttonPrevious, buttonNext;

    int currentArtwork = 0;

    int[] artworkImages = {
            R.drawable.art1,
            R.drawable.art2,
            R.drawable.art3,
            R.drawable.art4,
            R.drawable.art5,
            R.drawable.art6
    };

    String[] artworkTitles = {
            "Deep Field Stellar Deaths",
            "Cosmic Cliffs",
            "Southern Ring Nebula",
            "Stephan's Quintet",
            "Pillars of Creation",
            "Hubble NGC Flat Final"
    };

    String[] artworkArtists = {
            "Smacs Carin",
            "NASA Webb Team",
            "NASA Webb Team",
            "NASA Webb Team",
            "NASA, ESA, CSA",
            "Sonay Billan"
    };

    String[] artworkYears = {
            "2021",
            "2022",
            "2022",
            "2022",
            "2022",
            "2019"
    };

    private static final String STATE_ARTWORK = "current_artwork";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setStatusBarColor(Color.parseColor("#FFF5EC"));
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        );
        getWindow().setNavigationBarColor(Color.WHITE);

        textCounter = findViewById(R.id.textCounter);
        textTitle = findViewById(R.id.textTitle);
        textArtist = findViewById(R.id.textArtist);

        imageArtwork = findViewById(R.id.imageArtwork);

        buttonPrevious = findViewById(R.id.buttonPrevious);
        buttonNext = findViewById(R.id.buttonNext);

        if (savedInstanceState != null) {
            currentArtwork = savedInstanceState.getInt(STATE_ARTWORK, 0);
        }

        displayArtwork();

        buttonNext.setOnClickListener(view -> {
            if (currentArtwork < artworkImages.length - 1) {
                currentArtwork++;
                displayArtwork();
            }
        });

        buttonPrevious.setOnClickListener(view -> {
            if (currentArtwork > 0) {
                currentArtwork--;
                displayArtwork();
            }
        });

        imageArtwork.setOnClickListener(view -> {
            Intent intent = new Intent(
                    MainActivity.this,
                    ArtworkDetailsActivity.class
            );

            intent.putExtra("title", artworkTitles[currentArtwork]);
            intent.putExtra("artist", artworkArtists[currentArtwork]);
            intent.putExtra("year", artworkYears[currentArtwork]);
            intent.putExtra("imageResId", artworkImages[currentArtwork]);

            startActivity(intent);
        });
    }

    private void displayArtwork() {
        imageArtwork.setImageResource(artworkImages[currentArtwork]);

        textCounter.setText(
                (currentArtwork + 1) + " / " + artworkImages.length
        );

        textTitle.setText(artworkTitles[currentArtwork]);

        textArtist.setText(
                artworkArtists[currentArtwork]
                        + " (" + artworkYears[currentArtwork] + ")"
        );

        boolean canGoPrevious = currentArtwork > 0;
        boolean canGoNext = currentArtwork < artworkImages.length - 1;

        updateButtonState(buttonPrevious, canGoPrevious);
        updateButtonState(buttonNext, canGoNext);
    }

    private void updateButtonState(Button button, boolean enabled) {
        button.setEnabled(enabled);
        int bgColor = Color.parseColor(enabled ? "#8B4B1A" : "#D8CFC7");
        int textColor = Color.parseColor(enabled ? "#FFFFFF" : "#8E857D");
        button.setBackgroundTintList(ColorStateList.valueOf(bgColor));
        button.setTextColor(textColor);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STATE_ARTWORK, currentArtwork);
    }
}
