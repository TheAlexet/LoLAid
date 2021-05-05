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

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

import adapters.LiveGameAdapter;
import business_logic.data_models.custom_pojo.LiveMatchInfo;
import business_logic.services.RiotApiService;
import databases.LoLAidDatabase;
import databases.models.Champion;

public class LiveGameActivity extends Fragment {

    private RiotApiService apiService;
    private ImageView championIcon;
    private ImageView summSpell1;
    private ImageView summSpell2;
    private ImageView mainRune;
    private ImageView subRune;
    private TextView championName;
    private TextView summonerName;
    private TextView gameTime;
    private TextView team;
    private LiveMatchInfo matchInfo;
    private SharedPreferences sharedPrefs;
    private String summName;

    public LiveGameActivity() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.live_game__one_player_activity, container, false);
        sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        apiService = new RiotApiService();
        championIcon = view.findViewById(R.id.championIcon);
        summSpell1 = view.findViewById(R.id.summonerSpell1);
        summSpell2 = view.findViewById(R.id.summonerSpell2);
        mainRune = view.findViewById(R.id.mainRune);
        subRune = view.findViewById(R.id.subRune);
        championName = view.findViewById(R.id.championName);
        summonerName = view.findViewById(R.id.summonerNameLive);
        gameTime = view.findViewById(R.id.gameTimeNumber);
        team = view.findViewById(R.id.TeamLive);
        summName = sharedPrefs.getString("summonerName", "Jose");
        apiService.getCurrentMatchInfo(summName, this);
        return view;
    }

    public void getLiveMatchInfo(LiveMatchInfo matchInfo){
        summonerName.setText(matchInfo.getSummonerName());
        Timestamp gameStartTime = new Timestamp(matchInfo.getGameStartTime());
        long gameDurationMilliseconds = System.currentTimeMillis() - gameStartTime.getTime();
        int gameMinutes = (int) (gameDurationMilliseconds/1000)/60;
        int gameSeconds = (int) (gameDurationMilliseconds/1000)%60;
        String secondsFormatted = gameSeconds >= 10 ? Integer.toString(gameSeconds) : "0" + Integer.toString(gameSeconds);
        String gameLength = Integer.toString(gameMinutes) + ":" + secondsFormatted;
        gameTime.setText(gameLength);
        String teamName = "Team";
        if(matchInfo.getTeamId() == 100){
            teamName = getString(R.string.blue_team);
        }else if(matchInfo.getTeamId() == 200){
            teamName = getString(R.string.red_team);
        }
        team.setText(teamName);

        Thread championThread = new Thread(() -> {
            Champion currentChampionInfo = LoLAidDatabase.getInstance(getActivity()).ChampionDAO().getChampion(matchInfo.getChampionId());
            getActivity().runOnUiThread(() -> {
                championName.setText(currentChampionInfo.getName());
                championIcon.setImageResource(currentChampionInfo.getChampionIconId());
            });
        });

        championThread.start();
        //championIcon.setImageResource();
        //summSpell1.setImageResource();
        //summSpell2.setImageResource();
        //mainRune.setImageResource();
        //subRune.setImageResource();
    }

}
