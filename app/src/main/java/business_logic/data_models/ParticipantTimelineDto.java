package business_logic.data_models;

import java.util.Map;

public class ParticipantTimelineDto
{
    private int participantId;
    private Map<String, Double> csDiffPerMinDeltas;
    private Map<String, Double> damageTakenPerMinDeltas;
    private String role;
    private Map<String, Double> damageTakenDiffPerMinDeltas;
    private Map<String, Double> xpPerMinDeltas;
    private Map<String, Double> xpDiffPerMinDeltas;
    private String lane;
    private Map<String, Double> creepsPerMinDeltas;
    private Map<String, Double> goldPerMinDeltas;

    public ParticipantTimelineDto(){}

    public ParticipantTimelineDto(int participantId, Map<String, Double> csDiffPerMinDeltas, Map<String, Double> damageTakenPerMinDeltas, String role, Map<String, Double> damageTakenDiffPerMinDeltas, Map<String, Double> xpPerMinDeltas, Map<String, Double> xpDiffPerMinDeltas, String lane, Map<String, Double> creepsPerMinDeltas, Map<String, Double> goldPerMinDeltas) {
        this.participantId = participantId;
        this.csDiffPerMinDeltas = csDiffPerMinDeltas;
        this.damageTakenPerMinDeltas = damageTakenPerMinDeltas;
        this.role = role;
        this.damageTakenDiffPerMinDeltas = damageTakenDiffPerMinDeltas;
        this.xpPerMinDeltas = xpPerMinDeltas;
        this.xpDiffPerMinDeltas = xpDiffPerMinDeltas;
        this.lane = lane;
        this.creepsPerMinDeltas = creepsPerMinDeltas;
        this.goldPerMinDeltas = goldPerMinDeltas;
    }

    public int getParticipantId() {
        return participantId;
    }

    public void setParticipantId(int participantId) {
        this.participantId = participantId;
    }

    public Map<String, Double> getCsDiffPerMinDeltas() {
        return csDiffPerMinDeltas;
    }

    public void setCsDiffPerMinDeltas(Map<String, Double> csDiffPerMinDeltas) {
        this.csDiffPerMinDeltas = csDiffPerMinDeltas;
    }

    public Map<String, Double> getDamageTakenPerMinDeltas() {
        return damageTakenPerMinDeltas;
    }

    public void setDamageTakenPerMinDeltas(Map<String, Double> damageTakenPerMinDeltas) {
        this.damageTakenPerMinDeltas = damageTakenPerMinDeltas;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Map<String, Double> getDamageTakenDiffPerMinDeltas() {
        return damageTakenDiffPerMinDeltas;
    }

    public void setDamageTakenDiffPerMinDeltas(Map<String, Double> damageTakenDiffPerMinDeltas) {
        this.damageTakenDiffPerMinDeltas = damageTakenDiffPerMinDeltas;
    }

    public Map<String, Double> getXpPerMinDeltas() {
        return xpPerMinDeltas;
    }

    public void setXpPerMinDeltas(Map<String, Double> xpPerMinDeltas) {
        this.xpPerMinDeltas = xpPerMinDeltas;
    }

    public Map<String, Double> getXpDiffPerMinDeltas() {
        return xpDiffPerMinDeltas;
    }

    public void setXpDiffPerMinDeltas(Map<String, Double> xpDiffPerMinDeltas) {
        this.xpDiffPerMinDeltas = xpDiffPerMinDeltas;
    }

    public String getLane() {
        return lane;
    }

    public void setLane(String lane) {
        this.lane = lane;
    }

    public Map<String, Double> getCreepsPerMinDeltas() {
        return creepsPerMinDeltas;
    }

    public void setCreepsPerMinDeltas(Map<String, Double> creepsPerMinDeltas) {
        this.creepsPerMinDeltas = creepsPerMinDeltas;
    }

    public Map<String, Double> getGoldPerMinDeltas() {
        return goldPerMinDeltas;
    }

    public void setGoldPerMinDeltas(Map<String, Double> goldPerMinDeltas) {
        this.goldPerMinDeltas = goldPerMinDeltas;
    }
}
