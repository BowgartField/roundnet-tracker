package fr.hamchez.roundnettracker.ui.team;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import fr.hamchez.roundnettracker.R;
import fr.hamchez.roundnettracker.adapters.TeamRecyclerViewAdapter;
import fr.hamchez.roundnettracker.database.dao.TeamDAO;
import fr.hamchez.roundnettracker.models.Team;

public class TeamFragment extends Fragment {

    RecyclerView teamRecyclerView;
    EditText teamNameEditText;
    EditText localisationTeamEditText;
    Button addButton;
    LinearLayout loadingLayout;

    Handler handler = new Handler();
    List<Team> teamList = new ArrayList<>();
    TeamRecyclerViewAdapter teamRecyclerViewAdapter;

    View view;

    public TeamFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_team, container, false);

        teamRecyclerView = view.findViewById(R.id.teamRecyclerView);
        teamNameEditText = view.findViewById(R.id.teamNameEditText);
        localisationTeamEditText = view.findViewById(R.id.localisationTeamEditText);
        addButton = view.findViewById(R.id.addButton);
        loadingLayout = view.findViewById(R.id.loadingLayout);

        addButton.setOnClickListener(this::onAddTeam);

        addTeam();

        return view;
    }

    private void addTeam(){

        teamRecyclerViewAdapter = new TeamRecyclerViewAdapter(teamList);

        teamRecyclerView.setAdapter(teamRecyclerViewAdapter);
        teamRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        teamRecyclerView.hasFixedSize();

        new Thread(() -> {

            List<Team> teamList = new TeamDAO(getContext()).getAll();

            handler.post(() -> {

                int sizeBeforeAdding = this.teamList.size();

                this.teamList.addAll(teamList);

                teamRecyclerViewAdapter.notifyItemRangeChanged(sizeBeforeAdding,teamList.size());
                loadingLayout.setVisibility(View.INVISIBLE);

            });

        }).start();

    }

    public void onAddTeam(View view){

        String teamName = teamNameEditText.getText().toString();
        String teamLocalisation = localisationTeamEditText.getText().toString();

        if(!teamName.isEmpty() && !teamLocalisation.isEmpty()){

            Team team = new Team(0,teamName,teamLocalisation);

            boolean result = new TeamDAO(getContext()).insert(team);

            if(result){
                handler.post(() -> {

                    int sizeBeforeAdding = this.teamList.size();

                    teamList.add(team);
                    teamNameEditText.setText("");
                    localisationTeamEditText.setText("");

                    teamRecyclerViewAdapter.notifyItemRangeChanged(sizeBeforeAdding,teamList.size());
                    loadingLayout.setVisibility(View.INVISIBLE);

                });
            }

        }else{

            Snackbar snackbar = Snackbar.make(view,R.string.createTeamError, Snackbar.LENGTH_LONG);
            snackbar.show();

        }

    }
}