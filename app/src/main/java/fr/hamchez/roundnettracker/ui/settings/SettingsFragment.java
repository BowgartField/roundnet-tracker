package fr.hamchez.roundnettracker.ui.settings;

import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.snackbar.Snackbar;

import java.util.Locale;

import fr.hamchez.roundnettracker.R;
import fr.hamchez.roundnettracker.database.dao.GameDAO;

public class SettingsFragment extends Fragment {

    private SettingsViewModel mViewModel;
    private ImageView frenchImage;
    private ImageView englishImage;

    private Button saveButton;

    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.settings_fragment, container, false);
        englishImage = result.findViewById(R.id.imageboutonAnglais);
        frenchImage = result.findViewById(R.id.imageboutonFrench);
        saveButton = result.findViewById(R.id.saveButton);

        frenchImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String languageToLoad  = "fr";
                Locale locale = new Locale(languageToLoad);
                Locale.setDefault(locale);
                Configuration config = new Configuration();
                config.locale = locale;
                v.getResources().updateConfiguration(config,v.getResources().getDisplayMetrics());

                Intent intent = getActivity().getIntent();
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK
                        | Intent.FLAG_ACTIVITY_NO_ANIMATION);
                getActivity().overridePendingTransition(0, 0);
                getActivity().finish();

                getActivity().overridePendingTransition(0, 0);
                startActivity(intent);
            }
        });

        englishImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String languageToLoad  = "";
                Locale locale = new Locale(languageToLoad);
                Locale.setDefault(locale);
                Configuration config = new Configuration();
                config.locale = locale;
                v.getResources().updateConfiguration(config,v.getResources().getDisplayMetrics());

                Intent intent = getActivity().getIntent();
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK
                        | Intent.FLAG_ACTIVITY_NO_ANIMATION);
                getActivity().overridePendingTransition(0, 0);
                getActivity().finish();

                getActivity().overridePendingTransition(0, 0);
                startActivity(intent);
            }
        });

        saveButton.setOnClickListener(this::onSave);

        return result;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(SettingsViewModel.class);
        // TODO: Use the ViewModel
    }

    public void onSave(View view){

        new Thread(() -> {

            new GameDAO(getContext()).saveGame();

            Snackbar snackbar = Snackbar.make(view,R.string.cloudSaveSucess,Snackbar.LENGTH_LONG);
            snackbar.show();

        }).start();

    }

}