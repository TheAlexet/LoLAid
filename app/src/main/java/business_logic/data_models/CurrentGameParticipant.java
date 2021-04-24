package business_logic.data_models;

import java.util.List;

public class CurrentGameParticipant
{
    private long championId;
    private Perks perks;
    private long profileIconId;
    private boolean bot;
    private long teamId;
    private String summonerName;
    private String summonerId;
    private long spell1Id;
    private long spell2Id;
    private List<GameCustomizationObject> gameCustomizationObjects;

    public CurrentGameParticipant(){}

    public CurrentGameParticipant(long championId, Perks perks, long profileIconId, boolean bot, long teamId, String summonerName, String summonerId, long spell1Id, long spell2Id, List<GameCustomizationObject> gameCustomizationObjects) {
        this.championId = championId;
        this.perks = perks;
        this.profileIconId = profileIconId;
        this.bot = bot;
        this.teamId = teamId;
        this.summonerName = summonerName;
        this.summonerId = summonerId;
        this.spell1Id = spell1Id;
        this.spell2Id = spell2Id;
        this.gameCustomizationObjects = gameCustomizationObjects;
    }

    public long getChampionId() {
        return championId;
    }

    public void setChampionId(long championId) {
        this.championId = championId;
    }

    public Perks getPerks() {
        return perks;
    }

    public void setPerks(Perks perks) {
        this.perks = perks;
    }

    public long getProfileIconId() {
        return profileIconId;
    }

    public void setProfileIconId(long profileIconId) {
        this.profileIconId = profileIconId;
    }

    public boolean isBot() {
        return bot;
    }

    public void setBot(boolean bot) {
        this.bot = bot;
    }

    public long getTeamId() {
        return teamId;
    }

    public void setTeamId(long teamId) {
        this.teamId = teamId;
    }

    public String getSummonerName() {
        return summonerName;
    }

    public void setSummonerName(String summonerName) {
        this.summonerName = summonerName;
    }

    public String getSummonerId() {
        return summonerId;
    }

    public void setSummonerId(String summonerId) {
        this.summonerId = summonerId;
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

    public List<GameCustomizationObject> getGameCustomizationObjects() {
        return gameCustomizationObjects;
    }

    public void setGameCustomizationObjects(List<GameCustomizationObject> gameCustomizationObjects) {
        this.gameCustomizationObjects = gameCustomizationObjects;
    }
}
