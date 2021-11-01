package fr.hamchez.roundnettracker.Holder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import fr.hamchez.roundnettracker.R;
import fr.hamchez.roundnettracker.models.Team;

public class TeamHolder extends RecyclerView.ViewHolder {

    TextView textView;

    public TeamHolder(@NonNull View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.teamName);
    }

    public void updateWithTeam(Team team){
        this.textView.setText(team.getName() + " (" + team.getLocalisation() + ")");
    }

}
