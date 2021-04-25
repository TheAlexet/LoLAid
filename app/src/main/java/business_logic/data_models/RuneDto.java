package business_logic.data_models;

public class RuneDto
{
    private int runeId;
    private int rank;

    public RuneDto(){}

    public RuneDto(int runeId, int rank) {
        this.runeId = runeId;
        this.rank = rank;
    }

    public int getRuneId() {
        return runeId;
    }

    public void setRuneId(int runeId) {
        this.runeId = runeId;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
