package business_logic.services;

import android.app.Activity;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import business_logic.data_models.ChampionMasteryDto;
import business_logic.data_models.CurrentGameInfo;
import business_logic.data_models.LeagueEntryDTO;
import business_logic.data_models.MatchDto;
import business_logic.data_models.SummonerDTO;
import business_logic.data_models.custom_pojo.LiveMatchInfo;
import business_logic.data_models.custom_pojo.PlayerStatsInfo;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class RiotApiService
{
    private final String RIOT_API_KEY = "RGAPI-ce8c5460-dc3f-4720-bbde-b9a4f06a3528";
    private final String RANKED_SOLO = "RANKED_SOLO_5x5";
    private final String RANKED_FLEX = "RANKED_FLEX_SR";

    private IRiotApiServiceREST service;
    private Activity activity;

    public RiotApiService(Activity activity)
    {
        this.activity = activity;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://euw1.api.riotgames.com")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        service = retrofit.create(IRiotApiServiceREST.class);
    }

    /*
    public void printSummonerByNameTest()
    {
        Call<SummonerDTO> call = service.getSummonerByName("pabletefest", RIOT_API_KEY);
        Thread requestThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try
                {
                    SummonerDTO summoner = call.execute().body();

                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        });
        requestThread.start();
    }

    public void printSummonerByNameTestAsync()
    {

        Call<SummonerDTO> call = service.getSummonerByName("pabletefest", RIOT_API_KEY);

        call.enqueue(new Callback<SummonerDTO>()
        {
            @Override
            public void onResponse(Call<SummonerDTO> call, Response<SummonerDTO> response)
            {
                SummonerDTO summoner = response.body();
                Log.d("ID", summoner.getId());
                Log.d("ACCOUNT_ID", summoner.getAccountId());
                Log.d("LEVEL", summoner.getSummonerLevel() + "");
            }

            @Override
            public void onFailure(Call<SummonerDTO> call, Throwable t)
            {

            }
        });
    }

    public void getSummonerByName(String summonerName)
    {
        Call<SummonerDTO> call = service.getSummonerByName(summonerName, RIOT_API_KEY);

        call.enqueue(new Callback<SummonerDTO>() {
            @Override
            public void onResponse(Call<SummonerDTO> call, Response<SummonerDTO> response) {
                if (response.isSuccessful())
                {
                    SummonerDTO summoner = response.body();
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<SummonerDTO> call, Throwable t) {

            }
        });
    }

    public void getLeagueEntriesWithSummonerId(String encryptedSummonerId)
    {
        Call<Set<LeagueEntryDTO>> call =  service.getLeagueEntriesWithSummonerId("6NZOe5xujeQwYThyfSSxuc1j9Yo1q6BpB6qE0X6fUn_hRPY", RIOT_API_KEY);

        call.enqueue(new Callback<Set<LeagueEntryDTO>>() {
            @Override
            public void onResponse(Call<Set<LeagueEntryDTO>> call, Response<Set<LeagueEntryDTO>> response) {
                if (response.isSuccessful())
                {
                    Set<LeagueEntryDTO> leagueEntries = response.body();
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            for (LeagueEntryDTO leagueEntry : leagueEntries)
                            {
                                Log.d("LEAGUE_ENTRY", leagueEntry.getSummonerName() + " " + leagueEntry.getRank() + " " + leagueEntry.getTier() + " - MiniSeries: " + leagueEntry.getMiniSeries());
                            }
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<Set<LeagueEntryDTO>> call, Throwable t) {

            }
        });
    }

    public void getMatchByMatchId(String matchId)
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://europe.api.riotgames.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(IRiotApiServiceREST.class);

        Call<MatchDto> call = service = retrofit.create(IRiotApiServiceREST.class);

        call.enqueue(new Callback<MatchDto>() {
            @Override
            public void onResponse(Call<MatchDto> call, Response<MatchDto> response) {
                if (response.isSuccessful())
                {
                    MatchDto match = response.body();
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Log.d("MATCH", match.getGameDuration() + " | " + match.getQueueId() + " | " + match.getParticipantIdentities() + " | " + match.getTeams() + " | " + match.getParticipants());
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<MatchDto> call, Throwable t) {

            }
        });
    }
    */

    private Observable<SummonerDTO> getSummonerByNameObservable(String summonerName)
    {
        Observable<SummonerDTO> summonerObservable = service.getSummonerByName(summonerName, RIOT_API_KEY);

        /*
        summonerObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(summoner -> {
                    Log.d("ID", summoner.getId());
                    Log.d("ACCOUNT_ID", summoner.getAccountId());
                    Log.d("LEVEL", summoner.getSummonerLevel() + "");
                    Log.d("PUUID", summoner.getPuuid());
                });
                */
        return summonerObservable;
    }

    public void getCurrentMatchInfo(String summonerName)
    {
        Observable<SummonerDTO> summonerObservable = getSummonerByNameObservable(summonerName);

        //Observable<CurrentGameInfo> currentGameObservable = service.getCurrentGameInfoWithSummonerId(summonerId, RIOT_API_KEY);

        summonerObservable.subscribeOn(Schedulers.io())
                .flatMap(summoner -> {
                    String summonerId = summoner.getId();
                    return service.getCurrentGameInfoWithSummonerId(summonerId, RIOT_API_KEY);
                })
                .flatMap(currentGameInfo -> Observable.fromIterable(currentGameInfo.getParticipants())
                        .filter(currentGameParticipant -> currentGameParticipant.getSummonerName().equals(summonerName))
                        .toList()
                        .toObservable()
                        .map(currentGameParticipants -> {
                            currentGameInfo.setParticipants(currentGameParticipants);
                            return currentGameInfo;
                        }))
                .flatMap(currentGameInfo -> {
                    long championId = currentGameInfo.getParticipants().get(0).getChampionId();
                    long teamId = currentGameInfo.getParticipants().get(0).getTeamId();
                    long spell1Id = currentGameInfo.getParticipants().get(0).getSpell1Id();
                    long spell2Id = currentGameInfo.getParticipants().get(0).getSpell2Id();

                    long gameStartTime = currentGameInfo.getGameStartTime();

                    long perkStyle = currentGameInfo.getParticipants().get(0).getPerks().getPerkStyle();
                    long perkSubStyle = currentGameInfo.getParticipants().get(0).getPerks().getPerkSubStyle();

                    LiveMatchInfo liveMatch = new LiveMatchInfo(championId, teamId, spell1Id, spell2Id, summonerName, gameStartTime, perkStyle, perkSubStyle);

                    return Observable.just(liveMatch);
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(liveMatchInfo -> {
                    Log.d("NAME", liveMatchInfo.getSummonerName());
                    Log.d("CHAMPION_ID", String.valueOf(liveMatchInfo.getChampionId()));
                    Log.d("SPELL1_ID", String.valueOf(liveMatchInfo.getSpell1Id()));
                    Log.d("SPEEL2_ID", String.valueOf(liveMatchInfo.getSpell2Id()));
                    Log.d("PERKS_STYLE", String.valueOf(liveMatchInfo.getPerkStyle()));
                    Log.d("PERKS_SUBSTYLE", String.valueOf(liveMatchInfo.getPerkSubStyle()));
                }, throwable -> {
                    Log.d("ERROR_REST", "Data not found!");
                });
    }

    public void getPlayerStats(String summonerName)
    {
        Observable<SummonerDTO> summonerObservable = getSummonerByNameObservable(summonerName);

        summonerObservable.subscribeOn(Schedulers.io())
                .flatMap(summoner -> {
                    String summonerId = summoner.getId();

                    Observable<Set<LeagueEntryDTO>> leagueEntriesObservable = service.getLeagueEntriesWithSummonerId(summonerId, RIOT_API_KEY).
                            subscribeOn(Schedulers.io())
                            .map(leagueEntries -> {
                                List<LeagueEntryDTO> leagueEntriesList = new ArrayList<>(leagueEntries);
                                Set<LeagueEntryDTO> leagueEntriesSet = new HashSet<>(leagueEntriesList.stream().filter(leagueEntry -> leagueEntry.getQueueType().equals(RANKED_SOLO)).collect(Collectors.toList()));
                                return leagueEntriesSet;
                            });

                    Observable<List<ChampionMasteryDto>> championMasteriesObservable = service.getChampionsMasteryBySummonerId(summonerId, RIOT_API_KEY).
                            subscribeOn(Schedulers.io())
                            .map(championMasteries -> {
                                List<ChampionMasteryDto> listToSort = new ArrayList<>(championMasteries);
                                Collections.sort(listToSort, (championMastery1, championMastery2) -> {
                                    if (championMastery1.getChampionPoints() < championMastery2.getChampionPoints())
                                    {
                                        return 1;
                                    }
                                    else if (championMastery1.getChampionPoints() > championMastery2.getChampionPoints())
                                    {
                                        return -1;
                                    }
                                    else
                                    {
                                        return 0;
                                    }
                                });

                                return listToSort;
                            });

                    Observable<PlayerStatsInfo> playerStatsObservable = Observable.zip(leagueEntriesObservable, championMasteriesObservable.subscribeOn(Schedulers.io()),
                            (leagueEntriesResult, championMasteriesResult) -> {

                        List<LeagueEntryDTO> leagueEntries = new ArrayList<>(leagueEntriesResult);
                        String tier = leagueEntries.get(0).getTier();
                        String rank = leagueEntries.get(0).getRank();
                        int leaguePoints = leagueEntries.get(0).getLeaguePoints();
                        int wins = leagueEntries.get(0).getWins();
                        int losses = leagueEntries.get(0).getLosses();

                        long summonerLevel = summoner.getSummonerLevel();

                        long top1ChampPlayed = championMasteriesResult.get(0).getChampionId();
                        long top2ChampPlayed = championMasteriesResult.get(1).getChampionId();
                        long top3ChampPlayed = championMasteriesResult.get(2).getChampionId();

                        PlayerStatsInfo playerStats = new PlayerStatsInfo(summonerName, tier, rank, leaguePoints, wins, losses, summonerLevel, top1ChampPlayed, top2ChampPlayed, top3ChampPlayed);
                        return playerStats;
                    });

                    return playerStatsObservable;
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(playerStatsInfo -> {
                    Log.d("TOP1_CHAMP_PLAYED", String.valueOf(playerStatsInfo.getTop1ChampPlayed()));
                    Log.d("TOP2_CHAMP_PLAYED", String.valueOf(playerStatsInfo.getTop2ChampPlayed()));
                    Log.d("TOP3_CHAMP_PLAYED", String.valueOf(playerStatsInfo.getTop3ChampPlayed()));
                    Log.d("TOTAL_WINS", String.valueOf(playerStatsInfo.getWins()));
                    Log.d("TOTAL_LOSSES", String.valueOf(playerStatsInfo.getLosses()));
                    Log.d("LEAGUE_POINTS", String.valueOf(playerStatsInfo.getLeaguePoints()));
                    Log.d("SUMMONER_NAME", playerStatsInfo.getSummonerName());
                    Log.d("TIER", playerStatsInfo.getTier());
                    Log.d("RANK", playerStatsInfo.getRank());
                }, throwable -> {
                    Log.d("ERROR_REST", "Data not found!");
                });
    }
}
