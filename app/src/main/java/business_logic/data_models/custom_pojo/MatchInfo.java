package business_logic.data_models.custom_pojo;

public class MatchInfo
{
    //MatchDto
    private long gameDuration;
    private long gameCreation;

    //TeamStatsDto
    private String winnerTeam;

    //ParticipantDto
    private int championId;

    //ParticipantStatsDto
    private int kills;
    private int deaths;
    private int assists;
    private int champLevel;
    private int goldEarned;
    private int totalMinionsKilled;

    public MatchInfo(long gameDuration, long gameCreation, String winnerTeam, int championId, int kills, int deaths, int assists, int champLevel, int goldEarned, int totalMinionsKilled) {
        this.gameDuration = gameDuration;
        this.gameCreation = gameCreation;
        this.winnerTeam = winnerTeam;
        this.championId = championId;
        this.kills = kills;
        this.deaths = deaths;
        this.assists = assists;
        this.champLevel = champLevel;
        this.goldEarned = goldEarned;
        this.totalMinionsKilled = totalMinionsKilled;
    }

    public long getGameDuration() {
        return gameDuration;
    }

    public void setGameDuration(long gameDuration) {
        this.gameDuration = gameDuration;
    }

    public long getGameCreation() {
        return gameCreation;
    }

    public void setGameCreation(long gameCreation) {
        this.gameCreation = gameCreation;
    }

    public String getWinnerTeam() {
        return winnerTeam;
    }

    public void setWinnerTeam(String winnerTeam) {
        this.winnerTeam = winnerTeam;
    }

    public int getChampionId() {
        return championId;
    }

    public void setChampionId(int championId) {
        this.championId = championId;
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public int getAssists() {
        return assists;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public int getChampLevel() {
        return champLevel;
    }

    public void setChampLevel(int champLevel) {
        this.champLevel = champLevel;
    }

    public int getGoldEarned() {
        return goldEarned;
    }

    public void setGoldEarned(int goldEarned) {
        this.goldEarned = goldEarned;
    }

    public int getTotalMinionsKilled() {
        return totalMinionsKilled;
    }

    public void setTotalMinionsKilled(int totalMinionsKilled) {
        this.totalMinionsKilled = totalMinionsKilled;
    }
}
