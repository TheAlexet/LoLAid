package business_logic.data_models;

import java.util.List;

public class ParticipantDto
{
    private int participantId;
    private int championId;
    private List<RuneDto> runes;
    private ParticipantStatsDto stats;
    private int teamId;
    private ParticipantTimelineDto timeline;
    private int spell1Id; //old version Match-v4
    //private int summoner1Id; /Match-v5
    private int spell2Id; //old version Match-v4
    //private int summoner2Id; //Match-v5
    private String highestAchievedSeasonTier;
    private List<MasteryDto> masteries;

    public ParticipantDto(){}

    public ParticipantDto(int participantId, int championId, List<RuneDto> runes, ParticipantStatsDto stats, int teamId, ParticipantTimelineDto timeline, int spell1Id, int spell2Id, String highestAchievedSeasonTier, List<MasteryDto> masteries) {
        this.participantId = participantId;
        this.championId = championId;
        this.runes = runes;
        this.stats = stats;
        this.teamId = teamId;
        this.timeline = timeline;
        this.spell1Id = spell1Id;
        this.spell2Id = spell2Id;
        this.highestAchievedSeasonTier = highestAchievedSeasonTier;
        this.masteries = masteries;
    }

    public int getParticipantId() {
        return participantId;
    }

    public void setParticipantId(int participantId) {
        this.participantId = participantId;
    }

    public int getChampionId() {
        return championId;
    }

    public void setChampionId(int championId) {
        this.championId = championId;
    }

    public List<RuneDto> getRunes() {
        return runes;
    }

    public void setRunes(List<RuneDto> runes) {
        this.runes = runes;
    }

    public ParticipantStatsDto getStats() {
        return stats;
    }

    public void setStats(ParticipantStatsDto stats) {
        this.stats = stats;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public ParticipantTimelineDto getTimeline() {
        return timeline;
    }

    public void setTimeline(ParticipantTimelineDto timeline) {
        this.timeline = timeline;
    }

    public int getSummoner1Id() {
        return spell1Id;
    }

    public void setSummoner1Id(int spell1Id) {
        this.spell1Id = spell1Id;
    }

    public int getSummoner2Id() {
        return spell2Id;
    }

    public void setSummoner2Id(int spell2Id) {
        this.spell2Id = spell2Id;
    }

    public String getHighestAchievedSeasonTier() {
        return highestAchievedSeasonTier;
    }

    public void setHighestAchievedSeasonTier(String highestAchievedSeasonTier) {
        this.highestAchievedSeasonTier = highestAchievedSeasonTier;
    }

    public List<MasteryDto> getMasteries() {
        return masteries;
    }

    public void setMasteries(List<MasteryDto> masteries) {
        this.masteries = masteries;
    }
}
