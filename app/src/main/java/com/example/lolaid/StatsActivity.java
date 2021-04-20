package com.example.lolaid;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class StatsActivity extends Fragment {

    private SharedPreferences sharedPrefs;
    private TextView summonerName;
    private TextView summonerLevel;
    private ImageView mainChampion1image;
    private ImageView mainChampion2image;
    private ImageView mainChampion3image;
    private TextView mainChampion1value;
    private TextView mainChampion2value;
    private TextView mainChampion3value;


    public StatsActivity() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.stats_activity, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getActivity());

        summonerName =  getView().findViewById(R.id.statsSummonerNameValue);
        summonerName.setText(sharedPrefs.getString("summonerName", ""));

        summonerLevel =  getView().findViewById(R.id.statsSummonerLevelValue);
        summonerLevel.setText("250");

        mainChampion1image = getView().findViewById(R.id.statsMainChampion1Image);
        mainChampion1image.setImageResource(selectChampionIcon("ahri"));
        mainChampion1value = getView().findViewById(R.id.statsMainChampion1Value);
        mainChampion1value.setText(selectChampionName("ahri"));

        mainChampion2image = getView().findViewById(R.id.statsMainChampion2Image);
        mainChampion2image.setImageResource(selectChampionIcon("akali"));
        mainChampion2value = getView().findViewById(R.id.statsMainChampion2Value);
        mainChampion2value.setText(selectChampionName("akali"));

        mainChampion3image = getView().findViewById(R.id.statsMainChampion3Image);
        mainChampion3image.setImageResource(selectChampionIcon("amumu"));
        mainChampion3value = getView().findViewById(R.id.statsMainChampion3Value);
        mainChampion3value.setText(selectChampionName("amumu"));
    }

    private int selectChampionIcon(String champ) {
        int icon = 0;
        switch(champ) {
            case "aatrox":
                icon = R.drawable.aatrox;
                break;
            case "ahri":
                icon = R.drawable.ahri;
                break;
            case "akali":
                icon = R.drawable.akali;
                break;
            case "alistar":
                icon = R.drawable.alistar;
                break;
            case "amumu":
                icon = R.drawable.amumu;
                break;
        }
        return icon;
    }

    private String selectChampionName(String champ) {
        String name = "Aatrox";
        switch(champ) {
            case "aatrox":
                name = "Aatrox";
                break;
            case "ahri":
                name = "Ahri";
                break;
            case "akali":
                name = "Akali";
                break;
            case "alistar":
                name = "Alistar";
                break;
            case "amumu":
                name = "Amumu";
                break;
        }
        return name;
    }

}
