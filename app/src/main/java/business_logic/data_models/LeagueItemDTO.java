package business_logic.data_models;

public class LeagueItemDTO
{
    private boolean freshBlood;
    private int wins;
    private String summonerName;
    private MiniSeriesDTO miniSeries;
    private boolean inactive;
    private boolean veteran;
    private boolean hotStreak;
    private String rank;
    private int leaguePoints;
    private int losses;
    private String summonerId;

    public LeagueItemDTO(){}

    public LeagueItemDTO(boolean freshBlood, int wins, String summonerName, MiniSeriesDTO miniSeries, boolean inactive, boolean veteran, boolean hotStreak, String rank, int leaguePoints, int losses, String summonerId) {
        this.freshBlood = freshBlood;
        this.wins = wins;
        this.summonerName = summonerName;
        this.miniSeries = miniSeries;
        this.inactive = inactive;
        this.veteran = veteran;
        this.hotStreak = hotStreak;
        this.rank = rank;
        this.leaguePoints = leaguePoints;
        this.losses = losses;
        this.summonerId = summonerId;
    }

    public boolean isFreshBlood() {
        return freshBlood;
    }

    public void setFreshBlood(boolean freshBlood) {
        this.freshBlood = freshBlood;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public String getSummonerName() {
        return summonerName;
    }

    public void setSummonerName(String summonerName) {
        this.summonerName = summonerName;
    }

    public MiniSeriesDTO getMiniSeries() {
        return miniSeries;
    }

    public void setMiniSeries(MiniSeriesDTO miniSeries) {
        this.miniSeries = miniSeries;
    }

    public boolean isInactive() {
        return inactive;
    }

    public void setInactive(boolean inactive) {
        this.inactive = inactive;
    }

    public boolean isVeteran() {
        return veteran;
    }

    public void setVeteran(boolean veteran) {
        this.veteran = veteran;
    }

    public boolean isHotStreak() {
        return hotStreak;
    }

    public void setHotStreak(boolean hotStreak) {
        this.hotStreak = hotStreak;
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

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public String getSummonerId() {
        return summonerId;
    }

    public void setSummonerId(String summonerId) {
        this.summonerId = summonerId;
    }
}
