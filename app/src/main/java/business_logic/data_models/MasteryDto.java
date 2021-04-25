package business_logic.data_models;

public class MasteryDto
{
    private int rank;
    private int masteryId;

    public MasteryDto(){}

    public MasteryDto(int rank, int masteryId) {
        this.rank = rank;
        this.masteryId = masteryId;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getMasteryId() {
        return masteryId;
    }

    public void setMasteryId(int masteryId) {
        this.masteryId = masteryId;
    }
}
