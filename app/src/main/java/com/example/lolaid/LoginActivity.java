package com.example.lolaid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import databases.LoLAidDatabase;
import databases.models.Champion;


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
        Thread populateDBThread = new Thread(() -> {
            populateDataBase();
        });

        int dbPopulatedID = sharedPrefs.getInt("dbPopulatedID", 0);

        if (dbPopulatedID == 0)
        {
            populateDBThread.start();
            sharedPrefs.edit().putInt("dbPopulatedID", 1).apply();
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

    private void populateDataBase()
    {
        //LoLAidDatabase.getInstance(this).ChampionDAO().deleteAllChampions();

        Gson gson = new Gson();
        String championsJSON = readChampionsJSON();

        Champion[] champions = gson.fromJson(championsJSON, Champion[].class);

        for (Champion champion : champions)
        {
            String championName = champion.getName();
            Log.d("CHAMPION_NAME", championName);
            String formattedName = formatStringToDB(championName);
            Log.d("FORMATTED_NAME", formattedName);
            champion.setName(championName);
            long championKey = champion.getKey();
            Log.d("CHAMPION_KEY", championKey + "");
            int championIconId = getResources().getIdentifier(formattedName, "drawable", getPackageName());
            Log.d("CHAMP_ICON_ID", championIconId + "");
            champion.setChampionIconId(championIconId);

            LoLAidDatabase.getInstance(this).ChampionDAO().insertChampion(champion);
        }

        //populateLatestChamps();
    }

    private String formatStringToDB(String stringNotFormatted)
    {
        String formatted = stringNotFormatted.toLowerCase().replaceAll("[' ]", "_").replace("dr.", "dr").replace("_&_willump", "");
        return formatted;
    }

    private String readChampionsJSON()
    {
        InputStream is = getResources().openRawResource(R.raw.champions);
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];

        try
        {
            Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            int n;
            while ((n = reader.read(buffer)) != -1)
            {
                writer.write(buffer, 0, n);
            }
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                is.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        String jsonString = writer.toString();

        return jsonString;
    }

    private void populateLatestChamps()
    {
        //Populate Gwen
        Champion championGwen = new Champion();
        String championNameGwen = "Gwen";
        String formattedNameGwen = formatStringToDB(championNameGwen);
        Log.d("FORMATTED_GWEN", championNameGwen);
        championGwen.setName(championNameGwen);
        long championKeyGwen = 887;
        championGwen.setKey(championKeyGwen);
        Log.d("CHAMPION_KEY", championKeyGwen + "");
        int championIconIdGwen = getResources().getIdentifier(formattedNameGwen, "drawable", getPackageName());
        championGwen.setChampionIconId(championIconIdGwen);
        Log.d("CHAMP_ICON_ID", championIconIdGwen + "");

        //Populate Rell
        Champion championRell = new Champion();
        String championNameRell = "Rell";
        String formattedNameRell = formatStringToDB(championNameRell);
        Log.d("FORMATTED_RELL", formattedNameRell);
        championRell.setName(championNameRell);
        long championKeyRell = 526;
        championRell.setKey(championKeyRell);
        Log.d("CHAMPION_KEY", championKeyRell + "");
        int championIconIdRell = getResources().getIdentifier(formattedNameRell, "drawable", getPackageName());
        championRell.setChampionIconId(championIconIdRell);
        Log.d("CHAMP_ICON_ID", championIconIdRell + "");

        //Populate Viego
        Champion championViego = new Champion();
        String championNameViego = "Viego";
        String formattedNameViego = formatStringToDB(championNameViego);
        Log.d("FORMATTED_VIEGO", formattedNameViego);
        championViego.setName(championNameViego);
        long championKeyViego = 234;
        championViego.setKey(championKeyViego);
        Log.d("CHAMPION_KEY", championKeyViego + "");
        int championIconIdViego = getResources().getIdentifier(formattedNameViego, "drawable", getPackageName());
        championViego.setChampionIconId(championIconIdViego);
        Log.d("CHAMP_ICON_ID", championIconIdViego + "");

        LoLAidDatabase.getInstance(this).ChampionDAO().insertChampion(championViego);
        LoLAidDatabase.getInstance(this).ChampionDAO().deleteChampion(championViego);
        LoLAidDatabase.getInstance(this).ChampionDAO().insertChampion(championViego);
        LoLAidDatabase.getInstance(this).ChampionDAO().insertChampion(championGwen);
        LoLAidDatabase.getInstance(this).ChampionDAO().insertChampion(championRell);


    }
}
