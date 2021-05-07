package com.example.lolaid;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.math.RoundingMode;
import java.text.DecimalFormat;

import business_logic.data_models.custom_pojo.PlayerStatsInfo;
import business_logic.services.RiotApiService;
import databases.LoLAidDatabase;
import databases.models.Champion;

public class StatsActivity extends Fragment {

    private SharedPreferences sharedPrefs;
    private TextView summonerNameTitle;
    private TextView summonerName;
    private TextView summonerLevelTitle;
    private TextView summonerLevel;
    private TextView summonerRankTitle;
    private TextView summonerRank;
    private TextView summonerWinRateTitle;
    private TextView summonerWinRate;
    private TextView summonerMainChampionsTitle;
    private ImageView mainChampion1image;
    private ImageView mainChampion2image;
    private ImageView mainChampion3image;
    private TextView mainChampion1value;
    private TextView mainChampion2value;
    private TextView mainChampion3value;
    private TextView errorMessage;
    private ProgressBar progressBarStats;

    private RiotApiService apiService;

    public StatsActivity() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.stats_activity, container, false);
        apiService = new RiotApiService();

        summonerName =  view.findViewById(R.id.statsSummonerNameValue);
        summonerLevel =  view.findViewById(R.id.statsSummonerLevelValue);
        summonerName =  view.findViewById(R.id.statsSummonerNameValue);
        summonerLevel =  view.findViewById(R.id.statsSummonerLevelValue);
        summonerRank =  view.findViewById(R.id.statsSummonerRankValue);
        summonerWinRate =  view.findViewById(R.id.statsSummonerWinRateValue);
        mainChampion1image = view.findViewById(R.id.statsMainChampion1Image);
        mainChampion1value = view.findViewById(R.id.statsMainChampion1Value);
        mainChampion2image = view.findViewById(R.id.statsMainChampion2Image);
        mainChampion2value = view.findViewById(R.id.statsMainChampion2Value);
        mainChampion3image = view.findViewById(R.id.statsMainChampion3Image);
        mainChampion3value = view.findViewById(R.id.statsMainChampion3Value);
        summonerNameTitle =  view.findViewById(R.id.statsSummonerNameTitle);
        summonerLevelTitle =  view.findViewById(R.id.statsSummonerLevelTitle);
        summonerRankTitle =  view.findViewById(R.id.statsSummonerRankTitle);
        summonerWinRateTitle =  view.findViewById(R.id.statsSummonerWinRateTitle);
        summonerMainChampionsTitle = view.findViewById(R.id.statsMainChampionsTitle);
        errorMessage = view.findViewById(R.id.errorMessage);
        progressBarStats = view.findViewById(R.id.progressBarStats);

        sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dataLoaded(false);
        apiService.getPlayerStatsInfo(sharedPrefs.getString("summonerName", ""), this, view);
    }

    public void getPlayerStatsRest(PlayerStatsInfo playerInfo) {
        dataLoaded(true);

        summonerName.setText(playerInfo.getSummonerName());


        summonerLevel.setText(String.valueOf(playerInfo.getSummonerLevel()));

        summonerRank =  getView().findViewById(R.id.statsSummonerRankValue);
        summonerRank.setText(playerInfo.getTier() + " " + playerInfo.getRank() + " (" + playerInfo.getLeaguePoints() + " lp)");

        summonerWinRate =  getView().findViewById(R.id.statsSummonerWinRateValue);

        float winRate = (float) playerInfo.getWins() / (playerInfo.getWins() + playerInfo.getLosses()) * 100;
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        decimalFormat.setRoundingMode(RoundingMode.UP);

        summonerWinRate.setText(decimalFormat.format(winRate) + "% - " + playerInfo.getWins() + "W / " + playerInfo.getLosses() + "L");

        setMainChampions(playerInfo.getTop1ChampPlayed(), playerInfo.getTop2ChampPlayed(), playerInfo.getTop3ChampPlayed());
    }

    private void setMainChampions(Long champ1, Long champ2, Long champ3)
    {
        Thread championsThread = new Thread(() -> {
            Champion championTop1 = LoLAidDatabase.getInstance(getActivity()).ChampionDAO().getChampion(champ1);
            Champion championTop2 = LoLAidDatabase.getInstance(getActivity()).ChampionDAO().getChampion(champ2);
            Champion championTop3 = LoLAidDatabase.getInstance(getActivity()).ChampionDAO().getChampion(champ3);

            getActivity().runOnUiThread(() -> {
                mainChampion1image = getView().findViewById(R.id.statsMainChampion1Image);
                mainChampion1image.setImageResource(championTop1.getChampionIconId());
                mainChampion1value = getView().findViewById(R.id.statsMainChampion1Value);
                mainChampion1value.setText(championTop1.getName());

                mainChampion2image = getView().findViewById(R.id.statsMainChampion2Image);
                mainChampion2image.setImageResource(championTop2.getChampionIconId());
                mainChampion2value = getView().findViewById(R.id.statsMainChampion2Value);
                mainChampion2value.setText(championTop2.getName());

                mainChampion3image = getView().findViewById(R.id.statsMainChampion3Image);
                mainChampion3image.setImageResource(championTop3.getChampionIconId());
                mainChampion3value = getView().findViewById(R.id.statsMainChampion3Value);
                mainChampion3value.setText(championTop3.getName());
            });
        });

        championsThread.start();
    }

    private void dataLoaded(boolean loaded)
    {
        summonerName =  getView().findViewById(R.id.statsSummonerNameValue);
        summonerLevel =  getView().findViewById(R.id.statsSummonerLevelValue);
        summonerRank =  getView().findViewById(R.id.statsSummonerRankValue);
        summonerWinRate =  getView().findViewById(R.id.statsSummonerWinRateValue);
        mainChampion1image = getView().findViewById(R.id.statsMainChampion1Image);
        mainChampion1value = getView().findViewById(R.id.statsMainChampion1Value);
        mainChampion2image = getView().findViewById(R.id.statsMainChampion2Image);
        mainChampion2value = getView().findViewById(R.id.statsMainChampion2Value);
        mainChampion3image = getView().findViewById(R.id.statsMainChampion3Image);
        mainChampion3value = getView().findViewById(R.id.statsMainChampion3Value);
        errorMessage = getView().findViewById(R.id.errorMessage);

        if(loaded)
        {
            errorMessage.setVisibility(View.INVISIBLE);
            summonerName.setVisibility(View.VISIBLE);

            summonerLevel.setVisibility(View.VISIBLE);

            summonerRank.setVisibility(View.VISIBLE);

            summonerWinRate.setVisibility(View.VISIBLE);

            mainChampion1image.setVisibility(View.VISIBLE);

            mainChampion1value.setVisibility(View.VISIBLE);

            mainChampion2image.setVisibility(View.VISIBLE);

            mainChampion2value.setVisibility(View.VISIBLE);

            mainChampion3image.setVisibility(View.VISIBLE);

            mainChampion3value.setVisibility(View.VISIBLE);

            summonerNameTitle.setVisibility(View.VISIBLE);
            summonerLevelTitle.setVisibility(View.VISIBLE);
            summonerRankTitle.setVisibility(View.VISIBLE);
            summonerWinRateTitle.setVisibility(View.VISIBLE);
            summonerMainChampionsTitle.setVisibility(View.VISIBLE);
            progressBarStats.setVisibility(View.INVISIBLE);
        }
        else
        {
            progressBarStats.setVisibility(View.VISIBLE);
            errorMessage.setVisibility(View.INVISIBLE);

            summonerName.setVisibility(View.INVISIBLE);

            summonerLevel.setVisibility(View.INVISIBLE);

            summonerRank.setVisibility(View.INVISIBLE);

            summonerWinRate.setVisibility(View.INVISIBLE);

            mainChampion1image.setVisibility(View.INVISIBLE);

            mainChampion1value.setVisibility(View.INVISIBLE);

            mainChampion2image.setVisibility(View.INVISIBLE);

            mainChampion2value.setVisibility(View.INVISIBLE);

            mainChampion3image.setVisibility(View.INVISIBLE);

            mainChampion3value.setVisibility(View.INVISIBLE);

            summonerNameTitle.setVisibility(View.INVISIBLE);
            summonerLevelTitle.setVisibility(View.INVISIBLE);
            summonerRankTitle.setVisibility(View.INVISIBLE);
            summonerWinRateTitle.setVisibility(View.INVISIBLE);
            summonerMainChampionsTitle.setVisibility(View.INVISIBLE);
        }
    }

    public void onErrorThrown(Throwable throwable, View view)
    {
        errorMessage = view.findViewById(R.id.errorMessage);
        errorMessage.setVisibility(View.VISIBLE);
        Log.d("ERROR", throwable.getMessage());
    }
}
