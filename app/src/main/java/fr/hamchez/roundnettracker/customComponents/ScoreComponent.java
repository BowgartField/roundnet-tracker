package fr.hamchez.roundnettracker.customComponents;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import fr.hamchez.roundnettracker.R;
import fr.hamchez.roundnettracker.database.dao.TeamPointDAO;
import fr.hamchez.roundnettracker.models.Team;
import fr.hamchez.roundnettracker.models.TeamPoint;

public class ScoreComponent extends LinearLayout {

    TextView scoreTextView;
    Button addButton;
    Button subButton;

    Team team;

    private int gameId;
    List<TeamPoint> teamPoints = new ArrayList<>();

    Handler handler = new Handler();

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

        scoreTextView = findViewById(R.id.scoreTextView);
        addButton = findViewById(R.id.addButton);
        subButton = findViewById(R.id.subButton);

        addButton.setOnClickListener(this::onAdd);
        subButton.setOnClickListener(this::onSub);

        getScore();

    }

    private void getScore() {
        new Thread(() -> {

            List<TeamPoint> teamPointList = new TeamPointDAO(getContext()).getFromTeamId(gameId)
                    .stream().filter(teamPoint -> teamPoint.getIdTeam() == team.getId()).collect(Collectors.toList());

            teamPoints.addAll(teamPointList);
            scoreTextView.setText(String.valueOf(teamPoints.size()));
        }).start();
    }

    public void onAdd(View view) {
        new Thread(() -> {

                TeamPoint teamPoint = new TeamPoint(new Random().nextInt(1000), gameId, team.getId());
                new TeamPointDAO(getContext()).insert(teamPoint);
                teamPoints.add(teamPoint);
                handler.post(() -> {
                    scoreTextView.setText(String.valueOf(teamPoints.size()));
                });
            }
        ).start();
    }

    public void onSub(View view){
        new Thread(() -> {

            int randomPointId = new Random().nextInt(teamPoints.size());
            new TeamPointDAO(getContext()).remove(teamPoints.get(randomPointId).getId());
            teamPoints.removeIf(teamPoint -> teamPoint.getId() == randomPointId);
            handler.post(() -> {
                scoreTextView.setText(String.valueOf(teamPoints.size()));
            });
        });
    }
}
