package business_logic.data_models.custom_pojo;

public class LiveMatchInfo
{
    //CurrentGameParticipant
    private long championId;
    private long teamId;
    private long spell1Id;
    private long spell2Id;
    private String summonerName;

    //CurrentGameInfo
    private long gameStartTime;

    //Perks
    private long perkStyle;
    private long perkSubStyle;

    public LiveMatchInfo(long championId, long teamId, long spell1Id, long spell2Id, String summonerName, long gameStartTime, long perkStyle, long perkSubStyle) {
        this.championId = championId;
        this.teamId = teamId;
        this.spell1Id = spell1Id;
        this.spell2Id = spell2Id;
        this.summonerName = summonerName;
        this.gameStartTime = gameStartTime;
        this.perkStyle = perkStyle;
        this.perkSubStyle = perkSubStyle;
    }

    public long getChampionId() {
        return championId;
    }

    public void setChampionId(long championId) {
        this.championId = championId;
    }

    public long getTeamId() {
        return teamId;
    }

    public void setTeamId(long teamId) {
        this.teamId = teamId;
    }

    public long getSpell1Id() {
        return spell1Id;
    }

    public void setSpell1Id(long spell1Id) {
        this.spell1Id = spell1Id;
    }

    public long getSpell2Id() {
        return spell2Id;
    }

    public void setSpell2Id(long spell2Id) {
        this.spell2Id = spell2Id;
    }

    public String getSummonerName() {
        return summonerName;
    }

    public void setSummonerName(String summonerName) {
        this.summonerName = summonerName;
    }

    public long getGameStartTime() {
        return gameStartTime;
    }

    public void setGameStartTime(long gameStartTime) {
        this.gameStartTime = gameStartTime;
    }

    public long getPerkStyle() {
        return perkStyle;
    }

    public void setPerkStyle(long perkStyle) {
        this.perkStyle = perkStyle;
    }

    public long getPerkSubStyle() {
        return perkSubStyle;
    }

    public void setPerkSubStyle(long perkSubStyle) {
        this.perkSubStyle = perkSubStyle;
    }
}
