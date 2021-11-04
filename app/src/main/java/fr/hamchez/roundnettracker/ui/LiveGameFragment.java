package fr.hamchez.roundnettracker.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;
import java.util.stream.Collectors;

import fr.hamchez.roundnettracker.R;
import fr.hamchez.roundnettracker.database.dao.GameDAO;
import fr.hamchez.roundnettracker.database.dao.TeamDAO;
import fr.hamchez.roundnettracker.models.Game;
import fr.hamchez.roundnettracker.models.Team;
import fr.hamchez.roundnettracker.ui.game.GameActivity;

public class LiveGameFragment extends Fragment {

    ImageView liveSymbol;

    LinearLayout liveLinearLayout;
    LinearLayout noLiveLinearLayout;

    TextView teamOneName;
    TextView teamOneLocalisation;
    TextView teamTwoName;
    TextView teamTwoLocalisation;

    int liveGameId;
    Team teamOne;
    Team teamTwo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_live_game, container, false);

        liveSymbol = view.findViewById(R.id.liveSymbol);
        liveLinearLayout = view.findViewById(R.id.liveLinearLayout);
        noLiveLinearLayout = view.findViewById(R.id.noLiveLinearLayout);

        teamOneName = view.findViewById(R.id.teamOneName);
        teamTwoName = view.findViewById(R.id.teamTwoName);
        teamOneLocalisation = view.findViewById(R.id.teamOneLocalisation);
        teamTwoLocalisation = view.findViewById(R.id.teamTwoLocalisation);

        configureInterface();

        return view;
    }

    private void configureInterface(){

        liveSymbol.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.live));

        Game liveGame = new GameDAO(getContext()).getLiveGame();

        if(liveGame != null){

            List<Team> teamList = new TeamDAO(getContext()).getAll();

            teamOne = teamList.stream().filter(team -> team.getId() == liveGame.getIdTeamOne()).collect(Collectors.toList()).get(0);
            teamTwo = teamList.stream().filter(team -> team.getId() == liveGame.getIdTeamTwo()).collect(Collectors.toList()).get(0);

            teamOneName.setText(teamOne.getName());
            teamTwoName.setText(teamTwo.getName());

            teamOneLocalisation.setText(teamOne.getLocalisation());
            teamTwoLocalisation.setText(teamTwo.getLocalisation());

            liveLinearLayout.setOnClickListener(v -> {

                Intent intent = new Intent(getContext(),GameActivity.class);
                intent.putExtra("gameId",liveGameId);
                intent.putExtra("teamOne",teamOne);
                intent.putExtra("teamTwo",teamTwo);

                startActivity(intent);

            });

            noLiveLinearLayout.setVisibility(View.INVISIBLE);

        }

    }
}