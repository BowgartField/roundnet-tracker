package fr.hamchez.roundnettracker.ui.pastMatch;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import fr.hamchez.roundnettracker.R;
import fr.hamchez.roundnettracker.adapters.PastMatchAdapter;
import fr.hamchez.roundnettracker.database.dao.GameDAO;
import fr.hamchez.roundnettracker.database.dao.TeamDAO;
import fr.hamchez.roundnettracker.database.dao.TeamPointDAO;
import fr.hamchez.roundnettracker.databinding.FragmentPastMatchBinding;
import fr.hamchez.roundnettracker.models.Game;
import fr.hamchez.roundnettracker.models.Team;
import fr.hamchez.roundnettracker.models.TeamPoint;

public class PastMatchFragment extends Fragment {

    private PastMatchViewModel pastMatchViewModel;
    private FragmentPastMatchBinding binding;

    private RecyclerView pastMatchRecyclerView;

    Handler handler = new Handler();

    List<Game> gameList = new ArrayList<>();
    List<Team> teamList = new ArrayList<>();
    List<TeamPoint> teamPointList = new ArrayList<>();

    PastMatchAdapter pastMatchAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        pastMatchViewModel = new ViewModelProvider(this).get(PastMatchViewModel.class);

        binding = FragmentPastMatchBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        pastMatchRecyclerView = root.findViewById(R.id.pastMatchRecyclerView);

        populateRecyclerView();

        return root;
    }

    private void populateRecyclerView() {

        pastMatchAdapter = new PastMatchAdapter(gameList,teamList,teamPointList);

        pastMatchRecyclerView.setAdapter(pastMatchAdapter);
        pastMatchRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        pastMatchRecyclerView.hasFixedSize();

        new Thread(() -> {

            gameList.addAll(
                    new GameDAO(getContext()).getAll().stream().filter(Game::isFinished).collect(Collectors.toList())
            );

            teamList.addAll(new TeamDAO(getContext()).getAll());
            teamPointList.addAll(new TeamPointDAO(getContext()).getAll());


            handler.post(() -> pastMatchAdapter.notifyItemRangeChanged(0,gameList.size()));

        }).start();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}