package fr.hamchez.roundnettracker.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fr.hamchez.roundnettracker.Holder.PastMatchViewHolder;
import fr.hamchez.roundnettracker.Holder.TeamHolder;
import fr.hamchez.roundnettracker.R;
import fr.hamchez.roundnettracker.models.Game;
import fr.hamchez.roundnettracker.models.Team;
import fr.hamchez.roundnettracker.models.TeamPoint;

public class PastMatchAdapter extends RecyclerView.Adapter<PastMatchViewHolder> {

    List<Game> gameList;
    List<Team> teamList;
    List<TeamPoint> teamPointList;

    public PastMatchAdapter(List<Game> gameList, List<Team> teamList, List<TeamPoint> teamPointList){
        this.gameList = gameList;
        this.teamList = teamList;
        this.teamPointList = teamPointList;
    }

    @NonNull
    @Override
    public PastMatchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.past_match_item, parent, false);

        return new PastMatchViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull PastMatchViewHolder holder, int position) {
        holder.updateWithInfos(this.gameList.get(position), teamList, teamPointList);
    }

    @Override
    public int getItemCount() {
        return gameList.size();
    }
}
