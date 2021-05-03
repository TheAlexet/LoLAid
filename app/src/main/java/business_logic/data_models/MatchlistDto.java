package business_logic.data_models;

import java.util.List;

public class MatchlistDto
{
    private int startIndex;
    private int totalGames;
    private int endIndex;
    private List<MatchReferenceDto> matches;

    public MatchlistDto(){}

    public MatchlistDto(int startIndex, int totalGames, int endIndex, List<MatchReferenceDto> matches) {
        this.startIndex = startIndex;
        this.totalGames = totalGames;
        this.endIndex = endIndex;
        this.matches = matches;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getTotalGames() {
        return totalGames;
    }

    public void setTotalGames(int totalGames) {
        this.totalGames = totalGames;
    }

    public int getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(int endIndex) {
        this.endIndex = endIndex;
    }

    public List<MatchReferenceDto> getMatches() {
        return matches;
    }

    public void setMatches(List<MatchReferenceDto> matches) {
        this.matches = matches;
    }
}
