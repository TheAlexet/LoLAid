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

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

import adapters.LiveGameAdapter;
import business_logic.data_models.custom_pojo.LiveMatchInfo;
import business_logic.services.RiotApiService;
import databases.LoLAidDatabase;
import databases.models.Champion;
import databases.models.Rune;
import databases.models.SummonerSpell;

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
    private TextView summNameTitle;
    private TextView gameTimeTitle;
    private TextView teamTitle;
    private TextView summSpellsTitle;
    private TextView runesTitle;
    private TextView errorMessage;
    private ProgressBar progressBar;

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
        summNameTitle = view.findViewById(R.id.summonerNameTitleLive);
        gameTimeTitle = view.findViewById(R.id.gameTime);
        teamTitle = view.findViewById(R.id.TeamTitleLive);
        summSpellsTitle = view.findViewById(R.id.summonerSpells);
        runesTitle = view.findViewById(R.id.RunesTitle);
        progressBar = view.findViewById(R.id.progressBarLive);
        errorMessage = view.findViewById(R.id.playerNotLive);
        summName = sharedPrefs.getString("summonerName", "Jose");

        hideAll();

        apiService.getCurrentMatchInfo(summName, this);

        return view;
    }

    private void hideAll(){
        championName.setVisibility(View.INVISIBLE);
        championIcon.setVisibility(View.INVISIBLE);
        summSpell1.setVisibility(View.INVISIBLE);
        summSpell2.setVisibility(View.INVISIBLE);
        mainRune.setVisibility(View.INVISIBLE);
        subRune.setVisibility(View.INVISIBLE);
        summonerName.setVisibility(View.INVISIBLE);
        gameTime.setVisibility(View.INVISIBLE);
        team.setVisibility(View.INVISIBLE);
        summNameTitle.setVisibility(View.INVISIBLE);
        gameTimeTitle.setVisibility(View.INVISIBLE);
        teamTitle.setVisibility(View.INVISIBLE);
        summSpellsTitle.setVisibility(View.INVISIBLE);
        runesTitle.setVisibility(View.INVISIBLE);
        errorMessage.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
    }

    public void errorNotLive(Throwable throwable){
        championName.setVisibility(View.INVISIBLE);
        championIcon.setVisibility(View.INVISIBLE);
        summSpell1.setVisibility(View.INVISIBLE);
        summSpell2.setVisibility(View.INVISIBLE);
        mainRune.setVisibility(View.INVISIBLE);
        subRune.setVisibility(View.INVISIBLE);
        summonerName.setVisibility(View.INVISIBLE);
        gameTime.setVisibility(View.INVISIBLE);
        team.setVisibility(View.INVISIBLE);
        summNameTitle.setVisibility(View.INVISIBLE);
        gameTimeTitle.setVisibility(View.INVISIBLE);
        teamTitle.setVisibility(View.INVISIBLE);
        summSpellsTitle.setVisibility(View.INVISIBLE);
        runesTitle.setVisibility(View.INVISIBLE);
        errorMessage.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.INVISIBLE);

        Log.d("ERROR", throwable.getMessage());
    }

    public void showAll(){
        championName.setVisibility(View.VISIBLE);
        championIcon.setVisibility(View.VISIBLE);
        summSpell1.setVisibility(View.VISIBLE);
        summSpell2.setVisibility(View.VISIBLE);
        mainRune.setVisibility(View.VISIBLE);
        subRune.setVisibility(View.VISIBLE);
        summonerName.setVisibility(View.VISIBLE);
        gameTime.setVisibility(View.VISIBLE);
        team.setVisibility(View.VISIBLE);
        summNameTitle.setVisibility(View.VISIBLE);
        gameTimeTitle.setVisibility(View.VISIBLE);
        teamTitle.setVisibility(View.VISIBLE);
        summSpellsTitle.setVisibility(View.VISIBLE);
        runesTitle.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.INVISIBLE);
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

        Thread runesThread = new Thread(() -> {
            Rune currentMainRuneInfo = LoLAidDatabase.getInstance(getActivity()).RuneDAO().getRune(matchInfo.getPerkStyle());
            Rune currentSubRuneInfo = LoLAidDatabase.getInstance(getActivity()).RuneDAO().getRune(matchInfo.getPerkSubStyle());

            getActivity().runOnUiThread(() -> {
                mainRune.setImageResource(currentMainRuneInfo.getIconId());
                subRune.setImageResource(currentSubRuneInfo.getIconId());
            });
        });

        Thread summSpellsThread = new Thread(() -> {
            SummonerSpell currentSummSpell1Info = LoLAidDatabase.getInstance(getActivity()).SummonerSpellDAO().getSummonerSpell(matchInfo.getSpell1Id());
            SummonerSpell currentSummSpell2Info = LoLAidDatabase.getInstance(getActivity()).SummonerSpellDAO().getSummonerSpell(matchInfo.getSpell2Id());
            getActivity().runOnUiThread(() -> {
                summSpell1.setImageResource(currentSummSpell1Info.getIconId());
                summSpell2.setImageResource(currentSummSpell2Info.getIconId());
            });
        });


        championThread.start();
        //runesThread.start();
        //summSpellsThread.start();
        showAll();
        //championIcon.setImageResource();
        //summSpell1.setImageResource();
        //summSpell2.setImageResource();
        //mainRune.setImageResource();
        //subRune.setImageResource();
    }

}
