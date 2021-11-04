package fr.hamchez.roundnettracker.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import fr.hamchez.roundnettracker.R;
import fr.hamchez.roundnettracker.databinding.FragmentHomeBinding;
import fr.hamchez.roundnettracker.models.Player;
import fr.hamchez.roundnettracker.utils.ObjectSharedPreferences;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;

    TextView playerUserName;
    TextView todayTextView;

    Player player;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        player = ObjectSharedPreferences.getSavedObjectFromPreference(getContext(),"player",Player.class);

        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        playerUserName = root.findViewById(R.id.playerUserName);
        todayTextView = root.findViewById(R.id.todayTextView);

        setInterface();

        return root;
    }

    private void setInterface(){

        playerUserName.setText(player.getFirstname());

        SimpleDateFormat sdf = new SimpleDateFormat("EEEE, MMM d, yyyy", Locale.getDefault());
        String currentDateandTime = sdf.format(new Date());

        todayTextView.setText(currentDateandTime);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}