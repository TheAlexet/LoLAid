package business_logic.data_models;

public class BannedChampion
{
    private int pickTurn;
    private long championId;
    private long teamId;

    public BannedChampion(){}

    public BannedChampion(int pickTurn, long championId, long teamId) {
        this.pickTurn = pickTurn;
        this.championId = championId;
        this.teamId = teamId;
    }

    public int getPickTurn() {
        return pickTurn;
    }

    public void setPickTurn(int pickTurn) {
        this.pickTurn = pickTurn;
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
}
