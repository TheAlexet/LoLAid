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
import business_logic.services.RiotApiService;
import databases.LoLAidDatabase;
import databases.models.Champion;

public class StatsActivity extends Fragment {

    private SharedPreferences sharedPrefs;
    private TextView summonerName;
    private TextView summonerLevel;
    private TextView summonerRank;
    private TextView summonerWinRate;
    private ImageView mainChampion1image;
    private ImageView mainChampion2image;
    private ImageView mainChampion3image;
    private TextView mainChampion1value;
    private TextView mainChampion2value;
    private TextView mainChampion3value;

    private RiotApiService apiService;

    public StatsActivity() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        apiService = new RiotApiService();
        sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        apiService.getPlayerStatsInfo(sharedPrefs.getString("summonerName", ""), this);
        return inflater.inflate(R.layout.stats_activity, container, false);
    }

    public void getPlayerStatsRest(PlayerStatsInfo playerInfo) {

        summonerName =  getView().findViewById(R.id.statsSummonerNameValue);
        summonerName.setText(playerInfo.getSummonerName());

        summonerLevel =  getView().findViewById(R.id.statsSummonerLevelValue);
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
        Log.d("CHAMP1", champ1 + "");
        Log.d("CHAMP2", champ2 + "");
        Log.d("CHAMP3", champ3 + "");
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
            case "anivia":
                icon = R.drawable.anivia;
                break;
            case "annie":
                icon = R.drawable.annie;
                break;
            case "aphelios":
                icon = R.drawable.aphelios;
                break;
            case "ashe":
                icon = R.drawable.ashe;
                break;
            case "aurelion_sol":
                icon = R.drawable.aurelion_sol;
                break;
            case "azir":
                icon = R.drawable.azir;
                break;
            case "bard":
                icon = R.drawable.bard;
                break;
            case "blitzcrank":
                icon = R.drawable.blitzcrank;
                break;
            case "brand":
                icon = R.drawable.brand;
                break;
            case "braum":
                icon = R.drawable.braum;
                break;
            case "caitlyn":
                icon = R.drawable.caitlyn;
                break;
            case "camille":
                icon = R.drawable.camille;
                break;
            case "cassiopeia":
                icon = R.drawable.cassiopeia;
                break;
            case "chogath":
                icon = R.drawable.cho_gath;
                break;
            case "corki":
                icon = R.drawable.corki;
                break;
            case "darius":
                icon = R.drawable.darius;
                break;
            case "diana":
                icon = R.drawable.diana;
                break;
            case "draven":
                icon = R.drawable.draven;
                break;
            case "ekko":
                icon = R.drawable.ekko;
                break;
            case "elise":
                icon = R.drawable.elise;
                break;
            case "evelynn":
                icon = R.drawable.evelynn;
                break;
            case "ezreal":
                icon = R.drawable.ezreal;
                break;
            case "fiddlesticks":
                icon = R.drawable.fiddlesticks;
                break;
            case "fiora":
                icon = R.drawable.fiora;
                break;
            case "fizz":
                icon = R.drawable.fizz;
                break;
            case "galio":
                icon = R.drawable.galio;
                break;
            case "gangplank":
                icon = R.drawable.gangplank;
                break;
            case "garen":
                icon = R.drawable.garen;
                break;
            case "gnar":
                icon = R.drawable.gnar;
                break;
            case "gragas":
                icon = R.drawable.gragas;
                break;
            case "graves":
                icon = R.drawable.graves;
                break;
            case "gwen":
                icon = R.drawable.gwen;
                break;
            case "hecarim":
                icon = R.drawable.hecarim;
                break;
            case "heimerdinger":
                icon = R.drawable.heimerdinger;
                break;
            case "illaoi":
                icon = R.drawable.illaoi;
                break;
            case "irelia":
                icon = R.drawable.irelia;
                break;
            case "ivern":
                icon = R.drawable.ivern;
                break;
            case "janna":
                icon = R.drawable.janna;
                break;
            case "jarvan_iv":
                icon = R.drawable.jarvan_iv;
                break;
            case "jax":
                icon = R.drawable.jax;
                break;
            case "jayce":
                icon = R.drawable.jayce;
                break;
            case "jhin":
                icon = R.drawable.jhin;
                break;
            case "jinx":
                icon = R.drawable.jinx;
                break;
            case "kaisa":
                icon = R.drawable.kaisa;
                break;
            case "kalista":
                icon = R.drawable.kalista;
                break;
            case "karma":
                icon = R.drawable.karma;
                break;
            case "karthus":
                icon = R.drawable.karthus;
                break;
            case "kassadin":
                icon = R.drawable.kassadin;
                break;
            case "katarina":
                icon = R.drawable.katarina;
                break;
            case "kayle":
                icon = R.drawable.kayle;
                break;
            case "kayn":
                icon = R.drawable.kayn;
                break;
            case "kennen":
                icon = R.drawable.kennen;
                break;
            case "khazix":
                icon = R.drawable.kha_zix;
                break;
            case "kindred":
                icon = R.drawable.kindred;
                break;
            case "kled":
                icon = R.drawable.kled;
                break;
            case "kogmaw":
                icon = R.drawable.kog_maw;
                break;
            case "leblanc":
                icon = R.drawable.leblanc;
                break;
            case "lee_sin":
                icon = R.drawable.lee_sin;
                break;
            case "leona":
                icon = R.drawable.leona;
                break;
            case "lillia":
                icon = R.drawable.lillia;
                break;
            case "lissandra":
                icon = R.drawable.lissandra;
                break;
            case "lucian":
                icon = R.drawable.lucian;
                break;
            case "lulu":
                icon = R.drawable.lulu;
                break;
            case "lux":
                icon = R.drawable.lux;
                break;
            case "malphite":
                icon = R.drawable.malphite;
                break;
            case "malzahar":
                icon = R.drawable.malzahar;
                break;
            case "maokai":
                icon = R.drawable.maokai;
                break;
            case "master_yi":
                icon = R.drawable.master_yi;
                break;
            case "miss_fortune":
                icon = R.drawable.miss_fortune;
                break;
            case "mordekaiser":
                icon = R.drawable.mordekaiser;
                break;
            case "morgana":
                icon = R.drawable.morgana;
                break;
            case "mundo":
                icon = R.drawable.dr_mundo;
                break;
            case "nami":
                icon = R.drawable.nami;
                break;
            case "nasus":
                icon = R.drawable.nasus;
                break;
            case "nautilus":
                icon = R.drawable.nautilus;
                break;
            case "neeko":
                icon = R.drawable.neeko;
                break;
            case "nidalee":
                icon = R.drawable.nidalee;
                break;
            case "nocturne":
                icon = R.drawable.nocturne;
                break;
            case "nunu":
                icon = R.drawable.nunu;
                break;
            case "olaf":
                icon = R.drawable.olaf;
                break;
            case "orianna":
                icon = R.drawable.orianna;
                break;
            case "ornn":
                icon = R.drawable.ornn;
                break;
            case "pantheon":
                icon = R.drawable.pantheon;
                break;
            case "poppy":
                icon = R.drawable.poppy;
                break;
            case "pyke":
                icon = R.drawable.pyke;
                break;
            case "qiyana":
                icon = R.drawable.qiyana;
                break;
            case "quinn":
                icon = R.drawable.quinn;
                break;
            case "rakan":
                icon = R.drawable.rakan;
                break;
            case "rammus":
                icon = R.drawable.rammus;
                break;
            case "rek_sai":
                icon = R.drawable.rek_sai;
                break;
            case "renekton":
                icon = R.drawable.renekton;
                break;
            case "rengar":
                icon = R.drawable.rengar;
                break;
            case "riven":
                icon = R.drawable.riven;
                break;
            case "rumble":
                icon = R.drawable.rumble;
                break;
            case "ryze":
                icon = R.drawable.ryze;
                break;
            case "sejuani":
                icon = R.drawable.sejuani;
                break;
            case "senna":
                icon = R.drawable.senna;
                break;
            case "sett":
                icon = R.drawable.sett;
                break;
            case "shaco":
                icon = R.drawable.shaco;
                break;
            case "shen":
                icon = R.drawable.shen;
                break;
            case "shyvana":
                icon = R.drawable.shyvana;
                break;
            case "singed":
                icon = R.drawable.singed;
                break;
            case "sion":
                icon = R.drawable.sion;
                break;
            case "sivir":
                icon = R.drawable.sivir;
                break;
            case "skarner":
                icon = R.drawable.skarner;
                break;
            case "sona":
                icon = R.drawable.sona;
                break;
            case "soraka":
                icon = R.drawable.soraka;
                break;
            case "swain":
                icon = R.drawable.swain;
                break;
            case "sylas":
                icon = R.drawable.sylas;
                break;
            case "syndra":
                icon = R.drawable.syndra;
                break;
            case "tahm_kench":
                icon = R.drawable.tahm_kench;
                break;
            case "taliyah":
                icon = R.drawable.taliyah;
                break;
            case "talon":
                icon = R.drawable.talon;
                break;
            case "taric":
                icon = R.drawable.taric;
                break;
            case "teemo":
                icon = R.drawable.teemo;
                break;
            case "thresh":
                icon = R.drawable.thresh;
                break;
            case "tristana":
                icon = R.drawable.tristana;
                break;
            case "trundle":
                icon = R.drawable.trundle;
                break;
            case "tryndamere":
                icon = R.drawable.tryndamere;
                break;
            case "twisted_fate":
                icon = R.drawable.twisted_fate;
                break;
            case "twitch":
                icon = R.drawable.twitch;
                break;
            case "udyr":
                icon = R.drawable.udyr;
                break;
            case "urgot":
                icon = R.drawable.urgot;
                break;
            case "varus":
                icon = R.drawable.varus;
                break;
            case "vayne":
                icon = R.drawable.vayne;
                break;
            case "veigar":
                icon = R.drawable.veigar;
                break;
            case "velkoz":
                icon = R.drawable.vel_koz;
                break;
            case "vi":
                icon = R.drawable.vi;
                break;
            case "viego":
                icon = R.drawable.viego;
                break;
            case "viktor":
                icon = R.drawable.viktor;
                break;
            case "vladimir":
                icon = R.drawable.vladimir;
                break;
            case "volibear":
                icon = R.drawable.volibear;
                break;
            case "warwick":
                icon = R.drawable.warwick;
                break;
            case "wukong":
                icon = R.drawable.wukong;
                break;
            case "xayah":
                icon = R.drawable.xayah;
                break;
            case "xerath":
                icon = R.drawable.xerath;
                break;
            case "xin_zhao":
                icon = R.drawable.xin_zhao;
                break;
            case "yasuo":
                icon = R.drawable.yasuo;
                break;
            case "yorick":
                icon = R.drawable.yorick;
                break;
            case "yuumi":
                icon = R.drawable.yuumi;
                break;
            case "zac":
                icon = R.drawable.zac;
                break;
            case "zed":
                icon = R.drawable.zed;
                break;
            case "ziggs":
                icon = R.drawable.ziggs;
                break;
            case "zilean":
                icon = R.drawable.zilean;
                break;
            case "zoe":
                icon = R.drawable.zoe;
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
            case "anivia":
                name = "Anivia";
                break;
            case "annie":
                name = "Annie";
                break;
            case "aphelios":
                name = "Aphelios";
                break;
            case "ashe":
                name = "Ashe";
                break;
            case "aurelion_sol":
                name = "Aurelion Sol";
                break;
            case "azir":
                name = "Azir";
                break;
            case "bard":
                name = "Bard";
                break;
            case "blitzcrank":
                name = "Blitzcranck";
                break;
            case "brand":
                name = "Brand";
                break;
            case "braum":
                name = "Braum";
                break;
            case "caitlyn":
                name = "Caitlyn";
                break;
            case "camille":
                name = "Camille";
                break;
            case "cassiopeia":
                name = "Cassiopeia";
                break;
            case "chogath":
                name = "Chogath";
                break;
            case "corki":
                name = "Corki";
                break;
            case "darius":
                name = "Darius";
                break;
            case "diana":
                name = "Diana";
                break;
            case "draven":
                name = "draven";
                break;
            case "ekko":
                name = "Ekko";
                break;
            case "elise":
                name = "Elise";
                break;
            case "evelynn":
                name = "Evelynn";
                break;
            case "ezreal":
                name = "Ezreal";
                break;
            case "fiddlesticks":
                name = "Fiddlesticks";
                break;
            case "fiora":
                name = "fiora";
                break;
            case "fizz":
                name = "Fizz";
                break;
            case "galio":
                name = "Galio";
                break;
            case "gangplank":
                name = "Gangplank";
                break;
            case "garen":
                name = "Garen";
                break;
            case "gnar":
                name = "Gnar";
                break;
            case "gragas":
                name = "Gragas";
                break;
            case "graves":
                name = "Graves";
                break;
            case "gwen":
                name = "Gwen";
                break;
            case "hecarim":
                name = "Hecarim";
                break;
            case "heimerdinger":
                name = "Heimerdinger";
                break;
            case "illaoi":
                name = "Illaoi";
                break;
            case "irelia":
                name = "Irelia";
                break;
            case "ivern":
                name = "Ivern";
                break;
            case "janna":
                name = "Janna";
                break;
            case "jarvan_iv":
                name = "Jarvan IV";
                break;
            case "jax":
                name = "Jax";
                break;
            case "jayce":
                name = "Jayce";
                break;
            case "jhin":
                name = "Jhin";
                break;
            case "jinx":
                name = "Jinx";
                break;
            case "kaisa":
                name = "Kai'sa";
                break;
            case "kalista":
                name = "Kalista";
                break;
            case "karma":
                name = "Karma";
                break;
            case "karthus":
                name = "Karthus";
                break;
            case "kassadin":
                name = "Kassadin";
                break;
            case "katarina":
                name = "Katarina";
                break;
            case "kayle":
                name = "Kayle";
                break;
            case "kayn":
                name = "Kayn";
                break;
            case "kennen":
                name = "Kennen";
                break;
            case "khazix":
                name = "Kha'zix";
                break;
            case "kindred":
                name = "Kindred";
                break;
            case "kled":
                name = "Kled";
                break;
            case "kogmaw":
                name = "Kog'maw";
                break;
            case "leblanc":
                name = "LeBlanc";
                break;
            case "lee_sin":
                name = "Lee Sin";
                break;
            case "leona":
                name = "Leona";
                break;
            case "lillia":
                name = "Lillia";
                break;
            case "lissandra":
                name = "Lissandra";
                break;
            case "lucian":
                name = "Lucian";
                break;
            case "lulu":
                name = "Lulu";
                break;
            case "lux":
                name = "Lux";
                break;
            case "malphite":
                name = "Malphite";
                break;
            case "malzahar":
                name = "Malzahar";
                break;
            case "maokai":
                name = "Maokai";
                break;
            case "master_yi":
                name = "Master Yi";
                break;
            case "miss_fortune":
                name = "Miss Fortune";
                break;
            case "mordekaiser":
                name = "Mordekaiser";
                break;
            case "morgana":
                name = "morgana";
                break;
            case "mundo":
                name = "Mundo";
                break;
            case "nami":
                name = "Nami";
                break;
            case "nasus":
                name = "Nasus";
                break;
            case "nautilus":
                name = "Nautilus";
                break;
            case "neeko":
                name = "Neeko";
                break;
            case "nidalee":
                name = "Nidalee";
                break;
            case "nocturne":
                name = "Nocturne";
                break;
            case "nunu":
                name = "Nunu";
                break;
            case "olaf":
                name = "Olaf";
                break;
            case "orianna":
                name = "Orianna";
                break;
            case "ornn":
                name = "Ornn";
                break;
            case "pantheon":
                name = "Pantheon";
                break;
            case "poppy":
                name = "Poppy";
                break;
            case "pyke":
                name = "Pyke";
                break;
            case "qiyana":
                name = "Qiyana";
                break;
            case "quinn":
                name = "Quinn";
                break;
            case "rakan":
                name = "Rakan";
                break;
            case "rammus":
                name = "Rammus";
                break;
            case "rek_sai":
                name = "Rek'Sai";
                break;
            case "renekton":
                name = "Renekton";
                break;
            case "rengar":
                name = "Rengar";
                break;
            case "riven":
                name = "Riven";
                break;
            case "rumble":
                name = "Rumble";
                break;
            case "ryze":
                name = "Ryze";
                break;
            case "sejuani":
                name = "Sejuani";
                break;
            case "senna":
                name = "Senna";
                break;
            case "sett":
                name = "Sett";
                break;
            case "shaco":
                name = "Shaco";
                break;
            case "shen":
                name = "Shen";
                break;
            case "shyvana":
                name = "Shyvana";
                break;
            case "singed":
                name = "Singed";
                break;
            case "sion":
                name = "Sion";
                break;
            case "sivir":
                name = "Sivir";
                break;
            case "skarner":
                name = "Skarner";
                break;
            case "sona":
                name = "Sona";
                break;
            case "soraka":
                name = "Soraka";
                break;
            case "swain":
                name = "Swain";
                break;
            case "sylas":
                name = "Sylas";
                break;
            case "syndra":
                name = "Syndra";
                break;
            case "tahm_kench":
                name = "Tahm Kench";
                break;
            case "taliyah":
                name = "Taliyah";
                break;
            case "talon":
                name = "Talon";
                break;
            case "taric":
                name = "Taric";
                break;
            case "teemo":
                name = "Teemo";
                break;
            case "thresh":
                name = "Thresh";
                break;
            case "tristana":
                name = "Tristana";
                break;
            case "trundle":
                name = "Trundle";
                break;
            case "tryndamere":
                name = "Tryndamere";
                break;
            case "twisted_fate":
                name = "Twisted Fate";
                break;
            case "twitch":
                name = "Twitch";
                break;
            case "udyr":
                name = "Udyr";
                break;
            case "urgot":
                name = "Urgot";
                break;
            case "varus":
                name = "Varus";
                break;
            case "vayne":
                name = "Vayne";
                break;
            case "veigar":
                name = "Veigar";
                break;
            case "velkoz":
                name = "Vel'koz";
                break;
            case "vi":
                name = "Vi";
                break;
            case "viego":
                name = "Viego";
                break;
            case "viktor":
                name = "Viktor";
                break;
            case "vladimir":
                name = "Vladimir";
                break;
            case "volibear":
                name = "Volibear";
                break;
            case "warwick":
                name = "Warwick";
                break;
            case "wukong":
                name = "Wukong";
                break;
            case "xayah":
                name = "Xayah";
                break;
            case "xerath":
                name = "Xerath";
                break;
            case "xin_zhao":
                name = "Xin Zhao";
                break;
            case "yasuo":
                name = "Yasuo";
                break;
            case "yorick":
                name = "Yorick";
                break;
            case "yuumi":
                name = "Yuumi";
                break;
            case "zac":
                name = "Zac";
                break;
            case "zed":
                name = "Zed";
                break;
            case "ziggs":
                name = "Ziggs";
                break;
            case "zilean":
                name = "Zilean";
                break;
            case "zoe":
                name = "Zoe";
                break;
        }
        return name;
    }

}
