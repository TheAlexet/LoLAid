package business_logic.data_models.custom_pojo;

public class RecomendationsInfo
{
    //LeagueEntryDTO
    private String tier;
    private int wins;
    private int losses;

    //Filtering ChampionMasteryDTO list to get MasteryPoints of the top 3
    private int top1championPoints;
    private int top2championPoints;
    private int top3championPoints;

    public RecomendationsInfo(){}

    public RecomendationsInfo(String tier, int wins, int losses, int top1championPoints, int top2championPoints, int top3championPoints) {
        this.tier = tier;
        this.wins = wins;
        this.losses = losses;
        this.top1championPoints = top1championPoints;
        this.top2championPoints = top2championPoints;
        this.top3championPoints = top3championPoints;
    }

    public String getTier() {
        return tier;
    }

    public void setTier(String tier) {
        this.tier = tier;
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

    public int getTop1championPoints() {
        return top1championPoints;
    }

    public void setTop1championPoints(int top1championPoints) {
        this.top1championPoints = top1championPoints;
    }

    public int getTop2championPoints() {
        return top2championPoints;
    }

    public void setTop2championPoints(int top2championPoints) {
        this.top2championPoints = top2championPoints;
    }

    public int getTop3championPoints() {
        return top3championPoints;
    }

    public void setTop3championPoints(int top3championPoints) {
        this.top3championPoints = top3championPoints;
    }
}
