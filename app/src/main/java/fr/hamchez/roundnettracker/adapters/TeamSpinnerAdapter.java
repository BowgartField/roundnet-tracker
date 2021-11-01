package fr.hamchez.roundnettracker.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import fr.hamchez.roundnettracker.models.Team;

public class TeamSpinnerAdapter extends ArrayAdapter<Team> {

    private Context context;
    private List<Team> teamList;

    public TeamSpinnerAdapter(@NonNull Context context, int resource, List<Team> teamList) {
        super(context, resource);

        this.teamList = teamList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        TextView label = (TextView) super.getView(position,convertView,parent);
        label.setTextColor(Color.BLACK);
        label.setText(teamList.get(position).getName());

        return label;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getView(position,convertView,parent);
    }

    @Override
    public int getCount() {
        return teamList.size();
    }

    @Nullable
    @Override
    public Team getItem(int position) {
        return teamList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
