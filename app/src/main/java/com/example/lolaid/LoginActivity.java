package com.example.lolaid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;


public class LoginActivity  extends AppCompatActivity {

    private EditText summonerName;
    private Spinner region;
    private SharedPreferences sharedPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        summonerName = findViewById(R.id.loginSummonerNameBox);
        region = findViewById(R.id.loginRegionSpinner);

        sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);

        //Sets language of the app at the starting point based on our sharedprefs
        sharedPrefs = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
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
    }

    public void startButtonHandler(View v) {

        String newSummonerName = summonerName.getText().toString();

        if(newSummonerName.equals("")) {
            Toast.makeText(LoginActivity.this, R.string.login_failed, Toast.LENGTH_SHORT).show();
        } else {
            SharedPreferences.Editor editor = sharedPrefs.edit();
            editor.putString("summonerName", newSummonerName);
            editor.putInt("region", region.getSelectedItemPosition());
            editor.apply();

            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
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
