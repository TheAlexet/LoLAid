package business_logic.data_models;

import java.util.List;

public class MatchDto
{
    private long gameId;
    private List<ParticipantIdentityDto> participantIdentities;
    private int queueId;
    private String gameType;
    private long gameDuration;
    private List<TeamStatsDto> teams;
    private String platformId;
    private long gameCreation;
    //private int seasonId; //Removed in Match-v5, determined based on timestamps
    private String gameVersion;
    private int mapId;
    private String gameMode;
    private List<ParticipantDto> participants;

    public MatchDto(){}

    public MatchDto(long gameId, List<ParticipantIdentityDto> participantIdentities, int queueId, String gameType, long gameDuration, List<TeamStatsDto> teams, String platformId, long gameCreation, String gameVersion, int mapId, String gameMode, List<ParticipantDto> participants) {
        this.gameId = gameId;
        this.participantIdentities = participantIdentities;
        this.queueId = queueId;
        this.gameType = gameType;
        this.gameDuration = gameDuration;
        this.teams = teams;
        this.platformId = platformId;
        this.gameCreation = gameCreation;
        //this.seasonId = seasonId;
        this.gameVersion = gameVersion;
        this.mapId = mapId;
        this.gameMode = gameMode;
        this.participants = participants;
    }

    public long getGameId() {
        return gameId;
    }

    public void setGameId(long gameId) {
        this.gameId = gameId;
    }

    public List<ParticipantIdentityDto> getParticipantIdentities() {
        return participantIdentities;
    }

    public void setParticipantIdentities(List<ParticipantIdentityDto> participantIdentities) {
        this.participantIdentities = participantIdentities;
    }

    public int getQueueId() {
        return queueId;
    }

    public void setQueueId(int queueId) {
        this.queueId = queueId;
    }

    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    public long getGameDuration() {
        return gameDuration;
    }

    public void setGameDuration(long gameDuration) {
        this.gameDuration = gameDuration;
    }

    public List<TeamStatsDto> getTeams() {
        return teams;
    }

    public void setTeams(List<TeamStatsDto> teams) {
        this.teams = teams;
    }

    public String getPlatformId() {
        return platformId;
    }

    public void setPlatformId(String platformId) {
        this.platformId = platformId;
    }

    public long getGameCreation() {
        return gameCreation;
    }

    public void setGameCreation(long gameCreation) {
        this.gameCreation = gameCreation;
    }
    /*
    public int getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(int seasonId) {
        this.seasonId = seasonId;
    }
    */
    public String getGameVersion() {
        return gameVersion;
    }

    public void setGameVersion(String gameVersion) {
        this.gameVersion = gameVersion;
    }

    public int getMapId() {
        return mapId;
    }

    public void setMapId(int mapId) {
        this.mapId = mapId;
    }

    public String getGameMode() {
        return gameMode;
    }

    public void setGameMode(String gameMode) {
        this.gameMode = gameMode;
    }

    public List<ParticipantDto> getParticipants() {
        return participants;
    }

    public void setParticipants(List<ParticipantDto> participants) {
        this.participants = participants;
    }
}
