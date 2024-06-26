package business_logic.services;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.util.Log;
import android.view.View;

import androidx.core.util.Pair;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.example.lolaid.HistoryActivity;
import com.example.lolaid.LiveGameActivity;
import com.example.lolaid.R;
import com.example.lolaid.RecommendationsActivity;
import com.example.lolaid.StatsActivity;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import business_logic.data_models.ChampionMasteryDto;
import business_logic.data_models.CurrentGameInfo;
import business_logic.data_models.LeagueEntryDTO;
import business_logic.data_models.MatchDto;
import business_logic.data_models.MatchReferenceDto;
import business_logic.data_models.MatchlistDto;
import business_logic.data_models.ParticipantDto;
import business_logic.data_models.ParticipantIdentityDto;
import business_logic.data_models.SummonerDTO;
import business_logic.data_models.TeamStatsDto;
import business_logic.data_models.custom_pojo.LiveMatchInfo;
import business_logic.data_models.custom_pojo.MatchInfo;
import business_logic.data_models.custom_pojo.PlayerStatsInfo;
import business_logic.data_models.custom_pojo.RecomendationsInfo;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
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
    private final String RIOT_API_KEY = "RGAPI-bc5e00bc-a611-46d8-bb80-d0cc759cc314";
    private final String RANKED_SOLO = "RANKED_SOLO_5x5";
    private final String RANKED_FLEX = "RANKED_FLEX_SR";
    private final String EUW_REGION = "euw1";
    private final String NA_REGION = "na1";
    private final String KR_REGION = "kr";
    private String selectedRegion;

    private IRiotApiServiceREST service;
    //private WeakReference<FragmentActivity> activityReference;
    private WeakReference<LiveGameActivity> liveGameFragmentReference;
    private WeakReference<StatsActivity> statsFragmentReference;
    private WeakReference<HistoryActivity> historyFragmentReference;
    private WeakReference<RecommendationsActivity> recomendationsFragmentReference;

    public RiotApiService(int regionSelected)
    {
        checkRegionSelected(regionSelected);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://" + selectedRegion + ".api.riotgames.com")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        service = retrofit.create(IRiotApiServiceREST.class);
    }

    private void checkRegionSelected(int regionSelected)
    {
        switch (regionSelected)
        {
            case 0: selectedRegion = EUW_REGION;
                break;

            case 1: selectedRegion = NA_REGION;
                break;
            case 2: selectedRegion = KR_REGION;
                break;
        }
    }

    private Observable<SummonerDTO> getSummonerByNameObservable(String summonerName)
    {
        Observable<SummonerDTO> summonerObservable = service.getSummonerByName(summonerName, RIOT_API_KEY);

        return summonerObservable;
    }


    @SuppressLint("CheckResult")
    public void getCurrentMatchInfo(String summonerName, LiveGameActivity fragment)
    {
        if (fragment == null) return;
        //if (liveGameFragmentReference.get() == null) return;

        Observable<SummonerDTO> summonerObservable = getSummonerByNameObservable(summonerName);

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
                    LiveMatchInfo liveMatch = createLiveMatchInfoObject(currentGameInfo, summonerName);

                    return Observable.just(liveMatch);
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(fragment::getLiveMatchInfo
                , fragment::errorNotLive);
    }

    private LiveMatchInfo createLiveMatchInfoObject(CurrentGameInfo currentGameInfo, String summonerName)
    {
        long championId = currentGameInfo.getParticipants().get(0).getChampionId();
        long teamId = currentGameInfo.getParticipants().get(0).getTeamId();
        long spell1Id = currentGameInfo.getParticipants().get(0).getSpell1Id();
        long spell2Id = currentGameInfo.getParticipants().get(0).getSpell2Id();

        long gameStartTime = currentGameInfo.getGameStartTime();

        long perkStyle = currentGameInfo.getParticipants().get(0).getPerks().getPerkStyle();
        long perkSubStyle = currentGameInfo.getParticipants().get(0).getPerks().getPerkSubStyle();

        LiveMatchInfo liveMatch = new LiveMatchInfo(championId, teamId, spell1Id, spell2Id, summonerName, gameStartTime, perkStyle, perkSubStyle);

        return liveMatch;
    }


    @SuppressLint("CheckResult")
    public void getPlayerStatsInfo(String summonerName, StatsActivity fragment, View view)
    {
        if (fragment == null) return;
        //if (statsFragmentReference.get() == null) return;

        Observable<SummonerDTO> summonerObservable = getSummonerByNameObservable(summonerName);

        summonerObservable.subscribeOn(Schedulers.io())
                .flatMap(summoner -> {
                    String summonerId = summoner.getId();

                    Observable<Set<LeagueEntryDTO>> leagueEntriesObservable = service.getLeagueEntriesWithSummonerId(summonerId, RIOT_API_KEY).
                            subscribeOn(Schedulers.io())
                            .map(leagueEntries -> {
                                List<LeagueEntryDTO> leagueEntriesList = new ArrayList<>(leagueEntries);
                                Collection filteredCollection = leagueEntriesList.stream().filter(leagueEntry -> leagueEntry.getQueueType().equals(RANKED_SOLO)).collect(Collectors.toList());
                                Set<LeagueEntryDTO> leagueEntriesSet = new HashSet<>(filteredCollection);
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

                        //PlayerStatsInfo playerStats = createPlayerStatsInfoObject(summoner, summonerName, leagueEntriesResult, championMasteriesResult);
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
                .subscribe(fragment::getPlayerStatsRest
                        , throwable -> fragment.onErrorThrown(throwable, view));
    }

    private PlayerStatsInfo createPlayerStatsInfoObject(SummonerDTO summoner, String summonerName, Set<LeagueEntryDTO> leagueEntriesResult, List<ChampionMasteryDto> championMasteriesResult)
    {
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
    }

    @SuppressLint("CheckResult")
    public void getMatchInfo(String summonerName, HistoryActivity fragment)
    {
        if (fragment == null) return;

        Observable<SummonerDTO> summonerObservable = getSummonerByNameObservable(summonerName);

        summonerObservable.subscribeOn(Schedulers.io())
                .flatMap(summoner -> {
                    String summonerAccountId = summoner.getAccountId();
                    return service.getMatchlistWithEncryptedAccountId(summonerAccountId, RIOT_API_KEY);
                })
                .flatMap(matchListDto -> {
                    List<MatchReferenceDto> matches = matchListDto.getMatches();
                    return Observable.fromIterable(matches)
                            .flatMap(matchReferenceDto -> {
                                //Observable<Long> gameIdObservable = Observable.just(matchReferenceDto.getGameId());
                                Observable<MatchDto> matchObservable = service.getMatchByMatchId(String.valueOf(matchReferenceDto.getGameId()), RIOT_API_KEY);
                                return matchObservable;//.zipWith(Observable.interval(50, TimeUnit.MILLISECONDS), (observable, timer) -> observable);
                                //return Observable.zip(gameIdObservable, matchObservable, (gameId, matchDto) -> new Pair<Long, MatchDto>(gameId, matchDto));
                            }).toList()
                            .toObservable();//.zipWith(Observable.interval(50, TimeUnit.MILLISECONDS), (observable, timer) -> observable);
                })
                .flatMap(matchesList -> {
                    List<MatchInfo> matchesInfo = new ArrayList<>();

                    for (MatchDto matchDto : matchesList)
                    {
                        long gameDuration = matchDto.getGameDuration();
                        long gameCreation = matchDto.getGameCreation();

                        List<TeamStatsDto> teamStatsToFilter = matchDto.getTeams();
                        Collection filteredTeamsCollection = teamStatsToFilter.stream().filter(teamStatsDto -> teamStatsDto.getWin().equals("Win")).collect(Collectors.toList());
                        List<TeamStatsDto> winnerTeamStatsFiltered  = new ArrayList<>(filteredTeamsCollection);

                        List<ParticipantIdentityDto> participantIdentities = matchDto.getParticipantIdentities();
                        Collection filteredParticipantIdentity = participantIdentities.stream().filter(participantIdentity -> participantIdentity.getPlayer().getSummonerName().equals(summonerName)).collect(Collectors.toList());
                        List<ParticipantIdentityDto> participantIdentityFiltered  = new ArrayList<>(filteredParticipantIdentity);

                        List<ParticipantDto> participants = matchDto.getParticipants();
                        Collection filteredParticipantDto = participants.stream().filter(participantDto -> participantDto.getParticipantId() == participantIdentityFiltered.get(0).getParticipantId()).collect(Collectors.toList());
                        List<ParticipantDto> participantDtoFiltered  = new ArrayList<>(filteredParticipantDto);

                        boolean isWinnerTeam = participantDtoFiltered.stream().anyMatch(filteredParticipant -> filteredParticipant.getTeamId() == winnerTeamStatsFiltered.get(0).getTeamId());

                        String winnerTeam = isWinnerTeam ? "WIN" : "LOSE";

                        int championId = participantDtoFiltered.get(0).getChampionId();

                        int kills = participantDtoFiltered.get(0).getStats().getKills();
                        int deaths = participantDtoFiltered.get(0).getStats().getDeaths();
                        int assists = participantDtoFiltered.get(0).getStats().getAssists();
                        int champLevel = participantDtoFiltered.get(0).getStats().getChampLevel();
                        int goldEarned = participantDtoFiltered.get(0).getStats().getGoldEarned();
                        int totalMinionsKilled = participantDtoFiltered.get(0).getStats().getTotalMinionsKilled();

                        MatchInfo matchInfo = new MatchInfo(gameDuration, gameCreation, winnerTeam, championId, kills, deaths, assists, champLevel, goldEarned, totalMinionsKilled);
                        Log.d("MATCH_INFO", matchInfo.toString());
                        matchesInfo.add(matchInfo);
                    }

                    return Observable.just(matchesInfo);
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(matchesInfo -> {

                    Log.d("GAME_DURATION", String.valueOf(matchesInfo.get(0).getGameDuration()));
                    Log.d("GAME_CREATION", String.valueOf(matchesInfo.get(0).getGameCreation()));
                    Log.d("WINNER_TEAM", String.valueOf(matchesInfo.get(0).getWinnerTeam()));
                    Log.d("CHAMPION_ID", String.valueOf(matchesInfo.get(0).getChampionId()));
                    Log.d("KILLS", String.valueOf(matchesInfo.get(0).getKills()));
                    Log.d("DEATHS", String.valueOf(matchesInfo.get(0).getDeaths()));
                    Log.d("ASSISTS", String.valueOf(matchesInfo.get(0).getAssists()));
                    Log.d("CHAMP_LEVEL", String.valueOf(matchesInfo.get(0).getChampLevel()));
                    Log.d("GOLD_EARNED", String.valueOf(matchesInfo.get(0).getGoldEarned()));
                    Log.d("TOTAL_MINIONS_KILLED", String.valueOf(matchesInfo.get(0).getTotalMinionsKilled()));
                });
    }

    public void getMatchesHistoryInfo(String summonerName, HistoryActivity fragment)
    {
        if (fragment == null) return;

        int MAX_ENTRIES = 20; //20, LoL Standard Nº Entries

        Call<SummonerDTO> callSummonerDTO =  service.getSummonerByNameRetrofit(summonerName, RIOT_API_KEY);

        callSummonerDTO.enqueue(new Callback<SummonerDTO>() {
            @Override
            public void onResponse(Call<SummonerDTO> call, Response<SummonerDTO> response) {
                if (response.isSuccessful())
                {
                    SummonerDTO summoner = response.body();

                    Call<MatchlistDto>  callMatchlistDTO = service.getMatchlistWithEncryptedAccountIdRetrofit(summoner.getAccountId(), RIOT_API_KEY);

                    callMatchlistDTO.enqueue(new Callback<MatchlistDto>() {
                        @Override
                        public void onResponse(Call<MatchlistDto> call, Response<MatchlistDto> response) {
                            if (response.isSuccessful())
                            {
                                List<MatchDto> matches = new ArrayList<>();
                                MatchlistDto matchlistDto = response.body();
                                List<MatchReferenceDto> matchesReferences = matchlistDto.getMatches();

                                Thread multipleRequestsThread = new Thread(() ->
                                {

                                    for (int i = 0; i < MAX_ENTRIES; i++)
                                    {
                                        Call<MatchDto> callMatchDto = service.getMatchByMatchIdRetrofit(String.valueOf(matchesReferences.get(i).getGameId()), RIOT_API_KEY);

                                        try
                                        {
                                            Response<MatchDto> matchResponse = callMatchDto.execute();

                                            if (response.isSuccessful())
                                            {
                                                MatchDto match = matchResponse.body();
                                                matches.add(match);
                                            }
                                        }
                                        catch (IOException e)
                                        {
                                            e.printStackTrace();
                                        }
                                    }
                                });

                                multipleRequestsThread.start();

                                try
                                {
                                    multipleRequestsThread.join();
                                }
                                catch (InterruptedException e)
                                {
                                    e.printStackTrace();
                                }

                                List<MatchInfo> matchesInfo = new ArrayList<>();

                                for (MatchDto matchDto : matches)
                                {
                                    long gameDuration = matchDto.getGameDuration();
                                    long gameCreation = matchDto.getGameCreation();

                                    List<TeamStatsDto> teamStatsToFilter = matchDto.getTeams();
                                    Collection filteredTeamsCollection = teamStatsToFilter.stream().filter(teamStatsDto -> teamStatsDto.getWin().equals("Win")).collect(Collectors.toList());
                                    List<TeamStatsDto> winnerTeamStatsFiltered  = new ArrayList<>(filteredTeamsCollection);

                                    List<ParticipantIdentityDto> participantIdentities = matchDto.getParticipantIdentities();
                                    Collection filteredParticipantIdentity = participantIdentities.stream().filter(participantIdentity -> participantIdentity.getPlayer().getSummonerName().equals(summonerName)).collect(Collectors.toList());
                                    List<ParticipantIdentityDto> participantIdentityFiltered  = new ArrayList<>(filteredParticipantIdentity);

                                    List<ParticipantDto> participants = matchDto.getParticipants();
                                    Collection filteredParticipantDto = participants.stream().filter(participantDto -> participantDto.getParticipantId() == participantIdentityFiltered.get(0).getParticipantId()).collect(Collectors.toList());
                                    List<ParticipantDto> participantDtoFiltered  = new ArrayList<>(filteredParticipantDto);

                                    boolean isWinnerTeam = participantDtoFiltered.stream().anyMatch(filteredParticipant -> filteredParticipant.getTeamId() == winnerTeamStatsFiltered.get(0).getTeamId());

                                    String winnerTeam = isWinnerTeam ? "WIN" : "LOSE";

                                    int championId = participantDtoFiltered.get(0).getChampionId();

                                    int kills = participantDtoFiltered.get(0).getStats().getKills();
                                    int deaths = participantDtoFiltered.get(0).getStats().getDeaths();
                                    int assists = participantDtoFiltered.get(0).getStats().getAssists();
                                    int champLevel = participantDtoFiltered.get(0).getStats().getChampLevel();
                                    int goldEarned = participantDtoFiltered.get(0).getStats().getGoldEarned();
                                    int totalMinionsKilled = participantDtoFiltered.get(0).getStats().getTotalMinionsKilled();

                                    MatchInfo matchInfo = new MatchInfo(gameDuration, gameCreation, winnerTeam, championId, kills, deaths, assists, champLevel, goldEarned, totalMinionsKilled);

                                    matchesInfo.add(matchInfo);
                                }

                                fragment.getActivity().runOnUiThread(() -> fragment.getMatchHistoryList(matchesInfo));
                            }
                        }

                        @Override
                        public void onFailure(Call<MatchlistDto> call, Throwable t) {
                            Log.d("ERROR", t.getMessage());
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<SummonerDTO> call, Throwable t) {
                Log.d("ERROR", t.getMessage());
            }
        });
    }

    @SuppressLint("CheckResult")
    public void getRecomendationsInfo(String summonerName, RecommendationsActivity fragment, View view)
    {
        if (fragment == null) return;
        //if (statsFragmentReference.get() == null) return;

        Observable<SummonerDTO> summonerObservable = getSummonerByNameObservable(summonerName);

        summonerObservable.subscribeOn(Schedulers.io())
                .flatMap(summoner -> {
                    String summonerId = summoner.getId();

                    Observable<Set<LeagueEntryDTO>> leagueEntriesObservable = service.getLeagueEntriesWithSummonerId(summonerId, RIOT_API_KEY).
                            subscribeOn(Schedulers.io())
                            .map(leagueEntries -> {
                                List<LeagueEntryDTO> leagueEntriesList = new ArrayList<>(leagueEntries);
                                Collection filteredCollection = leagueEntriesList.stream().filter(leagueEntry -> leagueEntry.getQueueType().equals(RANKED_SOLO)).collect(Collectors.toList());
                                Set<LeagueEntryDTO> leagueEntriesSet = new HashSet<>(filteredCollection);
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

                    Observable<RecomendationsInfo> playerStatsObservable = Observable.zip(leagueEntriesObservable, championMasteriesObservable.subscribeOn(Schedulers.io()),
                            (leagueEntriesResult, championMasteriesResult) -> {

                                //PlayerStatsInfo playerStats = createPlayerStatsInfoObject(summoner, summonerName, leagueEntriesResult, championMasteriesResult);
                                List<LeagueEntryDTO> leagueEntries = new ArrayList<>(leagueEntriesResult);
                                String tier = leagueEntries.get(0).getTier();
                                int wins = leagueEntries.get(0).getWins();
                                int losses = leagueEntries.get(0).getLosses();

                                int top1championPoints = championMasteriesResult.get(0).getChampionPoints();
                                int top2championPoints = championMasteriesResult.get(1).getChampionPoints();
                                int top3championPoints = championMasteriesResult.get(2).getChampionPoints();

                                RecomendationsInfo recomendationsInfo = new RecomendationsInfo(tier, wins, losses, top1championPoints, top2championPoints, top3championPoints);
                                return recomendationsInfo;
                            });

                    return playerStatsObservable;
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(fragment::getRecommendationsRest
                        , throwable -> fragment.onErrorThrown(throwable, view));
    }
}
