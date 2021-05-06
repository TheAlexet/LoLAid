package com.example.lolaid;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.math.RoundingMode;
import java.text.DecimalFormat;

import business_logic.data_models.custom_pojo.PlayerStatsInfo;
import business_logic.data_models.custom_pojo.RecomendationsInfo;
import business_logic.services.RiotApiService;

public class RecommendationsActivity extends Fragment {

    private SharedPreferences sharedPrefs;
    private TextView rec1;
    private ImageView rec1Circle;
    private TextView rec2;
    private ImageView rec2Circle;
    private TextView rec3;
    private ImageView rec3Circle;
    private TextView errorMessage;

    private RiotApiService apiService;

    public RecommendationsActivity() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recommendations_activity, container, false);
        apiService = new RiotApiService();

        rec1 =  view.findViewById(R.id.rec1);
        rec1Circle =  view.findViewById(R.id.rec1_circle);
        rec2 =  view.findViewById(R.id.rec2);
        rec2Circle =  view.findViewById(R.id.rec2_circle);
        rec3 =  view.findViewById(R.id.rec3);
        rec3Circle =  view.findViewById(R.id.rec3_circle);
        errorMessage = view.findViewById(R.id.errorMessageRecommendations);

        sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dataLoaded(false);
        apiService.getRecomendationsInfo(sharedPrefs.getString("summonerName", ""), this, view);
    }

    public void getRecommendationsRest(RecomendationsInfo recomendationsInfo) {
        dataLoaded(true);

        String tier = recomendationsInfo.getTier();
        showRecommendation(tier);

        float winRate = (float) recomendationsInfo.getWins() / (recomendationsInfo.getWins() + recomendationsInfo.getLosses()) * 100;
        showRecommendation(winRate);

        int masteryPoints1 = recomendationsInfo.getTop1championPoints();
        int masteryPoints2 = recomendationsInfo.getTop2championPoints();
        int masteryPoints3 = recomendationsInfo.getTop3championPoints();

        showRecommendation(masteryPoints1, masteryPoints2, masteryPoints3);
    }

    private void showRecommendation(String tier)
    {
        switch (tier)
        {
            case "IRON":
                rec1.setText(R.string.rec1_iron);
                break;
            case "BRONZE":
                rec1.setText(R.string.rec1_bronze);
                break;
            case "SILVER":
                rec1.setText(R.string.rec1_silver);
                break;
            case "GOLD":
                rec1.setText(R.string.rec1_gold);
                break;
            case "PLATINUM":
                rec1.setText(R.string.rec1_platinum);
                break;
            case "DIAMOND":
                rec1.setText(R.string.rec1_diamond);
                break;

            default:
                rec1.setText(R.string.rec1_master);
                break;
        }
    }

    private void showRecommendation(float winRate)
    {
        if(winRate > 51)
        {
            rec2.setText(R.string.rec2_positive);
        }
        else if (winRate < 51)
        {
            rec2.setText(R.string.rec2_negative);
        }
        else
        {
            rec2.setText(R.string.rec2_neutral);
        }
    }

    private void showRecommendation(int mastery1, int mastery2, int mastery3)
    {
        if(mastery1 < 100000)
        {
            rec3.setText(R.string.rec3_no_champs);
        }
        else if(mastery2 < 100000)
        {
            rec3.setText(R.string.rec3_one_champs);
        }
        else if(mastery3 < 100000)
        {
            rec3.setText(R.string.rec3_two_champs);
        }
        else
        {
            rec3.setText(R.string.rec3_three_champs);
        }
    }

    private void dataLoaded(boolean loaded)
    {
        if(loaded)
        {
            errorMessage.setVisibility(View.INVISIBLE);
            rec1.setVisibility(View.VISIBLE);

            rec1Circle.setVisibility(View.VISIBLE);

            rec2.setVisibility(View.VISIBLE);

            rec2Circle.setVisibility(View.VISIBLE);

            rec3.setVisibility(View.VISIBLE);

            rec3Circle.setVisibility(View.VISIBLE);
        }
        else
        {
            errorMessage.setVisibility(View.INVISIBLE);

            rec1.setVisibility(View.INVISIBLE);

            rec1Circle.setVisibility(View.INVISIBLE);

            rec2.setVisibility(View.INVISIBLE);

            rec2Circle.setVisibility(View.INVISIBLE);

            rec3.setVisibility(View.INVISIBLE);

            rec3Circle.setVisibility(View.INVISIBLE);
        }
    }

    public void onErrorThrown(Throwable throwable, View view)
    {
        errorMessage = view.findViewById(R.id.errorMessageRecommendations);
        errorMessage.setVisibility(View.VISIBLE);
        Log.d("ERROR", throwable.getMessage());
    }
}
