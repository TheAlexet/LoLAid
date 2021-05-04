package com.example.lolaid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Html;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.Locale;

import business_logic.services.RiotApiService;

public class MainActivity extends AppCompatActivity {

    private ActionBar actionBar;
    private SharedPreferences sharedPrefs;
    private Menu menu;
    private boolean showSettingsButton;
    Class<? extends Fragment> fragmentClass = null;
    Class<? extends Fragment> previousClass = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Loads the default fragment when the app starts
        fragmentClass = DefaultActivity.class;
        previousClass = DefaultActivity.class;
        if(fragmentClass != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.fcvView, fragmentClass, null)
                    .commit();
        }

        //Set-up of the action bar
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);
        showSettingsButton = true;
        setTitle(Html.fromHtml("<font color=\"yellow\">" + getString(R.string.app_name) + "</font>"));

        //Sets language of the app at the starting point based on our sharedprefs
        sharedPrefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        switch(sharedPrefs.getInt("language", 0)) {
            case 0:
                setLocale(Locale.getDefault().toLanguageTag());
                break;
            case 1:
                setLocale("es");
                break;
            case 2:
                setLocale("ca");
                break;
        }

        //WrapperRiotAPI riotAPI = new WrapperRiotAPI();
        //riotAPI.printSummonerInfoTest();
        //WrapperRiotApi2 wrapper2 = new WrapperRiotApi2();
        /*new Thread(() -> {
            WrapperRiotAPI riotAPI = new WrapperRiotAPI();
            riotAPI.printSummonerInfoTest();
            riotAPI.printChamps();
            riotAPI.printSummonerSpells();
            //WrapperRiotApi2 wrapper2 = new WrapperRiotApi2();
        }).start();*/
        /*
        //ASYNC WAY (AUTO CREATION CREATION)
        RiotApiService riotApiServiceAsync = new RiotApiService();
        riotApiServiceAsync.printSummonerByNameTestAsync();

        //SYNC WAY (MANUAL THREAD CREATION)
        new Thread(() ->
        {
            RiotApiService riotApiService = new RiotApiService();
            riotApiService.printSummonerByNameTest();
        }).start();
        */
        //Intent intent = new Intent(this, TestJobIntentService.class);
        //startService(intent);
        RiotApiService riotApiService = new RiotApiService();
        Log.d("INFO", "I'm getting called");
        //riotApiService.printSummonerByNameTest();
        //riotApiService.getLeagueEntriesWithSummonerId("");
        //riotApiService.getMatchByMatchId("");
        //riotApiService.getSummonerByName("");
        //riotApiService.getCurrentMatchInfo("pabletefest");
        //riotApiService.getPlayerStatsInfo("pabletefest");
        //riotApiService.getMatchInfo("pabletefest");
        Log.d("INFO", "I got called");
    }

    //Action bar buttons set-up
    @Override
    public boolean onCreateOptionsMenu(Menu newMenu) {
        getMenuInflater().inflate(R.menu.main_menu, newMenu);
        menu = newMenu;
        if(showSettingsButton) {
            menu.findItem(R.id.settingsOption).setVisible(true);
        } else {
            menu.findItem(R.id.settingsOption).setVisible(false);
        }
        return true;
    }

    //Manages the action bar buttons
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                if(fragmentClass == SettingsActivity.class) {
                    fragmentClass = previousClass;
                    if(previousClass == DefaultActivity.class) {
                        setTitle(Html.fromHtml("<font color=\"yellow\">" + getString(R.string.app_name) + "</font>"));
                        actionBar.setDisplayHomeAsUpEnabled(false);
                    } else if(previousClass == StatsActivity.class) {
                        setTitle(Html.fromHtml("<font color=\"yellow\">" + getString(R.string.stats_title) + "</font>"));
                    } else if(previousClass == HistoryActivity.class) {
                        setTitle(Html.fromHtml("<font color=\"yellow\">" + getString(R.string.history_title) + "</font>"));
                    } else if(previousClass == RecommendationsActivity.class) {
                        setTitle(Html.fromHtml("<font color=\"yellow\">" + getString(R.string.recommendations_title) + "</font>"));
                    } else if(previousClass == LiveGameActivity.class) {
                        setTitle(Html.fromHtml("<font color=\"yellow\">" + getString(R.string.live_game_title) + "</font>"));
                    }
                } else {
                    fragmentClass = DefaultActivity.class;
                    actionBar.setDisplayHomeAsUpEnabled(false);
                    setTitle(Html.fromHtml("<font color=\"yellow\">" + getString(R.string.app_name) + "</font>"));
                }
                menu.findItem(R.id.settingsOption).setVisible(true);
                break;

            case R.id.settingsOption:
                fragmentClass = SettingsActivity.class;
                actionBar.setDisplayHomeAsUpEnabled(true);
                menu.findItem(R.id.settingsOption).setVisible(false);
                setTitle(Html.fromHtml("<font color=\"yellow\">" + getString(R.string.settings_title) + "</font>"));
                break;
        }

        if(fragmentClass != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.fcvView, fragmentClass, null, fragmentClass.getSimpleName())
                    .commit();
        }

        return super.onOptionsItemSelected(item);
    }

    //Manages the bottom bar buttons
    public void changeFragment(View view) {
        if (view.getId() == R.id.bStats) {
            fragmentClass = StatsActivity.class;
            previousClass = StatsActivity.class;
            setTitle(Html.fromHtml("<font color=\"yellow\">" + getString(R.string.stats_title) + "</font>"));
        } else if(view.getId() == R.id.bHistory) {
            fragmentClass = HistoryActivity.class;
            previousClass = HistoryActivity.class;
            setTitle(Html.fromHtml("<font color=\"yellow\">" + getString(R.string.history_title) + "</font>"));
        } else if(view.getId() == R.id.bRecommendations) {
            fragmentClass = RecommendationsActivity.class;
            previousClass = RecommendationsActivity.class;
            setTitle(Html.fromHtml("<font color=\"yellow\">" + getString(R.string.recommendations_title) + "</font>"));
        } else if(view.getId() == R.id.bLiveGame) {
            fragmentClass = LiveGameActivity.class;
            previousClass = LiveGameActivity.class;
            setTitle(Html.fromHtml("<font color=\"yellow\">" + getString(R.string.live_game_title) + "</font>"));
        }

        actionBar.setDisplayHomeAsUpEnabled(true);
        showSettingsButton = true;
        if(fragmentClass != null) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.fcvView, fragmentClass, null)
                        .commit();
        }
    }

    //Sets the language of the application
    public void setLocale(String lang) {
        Locale myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
    }
}