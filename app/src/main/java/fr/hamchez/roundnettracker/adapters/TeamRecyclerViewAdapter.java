package fr.hamchez.roundnettracker.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fr.hamchez.roundnettracker.Holder.TeamHolder;
import fr.hamchez.roundnettracker.R;
import fr.hamchez.roundnettracker.models.Team;

public class TeamRecyclerViewAdapter extends RecyclerView.Adapter<TeamHolder> {

    private List<Team> teamList;

    public TeamRecyclerViewAdapter(List<Team> teamList){
        this.teamList = teamList;
    }

    @NonNull
    @Override
    public TeamHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.team_recyclerview_item, parent, false);

        return new TeamHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TeamHolder holder, int position) {

        if(position%2 == 1){
            holder.itemView.setBackgroundColor(Color.GRAY);
            TextView view = holder.itemView.findViewById(R.id.teamName);
            view.setTextColor(Color.WHITE);
        }

        holder.updateWithTeam(this.teamList.get(position));
    }

    @Override
    public int getItemCount() {
        return teamList.size();
    }

}
