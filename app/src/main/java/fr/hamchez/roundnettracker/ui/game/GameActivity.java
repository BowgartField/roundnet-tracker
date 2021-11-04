package fr.hamchez.roundnettracker.ui.game;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;

import fr.hamchez.roundnettracker.R;
import fr.hamchez.roundnettracker.customComponents.ScoreComponent;
import fr.hamchez.roundnettracker.models.Team;

public class GameActivity extends AppCompatActivity {

    LinearLayout teamComponent;

    int liveGameId;
    Team teamOne;
    Team teamTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        liveGameId = (int) getIntent().getExtras().get("gameId");
        teamOne = (Team) getIntent().getExtras().get("teamOne");
        teamTwo = (Team) getIntent().getExtras().get("teamTwo");

        teamComponent = findViewById(R.id.teamComponent);

        teamComponent.addView(new ScoreComponent(this, liveGameId, teamOne));
        teamComponent.addView(new ScoreComponent(this, liveGameId, teamTwo));

    }
}