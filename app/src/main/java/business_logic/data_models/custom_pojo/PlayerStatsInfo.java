package business_logic.data_models.custom_pojo;

public class PlayerStatsInfo
{
    //LeagueEntryDTO
    private String summonerName;
    private String tier;
    private String rank;
    private int leaguePoints;
    private int wins;
    private int losses;

    //SummonerDTO
    private long summonerLevel;

    //Filtering ChampionMasteryDTO list to get the top 3 champions most played
    private long top1ChampPlayed;
    private long top2ChampPlayed;
    private long top3ChampPlayed;

    public PlayerStatsInfo(String summonerName, String tier, String rank, int leaguePoints, int wins, int losses, long summonerLevel, long top1ChampPlayed, long top2ChampPlayed, long top3ChampPlayed) {
        this.summonerName = summonerName;
        this.tier = tier;
        this.rank = rank;
        this.leaguePoints = leaguePoints;
        this.wins = wins;
        this.losses = losses;
        this.summonerLevel = summonerLevel;
        this.top1ChampPlayed = top1ChampPlayed;
        this.top2ChampPlayed = top2ChampPlayed;
        this.top3ChampPlayed = top3ChampPlayed;
    }

    public String getSummonerName() {
        return summonerName;
    }

    public void setSummonerName(String summonerName) {
        this.summonerName = summonerName;
    }

    public String getTier() {
        return tier;
    }

    public void setTier(String tier) {
        this.tier = tier;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public int getLeaguePoints() {
        return leaguePoints;
    }

    public void setLeaguePoints(int leaguePoints) {
        this.leaguePoints = leaguePoints;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public long getSummonerLevel() {
        return summonerLevel;
    }

    public void setSummonerLevel(long summonerLevel) {
        this.summonerLevel = summonerLevel;
    }

    public long getTop1ChampPlayed() {
        return top1ChampPlayed;
    }

    public void setTop1ChampPlayed(long top1ChampPlayed) {
        this.top1ChampPlayed = top1ChampPlayed;
    }

    public long getTop2ChampPlayed() {
        return top2ChampPlayed;
    }

    public void setTop2ChampPlayed(long top2ChampPlayed) {
        this.top2ChampPlayed = top2ChampPlayed;
    }

    public long getTop3ChampPlayed() {
        return top3ChampPlayed;
    }

    public void setTop3ChampPlayed(long top3ChampPlayed) {
        this.top3ChampPlayed = top3ChampPlayed;
    }
}
