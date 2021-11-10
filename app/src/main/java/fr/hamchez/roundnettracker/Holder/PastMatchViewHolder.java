package fr.hamchez.roundnettracker.Holder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.stream.Collectors;

import fr.hamchez.roundnettracker.R;
import fr.hamchez.roundnettracker.models.Game;
import fr.hamchez.roundnettracker.models.Team;
import fr.hamchez.roundnettracker.models.TeamPoint;

public class PastMatchViewHolder extends RecyclerView.ViewHolder {

    TextView scoreTeam1;
    TextView scoreTeam2;

    TextView team1NameTextView;
    TextView team2NameTextView;

    TextView winnerTeamName;

    public PastMatchViewHolder(@NonNull View itemView) {
        super(itemView);


        System.out.println("ok");

        scoreTeam1 = itemView.findViewById(R.id.scoreTeam1);
        scoreTeam2 = itemView.findViewById(R.id.scoreTeam2);

        team1NameTextView = itemView.findViewById(R.id.team1Name);
        team2NameTextView = itemView.findViewById(R.id.team2Name);

        winnerTeamName = itemView.findViewById(R.id.winnerTeamName);

    }

    public void updateWithInfos(Game game, List<Team> teamList, List<TeamPoint> teamPointList){

        String team1Name = teamList.stream().filter(team -> team.getId() == game.getIdTeamOne()).collect(Collectors.toList()).get(0).getName();
        String team2Name = teamList.stream().filter(team -> team.getId() == game.getIdTeamTwo()).collect(Collectors.toList()).get(0).getName();

        team1NameTextView.setText(team1Name);
        team2NameTextView.setText(team2Name);

        int team1Points = (int) teamPointList.stream().filter(
                teamPoint -> teamPoint.getIdGame() == game.getId() && teamPoint.getIdTeam() == game.getIdTeamOne()
        ).count();

        int team2Points = (int) teamPointList.stream().filter(
                teamPoint -> teamPoint.getIdGame() == game.getId() && teamPoint.getIdTeam() == game.getIdTeamTwo()
        ).count();

        scoreTeam1.setText(String.valueOf(team1Points));
        scoreTeam2.setText(String.valueOf(team2Points));

        if(team1Points > team2Points){
            winnerTeamName.setText(team1Name);
        }else if(team1Points < team2Points){
            winnerTeamName.setText(team2Name);
        }else{
            winnerTeamName.setText(R.string.equality);
        }

    }

}
