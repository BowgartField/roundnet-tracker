package fr.hamchez.roundnettracker.customComponents;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import fr.hamchez.roundnettracker.R;
import fr.hamchez.roundnettracker.database.dao.TeamPointDAO;
import fr.hamchez.roundnettracker.models.Team;
import fr.hamchez.roundnettracker.models.TeamPoint;

public class ScoreComponent extends LinearLayout {

    TextView score;
    Button addButton;
    Button subButton;

    Team team;

    private int gameId;
    List<TeamPoint> teamPoints;

    public ScoreComponent(Context context, int gameId, Team team) {

        super(context);

        setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT,1.0f));

        initControls(context);

        this.gameId = gameId;
        this.team = team;
    }

    private void initControls(Context context) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        inflater.inflate(R.layout.score_component, this);

        score = findViewById(R.id.scoreTextView);
        addButton = findViewById(R.id.addButton);
        subButton = findViewById(R.id.subButton);

        addButton.setOnClickListener(this::onAdd);
        subButton.setOnClickListener(this::onSub);

        getScore();

    }

    private void getScore() {

        new Thread(() -> {

            teamPoints = new TeamPointDAO(getContext()).getFromTeamId(gameId);
            //score.setText(teamPoints.size());

        }).start();

    }

    public void onAdd(View view) {

    }

    public void onSub(View view){

    }
}
