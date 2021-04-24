package business_logic.data_models;

import java.util.List;

public class Perks
{
    private List<Long> perkIds;
    private long perkStyle;
    private long perkSubStyle;

    public Perks(){}

    public Perks(List<Long> perkIds, long perkStyle, long perkSubStyle) {
        this.perkIds = perkIds;
        this.perkStyle = perkStyle;
        this.perkSubStyle = perkSubStyle;
    }

    public List<Long> getPerkIds() {
        return perkIds;
    }

    public void setPerkIds(List<Long> perkIds) {
        this.perkIds = perkIds;
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
